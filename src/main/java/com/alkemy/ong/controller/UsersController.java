package com.alkemy.ong.controller;

import com.alkemy.ong.dto.LoginUsersDTO;
import com.alkemy.ong.dto.UsersNoAuthDto;
import com.alkemy.ong.service.UsersServiceImpl;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.alkemy.ong.util.Constants.REQ_MAPP_CLASS_USER;
import static com.alkemy.ong.util.Constants.REQ_MAPP_DELETE_LOGIN_USER;
import static com.alkemy.ong.util.Constants.REQ_MAPP_POST_LOGIN_USER;

@RestController
@RequestMapping(value = REQ_MAPP_CLASS_USER)
public class UsersController {

    @Autowired
    UsersServiceImpl usersService;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping(value = REQ_MAPP_POST_LOGIN_USER)
    private ResponseEntity<?> userAuthLogin(@Valid @RequestBody LoginUsersDTO loginUser) throws Exception {
        try {
            if (usersService.login(loginUser) != null) {
                return ResponseEntity.ok(usersService.login(loginUser));
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

}
