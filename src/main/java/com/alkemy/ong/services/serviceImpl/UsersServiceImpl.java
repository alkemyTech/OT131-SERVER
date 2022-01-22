package com.alkemy.ong.services.serviceImpl;

import com.alkemy.ong.dto.RegisterResponse;
import com.alkemy.ong.dto.RegisterUsersDTO;
import com.alkemy.ong.entities.Users;
import com.alkemy.ong.repositories.UsersRepository;
import com.alkemy.ong.services.UsersService;
import com.alkemy.ong.utility.UsersConverter;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

    private PasswordEncoder passwordEncoder;
    private UsersRepository usersRepository;
    private UsersConverter usersConverter;

    @Autowired
    public UsersServiceImpl(PasswordEncoder passwordEncoder, UsersRepository usersRepository, UsersConverter usersConverter) {
        this.passwordEncoder = passwordEncoder;
        this.usersRepository = usersRepository;
        this.usersConverter = usersConverter;
    }
    
    @Override
    @Transactional
    public RegisterResponse save(RegisterUsersDTO userDTO) {
        Optional<Users> user = usersRepository.findByEmail(userDTO.getEmail());

        if (user.isPresent()) {
            throw new IllegalArgumentException("User already exists");
        }

        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        Users userEntity = usersConverter.usersDTO2Entity(userDTO);

        Users userSaved = usersRepository.save(userEntity);

        RegisterResponse response = usersConverter.usersEntity2RegisterResponse(userSaved);

        return response;
    }

}
