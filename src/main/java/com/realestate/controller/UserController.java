package com.realestate.controller;

import com.realestate.dto.UserDTO;
import com.realestate.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    //@CrossOrigin("*")
    @GetMapping("/auth/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);

    }

    @GetMapping()//soru id yi nerden aldim//ipucu fileter a bak
    //token a subject olarak id verildi JwtUtilsclass 34.satir
    // AuthTokenFilter classin da 42. satirda id yi aldik
    //46. satirda resource imi icinde yani controllerin icinde setAttribute ile bu tokenin bu id si olarak set etttik
    @PreAuthorize("hasRole('ADMIN') or hasRole('CUSTOMER')")
    public ResponseEntity<UserDTO> getUserById(HttpServletRequest request) {
        Long id = (Long) request.getAttribute("id");
        UserDTO userDTO = userService.findById(id);

        return ResponseEntity.ok(userDTO);
    }


}
