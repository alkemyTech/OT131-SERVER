package com.alkemy.ong.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginUsersDTO{

    @NotBlank(message = "Email can't be empty")
    private String email;
    
    @NotBlank(message = "Password can't be empty")
    private String password;

}
