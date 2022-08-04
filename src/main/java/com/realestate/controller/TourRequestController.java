package com.realestate.controller;

import com.realestate.domain.TourRequest;
import com.realestate.domain.enums.TourRequestStatus;
import com.realestate.dto.request.TourRequestRequest;
import com.realestate.dto.response.RealEstateResponse;
import com.realestate.dto.response.ResponseMessage;
import com.realestate.service.ITourRequestService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/tourRequest")
public class TourRequestController {

    private ITourRequestService tourRequestService;

    @GetMapping("/getAll")
    public ResponseEntity<List<TourRequest>> getAllTourRequest(@PathVariable TourRequestStatus tourRId){
        List<TourRequest> list=tourRequestService.findAllTourRequest(tourRId);

        return ResponseEntity.ok(list);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<TourRequest> getByIdTourRequest(@PathVariable Long tourRequestId){
        TourRequest tourRequest=tourRequestService.findByIdTourRequest(tourRequestId);

        return ResponseEntity.ok(tourRequest);
    }

    @PostMapping("/create")
    public ResponseEntity<RealEstateResponse> createTourRequest(HttpServletRequest httpServletRequest,
                                                                @RequestBody TourRequestRequest tourRequestRequest,
                                                                @RequestParam Long propertyId){

        Long id = (Long) httpServletRequest.getAttribute("id");

        tourRequestService.createTourRequest(tourRequestRequest,id,propertyId);

        RealEstateResponse response=new RealEstateResponse();
        response.setMessage(ResponseMessage.TOURREQUEST_CREATED_RESPONSE_MESSAGE);
        response.setSuccess(true);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<RealEstateResponse> updateTourRequest(@RequestBody TourRequest tourRequest){
        return null;
    }

    @DeleteMapping("/delete")
    public ResponseEntity<RealEstateResponse> deleteTourRequest(@PathVariable Long tourRequestId){

        tourRequestService.deleteTourRequest(tourRequestId);

        RealEstateResponse response=new RealEstateResponse();
        response.setMessage(ResponseMessage.TOURREQUEST_DELETED_RESPONSE_MESSAGE);
        response.setSuccess(true);

        return ResponseEntity.ok(response);
    }
}
