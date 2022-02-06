package com.alkemy.ong.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Entity
@NoArgsConstructor
@Table(name="organizations")
public class Organizations {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	@NotEmpty(message ="Name can't be empty")
	@NotNull(message ="Name can't be null")
	private String name;
	@NotEmpty(message ="Name can't be empty")
	@NotNull(message ="Name can't be null")
	private String images;
	private String addres;
	private int phone;
	@NotEmpty(message ="Name can't be empty")
	@NotNull(message ="Name can't be null")
	@Column(unique =true)
	private String email;
	@NotEmpty(message ="Name can't be empty")
	@NotNull(message ="Name can't be null")
	private  String welcomeText;
	private String aboutUsText;
	@Column( name ="modified_date")
	@UpdateTimestamp
	private LocalDate modifiedDate;
	@Column(name= "created_date")
	@CreationTimestamp
	private LocalDate createdDate;
	private Slides slide;

	private boolean isActive ;
	
	public Organizations(String name, String images, String addres, int phone) {
		super();
		this.name = name;
		this.images = images;
		this.addres = addres;
		this.phone = phone;
	}
}
