package com.realestate.service;

import com.realestate.domain.Agent;
import com.realestate.domain.Property;
import com.realestate.domain.PropertyDetail;
import com.realestate.domain.enums.PropertyStatus;
import com.realestate.dto.PropertyDTO;
import com.realestate.dto.mapper.PropertyMapper;
import com.realestate.exception.ResourceNotFoundException;
import com.realestate.exception.message.ErrorMessage;
import com.realestate.repository.AgentRepository;
import com.realestate.repository.PropertyDetailRepository;
import com.realestate.repository.PropertyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class PropertyServiceImpl implements IPropertyService {

    private PropertyRepository propertyRepository;

    private PropertyDetailRepository propertyDetailRepository;

    private AgentRepository agentRepository;

    private PropertyMapper propertyMapper;


    @Override
    @Transactional
    public void createProperty(PropertyDTO propertyDTO, Long agentId, Long detailId) {
        Agent agent = agentRepository.findById(agentId).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, agentId)));

        PropertyDetail propertyDetail = propertyDetailRepository.findById(detailId).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, detailId)));

        Property property = propertyMapper.propertyDTOToProperty(propertyDTO);

        property.getPropertyDetail().add(propertyDetail);

        property.setAgentId(agent);
        property.setStatus(PropertyStatus.ACTIVE);
        property.setLikes(0L);
        property.setVisitCount(0L);

        propertyRepository.save(property);


    }

    @Override
    public void updateProperty(PropertyDTO propertyDTO, Long agentId, Long detailId) {

    }

    @Override
    public void deleteProperty(Long propertyId) {

    }

    @Override
    public PropertyDTO getPropertyById(Long propertyId) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public List<PropertyDTO> getAllProperty() {
        return null;
    }
}
