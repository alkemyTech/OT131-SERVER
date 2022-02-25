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

@Tag(name = TESTIMONIALS_TAG_NAME, description = TESTIMONIALS_TAG_DESCRIPTION)
@RestController
@RequestMapping(REQ_MAPP_TESTIMONIALS)
public class TestimonialsController {

    @Autowired
    private TestimonialsService testimonialsService;

    @Operation(summary = TESTIMONIALS_DELETE_SUMARY)
    @ApiResponses(value = {
        @ApiResponse(responseCode = CODE_OK, description = TESTIMONIALS_DELETE_DESCRIPTION,
                content = {
                    @Content(mediaType = MEDIA_TYPE_APP_JSON,
                            schema = @Schema(implementation = TestimonialsDto.class))}),

        @ApiResponse(responseCode = CODE_BAD_REQUEST, description = TESTIMONIALS_BAD_REQUEST,
                content = @Content),
        @ApiResponse(responseCode = CODE_NOT_FOUND, description = TESTIMONIALS_NOT_FOUND,
                content = @Content)})
    @DeleteMapping(REQ_MAPP_ID)
    public ResponseEntity<?> deleteTestimonials(@Valid @PathVariable(value = "id", required = true) Long id) {
        testimonialsService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = TESTIMONIALS_PUT_SUMARY)
    @ApiResponses(value = {
        @ApiResponse(responseCode = CODE_OK, description = TESTIMONIALS_PUT_OK,
                content = {
                    @Content(mediaType = MEDIA_TYPE_APP_JSON,
                            schema = @Schema(implementation = TestimonialsDto.class))}),

        @ApiResponse(responseCode = CODE_BAD_REQUEST, description = TESTIMONIALS_BAD_REQUEST,
                content = @Content),
        @ApiResponse(responseCode = CODE_NOT_FOUND, description = TESTIMONIALS_NOT_FOUND,
                content = @Content)})
    @PutMapping(REQ_MAPP_ID)
    public ResponseEntity<?> updateTestimonials(@Valid @RequestBody TestimonialsDto testimonials, @PathVariable long id) throws Exception {
        return new ResponseEntity<>(testimonialsService.updateTestimonails(testimonials, id), HttpStatus.OK);
    }

    @Operation(
            summary = TESTIMONIALS_POST_SUMARY,
            description = TESTIMONIALS_POST_DESCRIPTION)
    @PostMapping()
    public ResponseEntity<?> addNewTestimonials(@Valid @RequestBody(required = true) TestimonialsAddNewDto testimonialsAddNewDto) {
        return ResponseEntity.ok(testimonialsService.save(testimonialsAddNewDto));
    }

    @Operation(summary = TESTIMONIALS_PAGE_INFO)
    @ApiResponses(value = {
        @ApiResponse(responseCode = CODE_OK, description = TESTIMONIALS_PAGE_OK,
                content = {
                    @Content(mediaType = MEDIA_TYPE_APP_JSON,
                            schema = @Schema(implementation = PagesDTO.class))}),
        @ApiResponse(responseCode = CODE_BAD_REQUEST, description = WRONG_PAGE_NUMBER)})
    @GetMapping
    public ResponseEntity<?> getPage(@RequestParam Integer page) {
        PagesDTO<TestimonialsResponseDto> response = testimonialsService.getAll(page);
        return ResponseEntity.ok().body(response);
    }

}
