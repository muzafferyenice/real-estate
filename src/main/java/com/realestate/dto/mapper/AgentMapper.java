package com.realestate.dto.mapper;


import com.realestate.domain.Agent;
import com.realestate.dto.AgentDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring")
public interface AgentMapper {


    Agent agentDTOToAgent(AgentDTO agentDTO);

    AgentDTO agentToAgentDTO(Agent agent);
    List<AgentDTO> map(List<Agent> agents);

}