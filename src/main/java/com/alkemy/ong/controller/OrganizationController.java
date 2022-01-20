package com.alkemy.ong.controller;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alkemy.ong.dto.OrganizationDTO;
import com.alkemy.ong.entities.Organization;
import com.alkemy.ong.repositories.OrganizationRepository;
import com.alkemy.ong.services.serviceImpl.OrganizationService;

@RestController
@RequestMapping("/organization")
public class OrganizationController {

	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private OrganizationRepository organizationRepository;

	@GetMapping("/public/{name}")
	public ResponseEntity<?> publicDataOrganization(@PathVariable String name) {
		Optional<Organization> entity = organizationRepository.findByName(name);


		return !entity.isPresent() ? 
				new ResponseEntity<>("NAME NOT FOUND", HttpStatus.NOT_FOUND) :
				new ResponseEntity<OrganizationDTO>(organizationService.publicDataOrganization(name), HttpStatus.OK);
	}

	@GetMapping
	public List<OrganizationDTO> listDataOrganization() {
		return organizationService.listOrganizations();

	}

	@PostMapping
	public ResponseEntity<?> createOrganization(@Valid @RequestBody Organization organization) throws Exception {

		if (organizationRepository.findByName(organization.getName()).isPresent()) {
			return new ResponseEntity<>("THE NAME OF THE ORGANIZATION ALREADY EXISTS ", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Organization>(organizationService.saveOrganization(organization), HttpStatus.CREATED);

	}

}
