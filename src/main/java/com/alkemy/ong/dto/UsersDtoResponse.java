package com.alkemy.ong.dto;

import com.alkemy.ong.model.Roles;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDtoResponse {

    private String firstName;
    private String lastName;
    private String email;
    
    private String token;
    private Roles role;

    public UsersDtoResponse(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }    
}
