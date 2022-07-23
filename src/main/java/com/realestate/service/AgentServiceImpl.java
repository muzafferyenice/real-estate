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

import java.util.List;
import java.util.Optional;

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
    @Override
    public void createAgent(AgentDTO agentDTO,  Long propertyId) {
       // if (agentRepository.existsByEmail(agentDTO.getEmail())) {
      //      throw new ConflictException(String.format(ErrorMessage.EMAIL_ALREADY_EXIST, agentDTO.getEmail()));
      //  }
      //Agent createAgent=  agentRepository.findById(agentDTO.getId()).orElseThrow(() ->
             //   new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, agentDTO.getId())));

        Property property = propertyRepository.findById(propertyId).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, propertyId)));

        Agent agent = agentMapper.agentDTOToAgent(agentDTO);//mapping done convert dto into entity

        property.setAgentId(agent);

        agentRepository.save(agent);
    }

    @Override
    public List<AgentDTO> getAllAgents() {
        List<Agent> agentList=agentRepository.findAll();
        return agentMapper.map(agentList);
    }

    @Override
    //@Transactional(readOnly = true)
    public AgentDTO findById(Long agentId) {
      Agent agent=  agentRepository.findById(agentId).orElseThrow(()->
                new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE,agentId)));
        return agentMapper.agentToAgentDTO(agent);
    }
}

