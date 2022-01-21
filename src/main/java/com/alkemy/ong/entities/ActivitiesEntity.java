package com.alkemy.ong.entities;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.aspectj.bridge.IMessage;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Table(name = "activities")
@Getter
@Setter
@SQLDelete(sql = "UPDATE activities SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class ActivitiesEntity {

    // ATTRIBUTES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "The name must be valid")
    private String name;
    @NotBlank(message = "The content must be valid")
    private String content;
    @NotBlank(message = "The image must be valid")
    private String image;

    // TIMESTAMPS
    @Column(name = "creation_date")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate creationDate;

    // SOFT DELETE
    private boolean deleted = Boolean.FALSE;
}

/*  Crear modelo y repositorio base de Activities

    COMO desarrollador
    QUIERO agregar la entidad Activity
    PARA representar en la implementación la estructura de datos

    Criterios de aceptación:
    Nombre de tabla: activities.

    Campos:
    name: VARCHAR NOT NULL
    content: TEXT NOT NULL
    image: VARCHAR NOT NULL
    timestamps y softDelete
*/