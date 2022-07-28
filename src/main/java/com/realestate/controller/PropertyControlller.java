package com.realestate.controller;


import com.realestate.dto.AgentDTO;
import com.realestate.dto.PropertyDTO;
import com.realestate.dto.response.RealEstateResponse;
import com.realestate.dto.response.ResponseMessage;
import com.realestate.service.IPropertyService;
import com.realestate.service.PropertyServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/property")
@AllArgsConstructor
public class PropertyControlller {

    private IPropertyService iPropertyService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RealEstateResponse> createProperty(@RequestParam(value="propertyDetailId") Long propertyDetailId,
                                                             @RequestParam(value="agentId") Long agentId,
                                                             @Valid @RequestBody PropertyDTO propertyDTO){

        iPropertyService.createProperty(propertyDTO, agentId, propertyDetailId);

        RealEstateResponse response=new RealEstateResponse();
        response.setMessage(ResponseMessage.PROPERTY_CREATED_RESPONSE_MESSAGE);
        response.setSuccess(true);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
    public ResponseEntity<PropertyDTO> getReview(@PathVariable("id") Long id) {

        PropertyDTO propertyDTO=  iPropertyService.getReview(id);

        return ResponseEntity.ok(propertyDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RealEstateResponse> deleteProperty(@PathVariable("id") Long id) {

        iPropertyService.deleteProperty(id);

        RealEstateResponse response = new RealEstateResponse();
        response.setMessage(ResponseMessage.PROPERTY_DELETED_RESPONSE_MESSAGE);
        response.setSuccess(true);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RealEstateResponse> updateProperty(@RequestParam(value="propertyId") Long propertyId,
                                                             @RequestParam(value="agentId") Long agentId,
                                                             @Valid @RequestBody PropertyDTO propertyDTO){

        iPropertyService.updateProperty(propertyDTO,agentId,propertyId);

        RealEstateResponse response=new RealEstateResponse();
        response.setMessage(ResponseMessage.PROPERTY_UPDATE_RESPONSE_MESSAGE);
        response.setSuccess(true);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<PropertyDTO>> getAllProperties(){
        List<PropertyDTO> propertyDTOs= iPropertyService.findAllProperties();
        return  ResponseEntity.ok(propertyDTOs);
    }


}
