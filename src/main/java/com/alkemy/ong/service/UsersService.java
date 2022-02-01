package com.alkemy.ong.service;

import com.alkemy.ong.dto.LoginUsersDTO;
import com.alkemy.ong.dto.UsersRegisterDTO;
import com.alkemy.ong.dto.UsersDTO;
import com.alkemy.ong.model.Users;
import com.alkemy.ong.dto.NewUsersDTO;
import com.alkemy.ong.dto.UsersDtoResponse;
import java.util.Optional;



public interface UsersService  {
    
    public Optional<Users> findByMail(String email);
    
    public Users save(UsersRegisterDTO usersRegisterDTO);
    
    public void delete(Long id) throws Exception;
    
    public Users select(Long id);

    public UsersDtoResponse save(NewUsersDTO user);
    
    public UsersDtoResponse getUserDetails(String authHeader);

    UsersDtoResponse update(Long id, NewUsersDTO dto);
}
