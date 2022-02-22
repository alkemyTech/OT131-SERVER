package com.alkemy.ong.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alkemy.ong.dto.NewsDTO;
import com.alkemy.ong.dto.PagesDTO;
import com.alkemy.ong.service.NewsService;
import static com.alkemy.ong.util.Constants.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import javax.validation.Valid;

@RestController
@RequestMapping(REQ_MAPP_NEWS)
public class NewsController {

    @Autowired
    private NewsService newsService;

    @Operation(summary = NEWS_POST_INFO)
    @ApiResponses(value = {
        @ApiResponse(responseCode = CODE_CREATED, description = NEWS_CREATED_OK,
                content = {
                    @Content(mediaType = MEDIA_TYPE_APP_JSON,
                            schema = @Schema(implementation = NewsDTO.class))}),
        @ApiResponse(responseCode = CODE_BAD_REQUEST, description = ERR_NEWS_NULL_DATA)})
    @PostMapping
    public ResponseEntity<NewsDTO> save(@Valid @RequestBody NewsDTO dto) {

        NewsDTO updated = newsService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(updated);
    }

    @Operation(summary = NEWS_PUT_INFO)
    @ApiResponses(value = {
        @ApiResponse(responseCode = CODE_OK, description = NEWS_PUT_OK,
                content = {
                    @Content(mediaType = MEDIA_TYPE_APP_JSON,
                            schema = @Schema(implementation = NewsDTO.class))}),

        @ApiResponse(responseCode = CODE_BAD_REQUEST, description = BAD_NEWS_ID,
                content = @Content)})
    @PutMapping(REQ_MAPP_ID)
    public ResponseEntity<NewsDTO> update(@PathVariable Long id, @Valid @RequestBody NewsDTO dto) {
        NewsDTO result = newsService.update(id, dto);
        return ResponseEntity.ok().body(result);
    }

    @Operation(summary = NEWS_GET_INFO)
    @ApiResponses(value = {
        @ApiResponse(responseCode = CODE_OK, description = NEWS_GET_OK,
                content = {
                    @Content(mediaType = MEDIA_TYPE_APP_JSON,
                            schema = @Schema(implementation = NewsDTO.class))}),

        @ApiResponse(responseCode = CODE_BAD_REQUEST, description = BAD_NEWS_ID,
                content = @Content)})
    @GetMapping(REQ_MAPP_ID)
    public ResponseEntity<NewsDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(newsService.findById(id));
    }

    @Operation(summary = NEWS_DELETE_INFO)
    @ApiResponses(value = {
        @ApiResponse(responseCode = CODE_OK, description = NEWS_DELETE_OK),

        @ApiResponse(responseCode = CODE_BAD_REQUEST, description = BAD_NEWS_ID,
                content = @Content)})
    @DeleteMapping(REQ_MAPP_ID)
    public ResponseEntity<?> deleteNews(@PathVariable(value = "id", required = true) Long id) {
        newsService.deleteNew(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = NEWS_PAGE_INFO)
    @ApiResponses(value = {
        @ApiResponse(responseCode = CODE_OK, description = NEWS_PAGE_OK,
                content = {
                    @Content(mediaType = MEDIA_TYPE_APP_JSON,
                            schema = @Schema(implementation = PagesDTO.class))}),
        @ApiResponse(responseCode = CODE_BAD_REQUEST, description = WRONG_PAGE_NUMBER)})
    @GetMapping
    public ResponseEntity<?> getPage(@RequestParam Integer page) {
        PagesDTO<NewsDTO> response = newsService.getAll(page);
        return ResponseEntity.ok().body(response);
    }
}
