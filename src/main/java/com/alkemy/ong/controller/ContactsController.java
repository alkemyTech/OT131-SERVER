package com.alkemy.ong.controller;

import com.alkemy.ong.dto.ContactsDTO;
import com.alkemy.ong.dto.NewContactsDTO;
import com.alkemy.ong.service.ContactsService;
import static com.alkemy.ong.util.Constants.CONTACTS_POST_INFO;
import static com.alkemy.ong.util.Constants.CONTACTS_CREATED_OK;
import static com.alkemy.ong.util.Constants.CONTACTS_DELETE_INFO;
import static com.alkemy.ong.util.Constants.CONTACTS_DELETE_OK;
import static com.alkemy.ong.util.Constants.ERR_CONTACT_ALREADY_EXISTS;
import static com.alkemy.ong.util.Constants.ERR_CONTACT_NOT_FOUND;
import static com.alkemy.ong.util.Constants.REQ_MAPP_CONTACTS;
import static com.alkemy.ong.util.Constants.REQ_MAPP_ID;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(REQ_MAPP_CONTACTS)
public class ContactsController {

    @Autowired
    private ContactsService contactService;

    @Operation(summary = CONTACTS_POST_INFO)
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = CONTACTS_CREATED_OK,
                content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ContactsDTO.class))}),
        @ApiResponse(responseCode = "400", description = ERR_CONTACT_ALREADY_EXISTS)})
    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody NewContactsDTO contact) {
        System.out.println("entrando save");
        ContactsDTO response = contactService.save(contact);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = CONTACTS_DELETE_INFO)
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = CONTACTS_DELETE_OK),
        @ApiResponse(responseCode = "400", description = ERR_CONTACT_NOT_FOUND)})
    @DeleteMapping(REQ_MAPP_ID)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        contactService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(
            summary = "List all contacts",
            description = "List all contacts")
    @GetMapping()
    public ResponseEntity<?> listContact() {
        return ResponseEntity.ok(contactService.findByAll());
    }

}
