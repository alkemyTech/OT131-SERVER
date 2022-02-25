package com.alkemy.ong.dto;

import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginUsersDTO {

    @NotBlank(message = "Email can't be empty")
    private String email;

    @NotBlank(message = "Password can't be empty")
    private String password;

    public LoginUsersDTO() {
    }

    public LoginUsersDTO(@NotBlank(message = "Email can't be empty") String email,
            @NotBlank(message = "Password can't be empty") String password) {
        this.email = email;
        this.password = password;
    }

}
