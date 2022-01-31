package com.alkemy.ong.service;


import com.alkemy.ong.dto.CategoriesDTO;
import java.util.List;

public interface CategoriesService {


    public CategoriesDTO publicDataCategory(String name);
    public String deleteCategory(Long id) throws Exception;
    CategoriesDTO update(Long id, CategoriesDTO dto);
    List<String> getAllByName();
}
