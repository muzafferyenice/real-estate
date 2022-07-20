package com.realestate.repository;

<<<<<<< HEAD
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.realestate.domain.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
=======
import com.realestate.domain.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property,Long> {

>>>>>>> 7c7656cd3720ff15356545569c986e2b9ce780b4

}
