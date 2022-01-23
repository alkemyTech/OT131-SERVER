package com.alkemy.ong.controller;

import com.alkemy.ong.dto.UsersNoAuthDto;
import com.alkemy.ong.services.UsersServiceImpl;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class UsersController {

    @Autowired
    UsersServiceImpl userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @GetMapping(value = "/login")
    private ResponseEntity<?> userAuthLogin(Principal principal, @RequestParam(required = false, name = "ok") String ok) {

        if (principal != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.getUserOkDto(principal.getName()));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UsersNoAuthDto());

    }



}
