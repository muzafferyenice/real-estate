package com.realestate.service;


import com.realestate.dto.PropertyDTO;
import com.realestate.exception.ResourceNotFoundException;

import java.util.List;

public interface IPropertyService {

    void createProperty(PropertyDTO propertyDTO,Long agentId,Long detailId);

    void updateProperty(PropertyDTO propertyDTO,Long agentId,Long detailId);

    void deleteProperty(Long propertyId);

    PropertyDTO getPropertyById(Long propertyId) throws ResourceNotFoundException;

    List<PropertyDTO> getAllProperty();




}
