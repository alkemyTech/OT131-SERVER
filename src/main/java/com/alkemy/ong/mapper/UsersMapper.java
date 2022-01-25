package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.NewUsersDTO;
import com.alkemy.ong.dto.UsersRegisterDTO;
import com.alkemy.ong.dto.UsersDTO;
import com.alkemy.ong.dto.UsersDtoResponse;
import com.alkemy.ong.dto.UsersOkDto;
import com.alkemy.ong.model.Users;
import java.time.LocalDate;
import org.springframework.stereotype.Component;

@Component
public class UsersMapper {

    public Users uaserToUserDto(UsersDTO usersDto) {
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
                userEntity.getEmail());
    }


}
