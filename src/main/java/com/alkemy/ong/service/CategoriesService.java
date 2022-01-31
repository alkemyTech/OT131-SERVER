package com.alkemy.ong.service;

import com.alkemy.ong.dto.CategoriesDTO;
import java.util.List;

public interface CategoriesService {

    CategoriesDTO publicDataCategory(String name);

    void deleteCategory(Long id);

    CategoriesDTO update(Long id, CategoriesDTO dto);

    CategoriesDTO addCategories(CategoriesDTO categoriesDto);

    List<String> getAllByName();

}
