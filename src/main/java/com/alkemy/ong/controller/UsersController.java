package com.alkemy.ong.controller;


import com.alkemy.ong.dto.UsersDtoResponse;

import com.alkemy.ong.dto.NewUsersDTO;
import com.alkemy.ong.dto.LoginUsersDTO;
import com.alkemy.ong.dto.UsersNoAuthDto;
import com.alkemy.ong.dto.UsersOkDto;
import com.alkemy.ong.service.UsersServiceImpl;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.alkemy.ong.util.Constants.REQ_MAPP_CLASS_USER;
import static com.alkemy.ong.util.Constants.REQ_MAPP_DELETE_LOGIN_USER;
import static com.alkemy.ong.util.Constants.REQ_MAPP_POST_LOGIN_USER;
import static com.alkemy.ong.util.Constants.REQ_MAPP_POST_REGISTER_USER;

import java.util.List;

import static com.alkemy.ong.util.Constants.REQ_MAPP_GET_LIST_USER;

@RestController
@RequestMapping(value = REQ_MAPP_CLASS_USER)
public class UsersController {

    @Autowired
    UsersServiceImpl usersService;

    @PostMapping(value = REQ_MAPP_POST_LOGIN_USER)
    private ResponseEntity<?> userAuthLogin(@Valid @RequestBody LoginUsersDTO loginUser) throws Exception {
        try {
            if (usersService.login(loginUser) != null) {
                return new ResponseEntity<>(usersService.login(loginUser) , HttpStatus.OK);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UsersNoAuthDto());
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UsersNoAuthDto(e.getMessage()));
        }

    }

    @DeleteMapping(value = REQ_MAPP_DELETE_LOGIN_USER)
    private ResponseEntity<?> deleteUser(@PathVariable(name = "id") Long id) throws Exception {
        try {
            usersService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).build();

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
        }

    }

    @PostMapping(value = REQ_MAPP_POST_REGISTER_USER)
    public ResponseEntity<?> register(@Valid @RequestBody NewUsersDTO userDTO) {       
        return new ResponseEntity<>(usersService.register(userDTO), HttpStatus.CREATED);
    }


    @GetMapping(value = REQ_MAPP_GET_LIST_USER)
    public List<UsersOkDto> listUsers() {

    	return usersService.listUsers();
    }

}