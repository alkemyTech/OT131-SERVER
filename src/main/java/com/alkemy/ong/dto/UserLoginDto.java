
package com.alkemy.ong.dto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter

public class UserLoginDto implements Serializable {
    
    private String mail;
    private String password;

    public UserLoginDto() {
        super();
    }

    public UserLoginDto(String mail, String password) {
        super();
        this.mail = mail;
        this.password = password;
    }
    
    
    
}
