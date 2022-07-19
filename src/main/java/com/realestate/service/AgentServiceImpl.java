package com.realestate.service;

import com.realestate.domain.Agent;
import com.realestate.dto.mapper.AgentMapper;
import com.realestate.dto.request.AgentRequest;
import com.realestate.exception.ResourceNotFoundException;
import com.realestate.exception.message.ErrorMessage;
import com.realestate.repository.AgentRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class AgentServiceImpl implements IAgent{

    private AgentRequest agentRequest;

    private AgentRepository agentRepository;

    private AgentMapper agentMapper;



    @Override
    @Transactional
    public Agent createAgent(Agent agent) {

        if (agentRepository.existsByEmail(agent.getEmail())){
        throw new ResourceNotFoundException(String.format(ErrorMessage.AGENT_ALREADY_EXIST,agent.getId()));
        }
     return   agentRepository.save(agent);


    }

    @Override
    public List<Agent> findAllAgents() {

        return agentRepository.findAll();

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Agent> findById(Long agentId) {
       return agentRepository.findById(agentId);
    }
}

