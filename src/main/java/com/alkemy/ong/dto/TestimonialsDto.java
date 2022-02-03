
package com.alkemy.ong.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestimonialsDto implements Serializable{

	private Long id;
	@NotNull(message ="Name can't be null")
    private String name;
	
    private String image;
    
    private String content;
    

}
