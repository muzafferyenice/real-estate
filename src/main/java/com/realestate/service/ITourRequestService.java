package com.realestate.service;

import com.realestate.domain.Agent;
import com.realestate.domain.TourRequest;
import com.realestate.domain.enums.TourRequestStatus;
import com.realestate.dto.request.TourRequestRequest;
import com.realestate.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface ITourRequestService {

    List<TourRequest> findAllTourRequest(TourRequestStatus tourRequestStatus);

    TourRequest findByIdTourRequest(Long tourRequestId) throws ResourceNotFoundException;

    void createTourRequest(TourRequestRequest tourRequestRequest,Long userId,Long propertyId);

    void updateTourRequest(TourRequest tourRequest);

    void deleteTourRequest(Long tourRequestId);
}
