package com.alkemy.ong.controller;

import java.util.List;
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
import com.alkemy.ong.service.OrganizationsServiceImp;
import static com.alkemy.ong.util.Constants.*;

@RestController
@RequestMapping(REQ_GET_MAPP)
public class OrganizationsController {

	@Autowired
	private OrganizationsServiceImp organizationService;
	
	private ModelMapper mapper = new ModelMapper();
	

	
	@GetMapping(POINT_GET_MAPP)
	public ResponseEntity<?> publicDataOrganization(@PathVariable String name) {
		return new ResponseEntity<>(mapper.map(organizationService.publicDataOrganization(name).get(), OrganizationsDTO.class), HttpStatus.OK);
	}

	@GetMapping
	public List<OrganizationsDTO> listDataOrganization() {
		return organizationService.listOrganizations();
	}

	@PostMapping
	public ResponseEntity<?> createOrganization(@Valid @RequestBody Organizations organization) throws Exception {
		return new ResponseEntity<Organizations>(organizationService.saveOrganization(organization), HttpStatus.CREATED);
	}

}
