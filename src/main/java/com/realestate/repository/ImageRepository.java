package com.realestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.realestate.domain.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, String>{

}
