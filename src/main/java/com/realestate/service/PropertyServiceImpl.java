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
    public void createProperty(PropertyDTO propertyDTO, Long agentId, Long propertyDetailId) {
        Agent agent = agentRepository.findById(agentId).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, agentId)));

        PropertyDetail propertyDetail = propertyDetailRepository.findById(propertyDetailId).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, propertyDetailId)));

        Property property = propertyMapper.propertyDTOToProperty(propertyDTO);

        property.setAgentId(agent);


        propertyRepository.save(property);


    }

    // TODO: Düsün status
    @Override
    public void updateProperty(PropertyDTO propertyDTO, Long agentId, Long propertyId) {
        Agent agent = agentRepository.findById(agentId).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, agentId)));


        Property findProperty = propertyRepository.findById(propertyId).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, propertyId)));

        Property property = propertyMapper.propertyDTOToProperty(propertyDTO);

        property.setId(findProperty.getId());
        property.setAgentId(agent);
        propertyRepository.save(property);

    }

    @Override
    public void deleteProperty(Long propertyId) {
        Property property=propertyRepository.findById(propertyId).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, propertyId)));

        propertyRepository.delete(property);

    }

    @Override
    public PropertyDTO getPropertyById(Long propertyId) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public List<PropertyDTO> getAllProperty() {
        return null;
    }

    @Override
    public PropertyDTO getReview(Long id) {
        Property property=propertyRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, id)));

        property.setVisitCount(property.getVisitCount()+1);

        propertyRepository.save(property);
        return  propertyMapper.propertyToPropertyDTO(property);
       
    }
}
