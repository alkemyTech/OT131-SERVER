package com.alkemy.ong.service;

import com.alkemy.ong.dto.LoginUsersDTO;
import com.alkemy.ong.model.Users;
import com.alkemy.ong.repository.UsersRepository;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsersDetailsServiceImp implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users userEntity = usersRepository.findByEmail(email).get();
        return new User(userEntity.getEmail(), userEntity.getPassword(), Collections.emptyList()); //Cambiar collection por roles
    }

    public UserDetails signin(LoginUsersDTO loginUser) throws Exception {
        UserDetails userDetails;

        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginUser.getEmail(), loginUser.getPassword())
            );

            userDetails = (UserDetails) auth.getPrincipal();

            return userDetails;
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid username or password");
        } catch (InternalAuthenticationServiceException e) {
            throw new InternalAuthenticationServiceException("The entered user does not exist");
        }
    }

}
