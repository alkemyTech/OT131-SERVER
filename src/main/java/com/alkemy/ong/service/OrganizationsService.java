package com.alkemy.ong.service;

import java.util.List;
import java.util.Optional;

import com.alkemy.ong.dto.OrganizationsAllDTO;
import com.alkemy.ong.dto.OrganizationsDTO;
import com.alkemy.ong.model.Organizations;

public interface OrganizationsService {
	
	public OrganizationsAllDTO saveOrganization(OrganizationsAllDTO organization) throws Exception;
	public List<OrganizationsDTO> listOrganizations();
	public Optional<Organizations> publicDataOrganization(String name);
	public OrganizationsAllDTO updateDataOrganization(OrganizationsAllDTO entity, long id) throws Exception;

}
