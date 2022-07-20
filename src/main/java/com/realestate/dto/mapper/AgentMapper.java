package com.realestate.dto.mapper;


import com.realestate.domain.Agent;
import com.realestate.dto.AgentDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface AgentMapper {


    Agent agentDTOToAgent(AgentDTO agentDTO);

}