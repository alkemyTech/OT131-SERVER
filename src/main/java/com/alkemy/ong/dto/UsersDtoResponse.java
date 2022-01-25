package com.alkemy.ong.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsersDtoResponse {

    private String firstName;
    private String lastName;
    private String email;
}
