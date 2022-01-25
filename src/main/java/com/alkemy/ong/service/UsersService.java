
package com.alkemy.ong.service;

import com.alkemy.ong.dto.UsersRegisterDTO;
import com.alkemy.ong.dto.UsersDto;
import com.alkemy.ong.model.Users;



public interface UsersService  {
    
    public Users findByMail(String email);
    
    public Users save(UsersRegisterDTO usersRegisterDTO);
    
    public Users update(UsersDto usersDto);
    
    public void delete(Long id) throws Exception;
    
    public Users select(Long id);
    
    
}
