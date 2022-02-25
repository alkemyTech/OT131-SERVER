package com.alkemy.ong.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewContactsDTO {

    @NotBlank(message = "The name can't be null or empty")
    private String name;
    private Long phone;

    @NotBlank(message = "The email can't be null or empty")
    @Email(message = "Wrong email format")
    private String email;
    private String message;
}
