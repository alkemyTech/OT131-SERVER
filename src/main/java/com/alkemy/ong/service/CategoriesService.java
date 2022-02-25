package com.alkemy.ong.service;

import com.alkemy.ong.dto.CategoriesDTO;
import com.alkemy.ong.dto.PagesDTO;
import com.alkemy.ong.exception.ParamNotFoundException;
import com.alkemy.ong.model.Categories;

import java.util.List;

import org.springframework.data.domain.Page;

public interface CategoriesService {

    public CategoriesDTO publicDataCategory(String name);

    public String deleteCategory(Long id) throws Exception;

    CategoriesDTO update(Long id, CategoriesDTO dto);

    List<String> getAllByName();

    List<Categories> listAll();

    CategoriesDTO addCategories(CategoriesDTO categoriesDto);

    public CategoriesDTO detailCategory(Long id) throws Exception;

    public PagesDTO<CategoriesDTO> getAll(Integer page);

    public PagesDTO<CategoriesDTO> responsePage(Page<Categories> page);

    CategoriesDTO save(CategoriesDTO dto) throws ParamNotFoundException;
}
