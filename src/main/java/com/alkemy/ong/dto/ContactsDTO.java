package com.alkemy.ong.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ContactsDTO {

    private Long id;
    
    @NotBlank(message = "The name can't be null or empty")
    private String name;    
    private Long phone;
    
    @NotBlank(message = "The email can't be null or empty")
    @Email(message = "Wrong email format")
    private String email;
    private String message;
}
