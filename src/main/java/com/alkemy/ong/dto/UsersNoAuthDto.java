
package com.alkemy.ong.dto;

import java.io.Serializable;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UsersNoAuthDto implements Serializable{
    
    private boolean ok = false;
    private String msg;

    public UsersNoAuthDto() {
    }

    
    public UsersNoAuthDto(String msg) {
        this.msg = msg;
    }
    
    
}
