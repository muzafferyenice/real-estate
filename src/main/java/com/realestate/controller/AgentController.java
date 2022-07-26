package com.realestate.controller;


import com.realestate.dto.AgentDTO;
import com.realestate.dto.response.RealEstateResponse;
import com.realestate.dto.response.ResponseMessage;
import com.realestate.service.AgentServiceImpl;
import com.realestate.service.IAgent;
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

    private IAgent iAgent;//TODO

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RealEstateResponse> createAgent(@RequestParam(value="propertyId")Long propertyId,
                                                          @Valid @RequestBody AgentDTO agentDTO){


        iAgent.createAgent(agentDTO,propertyId);

        RealEstateResponse response=new RealEstateResponse();
        response.setMessage(ResponseMessage.AGENT_SAVED_RESPONSE_MESSAGE);
        response.setSuccess(true);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AgentDTO>> getAllAgents(){
        List<AgentDTO> agentDTOs= iAgent.getAllAgents();

        return  ResponseEntity.ok(agentDTOs);
    }
    @GetMapping("/id/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AgentDTO> getAgentById(@PathVariable Long id){
        AgentDTO agentDTO=iAgent.findById(id);

        return ResponseEntity.ok(agentDTO);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RealEstateResponse> deleteProperty(@PathVariable("id") Long id) {

        iAgent.deleteProperty(id);

        RealEstateResponse response = new RealEstateResponse();
        response.setMessage(ResponseMessage.PROPERTY_DELETED_RESPONSE_MESSAGE);
        response.setSuccess(true);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
