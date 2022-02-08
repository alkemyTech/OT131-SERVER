package com.alkemy.ong.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    @ManyToOne
    private Organizations organization;

    @NotNull
    @CreationTimestamp
    @Column(name = "date_created")
    private LocalDate dateCreated;

    @UpdateTimestamp
    @Column(name = "date_modified")
    private LocalDate dateModified;

    @Column(name = "is_active")
    private boolean isActive = Boolean.TRUE;



}
