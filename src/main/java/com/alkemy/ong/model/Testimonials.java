package com.alkemy.ong.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "testimonials")
public class Testimonials {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String image;

    @Column(nullable = true)
    private String content;

    @Column(name = "create_date")
    private LocalDate createDate;

    @Column(name = "remove_date")
    private LocalDate removeDate;

    @Column(name = "is_active")
    private Boolean isActive = Boolean.TRUE;

}
