package com.realestate.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.realestate.domain.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {



}
