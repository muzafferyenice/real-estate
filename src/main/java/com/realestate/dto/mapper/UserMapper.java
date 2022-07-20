package com.realestate.dto.mapper;

import com.realestate.domain.User;
import com.realestate.dto.UserDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel= "spring")
//mapper la map yapacagimizi soyluyoruz
//componentModel spring ile bu interface de bean uretip spring container a koyacagimizi soyluyoruz
//(componentModel= "spring") bizim enjekte edebilmemizi sagliyor
public interface UserMapper {
    UserDTO userToUserDTO(User user);

    List<UserDTO> map(List<User> user);//entity den dto ya donder


}
