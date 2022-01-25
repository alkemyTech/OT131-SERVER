package com.alkemy.ong.service;

import com.alkemy.ong.dto.NewUsersDTO;
import com.alkemy.ong.dto.UsersDtoResponse;
import com.alkemy.ong.model.Users;
import com.alkemy.ong.repository.UsersRepository;
import com.alkemy.ong.mapper.UsersMapper;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

    private PasswordEncoder passwordEncoder;
    private UsersRepository usersRepository;
    private UsersMapper usersMapper;

    @Autowired
    public UsersServiceImpl(PasswordEncoder passwordEncoder, UsersRepository usersRepository, UsersMapper usersMapper) {
        this.passwordEncoder = passwordEncoder;
        this.usersRepository = usersRepository;
        this.usersMapper = usersMapper;
    }

    @Override
    @Transactional    
    public UsersDtoResponse save(NewUsersDTO userDTO) {
        Optional<Users> user = usersRepository.findByEmail(userDTO.getEmail());

        if (user.isPresent()) {
            throw new IllegalArgumentException("User already exists");
        }

        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        Users userModel = usersMapper.newUsersDTO2Model(userDTO);

        Users userSaved = usersRepository.save(userModel);

        UsersDtoResponse response = usersMapper.usersModel2UsersDtoResponse(userSaved);

        return response;
    }

}
