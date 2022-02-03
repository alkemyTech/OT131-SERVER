package com.alkemy.ong.service;

import static com.alkemy.ong.util.Constants.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alkemy.ong.dto.OrganizationsAllDTO;
import com.alkemy.ong.dto.OrganizationsDTO;
import com.alkemy.ong.exception.ParamNotFoundException;
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
			return Optional.of(organizationRepository.findByName(name) 
		        .orElseThrow(() -> new NullPointerException(ENTITY_NOT_FOUND)));
	}
	
	@Override
	@Transactional
	public Organizations saveOrganization(Organizations organization) throws Exception {
		if (organizationRepository.findByName(organization.getName()).isPresent()) {
			throw new ParamNotFoundException(NAME_EXIST);
		}
		organization.setActive(true);
		return organizationRepository.save(organization);
	}

	@Override
	public OrganizationsAllDTO updateDataOrganization(OrganizationsAllDTO entity, long id) throws Exception {
		Optional<Organizations> org =organizationRepository.findById(id);
		if (!org.isPresent()) {
			throw new ParamNotFoundException (ENTITY_NOT_FOUND);
		}
		org.get().setName(entity.getName());
		org.get().setAddres(entity.getAddres());
		org.get().setActive(entity.isActive());
		org.get().setAboutUsText(entity.getAboutUsText());
		org.get().setPhone(entity.getPhone());
		entity.setEmail(entity.getEmail());
		org.get().setWelcomeText(entity.getWelcomeText());
		org.get().setImages(entity.getImages());
		organizationRepository.save(org.get());
		
		return mapper.map(org.get(), OrganizationsAllDTO.class);
	}

	
}
