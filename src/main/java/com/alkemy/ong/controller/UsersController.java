package com.alkemy.ong.controller;

import com.alkemy.ong.dto.UsersDtoResponse;

import com.alkemy.ong.dto.NewUsersDTO;
import com.alkemy.ong.dto.LoginUsersDTO;
import com.alkemy.ong.dto.UsersNoAuthDto;
import com.alkemy.ong.dto.UsersOkDto;
import com.alkemy.ong.model.Users;
import com.alkemy.ong.service.UsersServiceImpl;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import static com.alkemy.ong.util.Constants.REQ_MAPP_CLASS_USER;
import static com.alkemy.ong.util.Constants.REQ_MAPP_DELETE_LOGIN_USER;
import static com.alkemy.ong.util.Constants.REQ_MAPP_GET_AUTH_ME_USER;
import static com.alkemy.ong.util.Constants.REQ_MAPP_POST_LOGIN_USER;
import static com.alkemy.ong.util.Constants.REQ_MAPP_POST_REGISTER_USER;
import static com.alkemy.ong.util.Constants.REQ_MAPP_GET_LIST_USER;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController
@RequestMapping(value = REQ_MAPP_CLASS_USER)
public class UsersController {

    @Autowired
    UsersServiceImpl usersService;

    
    @Operation(summary = "Login into the api")
	 @ApiResponses(value = { 
        @ApiResponse (responseCode = "200", description = "Login ok. Return credentials" , 
   				    content = { @Content(mediaType = "application/json", 
   				      schema = @Schema(implementation = Users.class)) }),
   		  
   		@ApiResponse(responseCode = "401", description = "Unathorized. Error in log credentials", 
   		    content = @Content),})
    
    
    @PostMapping(value = REQ_MAPP_POST_LOGIN_USER)
    private ResponseEntity<?> userAuthLogin(@Valid @RequestBody LoginUsersDTO loginUser) throws Exception {
        try {
            if (usersService.login(loginUser) != null) {
                return new ResponseEntity<>(usersService.login(loginUser), HttpStatus.OK);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UsersNoAuthDto());
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UsersNoAuthDto(e.getMessage()));
        }

    }
   
    @Operation(summary = "Update user data")
    @ApiResponses(value = { 
    @ApiResponse (responseCode = "200", description = "Update ok. Return credentials" , 
                                 content = { @Content(mediaType = "application/json", 
                                   schema = @Schema(implementation = Users.class)) }),
                       
    @ApiResponse(responseCode = "404", description = "User not fouund. User id does not exist", 
                         content = @Content),})

    
    @PutMapping(REQ_MAPP_DELETE_LOGIN_USER)
    private ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody NewUsersDTO dto) {
        UsersDtoResponse result = usersService.update(id, dto);
        return result == null?
                ResponseEntity.status(HttpStatus.NOT_FOUND).build() :
                ResponseEntity.ok().body(result);

    }

    @Operation(summary = "Delete user from Database (set active as false)")
	@ApiResponses(value = { 
        @ApiResponse (responseCode = "200", description = "Login ok. Return credentials" , 
   				    content = {@Content(mediaType = "application/json", 
   				      schema = @Schema(implementation = Users.class)) }),
   		  
   		@ApiResponse(responseCode = "403", description = "Forbidden. Only admin users can delete registers from db", 
   		    content = @Content),
        @ApiResponse(responseCode = "404", description = "User not found. Wrong identifier", 
   		    content = @Content)})

    @DeleteMapping(value = REQ_MAPP_DELETE_LOGIN_USER)
    private ResponseEntity<?> deleteUser(@PathVariable(name = "id") Long id) throws Exception {
        try {
            usersService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).build();

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
        }

    }
    
    @Operation(summary = "Register into the api")
	@ApiResponses(value = { 
        @ApiResponse (responseCode = "200", description = "Register ok. Return credentials" , 
   				    content = {@Content(mediaType = "application/json", 
   				      schema = @Schema(implementation = Users.class)) }),
   		  
   		@ApiResponse(responseCode = "400", description = "Bad Request. Missing parameters on register, or username already exists", 
   		    content = @Content),
        })

    @PostMapping(value = REQ_MAPP_POST_REGISTER_USER)
    public ResponseEntity<?> register(@Valid @RequestBody NewUsersDTO userDTO) {
        return new ResponseEntity<>(usersService.register(userDTO), HttpStatus.CREATED);
    }

    @GetMapping(REQ_MAPP_GET_AUTH_ME_USER)
    public ResponseEntity<?> authMe(@RequestHeader(name = "authorization") String token) {
        return ResponseEntity.ok(usersService.extractPayload(token));
    }
    
    @GetMapping(value = REQ_MAPP_GET_LIST_USER)
    public List<UsersOkDto> listUsers() {
    	return usersService.listUsers();
    }


}
