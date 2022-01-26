package com.alkemy.ong.service;

import com.alkemy.ong.dto.UsersDtoResponse;
import com.alkemy.ong.dto.NewUsersDTO;

public interface UsersService {

    UsersDtoResponse save(NewUsersDTO user);
}
