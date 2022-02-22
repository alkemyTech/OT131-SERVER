package com.alkemy.ong.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class NewUsersDTO {

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

    public NewUsersDTO() {
    }

    public NewUsersDTO(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
