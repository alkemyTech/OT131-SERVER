package com.alkemy.ong.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Contacts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contact")
    private Long id;

    @NotNull
    private String name;
    private Long phone;

    @NotNull
    @Column(unique = true)
    private String email;
    private String message;

    @Column(name = "is_active")
    private boolean isActive = Boolean.TRUE;
}
