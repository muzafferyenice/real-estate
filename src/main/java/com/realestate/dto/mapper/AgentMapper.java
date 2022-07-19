package com.realestate.dto.mapper;


import com.realestate.domain.Agent;
import com.realestate.dto.request.AgentRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface AgentMapper {


    Agent agentRequestToAgent(AgentRequest agentRequest);

}