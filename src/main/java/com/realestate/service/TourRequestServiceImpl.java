package com.realestate.service;

import com.realestate.domain.Property;
import com.realestate.domain.TourRequest;
import com.realestate.domain.User;
import com.realestate.domain.enums.TourRequestStatus;
import com.realestate.dto.TourRequestDTO;
import com.realestate.dto.mapper.TourRequestMapper;
import com.realestate.dto.request.TourRequestRequest;
import com.realestate.dto.request.TourRequestUpdateRequest;
import com.realestate.exception.BadRequestException;
import com.realestate.exception.ResourceNotFoundException;
import com.realestate.exception.message.ErrorMessage;
import com.realestate.repository.PropertyRepository;
import com.realestate.repository.TourRequestRepository;
import com.realestate.repository.UserRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.List;

@Service
@AllArgsConstructor
public class TourRequestServiceImpl implements ITourRequestService{

    private TourRequestRepository tourRequestRepository;

    private PropertyRepository propertyRepository;

    private UserRepository userRepository;

    private TourRequestMapper tourRequestMapper;


    @Override
    public List<TourRequest> findAllTourRequestByStatus(TourRequestStatus tourRequestStatus) {
        return tourRequestRepository.findTourRequestByStatus(tourRequestStatus.name());
    }

    @Override
    public TourRequestDTO findByIdTourRequest(Long tourRequestId) throws ResourceNotFoundException {
        TourRequest tourRequest = tourRequestRepository.findById(tourRequestId).orElseThrow(()->
                new ResourceNotFoundException(String.format(ErrorMessage.TOUR_REQUEST_NOT_FOUND,tourRequestId)));

        TourRequestDTO tourRequestDTO = tourRequestMapper.tourRequestToTourRequestDTO(tourRequest);

        return  tourRequestDTO;
    }

    @Override
    @Transactional
    public void createTourRequest(TourRequestRequest tourRequestRequest,Long userId,Long propertyId) {
        checkTourRequestTimeIsCorrect(tourRequestRequest.getTourRequestFirstTime(),tourRequestRequest.getTourRequestLastTime());

        Property property=propertyRepository.findById(propertyId).orElseThrow(()->
                new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE,propertyId)));

        boolean propertyStatus= checkPropertyAvailability(propertyId,tourRequestRequest.getTourRequestFirstTime(),tourRequestRequest.getTourRequestLastTime());

        User user = userRepository.findById(userId).orElseThrow(()->
                new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE,userId)));

        TourRequest tourRequest=tourRequestMapper.tourRequestDTOToTourRequest(tourRequestRequest);

        if (!propertyStatus){
            tourRequest.setStatus(TourRequestStatus.PENDING);
        }else {
            throw new BadRequestException(ErrorMessage.TOUR_REQUEST_NOT_AVAILABLE_MESSAGE);
        }

        tourRequest.setPropertyId(property);
        tourRequest.setUserId(user);

        tourRequestRepository.save(tourRequest);
    }

    @Override
    public void updateTourRequest(Long id, TourRequestUpdateRequest tourRequestUpdateRequest) {

        tourRequestRepository.findById(id);

        tourRequestRepository.update(id, tourRequestUpdateRequest.getAdult(), tourRequestUpdateRequest.getChild());

    }

    @Override
    public void deleteTourRequest(Long id, Long tourRequestId) {

        userRepository.findById(id);

        TourRequest tourRequest=tourRequestRepository.findById(tourRequestId).orElseThrow(()->
                new ResourceNotFoundException(String.format(ErrorMessage.TOUR_REQUEST_NOT_FOUND,tourRequestId)));
        tourRequestRepository.delete(tourRequest);

    }

    private void checkTourRequestTimeIsCorrect(LocalDateTime tourRequestFirstTime,LocalDateTime tourRequestLastTime){
        LocalDateTime now = LocalDateTime.now();

        if (tourRequestFirstTime.isBefore(now)){
            throw new BadRequestException(String.format(ErrorMessage.TOUR_REQUEST_DATE_INCORRECT_MESSAGE));
        }
        boolean isEqual= tourRequestFirstTime.isEqual(tourRequestLastTime)?true:false;
        boolean isBefore=tourRequestFirstTime.isBefore(tourRequestLastTime)?true:false;

        if(isEqual||!isBefore) {
            throw new BadRequestException(ErrorMessage.RESERVATION_TIME_INCORRECT_MESSAGE);
        }
    }

    public boolean checkPropertyAvailability(Long propertyId,LocalDateTime firstDate,LocalDateTime lastDate){

        Property property = propertyRepository.findById(propertyId).get();

         TourRequestStatus[] status= {TourRequestStatus.CANCELED,TourRequestStatus.DONE,TourRequestStatus.REJECTED};

        List<TourRequest> existReservations = tourRequestRepository.checkPropertyStatus(property,firstDate,lastDate,status);
         return !existReservations.isEmpty();
    }
}