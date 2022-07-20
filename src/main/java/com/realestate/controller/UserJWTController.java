package com.realestate.controller;

import javax.validation.Valid;

import com.realestate.dto.request.LoginRequest;
import com.realestate.dto.request.RegisterRequest;
import com.realestate.dto.response.LoginResponse;
import com.realestate.dto.response.RealEstateResponse;
import com.realestate.dto.response.ResponseMessage;
import com.realestate.security.jwt.JwtUtils;
import com.realestate.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import lombok.AllArgsConstructor;


@RestController
@RequestMapping
@AllArgsConstructor
public class UserJWTController {

private UserService userService;
	
	private AuthenticationManager authManager;
	
	private JwtUtils jwtUtils;

	@PostMapping("/register")
	public ResponseEntity<RealEstateResponse> register(@Valid @RequestBody RegisterRequest registerRequest){
		userService.register(registerRequest);
		
		RealEstateResponse response=new RealEstateResponse();
		response.setMessage(ResponseMessage.REGISTER_RESPONSE_MESSAGE);
		response.setSuccess(true);
		
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> authenticate(@Valid  @RequestBody LoginRequest loginRequest){
	
		//kutuphanenin faydalari
		Authentication authentication= authManager.authenticate(new 
				UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));
		
		
		String token= jwtUtils.generateJwtToken(authentication);
		
		LoginResponse response=new LoginResponse();
		response.setToken(token);

		
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

	
	
}
	

