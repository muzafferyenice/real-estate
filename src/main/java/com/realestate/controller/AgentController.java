package com.realestate.controller;


import com.realestate.dto.AgentDTO;
import com.realestate.dto.response.RealEstateResponse;
import com.realestate.dto.response.ResponseMessage;
import com.realestate.service.AgentServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/agent")
@AllArgsConstructor
public class AgentController {

    private AgentServiceImpl agentServiceImpl;//TODO

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RealEstateResponse> createAgent(@RequestParam(value="propertyId")Long propertyId,
                                                          @Valid @RequestBody AgentDTO agentDTO){


       agentServiceImpl.createAgent(agentDTO,propertyId);

        RealEstateResponse response=new RealEstateResponse();
        response.setMessage(ResponseMessage.AGENT_SAVED_RESPONSE_MESSAGE);
        response.setSuccess(true);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AgentDTO>> getAllAgents(){
        List<AgentDTO> agentDTOs= agentServiceImpl.getAllAgents();

        return  ResponseEntity.ok(agentDTOs);
    }
    @GetMapping("/id/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AgentDTO> getAgentById(@PathVariable Long id){
        AgentDTO agentDTO=agentServiceImpl.findById(id);

        return ResponseEntity.ok(agentDTO);
    }
}
