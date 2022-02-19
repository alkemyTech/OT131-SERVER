
package com.alkemy.ong.dto;

import java.io.Serializable;
import java.time.LocalDate;
import lombok.Data;

@Data
public class TestimonialsDto implements Serializable{

    
    private Long id;
    
    private String name;
    
    private String image;
    
    private String content;
    
    private LocalDate createDate;
    
    private LocalDate removeDate;
    
    private Boolean isActive = Boolean.TRUE;
}
