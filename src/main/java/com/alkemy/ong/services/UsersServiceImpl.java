package com.alkemy.ong.services;

import com.alkemy.ong.dto.UsersRegisterDTO;
import com.alkemy.ong.dto.UsersDto;
import com.alkemy.ong.dto.UsersOkDto;
import com.alkemy.ong.model.Users;
import com.alkemy.ong.mapper.UsersConvert;
import com.alkemy.ong.repository.UsersRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements IUsersService {

    @Autowired
    UsersRepository userRepository;

    @Autowired
    UsersConvert userConvert;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Users findByMail(String email) {
        Users user = userRepository.findByEmail(email);
        if (user.isActive() == true) {
            return userRepository.findByEmail(email);
        } else {
            return null;
        }
    }

    @Override
    public Users save(UsersRegisterDTO usersRegisterDTO) {
        return null;
    }

    @Override
    public Users update(UsersDto usersDto) {
        return userRepository.save(userConvert.uaserToUserDto(usersDto));
    }

    @Override
    public void delete(UsersDto usersDto) {
        Optional<Users> user = userRepository.findById(usersDto.getId());
        if(user.isPresent()){
            user.get().setActive(false);
            userRepository.save(user.get());
        }
        userRepository.deleteById(usersDto.getId());
    }

    @Override
    public Users select(Long id) {
        return userRepository.getById(id);
    }

    public UsersOkDto getUserOkDto(String email) {
        return userConvert.userOkDtoToUser(userRepository.findByEmail(email));
    }

}
