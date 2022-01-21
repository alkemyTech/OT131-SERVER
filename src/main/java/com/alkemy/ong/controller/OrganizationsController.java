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
import com.alkemy.ong.dto.OrganizationsDTO;
import com.alkemy.ong.entities.Organizations;
import com.alkemy.ong.repositories.OrganizationsRepository;
import com.alkemy.ong.services.serviceImpl.OrganizationsServiceImp;

@RestController
@RequestMapping("/organization")
public class OrganizationsController {

	@Autowired
	private OrganizationsServiceImp organizationService;
	@Autowired
	private OrganizationsRepository organizationRepository;

	@GetMapping("/public/{name}")
	public ResponseEntity<?> publicDataOrganization(@PathVariable String name) {
		Optional<Organizations> entity = organizationRepository.findByName(name);


		return !entity.isPresent() ? 
				new ResponseEntity<>("NAME NOT FOUND", HttpStatus.NOT_FOUND) :
				new ResponseEntity<OrganizationsDTO>(organizationService.publicDataOrganization(name), HttpStatus.OK);
	}

	@GetMapping
	public List<OrganizationsDTO> listDataOrganization() {
		return organizationService.listOrganizations();

	}

	@PostMapping
	public ResponseEntity<?> createOrganization(@Valid @RequestBody Organizations organization) throws Exception {

		if (organizationRepository.findByName(organization.getName()).isPresent()) {
			return new ResponseEntity<>("THE NAME OF THE ORGANIZATION ALREADY EXISTS ", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Organizations>(organizationService.saveOrganization(organization), HttpStatus.CREATED);

	}

}
