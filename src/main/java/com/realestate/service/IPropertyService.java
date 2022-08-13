package com.realestate.service;


import com.realestate.dto.AgentDTO;
import com.realestate.dto.PropertyDTO;
import com.realestate.exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface IPropertyService {

    void createProperty(PropertyDTO propertyDTO,Long agentId,Long propertyId);

    void updateProperty(PropertyDTO propertyDTO,Long agentId,Long propertyDetailId);

    void deleteProperty(Long propertyId);

    PropertyDTO getPropertyById(Long propertyId) throws ResourceNotFoundException;

    List<PropertyDTO> getAllProperty();


    PropertyDTO getReview(Long id);


    List<PropertyDTO> findAllProperties();

    List<PropertyDTO> searchProperties(PropertyDTO propertyDTO);

}
