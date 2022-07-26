package com.realestate.service;


import com.realestate.domain.Agent;
import com.realestate.dto.AgentDTO;

import java.util.List;
import java.util.Optional;

public interface IAgent {

    void createAgent(AgentDTO agentDTO, Long propertyId);

    List<AgentDTO> getAllAgents();

    AgentDTO findById(Long agentId);

    void deleteProperty(Long agentId);

}
