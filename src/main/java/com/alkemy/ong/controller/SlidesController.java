package com.alkemy.ong.controller;

import com.alkemy.ong.dto.SlidesUpdateDto;
import com.alkemy.ong.dto.SlidesUpdateResponseDTO;
import com.alkemy.ong.dto.SlidesDTO;
import com.alkemy.ong.dto.SlidesListDto;
import com.alkemy.ong.dto.SlidesResponseDTO;
import com.alkemy.ong.service.SlidesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.alkemy.ong.util.Constants.*;

import java.util.List;

@Tag(name = "Slides", description = "Create, update and show Slides")
@RestController
@RequestMapping(REQ_MAPP_SLIDES)
public class SlidesController {

    @Autowired
    private SlidesService slidesService;

    @Operation(summary = SLIDES_POST_INFO)
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = SLIDES_POST_OK,
                content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SlidesDTO.class))}),
        @ApiResponse(responseCode = "400", description = BAD_REQUEST,
                content = @Content)})
    @PostMapping
    public ResponseEntity<SlidesResponseDTO> save(@Valid @RequestBody SlidesDTO dto) {

        SlidesResponseDTO saved = slidesService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @Operation(summary = SLIDES_GET_INFO)
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = SLIDES_GET_INFO,
                content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SlidesListDto.class))}),
        @ApiResponse(responseCode = "400", description = BAD_REQUEST,
                content = @Content)})
    @GetMapping
    public List<SlidesListDto> getAll() {
        return slidesService.getAll();
    }

    @Operation(summary = "Update slide data")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Update ok. Return credentials",
                content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SlidesUpdateDto.class))}),

        @ApiResponse(responseCode = "404", description = "Slide not found. Slide id does not exist",
                content = @Content),})
    @PutMapping(REQ_MAPP_ID)
    private ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody SlidesUpdateDto dto) {
        SlidesUpdateResponseDTO result = slidesService.update(id, dto);
        return result == null
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
                : ResponseEntity.ok().body(result);

    }

    @Operation(summary = "Delete slide from Database (set active as false)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Delete ok. Return credentials",
                content = {
                    @Content(mediaType = "application/json")}),

        @ApiResponse(responseCode = "403", description = "Forbidden. Only admin users can delete registers from db",
                content = @Content),
        @ApiResponse(responseCode = "404", description = "S not found. Wrong identifier",
                content = @Content)})
    @DeleteMapping(REQ_MAPP_ID)
    private ResponseEntity<?> deleteSlides(@PathVariable(name = "id") Long id) throws Exception {
        try {
            slidesService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).build();

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
        }
    }

    @Operation(summary = "Show Slide by Id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "404", description = "Slide not found. Wrong identifier",
                content = @Content)})
    @GetMapping(REQ_MAPP_ID)
    public ResponseEntity<SlidesResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(slidesService.findById(id));
    }
}
