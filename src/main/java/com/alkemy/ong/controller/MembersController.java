package com.alkemy.ong.controller;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.alkemy.ong.util.Constants.*;
import java.util.List;
import static com.alkemy.ong.util.Constants.GET_MAPP_LIST_MEMBERS;
import com.alkemy.ong.dto.MemberDTO;
import com.alkemy.ong.service.MembersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;



@Tag(name = "Members", description = "Create, update and show Members")
@RestController
@RequestMapping(REQ_MAPP_MEMBERS)
public class MembersController {

    @Autowired
    MembersService membersService;

    @GetMapping(GET_MAPP_LIST_MEMBERS)
    public ResponseEntity<List<MemberDTO>> listMembers() {

        return ResponseEntity.status(HttpStatus.SC_OK).body(membersService.getMembers());

    }
    
    
    @Operation(summary = "Delete a Member by id" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete member by id" ,
                    content = { @Content(mediaType = "application/json")
                            }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Member not found",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Access denied",
            content = @Content)})
    
    @DeleteMapping(REQ_MAPP_ID)
    public ResponseEntity<?> deleteMember(@PathVariable Long id) {
    	membersService.deleteMember(id);
        return ResponseEntity.status(HttpStatus.SC_OK).build();

    }

}
