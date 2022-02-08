package com.alkemy.ong.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ContactsDTO {

    private Long id;
    private String name;    
    private Long phone;
    private String email;
    private String message;
}
