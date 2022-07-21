package com.realestate.repository;

import com.realestate.domain.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property,Long> {

}
