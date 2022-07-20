package com.realestate.repository;

import com.realestate.domain.Agent;
import com.realestate.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgentRepository extends JpaRepository<Agent,Long> {
    Optional<Agent> findByEmail(String email);//nullpointexception dan kurtulmak icin Optional yaptik

    Boolean existsByEmail(String email);



}
