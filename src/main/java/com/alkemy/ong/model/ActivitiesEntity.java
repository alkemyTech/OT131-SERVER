package com.alkemy.ong.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Table(name = "activities")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE activities SET is_active = false WHERE id=?")
@Where(clause = "is_active=true")
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
    @Column(name = "created_date", updatable = false, nullable = false)
    @CreatedDate
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate modifiedDate;

    @Column(name = "deleted_date")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate deletedDate;

    // SOFT DELETE
    @Column(name = "is_active")
    private boolean isActive = Boolean.TRUE;
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