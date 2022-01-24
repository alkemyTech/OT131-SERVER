package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.UsersRegisterDTO;
import com.alkemy.ong.dto.UsersDto;
import com.alkemy.ong.dto.UsersOkDto;
import com.alkemy.ong.model.Users;
import org.springframework.stereotype.Component;

@Component
public class UsersMapper {

    public Users uaserToUserDto(UsersDto usersDto) {
        Users user = new Users();
        user.setId(usersDto.getId());
        user.setEmail(usersDto.getEmail());
        user.setPassword(user.getPassword());
        user.setLastName(usersDto.getLastName());
        user.setFirstName(usersDto.getFirstName());
        return user;
    }

    public Users uaserToUsersRegisterDTO(UsersRegisterDTO registerUsersDTO) {
        Users user = new Users();
        user.setEmail(registerUsersDTO.getEmail());
        user.setPassword(registerUsersDTO.getPassword());
        user.setLastName(registerUsersDTO.getLastName());
        user.setFirstName(registerUsersDTO.getFirstName());
        return user;
    }
    
    
    public UsersOkDto userOkDtoToUser(Users users){
        UsersOkDto usersOkDto = new UsersOkDto();
        usersOkDto.setId(users.getId());
        usersOkDto.setFirstName(users.getFirstName());
        usersOkDto.setLastName(users.getLastName());
        usersOkDto.setEmail(users.getEmail());
        return usersOkDto;
    }

}
