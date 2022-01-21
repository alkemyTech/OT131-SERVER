
package com.alkemy.ong.services;

import com.alkemy.ong.dto.RegisterUsersDTO;
import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.entities.Users;
import com.alkemy.ong.mapper.UserConvert;
import com.alkemy.ong.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    UserConvert userConvert;
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Override
    public Users findByMail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Users save(RegisterUsersDTO registerUserDto) {
        Users user = userConvert.uaserToRegisterUserDTO(registerUserDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Users update(UserDto userDto) {
        return userRepository.save(userConvert.uaserToUserDto(userDto));
    }

    @Override
    public void delete(UserDto userDto) {
        userRepository.deleteById(userDto.getId());
    }

    @Override
    public Users select(Long id) {
        return userRepository.getById(id);
    }
    


    
}
