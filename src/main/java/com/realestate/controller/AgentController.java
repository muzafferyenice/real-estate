package com.realestate.controller;

import com.realestate.domain.Agent;
import com.realestate.dto.AgentDTO;
import com.realestate.dto.response.RealEstateResponse;
import com.realestate.dto.response.ResponseMessage;
import com.realestate.service.AgentServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/agent")
@AllArgsConstructor
public class AgentController {

    private AgentServiceImpl agentServiceImpl;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RealEstateResponse> createAgent(HttpServletRequest request,
                                                          @RequestParam(value="propertId")Long propertyId,
                                                          @Valid @RequestBody AgentDTO agentDTO){

        Long userId = (Long) request.getAttribute("id");//buranin aciklamasi usercontroller da
       agentServiceImpl.createAgent(agentDTO,userId,propertyId);



        RealEstateResponse response=new RealEstateResponse();
        response.setMessage(ResponseMessage.AGENT_SAVED_RESPONSE_MESSAGE);
        response.setSuccess(true);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Agent>> getAllAgents(){
        List<Agent> lists= agentServiceImpl.findAllAgents();

        return  ResponseEntity.ok(lists);
    }
    @GetMapping("/id/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Optional<Agent>> getById(@PathVariable Long agentId){
        Optional<Agent> lists= agentServiceImpl.findById(agentId);

        return ResponseEntity.ok(lists);
    }
}
