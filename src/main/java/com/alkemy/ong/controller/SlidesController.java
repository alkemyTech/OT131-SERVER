package com.alkemy.ong.controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
                    content = {@Content(mediaType = "application/json",
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
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SlidesListDto.class))}),
            @ApiResponse(responseCode = "400", description = BAD_REQUEST,
                    content = @Content)})
    @GetMapping
    public List<SlidesListDto> getAll() {
        return slidesService.getAll();
    }
    
    
}
