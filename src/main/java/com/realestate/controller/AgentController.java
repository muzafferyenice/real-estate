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
   // public ResponseEntity<RealEstateResponse> createAgent(@RequestParam(value="propertyId")Long propertyId,
                                                       //   @Valid @RequestBody AgentDTO agentDTO){
        public ResponseEntity<RealEstateResponse> createAgent( @Valid @RequestBody AgentDTO agentDTO){

       // iAgent.createAgent(agentDTO,propertyId);
        iAgent.createAgent(agentDTO);

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
    public ResponseEntity<RealEstateResponse> deleteAgent(@PathVariable("id") Long id) {

        iAgent.deleteAgent(id);

        RealEstateResponse response = new RealEstateResponse();
        response.setMessage(ResponseMessage.AGENT_DELETED_RESPONSE_MESSAGE);
        response.setSuccess(true);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/admin/auth")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RealEstateResponse> updateAgent(@RequestParam("id") Long id,
                                                @RequestParam("propertyId") Long propertyId,
                                                @Valid @RequestBody AgentDTO agentDTO){
        iAgent.updateAgent(agentDTO,propertyId,id);

        RealEstateResponse response = new RealEstateResponse();
        response.setMessage(ResponseMessage.AGENT_UPDATED_RESPONSE_MESSAGE);
        response.setSuccess(true);

        return new ResponseEntity<>(response, HttpStatus.OK);



    }


}
