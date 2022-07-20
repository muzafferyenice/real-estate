package com.realestate.service;

import com.realestate.domain.Agent;
import com.realestate.domain.Property;
import com.realestate.domain.User;
import com.realestate.dto.AgentDTO;
import com.realestate.dto.mapper.AgentMapper;
import com.realestate.exception.ResourceNotFoundException;
import com.realestate.exception.message.ErrorMessage;
import com.realestate.repository.AgentRepository;
import com.realestate.repository.PropertyRepository;
import com.realestate.repository.UserRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AgentServiceImpl implements IAgent {


    private UserRepository userRepository;

    private PropertyRepository propertyRepository;

    private AgentRepository agentRepository;

    private AgentMapper agentMapper;


    @Override
    public void createAgent(AgentDTO agentDTO, Long userId, Long propertyId) {
        Property property = propertyRepository.findById(propertyId).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, propertyId)));

        //TODO bu property nin baska bir agent ta olup olmadigini kontrol eden method yaz
        //boolean propertyStatus=checkPropertyAvailability(propertyId)

       User user= userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, userId)));

        Agent agent = agentMapper.agentDTOToAgent(agentDTO);//mapping done convert dto into entity
/*
        if (!propertyStatus){
            agent.setStatus()
        }
*/
       /* agent.setFirstName(agentRequest.getFirstName()); MAPPER BUNLARDAN KURTARIYOR
        agent.setLastName(agentRequest.getLastName());
        agent.setEmail(agentRequest.getEmail());
        agent.setPhoneNumber(agentRequest.getPhoneNumber());
      */
        agentRepository.save(agent);

    }

    @Override
    public List<Agent> findAllAgents() {
        return agentRepository.findAll();
    }

    @Override
    //@Transactional(readOnly = true)
    public Optional<Agent> findById(Long agentId) {
        return agentRepository.findById(agentId);
    }
}

