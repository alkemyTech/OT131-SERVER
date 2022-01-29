package com.alkemy.ong.service;

import com.alkemy.ong.dto.CategoriesDTO;
import com.alkemy.ong.mapper.CategoriesMapper;
import com.alkemy.ong.model.Categories;
import com.alkemy.ong.repository.CategoriesRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriesServiceImpl implements CategoriesService {

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Autowired
    private CategoriesMapper categoriesMapper;

    @Override
    public CategoriesDTO publicDataCategory(String name) {
        Optional<Categories> cat = categoriesRepository.findByName(name);
        return categoriesMapper.converToDTO(cat.get());
    }

    @Override
    public void deleteCategory(Long id) {

        Optional<Categories> answer = categoriesRepository.findById(id);
        Categories category = answer.get();
        category.setIsActivated(false);
        categoriesRepository.save(category);
    }


    @Override
    public List<String> getAllByName() {
        List<CategoriesDTO> listDto = listAllDto();
        List<String> listName = new ArrayList();
        for (CategoriesDTO category : listDto) {
            listName.add(category.getName());
        }
        return listName;
    }

    private List<Categories> listAll() {
        return categoriesRepository.findAll();
    }

    private List<CategoriesDTO> listAllDto() {
        List<Categories> categories = listAll();
        List<CategoriesDTO> catDto = new ArrayList();
        for (Categories categoty : categories) {
            catDto.add(categoriesMapper.converToDTO(categoty));
        }
        return catDto;
    }

    @Override
    public CategoriesDTO addCategories(CategoriesDTO categoriesDto) {
        return categoriesMapper.converToDTO(categoriesRepository.save(categoriesMapper.converToModel(categoriesDto)));
    }

}
