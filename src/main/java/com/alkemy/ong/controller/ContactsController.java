package com.alkemy.ong.controller;

import com.alkemy.ong.dto.ContactsDTO;
import com.alkemy.ong.service.ContactsService;
import static com.alkemy.ong.util.Constants.CONTACTS_POST_INFO;
import static com.alkemy.ong.util.Constants.CONTACTS_POST_OK;
import static com.alkemy.ong.util.Constants.ERR_CONTACT_ALREADY_EXISTS;
import static com.alkemy.ong.util.Constants.REQ_MAPP_CONTACTS;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
        @ApiResponse(responseCode = "200", description = CONTACTS_POST_OK,
                content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ContactsDTO.class))}),
        @ApiResponse(responseCode = "400", description = ERR_CONTACT_ALREADY_EXISTS,
                content = @Content)})
    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody ContactsDTO contact) {
        ContactsDTO response = contactService.save(contact);
        return ResponseEntity.ok().body(response);
    }

}
