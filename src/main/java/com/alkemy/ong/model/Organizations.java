package com.alkemy.ong.model;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="organizations")
public class Organizations {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	@NotNull(message ="Name can't be null")
	private String name;
	@NotNull
	private String images;
	private String addres;
	private int phone;
	@NotNull
	private String email;
	@NotNull	
	private  String welcomeText;
	@Column (length = 5000)
	private String aboutUsText;
	@Column( name ="modified_date")
	@UpdateTimestamp
	private LocalDate modifiedDate;
	@Column(name= "created_date")
	@CreationTimestamp
	private LocalDate createdDate;
	@Column(name= "removed_date")
	private LocalDate removedDate;
	private boolean isActive = Boolean.TRUE;
	

	

}
