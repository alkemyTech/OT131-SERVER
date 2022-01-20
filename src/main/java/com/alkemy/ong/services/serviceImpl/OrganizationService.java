package com.alkemy.ong.services.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alkemy.ong.dto.OrganizationDTO;
import com.alkemy.ong.entities.Organization;
import com.alkemy.ong.repositories.OrganizationRepository;
import com.alkemy.ong.utility.OrganizationConverter;

import Services.IOrganizationService;

@Service
public class OrganizationService implements IOrganizationService {

	@Autowired
	private OrganizationRepository organizationRepository;
	@Autowired
	private OrganizationConverter organizationConverter;

	@Override
	public List<OrganizationDTO> listOrganizations() {
		List<OrganizationDTO> listOrganization = new ArrayList<>();
		for (Organization org : organizationRepository.findAll()) {
			OrganizationDTO Odto = organizationConverter.entityToModel(org);
			listOrganization.add(Odto);
		}
		return listOrganization;
	}

	@Override
	public OrganizationDTO publicDataOrganization(String name) {
		Optional<Organization> org = organizationRepository.findByName(name);
		return organizationConverter.entityToModel(org.get());
	}

	@Override
	public Organization saveOrganization(Organization organization) {

		return organizationRepository.save(organization);
	}

}
