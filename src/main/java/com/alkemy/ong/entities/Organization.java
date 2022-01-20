package com.alkemy.ong.entities;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name="organizations")
public class Organization {
	
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
	private String aboutUsText;
	@UpdateTimestamp
	private LocalDate UpdateOrganization;
	@CreationTimestamp
	private LocalDate timestamps;

	private boolean softDelete;
	
	
	public Organization(String name, String images, String addres, int phone) {
		super();
		this.name = name;
		this.images = images;
		this.addres = addres;
		this.phone = phone;
	}

	

}
