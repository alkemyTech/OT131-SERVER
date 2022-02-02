package com.alkemy.ong.controller;

import com.alkemy.ong.model.Activities;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alkemy.ong.dto.NewsDTO;
import com.alkemy.ong.service.NewsService;
import static com.alkemy.ong.util.Constants.REQ_MAPP_ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.alkemy.ong.util.Constants.REQ_MAPP_NEWS;

@RestController
@RequestMapping(REQ_MAPP_NEWS)
public class NewsController {

    @Autowired
    private NewsService newsService;

    @Operation(summary = "Save a new" )
    @PostMapping
    public ResponseEntity<NewsDTO> save(@Valid @RequestBody NewsDTO dto) {

        NewsDTO updated = newsService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(updated);
    }

    @Operation(summary = "Update a new by id" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "UpdateNew by id" ,
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Activities.class)) }),

            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "new not found",
                    content = @Content) })
    @PutMapping("/{id}")
    public ResponseEntity<NewsDTO> update(@PathVariable Long id, @Valid @RequestBody NewsDTO dto) {
        NewsDTO result = newsService.update(id, dto);
        return ResponseEntity.ok().body(result);
    }

    @Operation(summary = "get a new by id" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "GetNew by id" ,
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Activities.class)) }),

            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "new not found",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<NewsDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(newsService.findById(id));
    }

    @Operation(summary = "Delete a new by id" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "DeleteNew by id" ,
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Activities.class)) }),

            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "new not found",
                    content = @Content) })
    @DeleteMapping(REQ_MAPP_ID)
    public ResponseEntity<?> deleteNews(@Valid @PathVariable(value = "id", required = true) Long id) {
        newsService.deleteNew(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
