package com.alkemy.ong.controller;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import static com.alkemy.ong.util.Constants.*;
import com.alkemy.ong.dto.MemberDTO;
import com.alkemy.ong.dto.NewMemberDTO;
import com.alkemy.ong.dto.PagesDTO;
import com.alkemy.ong.service.MembersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Members", description = "Create, update and show Members")
@RestController
@RequestMapping(REQ_MAPP_MEMBERS)
public class MembersController {

    @Autowired
    MembersService membersService;

    @Operation(summary = MEMBERS_PAGE_INFO)
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = MEMBERS_PAGE_OK, content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = PagesDTO.class))}),
        @ApiResponse(responseCode = "400", description = WRONG_PAGE_NUMBER)})
    @GetMapping()
    public ResponseEntity<?> getPage(@RequestParam Integer page) {
        PagesDTO<MemberDTO> response = membersService.getAll(page);
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Delete a Member by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Delete member by id", content = {
            @Content(mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
        @ApiResponse(responseCode = "404", description = "Member not found", content = @Content),
        @ApiResponse(responseCode = "403", description = "Access denied", content = @Content)})

    @DeleteMapping(REQ_MAPP_ID)
    public ResponseEntity<?> deleteMember(@PathVariable Long id) {
        membersService.deleteMember(id);
        return ResponseEntity.status(HttpStatus.SC_OK).build();

    }

    @Operation(summary = MEMBERS_POST_INFO)
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = MEMBERS_CREATED_OK, content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = MemberDTO.class))}),
        @ApiResponse(responseCode = "400", description = ERR_MEMBER_ALREADY_EXISTS)})
    @PostMapping
    public ResponseEntity<NewMemberDTO> createMember(@Valid @RequestBody NewMemberDTO memberDTO) {
        NewMemberDTO response = membersService.createMember(memberDTO);
        return ResponseEntity.status(HttpStatus.SC_CREATED).body(response);
    }

    @Operation(summary = MEMBERS_PUT_INFO)
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = MEMBERS_UPDATE_OK, content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = NewMemberDTO.class))}),
        @ApiResponse(responseCode = "400", description = ENTITY_NOT_FOUND)})
    @PutMapping(REQ_MAPP_ID)
    public ResponseEntity<NewMemberDTO> updateMember(@Valid @RequestBody NewMemberDTO memberDTO,
            @PathVariable Long id) {
        NewMemberDTO response = membersService.updateMember(memberDTO, id);
        return ResponseEntity.status(HttpStatus.SC_OK).body(response);
    }
}
