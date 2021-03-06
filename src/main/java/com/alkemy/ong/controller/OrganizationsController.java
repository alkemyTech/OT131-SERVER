package com.alkemy.ong.controller;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
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
import com.alkemy.ong.model.Organizations;
import com.alkemy.ong.repository.OrganizationsRepository;
import com.alkemy.ong.service.OrganizationsServiceImp;
import static com.alkemy.ong.util.Constants.*;

@RestController
@RequestMapping(REQ_MAPP_ORG)
public class OrganizationsController {

	@Autowired
	private OrganizationsServiceImp organizationService;
	@Autowired
	private OrganizationsRepository organizationRepository;
	private ModelMapper mapper = new ModelMapper();
	
	@GetMapping(POINT_GET_MAPP)
	public ResponseEntity<?> publicDataOrganization(@PathVariable String name) {
		Optional<Organizations> org = organizationService.publicDataOrganization(name);
		return !org.isPresent() ? 
				new ResponseEntity<>(ENTITY_NOT_FOUND , HttpStatus.NOT_FOUND) :
				new ResponseEntity<>(mapper.map(org.get(), OrganizationsDTO.class), HttpStatus.OK);
	}

	@GetMapping
	public List<OrganizationsDTO> listDataOrganization() {
		return organizationService.listOrganizations();

	}

	@PostMapping
	public ResponseEntity<?> createOrganization(@Valid @RequestBody Organizations organization) throws Exception {

		if (organizationRepository.findByName(organization.getName()).isPresent()) {
			return new ResponseEntity<>(NAME_EXIST, HttpStatus.BAD_REQUEST);
		}
		organization.setActive(true);
		return new ResponseEntity<Organizations>(organizationService.saveOrganization(organization), HttpStatus.CREATED);

	}

}
