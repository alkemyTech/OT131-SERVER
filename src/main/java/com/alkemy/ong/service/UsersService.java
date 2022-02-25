package com.alkemy.ong.service;

import com.alkemy.ong.dto.UsersDTO;
import com.alkemy.ong.model.Users;
import com.alkemy.ong.dto.NewUsersDTO;
import com.alkemy.ong.dto.UsersDtoResponse;
import com.alkemy.ong.dto.UsersOkDto;

import java.util.List;
import java.util.Optional;

public interface UsersService {

    public Optional<Users> findByMail(String email);

    public Users update(UsersDTO usersDto);

    public void delete(Long id) throws Exception;

    public Users select(Long id);

    public UsersDtoResponse save(NewUsersDTO user);

    public UsersDtoResponse getUserDetails(String authHeader);

    public UsersDtoResponse register(NewUsersDTO usersDTO);

    public String extractPayload(String token);

    UsersDtoResponse update(Long id, NewUsersDTO dto);

    public List<UsersOkDto> listUsers();
}
