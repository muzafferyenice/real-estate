package com.realestate.service;


import com.realestate.domain.Agent;
import com.realestate.dto.AgentDTO;


import java.util.List;


public interface IAgent {


    void createAgent(AgentDTO agentDTO);

    List<AgentDTO> getAllAgents();

    void updateAgent(AgentDTO agentDTO, Long propertyId,Long id);

    AgentDTO findById(Long agentId);

    void deleteAgent(Long agentId);

}
