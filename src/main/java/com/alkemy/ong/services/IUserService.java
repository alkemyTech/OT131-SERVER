
package com.alkemy.ong.services;

import com.alkemy.ong.dto.RegisterUsersDTO;
import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.dto.UserLoginDto;
import com.alkemy.ong.entities.Users;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;


public interface IUserService  {
    
    public Users findByMail(String email);
    public Users save(RegisterUsersDTO registerUsersDTO);
    public Users update(UserDto userDto);
    public void delete(UserDto userDto);
    public Users select(Long id);
    
    
}
