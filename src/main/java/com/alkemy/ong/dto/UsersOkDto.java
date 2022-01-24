package com.alkemy.ong.dto;

import java.io.Serializable;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UsersOkDto implements Serializable {

    private Long id;
    
    private String firstName;
    
    private String lastName;
    
    private String email;

}
