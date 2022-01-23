package com.alkemy.ong.controller;

import com.alkemy.ong.dto.UsersDtoResponse;
import com.alkemy.ong.dto.NewUsersDTO;
import com.alkemy.ong.service.UsersService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UsersController {

    @Autowired
    private UsersService usersService;
 
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody NewUsersDTO userDTO) {
        UsersDtoResponse response = usersService.save(userDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
