package com.alkemy.ong.entities;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE slides SET isActive = false WHERE id=?")
@Where(clause = "isActive=true")
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
    @DateTimeFormat(pattern="yyyy/MM/dd")
    private LocalDate dateCreated = LocalDate.now();
    
    @Column(name="removal_date")
    @DateTimeFormat(pattern="yyyy/MM/dd")
    private LocalDate removalDate;
    private boolean isActive = Boolean.TRUE;

}
