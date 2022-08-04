package com.realestate.dto.mapper;

import com.realestate.domain.TourRequest;
import com.realestate.dto.request.TourRequestRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface TourRequestMapper {

    TourRequest tourRequestDTOToTourRequest(TourRequestRequest tourRequestRequest);
}
