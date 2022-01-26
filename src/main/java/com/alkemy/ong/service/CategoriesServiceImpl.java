package com.alkemy.ong.service;

import com.alkemy.ong.dto.CategoriesDTO;
import com.alkemy.ong.mapper.CategoriesMapper;
import com.alkemy.ong.model.Categories;
import com.alkemy.ong.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriesServiceImpl implements CategoriesService{

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Autowired
    private CategoriesMapper categoriesMapper;

    @Override
    public CategoriesDTO publicDataCategory(String name){
        Optional<Categories> cat = categoriesRepository.findByName(name);
        return categoriesMapper.converToDTO(cat.get());
    }

    @Override
    public void deleteCategory(Long id){

        Optional<Categories> answer = categoriesRepository.findById(id);
        Categories category = answer.get();
        category.setIsActivated(false);
        categoriesRepository.save(category);
    }

}
