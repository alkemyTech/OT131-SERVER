package com.alkemy.ong.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Slides {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_slide")
    private Long id;

    @NotNull
    @Column(name = "image_url")
    private String imageUrl;

    @NotNull
    private String text;

    @NotNull
    @Column(name = "order_number")
    private Integer order;

    @NotNull
    @Column(name = "organization_id")
    private Long organizationId;

    @NotNull
    @Column(name = "date_created")
    private LocalDate dateCreated;

    @Column(name = "date_modified")
    private LocalDate dateModified;

    @Column(name = "is_active")
    private boolean isActive = Boolean.TRUE;

}
