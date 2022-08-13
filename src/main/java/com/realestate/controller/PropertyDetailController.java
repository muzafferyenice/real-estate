package com.realestate.controller;


import com.realestate.dto.PropertyDTO;
import com.realestate.dto.response.RealEstateResponse;
import com.realestate.dto.response.ResponseMessage;
import com.realestate.service.IPropertyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/propertyDetail")
@AllArgsConstructor
public class PropertyDetailController {

    private IPropertyService iPropertyService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RealEstateResponse> createPropertyDetail(@RequestParam(value="propertyDetailId") Long propertyDetailId,
                                                             @RequestParam(value="agentId") Long agentId,
                                                             @Valid @RequestBody PropertyDTO propertyDTO){

        iPropertyService.createProperty(propertyDTO, agentId, propertyDetailId);

        RealEstateResponse response=new RealEstateResponse();
        response.setMessage(ResponseMessage.PROPERTY_CREATED_RESPONSE_MESSAGE);
        response.setSuccess(true);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }



}
