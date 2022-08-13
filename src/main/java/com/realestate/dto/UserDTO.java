package com.realestate.dto;

import com.realestate.domain.Role;
import com.realestate.domain.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {//datalogic ile ilgili hicbirsey burda olmamali

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String address;

    private String zipCode;

    private Boolean builtIn;

    private Set<String> roles;//string nedeni customer ve administrartor oalarak donmek icin

    public void setRoles(Set<Role> roles) {//rollere gore mapping yapildi string rollere donusturuldu
        Set<String> rolesStr = new HashSet<>();

        roles.forEach(r -> {
            if (r.getName().equals(RoleType.ROLE_ADMIN))
                rolesStr.add("Administrator o is sende ");
            else
                rolesStr.add("Customer");
        });//cool lambda syntax

        this.roles=rolesStr;
    }

}
