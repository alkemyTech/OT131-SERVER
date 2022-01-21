package com.alkemy.ong.utility;

import org.springframework.stereotype.Component;

import com.alkemy.ong.dto.OrganizationsDTO;
import com.alkemy.ong.entities.Organizations;

@Component
public class OrganizationsConverter {
	
public Organizations modelToEntity (OrganizationsDTO organizationDTO) {
		
		return new Organizations (organizationDTO.getName(),
								 organizationDTO.getImages(),
								 organizationDTO.getAddres(),
								 organizationDTO.getPhone());
		
	}
	public OrganizationsDTO entityToModel (Organizations organization) {
		
		return new OrganizationsDTO (organization.getName(),
								    organization.getImages(),
								    organization.getAddres(),
								    organization.getPhone());
		
	}

}
