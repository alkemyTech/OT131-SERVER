package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.NewUsersDTO;
import com.alkemy.ong.dto.UsersRegisterDTO;
import com.alkemy.ong.dto.UsersDTO;
import com.alkemy.ong.dto.UsersDtoResponse;
import com.alkemy.ong.dto.UsersOkDto;
import com.alkemy.ong.model.Users;
import com.alkemy.ong.repository.RolesRepository;
import com.alkemy.ong.util.RoleName;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsersMapper {

    @Autowired
    RolesRepository rolesRepository;

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

    public UsersOkDto userOkDtoToUser(Users users) {
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
        userEntity.setRole(rolesRepository.findByName(RoleName.ROLE_USER).get());

        return userEntity;
    }

    public UsersDtoResponse usersModel2UsersDtoResponse(Users userEntity) {
        return new UsersDtoResponse(
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getEmail());
    }

    public List<UsersOkDto> findallDto(List<Users> allEntityUsers) {

        try {

            List<UsersOkDto> dtos = new ArrayList<UsersOkDto>();

            for (Users user : allEntityUsers) {
                UsersOkDto auxDto = new UsersOkDto();
                auxDto.setId(user.getId());
                auxDto.setFirstName(user.getFirstName());
                auxDto.setLastName(user.getLastName());
                auxDto.setEmail(user.getEmail());
                dtos.add(auxDto);
            }

            return dtos;

        } catch (Exception e) {
            return null;
        }

    }

}
