package com.alkemy.ong.service;

import com.alkemy.ong.dto.LoginUsersDTO;
import com.alkemy.ong.dto.UsersDTO;
import com.alkemy.ong.dto.UsersOkDto;
import com.alkemy.ong.model.Users;
import com.alkemy.ong.mapper.UsersMapper;
import com.alkemy.ong.repository.UsersRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.alkemy.ong.dto.UsersDtoResponse;
import com.alkemy.ong.dto.NewUsersDTO;
import com.alkemy.ong.dto.UsersRegisterDTO;

@Service
public class UsersServiceImpl implements UsersService {

    private PasswordEncoder passwordEncoder;
    private UsersRepository usersRepository;
    private UsersMapper usersMapper;
    private AuthenticationManager authenticationManager;

    @Autowired
    public UsersServiceImpl(PasswordEncoder passwordEncoder, UsersRepository usersRepository,
            UsersMapper usersMapper, AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.usersRepository = usersRepository;
        this.usersMapper = usersMapper;
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public UsersServiceImpl(PasswordEncoder passwordEncoder, UsersRepository usersRepository, UsersMapper usersMapper) {
        this.passwordEncoder = passwordEncoder;
        this.usersRepository = usersRepository;
        this.usersMapper = usersMapper;
    }

    public UsersOkDto login(LoginUsersDTO loginUser) throws Exception {

        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginUser.getEmail(), loginUser.getPassword())
            );
            Optional<Users> users = usersRepository.findByEmail(auth.getName());
            if (users.isPresent()) {
                Users user = users.get();
                if (user.isActive()) {
                    return usersMapper.userOkDtoToUser(user);
                } else {
                    throw new Exception("Unsubscribed user");
                }
            } else {
                throw new BadCredentialsException("Username does not exist");
            }
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Username does not exist");
        } catch (InternalAuthenticationServiceException e) {
            throw new InternalAuthenticationServiceException("");
        }
    }

    @Override
    public Optional<Users> findByMail(String email) {
        Optional<Users> users = usersRepository.findByEmail(email);
        if (users.isPresent()) {
            Users user = users.get();
            if (user.isActive() == true) {
                return usersRepository.findByEmail(email);
            } else {
                return null;
            }
        }else{
            return null;
        }
    }

    @Override
    public UsersDtoResponse save(NewUsersDTO userDTO) {
        Optional<Users> user = usersRepository.findByEmail(userDTO.getEmail());

        if (user.isPresent()) {
            throw new IllegalArgumentException("User already exists");
        }

        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        Users userModel = usersMapper.newUsersDTO2Model(userDTO);
        Users userSaved = usersRepository.save(userModel);
        UsersDtoResponse response = (UsersDtoResponse) usersMapper.usersModel2UsersDtoResponse(userSaved);
        return response;
    }

    @Override
    public Users update(UsersDTO usersDto) {
        return usersRepository.save(usersMapper.uaserToUserDto(usersDto));
    }

    @Override
    public void delete(Long id) throws Exception {
        Optional<Users> user = usersRepository.findById(id);
        if (user.isPresent()) {
            user.get().setActive(false);
            usersRepository.save(user.get());
        } else {
            throw new Exception("user not found");
        }
    }

    @Override
    public Users select(Long id) {
        return usersRepository.getById(id);
    }

    public UsersOkDto getUserOkDto(String email) {
        return usersMapper.userOkDtoToUser(usersRepository.findByEmail(email).get());
    }

    @Override
    public Users save(UsersRegisterDTO usersRegisterDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
