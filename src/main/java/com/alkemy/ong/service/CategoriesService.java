package com.alkemy.ong.service;


import com.alkemy.ong.dto.CategoriesDTO;

public interface CategoriesService {

    CategoriesDTO publicDataCategory(String name);

    void deleteCategory(Long id);

    CategoriesDTO update(Long id, CategoriesDTO dto);
}
