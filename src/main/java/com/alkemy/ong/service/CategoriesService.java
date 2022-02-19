package com.alkemy.ong.service;

import com.alkemy.ong.dto.CategoriesDTO;
import java.util.List;

public interface CategoriesService {

    public CategoriesDTO publicDataCategory(String name);

    public void deleteCategory(Long id);

    public List<String> getAllByName();
    
    public CategoriesDTO addCategories(CategoriesDTO categoriesDto);
}
