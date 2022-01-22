package com.alkemy.ong.mapper;


import com.alkemy.ong.dto.CategoriesDTO;
import com.alkemy.ong.model.Categories;
import org.springframework.stereotype.Component;

@Component
public class CategoriesMapper {

    public CategoriesDTO modelToDTO(Categories category){
        return new CategoriesDTO(category.getName(),category.getDescription(), category.getImage());
    }

    public Categories DTOToModel(CategoriesDTO categoryDTO){
        return new Categories(categoryDTO.getName(),categoryDTO.getDescription(), categoryDTO.getImage());
    }
}
