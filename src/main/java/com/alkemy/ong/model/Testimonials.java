
package com.alkemy.ong.entities;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;






@SQLDelete(sql = "UPDATE testimonials SET is_active = false WHERE id=?")
@Where(clause = "is_active = true")
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
