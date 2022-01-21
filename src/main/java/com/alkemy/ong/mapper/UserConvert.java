package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.RegisterUsersDTO;
import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.entities.Users;
import org.springframework.stereotype.Component;

@Component
public class UserConvert {

    public Users uaserToUserDto(UserDto userDto) {
        Users user = new Users();
        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setPassword(user.getPassword());
        user.setLastName(userDto.getLastName());
        user.setFirstName(userDto.getFirstName());
        return user;
    }

    public Users uaserToRegisterUserDTO(RegisterUsersDTO registerUsersDTO) {
        Users user = new Users();
        user.setEmail(registerUsersDTO.getEmail());
        user.setPassword(registerUsersDTO.getPassword());
        user.setLastName(registerUsersDTO.getLastName());
        user.setFirstName(registerUsersDTO.getFirstName());
        return user;
    }

}
