/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.ong.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author recos
 */
@RestController
@RequestMapping(value = "auth/")
public class UserController {
    
    
    @PostMapping(value = "/login")
    private ResponseEntity<Object> UserLogin(@RequestBody(required = true) ResponseEntity<Object> response ){
        
        return null ;
    }
}
