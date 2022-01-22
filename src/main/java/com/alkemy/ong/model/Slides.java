package com.alkemy.ong.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    private Long id;
    
    private String imageUrl;
    
    private String text;
    
    @Column(name = "order_number")
    private Integer order;
    
    @Column(name = "organization_id")
    private Long organizationId;
    
    @Column(name="date_created")
    private LocalDate dateCreated = LocalDate.now();
    
    @Column(name="date_modified")
    private LocalDate dateModified;
    
    @Column(name="is_active")
    private boolean isActive = Boolean.TRUE;

}
