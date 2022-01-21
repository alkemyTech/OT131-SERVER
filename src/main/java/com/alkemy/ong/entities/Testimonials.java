
package com.alkemy.ong.entities;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;





@Entity
@SQLDelete(sql = "UPDATE personaje SET isActive = false WHERE id=?")
@Where(clause = "deleted = true")
@Setter
@Getter


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
    
    @Column(name = "create-date")
    private LocalDate createDate;
    
    @Column(name = "remove-date")
    private LocalDate removeDate;
    
    @Column(name = "is-active")
    private Boolean isActive = true;
    
    
    
    
}
