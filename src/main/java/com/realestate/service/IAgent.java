package com.realestate.service;


import com.realestate.domain.Agent;
import com.realestate.dto.AgentDTO;
import com.realestate.dto.PropertyDTO;

import java.util.List;
import java.util.Optional;

public interface IAgent {

    //void createAgent(AgentDTO agentDTO, Long propertyId);
    void createAgent(AgentDTO agentDTO );

    List<AgentDTO> getAllAgents();

    void updateAgent(AgentDTO agentDTO, Long propertyId,Long id);

    AgentDTO findById(Long agentId);

    void deleteAgent(Long agentId);

}
