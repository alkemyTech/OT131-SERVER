package com.alkemy.ong.service;

import java.util.List;
import java.util.Optional;

import com.alkemy.ong.dto.OrganizationsDTO;
import com.alkemy.ong.model.Organizations;

public interface OrganizationsService {
	
	public Organizations saveOrganization(Organizations organization) throws Exception;
	public List<OrganizationsDTO> listOrganizations();
	public Optional<Organizations> publicDataOrganization(String name);

}
