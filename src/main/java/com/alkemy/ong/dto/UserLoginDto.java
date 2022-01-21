
package com.alkemy.ong.dto;


import java.io.Serializable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.stereotype.Component;



@Data
@Component
public class UserLoginDto implements Serializable {
    
    @NotBlank(message = "El email no puede estar vacio")
    @Email(message = "Se requiere un emal")
    private String useremail;
    @NotBlank(message = "El password no puede estar vacio")
    private String password;

    public UserLoginDto() {
        super();
    }

    public UserLoginDto(String useremail, String password) {
        super();
        this.useremail = useremail;
        this.password = password;
    }
    
    
    
}
