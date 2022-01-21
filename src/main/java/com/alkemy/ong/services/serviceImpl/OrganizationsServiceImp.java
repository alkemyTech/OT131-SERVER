package com.alkemy.ong.services.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alkemy.ong.dto.OrganizationsDTO;
import com.alkemy.ong.entities.Organizations;
import com.alkemy.ong.repositories.OrganizationsRepository;
import com.alkemy.ong.utility.OrganizationsConverter;

import Services.OrganizationsService;

@Service
public class OrganizationsServiceImp implements OrganizationsService {

	@Autowired
	private OrganizationsRepository organizationRepository;
	@Autowired
	private OrganizationsConverter organizationConverter;

	@Override
	public List<OrganizationsDTO> listOrganizations() {
		List<OrganizationsDTO> listOrganization = new ArrayList<>();
		for (Organizations org : organizationRepository. findByIsActiveTrue()) {
			OrganizationsDTO Odto = organizationConverter.entityToModel(org);
			listOrganization.add(Odto);
		}
		return listOrganization;
	}

	@Override
	public OrganizationsDTO publicDataOrganization(String name) {
		Optional<Organizations> org = organizationRepository.findByName(name);
		return organizationConverter.entityToModel(org.get());
	}

	@Override
	public Organizations saveOrganization(Organizations organization) {

		return organizationRepository.save(organization);
	}

}
