package com.alkemy.ong.dto;

import com.alkemy.ong.model.Roles;
import lombok.*;

@Data
@Builder
public class UsersDtoResponse {

    private String firstName;
    private String lastName;
    private String email;
    private String token;
    private Roles role;

    public UsersDtoResponse() {
    }

    public UsersDtoResponse(String firstName, String lastName, String email, String token, Roles role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.token = token;
        this.role = role;
    }

    public UsersDtoResponse(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;

    }
}
