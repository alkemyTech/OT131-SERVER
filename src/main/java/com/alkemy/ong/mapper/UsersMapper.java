package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.UsersDtoResponse;
import com.alkemy.ong.dto.NewUsersDTO;
import com.alkemy.ong.model.Users;
import java.time.LocalDate;
import org.springframework.stereotype.Component;

@Component

public class UsersMapper {

    public Users newUsersDTO2Model(NewUsersDTO userDTO) {
        Users userEntity = new Users();

        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setCreationDate(LocalDate.now());
        userEntity.setActive(true);

        return userEntity;
    }

    public UsersDtoResponse usersModel2UsersDtoResponse(Users userEntity) {
        return new UsersDtoResponse(
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getEmail()
        );
    }
}
