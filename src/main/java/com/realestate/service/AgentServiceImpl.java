package com.realestate.service;

import com.realestate.domain.Agent;
import com.realestate.domain.Property;
import com.realestate.domain.User;
import com.realestate.dto.AgentDTO;
import com.realestate.dto.mapper.AgentMapper;
import com.realestate.exception.ConflictException;
import com.realestate.exception.ResourceNotFoundException;
import com.realestate.exception.message.ErrorMessage;
import com.realestate.repository.AgentRepository;
import com.realestate.repository.PropertyRepository;
import com.realestate.repository.UserRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@AllArgsConstructor
public class AgentServiceImpl implements IAgent {


    private UserRepository userRepository;

    private PropertyRepository propertyRepository;

    private AgentRepository agentRepository;

    private AgentMapper agentMapper;

    //TODO bu property nin baska bir agent ta olup olmadigini kontrol eden method yaz
    //boolean propertyStatus=checkPropertyAvailability(propertyId)
       /*

        if (!propertyStatus){
            agent.setStatus()
        }
*/
    /*
    @Override
    public void createAgent(AgentDTO agentDTO,  Long propertyId) {
        if (agentRepository.existsByEmail(agentDTO.getEmail())) {
            throw new ConflictException(String.format(ErrorMessage.EMAIL_ALREADY_EXIST, agentDTO.getEmail()));
        }

        Property property = propertyRepository.findById(propertyId).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, propertyId)));

        Agent agent = agentMapper.agentDTOToAgent(agentDTO);//mapping done convert dto into entity

        property.setAgentId(agent);

        agentRepository.save(agent);
    }
*/
    @Override
    public void createAgent(AgentDTO agentDTO) {
        if (agentRepository.existsByEmail(agentDTO.getEmail())) {
            throw new ConflictException(String.format(ErrorMessage.EMAIL_ALREADY_EXIST, agentDTO.getEmail()));
        }

        Agent agent = agentMapper.agentDTOToAgent(agentDTO);//mapping done convert dto into entity

        agentRepository.save(agent);
    }

    @Override
    public List<AgentDTO> getAllAgents() {
        List<Agent> agentList = agentRepository.findAll();
        return agentMapper.map(agentList);
    }

    @Override
    public void updateAgent(AgentDTO agentDTO, Long propertyId, Long id) {
        Agent foundAgent = agentRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, id)));

        Property foundProperty = propertyRepository.findById(propertyId).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, propertyId)));

        Agent agent = agentMapper.agentDTOToAgent(agentDTO);

       // agent.setPropertyId(foundProperty.getId());//agent a yeni bir property set ettik

        Set<Property>property=new HashSet<>();
        property.add(foundProperty);
        agent.setProperties(property);

        agent.setId(foundAgent.getId());//agent in id si arttrmasindiye ayni tuttuk
        agentRepository.save(agent);
    }

    @Override
    @Transactional(readOnly = true)
    public AgentDTO findById(Long agentId) {
        Agent agent = agentRepository.findById(agentId).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, agentId)));
        return agentMapper.agentToAgentDTO(agent);
    }

    @Override
    public void deleteAgent(Long agentId) {
        Agent agent = agentRepository.findById(agentId).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, agentId)));

        agentRepository.delete(agent);


    }

}

