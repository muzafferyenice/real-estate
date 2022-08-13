package com.realestate.service;

import com.realestate.domain.Agent;
import com.realestate.domain.TourRequest;
import com.realestate.domain.enums.TourRequestStatus;
import com.realestate.dto.TourRequestDTO;
import com.realestate.dto.request.TourRequestRequest;
import com.realestate.dto.request.TourRequestUpdateRequest;
import com.realestate.exception.ResourceNotFoundException;

import java.util.List;


public interface ITourRequestService {

    TourRequestDTO findByIdTourRequest(Long tourRequestId) throws ResourceNotFoundException;

    void createTourRequest(TourRequestRequest tourRequestRequest,Long userId,Long propertyId);

    void updateTourRequest(Long id,Long tourRequestId, TourRequestUpdateRequest tourRequestUpdateRequest);

    void deleteTourRequest(Long id, Long tourRequestId);

   List<TourRequestDTO>  getAll() throws ResourceNotFoundException;

    void updateStatus(Long tourRequestId, TourRequestDTO tourRequestDTO);
}