package com.alkemy.ong.controller;
import com.alkemy.ong.dto.CategoriesDTO;
import com.alkemy.ong.dto.PagesDTO;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

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
import static com.alkemy.ong.util.Constants.REQ_MAPP_PAGE;
import static com.alkemy.ong.util.Constants.WRONG_PAGE_NUMBER;

@RestController
@RequestMapping(REQ_MAPP_CATEGORIES)
public class CategoriesController {

    @Autowired
    private CategoriesService categoriesService;

    @Operation(
            summary = "Update a Category",
            description = "To update an existing category you must access this endpoint")
    @ApiModelProperty(notes="id category",name="id",required=true,value= "http://localhost:8080/categories/1")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "UpdateNew by id" ,
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CategoriesDTO.class)) }),

            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Category not found",
                    content = @Content) })

    @PutMapping(REQ_MAPP_ID)
    public ResponseEntity<CategoriesDTO> update(@PathVariable Long id, @Valid @RequestBody CategoriesDTO dto) {
        CategoriesDTO result = categoriesService.update(id, dto);
        return ResponseEntity.ok().body(result);
    }

    @Operation(
            summary = "List category names",
            description = "To list the names of the categories that already exist, you must access this endpoint")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "UpdateNew by id" ,
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CategoriesDTO.class)) }),

            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "category not found",
                    content = @Content) })

    @GetMapping
    public ResponseEntity<List<String>> listCategoriesName() {
        return ResponseEntity.ok().body(categoriesService.getAllByName());
    }
    
    @Operation(
            summary = "Delete  Categories",
            description = "To delete a category you must access this endpoint")
    @DeleteMapping(REQ_MAPP_ID)
    @ApiModelProperty(notes="id category",name="id",required=true,value= "http://localhost:8080/categories/1")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "UpdateNew by id" ,
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CategoriesDTO.class)) }),

            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "category not found",
                    content = @Content) })
   

    public String deleteCategories(@Valid @PathVariable long id) throws Exception {
        return categoriesService.deleteCategory(id);
    }

    @Operation(
            summary = "Add  Categories",
            description = "To add a category you must access this endpoint")
    @ApiModelProperty(notes="DTO category",name="DTO",required=true,value= "{\n" +
            "    \"name\": \"Aca staesdfsdflgo\",\n" +
            "    \"image\":\"imagenJuego.jpg\",\n" +
            "    \"description\":\"Area de juegos\"\n" +
            "}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "UpdateNew by id" ,
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CategoriesDTO.class)) }),

            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "category not found",
                    content = @Content) })

    @PostMapping
    public ResponseEntity<?> addCategories(@Valid @RequestBody() CategoriesDTO categoriesDto) {
        return ResponseEntity.ok(categoriesService.addCategories(categoriesDto));
    }

    @Operation(summary = "Get a category",
                description = "Get a detail of category")
    @ApiModelProperty(notes="id category",name="id",required=true,value= "http://localhost:8080/categories/1")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Show public data Organization by name" ,
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CategoriesDTO.class)) }),

            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Category not found",
                    content = @Content) })
    @GetMapping(REQ_MAPP_DETAIL_CAT)
    public CategoriesDTO detailCategories(@Valid @PathVariable("id") long id) throws Exception{
        return categoriesService.detailCategory(id);
    }
    
    
    @Operation(summary = "Get a list paginated category ",
            description = "Get a list category")
@ApiModelProperty(notes="Number page",name="id",required=true,value= "http://localhost:8080/categories/?page=0")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Show a list Categories" ,
                content = { @Content(mediaType = "application/json",
                        schema = @Schema(implementation = PagesDTO.class)) }),

        @ApiResponse(responseCode = "400", description = WRONG_PAGE_NUMBER,
                content = @Content) })
    @GetMapping(REQ_MAPP_PAGE)
    public ResponseEntity<?> getAll(@Valid @RequestParam ("page")Integer page){
    	 return ResponseEntity.ok().body(categoriesService.getAll(page));
    }

}
