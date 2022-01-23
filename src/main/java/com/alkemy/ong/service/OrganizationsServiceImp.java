package com.alkemy.ong.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alkemy.ong.dto.OrganizationsDTO;
import com.alkemy.ong.model.Organizations;
import com.alkemy.ong.repository.OrganizationsRepository;

@Service
public class OrganizationsServiceImp implements OrganizationsService {

	@Autowired
	private OrganizationsRepository organizationRepository;
	
	private ModelMapper mapper = new ModelMapper();
	
	@Override
	public List<OrganizationsDTO> listOrganizations() {
		List<OrganizationsDTO> listOrganization = new ArrayList<>();
		for (Organizations org : organizationRepository. findByIsActiveTrue()) {
			listOrganization.add(mapper.map(org, OrganizationsDTO.class));
		}
		return listOrganization;
	}
	@Override
	public Optional<Organizations>  publicDataOrganization(String name) {
		return organizationRepository.findByName(name);
	}
	@Override
	public Organizations saveOrganization(Organizations organization) {
		return organizationRepository.save(organization);
	}

	
}
