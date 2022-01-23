
package com.alkemy.ong.services;

import com.alkemy.ong.dto.UsersRegisterDTO;
import com.alkemy.ong.dto.UsersDto;
import com.alkemy.ong.model.Users;



public interface IUsersService  {
    
    public Users findByMail(String email);
    
    public Users save(UsersRegisterDTO usersRegisterDTO);
    
    public Users update(UsersDto usersDto);
    
    public void delete(UsersDto usersDto);
    
    public Users select(Long id);
    
    
}
