package com.realestate.service;


import com.realestate.domain.Agent;
import com.realestate.dto.AgentDTO;

import java.util.List;
import java.util.Optional;

public interface IAgent {

    void createAgent(AgentDTO agentDTO, Long userId, Long propertyId);

    List<Agent> findAllAgents();

    Optional<Agent> findById(Long agentId);
}
