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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.ong.dto.OrganizationsAllDTO;
import com.alkemy.ong.dto.OrganizationsDTO;
import com.alkemy.ong.model.Activities;
import com.alkemy.ong.model.Organizations;
import com.alkemy.ong.service.OrganizationsServiceImp;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import static com.alkemy.ong.util.Constants.*;

@RestController
@RequestMapping(REQ_MAPP_ORG)
public class OrganizationsController {

	@Autowired
	private OrganizationsServiceImp organizationService;
	
	private ModelMapper mapper = new ModelMapper();
	

	@Operation(summary = "Get a public data organization")
	 @ApiResponses(value = { 
   		  @ApiResponse(responseCode = "200", description = "Show public data Organization by name" , 
   				    content = { @Content(mediaType = "application/json", 
   				      schema = @Schema(implementation = Organizations.class)) }),
   		  
   		  @ApiResponse(responseCode = "400", description = "Invalid id supplied", 
   		    content = @Content), 
   		  @ApiResponse(responseCode = "404", description = "Organization not found", 
   		    content = @Content) })
	@GetMapping(POINT_GET_MAPP)
	public ResponseEntity<?> publicDataOrganization(@PathVariable String name) {
		return new ResponseEntity<>(mapper.map(organizationService.publicDataOrganization(name).get(), OrganizationsDTO.class), HttpStatus.OK);
	}

	@Operation(summary = "Get a list Organizations")
	 @ApiResponses(value = { 
	   		  @ApiResponse(responseCode = "200", description = "Show  a list public data Organization" , 
	   				    content = { @Content(mediaType = "application/json", 
	   				      schema = @Schema(implementation = Organizations.class)) }),
	   		  
	   		  @ApiResponse(responseCode = "400", description = "Invalid id supplied", 
	   		    content = @Content) })
	@GetMapping
	public List<OrganizationsDTO> listDataOrganization() {
		return organizationService.listOrganizations();
	}
	@Operation(summary = "Create a organization")
	 @ApiResponses(value = { 
	   		  @ApiResponse(responseCode = "200", description = "Create Organization" , 
	   				    content = { @Content(mediaType = "application/json", 
	   				      schema = @Schema(implementation = Organizations.class)) }),
	   		  
	   		  @ApiResponse(responseCode = "400", description = "Invalid id supplied", 
	   		    content = @Content), 
	   		  @ApiResponse(responseCode = "404", description = "Organization not found", 
	   		    content = @Content) })
	@PostMapping()
	public ResponseEntity<?> createOrganization(@Valid @RequestBody Organizations organization) throws Exception {
		return new ResponseEntity<Organizations>(organizationService.saveOrganization(organization), HttpStatus.CREATED);
	}
	
	@Operation(summary = "Update a organization by id" )
	 @ApiResponses(value = { 
	   		  @ApiResponse(responseCode = "200", description = "UpdateOrganization by id" , 
	   				    content = { @Content(mediaType = "application/json", 
	   				      schema = @Schema(implementation = Organizations.class)) }),
	   		  
	   		  @ApiResponse(responseCode = "400", description = "Invalid id supplied", 
	   		    content = @Content), 
	   		  @ApiResponse(responseCode = "404", description = "Organization not found", 
	   		    content = @Content) })
	@PostMapping(POINT_POST_MAPP)
	public ResponseEntity<?> updateOrganization(@Valid @RequestBody OrganizationsAllDTO organization , @RequestParam(value = "id") long id) throws Exception {
		return new ResponseEntity<>(organizationService.updateDataOrganization(organization, id), HttpStatus.OK);
	}
	
	

}
