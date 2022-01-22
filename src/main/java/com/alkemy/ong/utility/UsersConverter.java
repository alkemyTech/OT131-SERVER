package com.alkemy.ong.utility;

import com.alkemy.ong.dto.RegisterResponse;
import com.alkemy.ong.dto.RegisterUsersDTO;
import com.alkemy.ong.entities.Users;
import java.time.LocalDate;
import org.springframework.stereotype.Component;

@Component
public class UsersConverter {

    public Users usersDTO2Entity(RegisterUsersDTO userDTO) {
        Users userEntity = new Users();

        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setCreation(LocalDate.now());

        return userEntity;
    }

    public RegisterResponse usersEntity2RegisterResponse(Users userEntity) {
        RegisterResponse response = new RegisterResponse();

        response.setFirstName(userEntity.getFirstName());
        response.setLastName(userEntity.getLastName());
        response.setEmail(userEntity.getEmail());

        return response;
    }

}
