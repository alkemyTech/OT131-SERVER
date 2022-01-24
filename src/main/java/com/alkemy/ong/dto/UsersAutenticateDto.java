
package com.alkemy.ong.dto;


import java.io.Serializable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.stereotype.Component;



@Data
@Component
public class UsersAutenticateDto implements Serializable {
    
    @NotBlank(message = "The email cannot be empty")
    @Email(message = "An email is required")
    private String useremail;
    
    @NotBlank(message = "The password cannot be empty")
    private String password;


    
    
    
}
