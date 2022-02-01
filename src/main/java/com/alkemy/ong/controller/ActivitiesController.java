
package com.alkemy.ong.controller;

import com.alkemy.ong.dto.ActivitiesDTO;
import com.alkemy.ong.model.Activities;
import com.alkemy.ong.service.ActivitiesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.alkemy.ong.util.Constants.*;

@RestController
@RequestMapping(REQ_MAPP_ACTIVITIES)
public class ActivitiesController {

    @Autowired
    private ActivitiesService activitiesService;

    @Operation(summary = ACTIVITIES_GET_INFO)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = ACTIVITIES_GET_INFO_OK,
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Activities.class))}),
            @ApiResponse(responseCode = "400", description = ENTITY_NOT_FOUND,
                    content = @Content)})
    @GetMapping
    public ResponseEntity<List<ActivitiesDTO>> getAllActives() {
        return ResponseEntity.ok().body(activitiesService.getAllActives());
    }

    @Operation(summary = ACTIVITIES_PUT_INFO)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = ACTIVITIES_PUT_INFO_OK,
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Activities.class))}),
            @ApiResponse(responseCode = "400", description = BAD_REQUEST,
                    content = @Content),
            @ApiResponse(responseCode = "404", description = ERR_ACT_NOT_FOUND,
                    content = @Content)})
    @PutMapping("/{id}")
    public ResponseEntity<ActivitiesDTO> update(@PathVariable Long id,
                                                @Valid @RequestBody ActivitiesDTO dto) {
        ActivitiesDTO result = activitiesService.update(id, dto);
        return ResponseEntity.ok().body(result);
    }

    @Operation(summary = ACTIVITIES_DELETE_INFO)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = ACTIVITIES_DELETE_OK,
                    content = {@Content}),
            @ApiResponse(responseCode = "400", description = BAD_REQUEST,
                    content = @Content),
            @ApiResponse(responseCode = "404", description = ERR_ACT_NOT_FOUND,
                    content = @Content)})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        activitiesService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = ACTIVITIES_POST_INFO)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = ACTIVITIES_POST_OK,
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Activities.class))}),
            @ApiResponse(responseCode = "400", description = BAD_REQUEST,
                    content = @Content)})
    @PostMapping
    public ResponseEntity<?> createActivity(@Valid @RequestBody ActivitiesDTO activityDTO) {

        return new ResponseEntity<>(activitiesService.save(activityDTO), HttpStatus.CREATED);

    }


}