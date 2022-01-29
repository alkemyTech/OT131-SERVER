package com.alkemy.ong.controller;

import com.alkemy.ong.dto.CategoriesDTO;
import com.alkemy.ong.service.CategoriesService;
import static com.alkemy.ong.util.Constants.REQ_MAPP_CATEGORIES;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(REQ_MAPP_CATEGORIES)
public class CategoriesController {
    
    @Autowired
    private CategoriesService categoriesService;
    
    @GetMapping
    public ResponseEntity<List<String>> listCategoriesName(){
        return ResponseEntity.ok().body(categoriesService.getAllByName());
    }
    
    @PostMapping
    public ResponseEntity<?> addCategories(@Valid @RequestBody() CategoriesDTO categoriesDto ){
        return ResponseEntity.ok(categoriesService.addCategories(categoriesDto));
    }
}