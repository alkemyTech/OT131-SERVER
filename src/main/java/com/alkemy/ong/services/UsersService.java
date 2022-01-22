package com.alkemy.ong.services;

import com.alkemy.ong.dto.RegisterResponse;
import com.alkemy.ong.dto.RegisterUsersDTO;

public interface UsersService {

    RegisterResponse save(RegisterUsersDTO user);
}
