package com.alkemy.ong.controller;

import com.alkemy.ong.dto.CommentsDTO;
import com.alkemy.ong.dto.CommentsResponseDTO;
import com.alkemy.ong.service.CommentsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;
import static com.alkemy.ong.util.Constants.*;
import static com.alkemy.ong.util.Constants.REQ_MAPP_COMMENTS;
import static com.alkemy.ong.util.Constants.REQ_MAPP_ID;
import static com.alkemy.ong.util.Constants.COMMENTS_GET_INFO;
import static com.alkemy.ong.util.Constants.COMMENTS_GET_OK;
import static com.alkemy.ong.util.Constants.COMMENTS_GET_DENIED;

@RestController
@RequestMapping(REQ_MAPP_COMMENTS)
public class CommentsController {

    @Autowired
    private CommentsService commentsService;
    
    @Operation(summary = COMMENTS_POST_INFO)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = COMMENTS_POST_OK,
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentsResponseDTO.class))}),
            @ApiResponse(responseCode = "400", description = BAD_REQUEST,
                    content = @Content),
            @ApiResponse(responseCode = "404", description = ENTITY_NOT_FOUND,
                    content = @Content)})
    @PostMapping()
    public ResponseEntity<CommentsResponseDTO> create(@RequestParam Long news_id, @RequestParam String body, HttpServletRequest request) throws AccessDeniedException{
        
            CommentsResponseDTO response = commentsService.create(news_id, body, request.getHeader("Authorization"));
            return ResponseEntity.ok().body(response);
    }
    
    
    
    

    @Operation(summary = COMMENTS_PUT_INFO)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = COMMENTS_PUT_INFO_OK,
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentsResponseDTO.class))}),
            @ApiResponse(responseCode = "400", description = BAD_REQUEST,
                    content = @Content),
            @ApiResponse(responseCode = "404", description = ENTITY_NOT_FOUND,
                    content = @Content)})
    @PutMapping(REQ_MAPP_ID)
    public ResponseEntity<CommentsResponseDTO> update(@PathVariable Long id,
                                                      @Valid @RequestBody CommentsDTO dto,
                                                      HttpServletRequest request) {
        try {
            CommentsResponseDTO result = commentsService.update(id, dto, request.getHeader("Authorization"));
            return result == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() :
                    ResponseEntity.ok().body(result);
        } catch (AccessDeniedException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }
    
    @Operation(summary = COMMENTS_GET_INFO)
    @ApiResponses(value = {
        @ApiResponse (responseCode = "200", description = COMMENTS_GET_OK,
                content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentsDTO.class))}),
        @ApiResponse(responseCode = "403", description = COMMENTS_GET_DENIED)})
    @GetMapping
    public Optional<List<String>> listComments() throws AccessDeniedException {
        return commentsService.listComments();
    }
    
    
    @Operation(summary = COMMENTS_DELETE_INFO)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = COMMENTS_DELETE_OK,
                    content = {@Content}),
            @ApiResponse(responseCode = "400", description = BAD_REQUEST,
                    content = @Content),
            @ApiResponse(responseCode = "404", description = ERR_COMMENT_NOT_FOUND,
                    content = @Content)})
    @DeleteMapping(REQ_MAPP_ID)
    public ResponseEntity<Void> delete(@PathVariable Long id, HttpServletRequest request){        
        try {
            commentsService.delete(id, request.getHeader("Authorization"));
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (AccessDeniedException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }
    
}
