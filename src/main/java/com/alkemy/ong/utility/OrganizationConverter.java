package com.alkemy.ong.utility;

import org.springframework.stereotype.Component;

import com.alkemy.ong.dto.OrganizationDTO;
import com.alkemy.ong.entities.Organization;

@Component
public class OrganizationConverter {
	
public Organization modelToEntity (OrganizationDTO organizationDTO) {
		
		return new Organization (organizationDTO.getName(),
								 organizationDTO.getImages(),
								 organizationDTO.getAddres(),
								 organizationDTO.getPhone());
		
	}
	public OrganizationDTO entityToModel (Organization organization) {
		
		return new OrganizationDTO (organization.getName(),
								    organization.getImages(),
								    organization.getAddres(),
								    organization.getPhone());
		
	}

}
