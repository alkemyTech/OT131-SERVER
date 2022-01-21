
package com.alkemy.ong.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.stereotype.Component;





@Data
@Component
public class UserDto {
    
    
    private Long id;
    @NotEmpty(message = "El campo nombre no puede estar vacio")
    private String firstName;
    @NotEmpty(message = "El campo apellido no puede estar vacio")
    private String lastName;
    @NotEmpty(message = "El campo email no puede estar vacio")
    @Email(message = "Se requiere un emal")
    private String email;
    @NotEmpty(message = "El campo password no puede estar vacio")
    private String password;
    
    

    
}
