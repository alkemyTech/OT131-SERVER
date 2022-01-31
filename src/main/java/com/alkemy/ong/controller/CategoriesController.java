package com.alkemy.ong.controller;

import com.alkemy.ong.dto.CategoriesDTO;
import com.alkemy.ong.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.alkemy.ong.util.Constants.REQ_MAPP_CATEGORIES;

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

    @PostMapping
    public ResponseEntity<?> addCategories(@Valid @RequestBody() CategoriesDTO categoriesDto) {
        return ResponseEntity.ok(categoriesService.addCategories(categoriesDto));
    }
}
