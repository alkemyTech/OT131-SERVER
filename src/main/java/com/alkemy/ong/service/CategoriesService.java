package com.alkemy.ong.service;


import com.alkemy.ong.dto.CategoriesDTO;

public interface CategoriesService {

    public CategoriesDTO publicDataCategory(String name);

    public void deleteCategory(Long id);

}
