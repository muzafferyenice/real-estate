package com.realestate.controller;


import com.realestate.dto.PropertyDTO;
import com.realestate.dto.response.RealEstateResponse;
import com.realestate.dto.response.ResponseMessage;
import com.realestate.service.PropertyServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@RestController
@RequestMapping("/property")
@AllArgsConstructor
public class PropertyControlller {

    private PropertyServiceImpl propertyServiceImpl;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RealEstateResponse> createProperty(@RequestParam(value="propertyDetailId") Long propertyDetailId,
                                                             @RequestParam(value="agentId") Long agentId,
                                                             @Valid @RequestBody PropertyDTO propertyDTO){

        propertyServiceImpl.createProperty(propertyDTO, agentId, propertyDetailId);

        RealEstateResponse response=new RealEstateResponse();
        response.setMessage(ResponseMessage.PROPERTY_CREATED_RESPONSE_MESSAGE);
        response.setSuccess(true);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


}
