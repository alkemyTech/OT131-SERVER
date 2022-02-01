package com.alkemy.ong.controller;

import com.alkemy.ong.dto.*;
import com.alkemy.ong.service.UsersServiceImpl;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.alkemy.ong.util.Constants.REQ_MAPP_CLASS_USER;
import static com.alkemy.ong.util.Constants.REQ_MAPP_DELETE_LOGIN_USER;
import static com.alkemy.ong.util.Constants.REQ_MAPP_POST_LOGIN_USER;
import static com.alkemy.ong.util.Constants.REQ_MAPP_POST_REGISTER_USER;

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

    @PutMapping(REQ_MAPP_DELETE_LOGIN_USER)
    private ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody NewUsersDTO dto) {

        UsersDtoResponse result = usersService.update(id, dto);
        return result == null?
                ResponseEntity.status(HttpStatus.NOT_FOUND).build() :
                ResponseEntity.ok().body(result);

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


}