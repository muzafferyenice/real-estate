package com.realestate.service;

import com.realestate.domain.Role;
import com.realestate.domain.User;
import com.realestate.domain.enums.RoleType;
import com.realestate.dto.UserDTO;
import com.realestate.dto.mapper.UserMapper;
import com.realestate.dto.request.RegisterRequest;
import com.realestate.exception.ConflictException;
import com.realestate.exception.ResourceNotFoundException;
import com.realestate.exception.message.ErrorMessage;
import com.realestate.repository.RoleRepository;
import com.realestate.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    private UserMapper userMapper;//mapper ne yapiyordu mapstruck doc una bakiniz

    public void register(RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new ConflictException(String.format(ErrorMessage.EMAIL_ALREADY_EXIST, registerRequest.getEmail()));
        }
        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());

        Role role = roleRepository.findByName(RoleType.ROLE_CUSTOMER).orElseThrow(() -> new ResourceNotFoundException(
                String.format(ErrorMessage.ROLE_NOT_FOUND_MESSAGE, RoleType.ROLE_CUSTOMER.name())));


        //NEDEN SET E ATADIK
        Set<Role> roles = new HashSet<>(); //SETLERIN unique ozelligi oldugu icin
        roles.add(role);

        User user = new User();
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(encodedPassword);
        user.setPhoneNumber(registerRequest.getPhoneNumber());
        user.setAddress(registerRequest.getAddress());
        user.setZipCode(registerRequest.getZipCode());
        user.setRoles(roles);

        userRepository.save(user);
    }

    public List<UserDTO> getAllUsers() {//dto return etsin istiyoruz

        List<User> users = userRepository.findAll();//entity leri alioyoruz

        return userMapper.map(users);//convert from entity to dto
    }

    public UserDTO findById(Long id) {//user i dto ya cevirmelisin
      User user=  userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, id)));

return userMapper.userToUserDTO(user);
    }


}
