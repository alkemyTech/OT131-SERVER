package com.alkemy.ong.controller;

import java.util.List;
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
import com.alkemy.ong.dto.OrganizationsAllDTO;
import com.alkemy.ong.dto.OrganizationsDTO;
import com.alkemy.ong.service.OrganizationsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import static com.alkemy.ong.util.Constants.*;
import org.springframework.web.bind.annotation.PutMapping;

@Tag(name = "Organizations", description = "Create, update show Organizations")
@RestController
@RequestMapping(REQ_MAPP_ORG)
public class OrganizationsController {

    @Autowired
    private OrganizationsService organizationService;

    @Operation(summary = "Get a public data organization")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Show public data Organization by name",
                content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrganizationsDTO.class))}),

        @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                content = @Content),
        @ApiResponse(responseCode = "404", description = "Organization not found",
                content = @Content)})
    @GetMapping(POINT_GET_MAPP)
    public ResponseEntity<?> publicDataOrganization(@PathVariable String name) {
        return new ResponseEntity<>(organizationService.publicDataOrganization(name), HttpStatus.OK);
    }

    @Operation(summary = "Get a list Organizations")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Show  a list public data Organization",
                content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrganizationsAllDTO.class))}),

        @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                content = @Content)})
    @GetMapping
    public List<OrganizationsDTO> listDataOrganization() {
        return organizationService.listOrganizations();
    }

    @Operation(summary = "Create a organization")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Create Organization",
                content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrganizationsDTO.class))}),

        @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                content = @Content),
        @ApiResponse(responseCode = "404", description = "Organization not found",
                content = @Content)})
    @PostMapping()
    public ResponseEntity<OrganizationsAllDTO> createOrganization(@Valid @RequestBody OrganizationsAllDTO organizationDto) throws Exception {
        return new ResponseEntity<OrganizationsAllDTO>(organizationService.saveOrganization(organizationDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Update a organization by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "UpdateOrganization by id",
                content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrganizationsAllDTO.class))}),

        @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                content = @Content),
        @ApiResponse(responseCode = "404", description = "Organization not found",
                content = @Content)})
    @PutMapping(REQ_MAPP_ID)
    public ResponseEntity<?> updateOrganization(@Valid @RequestBody OrganizationsAllDTO organization, @PathVariable long id) throws Exception {
        return new ResponseEntity<>(organizationService.updateDataOrganization(organization, id), HttpStatus.OK);
    }

}
