package com.realestate.service;

import java.io.IOException;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.realestate.domain.Image;
import com.realestate.domain.Property;
import com.realestate.exception.ResourceNotFoundException;
import com.realestate.exception.message.ErrorMessage;
import com.realestate.repository.ImageRepository;
import com.realestate.repository.PropertyRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class ImageService {
	
	private ImageRepository imageRepository ;
	private PropertyRepository propertyRepository;
	
	public String saveImage(Long propertyId, MultipartFile file) throws IOException { //TODO: THROW a dikkat
		
		String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
		
		 Property property= propertyRepository.findById(propertyId).orElseThrow(()-> 
		 new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, propertyId)));
		
		 Image image = new Image(file.getBytes(), fileName);
		 image.setPropertyId(property);
		 
		 imageRepository.save(image);
		 
		 return image.getId();
		
		
	}

}
