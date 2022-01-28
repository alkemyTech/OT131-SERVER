package com.alkemy.ong.service;

import static com.alkemy.ong.util.Constants.*;
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
		organizationRepository.findByIsActiveTrue().forEach(  
	            (o)-> listOrganization.add(mapper.map(o, OrganizationsDTO.class))); 
		return listOrganization;
	}

	@Override
	public Optional<Organizations>  publicDataOrganization(String name) {
//Optional.of(<Objeto>): esto creará un Optional del objeto que le pasemos, pero cuidado si le pasamos un “null” lanzara un NullPointerException.
			return Optional.of(organizationRepository.findByName(name) 
		        .orElseThrow(() -> new NullPointerException(ENTITY_NOT_FOUND)));
	}
	@Override
	public Organizations saveOrganization(Organizations organization) throws Exception {
		if (organizationRepository.findByName(organization.getName()).isPresent()) {
			throw new IllegalArgumentException(NAME_EXIST);
		}
		organization.setActive(true);
		return organizationRepository.save(organization);
	}

	
}
