package com.alkemy.ong.controller;

import com.alkemy.ong.dto.CategoriesDTO;
import com.alkemy.ong.model.Categories;
import com.alkemy.ong.service.CategoriesService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import static com.alkemy.ong.util.Constants.REQ_MAPP_CATEGORIES;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(REQ_MAPP_CATEGORIES)
public class CategoriesController {

    @Autowired
    private CategoriesService categoriesService;

    @PutMapping("/{id}")
    public ResponseEntity<CategoriesDTO> update(@PathVariable Long id, @Valid @RequestBody CategoriesDTO dto) {
        CategoriesDTO result = categoriesService.update(id, dto);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping
    public ResponseEntity<List<String>> listCategoriesName() {
        return ResponseEntity.ok().body(categoriesService.getAllByName());
    }

    @Operation(summary = "Delete  Activities by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Delete category",
                content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Categories.class))}),

        @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                content = @Content),
        @ApiResponse(responseCode = "404", description = "Category not found",
                content = @Content)})
    @DeleteMapping("/{id}")
    public String deleteCategories(@Valid @PathVariable long id) throws Exception {
        return categoriesService.deleteCategory(id);
    }

    @PostMapping
    public ResponseEntity<?> addCategories(@Valid @RequestBody() CategoriesDTO categoriesDto) {
        return ResponseEntity.ok(categoriesService.addCategories(categoriesDto));
    }

}
