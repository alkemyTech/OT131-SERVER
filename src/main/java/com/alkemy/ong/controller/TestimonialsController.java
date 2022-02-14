package com.alkemy.ong.controller;

import com.alkemy.ong.dto.PagesDTO;
import com.alkemy.ong.dto.TestimonialsAddNewDto;
import com.alkemy.ong.dto.TestimonialsDto;
import com.alkemy.ong.dto.TestimonialsResponseDto;
import com.alkemy.ong.service.TestimonialsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.alkemy.ong.util.Constants.*;

@Tag(name = "Testimonials", description = "Create, update show and delete Testimonials")
@RestController
@RequestMapping(REQ_MAPP_TESTIMONIALS)
public class TestimonialsController {

    @Autowired
    private TestimonialsService testimonialsService;

    @Operation(summary = "Delete a testimonial by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "DeleteTestimonial by id",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TestimonialsDto.class))}),

            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Testimonial not found",
                    content = @Content)})
    @DeleteMapping(REQ_MAPP_ID)
    public ResponseEntity<?> deleteTestimonials(@Valid @PathVariable(value = "id", required = true) Long id) {
        testimonialsService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Update a Testimonials by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update Testimonials by id",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TestimonialsDto.class))}),

            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Testimonials not found",
                    content = @Content)})
    @PutMapping(REQ_MAPP_ID)
    public ResponseEntity<?> updateTestimonials(@Valid @RequestBody TestimonialsDto testimonials, @PathVariable long id) throws Exception {
        return new ResponseEntity<>(testimonialsService.updateTestimonails(testimonials, id), HttpStatus.OK);
    }

    @Operation(
            summary = "Add new Testimonials",
            description = "To add a testimonial, you must access this endpoint")
    @PostMapping()
    public ResponseEntity<?> addNewTestimonials(@Valid @RequestBody(required = true) TestimonialsAddNewDto testimonialsAddNewDto) {
        return ResponseEntity.ok(testimonialsService.save(testimonialsAddNewDto));
    }

    @Operation(summary = TESTIMONIALS_PAGE_INFO)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = TESTIMONIALS_PAGE_OK,
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = PagesDTO.class))}),
            @ApiResponse(responseCode = "400", description = WRONG_PAGE_NUMBER)})
    @GetMapping
    public ResponseEntity<?> getPage(@RequestParam Integer page) {
        PagesDTO<TestimonialsResponseDto> response = testimonialsService.getAll(page);
        return ResponseEntity.ok().body(response);
    }

}
