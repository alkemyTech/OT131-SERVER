package com.alkemy.ong.dto;

import lombok.Data;

@Data
public class ContactsDTO {

    private Long id;
    private String name;
    private Long phone;
    private String email;
    private String message;
}
