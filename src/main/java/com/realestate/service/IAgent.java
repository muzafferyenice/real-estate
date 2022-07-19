package com.realestate.service;


import com.realestate.domain.Agent;

import java.util.List;
import java.util.Optional;

public interface IAgent {



    Agent createAgent(Agent agent);

    List<Agent> findAllAgents();
    Optional<Agent> findById(Long agentId);
}
