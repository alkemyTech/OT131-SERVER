package com.alkemy.ong.controller;

import com.alkemy.ong.dto.RegisterUsersDTO;
import com.alkemy.ong.dto.UserLoginDto;
import com.alkemy.ong.entities.Users;
import com.alkemy.ong.services.IUserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class UsersController {

    @Autowired
    IUserService iUserService;
    
    
    @PostMapping(value = "/loginn")
    private ResponseEntity<?> userLogin(@RequestBody(required = true) UserLoginDto userLoginDto ){
        return ResponseEntity.status(HttpStatus.OK).body(userLoginDto);
    }

    @GetMapping(value = "/login")
    private String userFormLogin(){
        return "login";
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterUsersDTO userDTO) {
        Users response = iUserService.save(userDTO);
//        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }





}
