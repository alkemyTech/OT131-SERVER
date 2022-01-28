
package com.alkemy.ong.dto;

import com.alkemy.ong.model.Roles;
import java.io.Serializable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.Data;


@Data
public class UsersDTO implements Serializable{
    
    
    private Long id;
    @NotEmpty(message = "The name field cannot be empty")
    private String firstName;
    
    @NotEmpty(message = "The last name field cannot be empty")
    private String lastName;
    
    @NotEmpty(message = "The email field cannot be empty")
    @Email(message = "An email is required")
    private String email;
    
    @NotEmpty(message = "The password field cannot be empty")
    private String password;
    
    private String role;

    
}