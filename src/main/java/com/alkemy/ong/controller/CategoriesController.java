package com.alkemy.ong.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.alkemy.ong.util.Constants.*;

import com.alkemy.ong.model.Activities;
import com.alkemy.ong.service.CategoriesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping(REQ_MAPP_CATEGORIES)
public class CategoriesController {

    @Autowired
    private CategoriesService categoriesService;
    
    @Operation(summary = "Delete  Activities by id")
    @ApiResponses(value = { 
    		  @ApiResponse(responseCode = "200", description = "Delete category" , 
    				    content = { @Content(mediaType = "application/json", 
    				      schema = @Schema(implementation = Activities.class)) }),
    		  
    		  @ApiResponse(responseCode = "400", description = "Invalid id supplied", 
    		    content = @Content), 
    		  @ApiResponse(responseCode = "404", description = "Category not found", 
    		    content = @Content) })
    @DeleteMapping("/{id}")
    public String deleteCategories(@Valid @PathVariable  long id) throws Exception{
        return categoriesService.deleteCategory(id);
    }
    
    

}
