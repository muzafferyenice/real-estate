package com.realestate.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.realestate.dto.response.RealEstateResponse;
import com.realestate.service.ImageService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/image")
@AllArgsConstructor
public class ImageController {
	
	private ImageService imageService;
	
	@PostMapping("/{id}")
	public ResponseEntity<RealEstateResponse> uploadImage (@PathVariable("id") Long propertyId ,@RequestParam("file") MultipartFile file) throws IOException{
		
		String imageId = imageService.saveImage(propertyId, file);
		
		RealEstateResponse response = new RealEstateResponse();
		
		response.setMessage("Image is saved succesfully");
		response.setSuccess(true);
		
		return ResponseEntity.ok(response);
		
		
		
		
		
	}
	
	

}
