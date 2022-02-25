package com.alkemy.ong.dto;

import java.io.Serializable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsersRegisterDTO implements Serializable {

    @NotBlank(message = "First name can't be null")
    private String firstName;

    @NotBlank(message = "Last name can't be null")
    private String lastName;

    @Email(message = "Wrong email format")
    @NotBlank(message = "Email can't be null")
    private String email;

    @Size(min = 8, message = "The password must have at least 8 characters")
    @NotBlank(message = "Password can't be null")
    private String password;

}
