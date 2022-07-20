package com.realestate.dto.mapper;

<<<<<<< HEAD
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class AgentMapper {


}
=======

import com.realestate.domain.Agent;
import com.realestate.dto.AgentDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface AgentMapper {


    Agent agentDTOToAgent(AgentDTO agentDTO);

    AgentDTO agentToAgentDTO(Agent agent);

}
>>>>>>> master
