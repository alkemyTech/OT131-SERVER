package com.alkemy.ong.controller;

import com.alkemy.ong.dto.CategoriesDTO;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alkemy.ong.service.CategoriesService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import static com.alkemy.ong.util.Constants.REQ_MAPP_CATEGORIES;
import static com.alkemy.ong.util.Constants.REQ_MAPP_DETAIL_CAT;
import static com.alkemy.ong.util.Constants.REQ_MAPP_ID;

@RestController
@RequestMapping(REQ_MAPP_CATEGORIES)
public class CategoriesController {

    @Autowired
    private CategoriesService categoriesService;

    @Operation(
            summary = "Update a Category",
            description = "To update an existing category you must access this endpoint")

    @PutMapping(REQ_MAPP_ID)
    public ResponseEntity<CategoriesDTO> update(@PathVariable Long id, @Valid @RequestBody CategoriesDTO dto) {
        CategoriesDTO result = categoriesService.update(id, dto);
        return ResponseEntity.ok().body(result);
    }

    @Operation(
            summary = "List category names",
            description = "To list the names of the categories that already exist, you must access this endpoint")

    @GetMapping
    public ResponseEntity<List<String>> listCategoriesName() {
        return ResponseEntity.ok().body(categoriesService.getAllByName());
    }
    
    @Operation(
            summary = "Delete  Categories",
            description = "To delete a category you must access this endpoint")
    @DeleteMapping(REQ_MAPP_ID)
    public String deleteCategories(@Valid @PathVariable long id) throws Exception {
        return categoriesService.deleteCategory(id);
    }

    @Operation(
            summary = "Add  Categories",
            description = "To add a category you must access this endpoint")

    @PostMapping
    public ResponseEntity<?> addCategories(@Valid @RequestBody() CategoriesDTO categoriesDto) {
        return ResponseEntity.ok(categoriesService.addCategories(categoriesDto));
    }

    @GetMapping(REQ_MAPP_DETAIL_CAT)
    public CategoriesDTO detailCategories(@Valid @PathVariable("id") long id) throws Exception{
        return categoriesService.detailCategory(id);
    }

}
