package com.alkemy.ong.service;

import com.alkemy.ong.dto.LoginUsersDTO;

import com.alkemy.ong.dto.UsersRegisterDTO;
import com.alkemy.ong.dto.UsersDTO;
import com.alkemy.ong.model.Users;
import com.alkemy.ong.dto.NewUsersDTO;
import com.alkemy.ong.dto.UsersDtoResponse;
import com.alkemy.ong.dto.UsersOkDto;

<<<<<<< HEAD
import java.util.List;
=======
>>>>>>> 5ed7d9e3ea3c095910feed8acae13a591464c5d9
import java.util.Optional;



public interface UsersService  {
    
    public Optional<Users> findByMail(String email);
    
    public Users save(UsersRegisterDTO usersRegisterDTO);
    
    public Users update(UsersDTO usersDto);
    
    public void delete(Long id) throws Exception;
    
    public Users select(Long id);

    public UsersDtoResponse save(NewUsersDTO user);
    
    public UsersDtoResponse getUserDetails(String authHeader); 
    
    public List<UsersOkDto> listUsers();
    
    public UsersDtoResponse register (NewUsersDTO usersDTO);

}
