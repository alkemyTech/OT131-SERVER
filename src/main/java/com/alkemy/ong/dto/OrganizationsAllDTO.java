package com.alkemy.ong.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationsAllDTO {
	
	
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
	private boolean isActive;

}
