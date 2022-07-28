package com.realestate.controller;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.realestate.domain.Image;
import com.realestate.dto.response.RealEstateResponse;
import com.realestate.service.ImageService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/image")
@AllArgsConstructor
public class ImageController {
	
	private ImageService imageService;
	
	@PostMapping("/upload/{id}")
	public ResponseEntity<RealEstateResponse> uploadImage (@PathVariable("id") Long propertyId ,@RequestParam("file") MultipartFile file) throws IOException{
		
		String imageId = imageService.saveImage(propertyId, file);
		
		RealEstateResponse response = new RealEstateResponse();
		
		response.setMessage("Image is saved succesfully");
		response.setSuccess(true);
		
		return ResponseEntity.ok(response);
		
				
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<byte []> getImage(@PathVariable("id") String id){
		Image image = imageService.getImageById(id);
		
		
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename="+  //TODO : bu kısıma bakılacak
				image.getName()).body(image.getImage());
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<RealEstateResponse> deleteImage(@PathVariable("id") String id){
		imageService.deleteImageById(id);
		
		RealEstateResponse response = new RealEstateResponse();
		
		response.setMessage("Image is saved succesfully");
		response.setSuccess(true);
		
		return ResponseEntity.ok(response);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
