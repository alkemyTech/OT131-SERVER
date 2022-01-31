package com.alkemy.ong.service;

import com.alkemy.ong.dto.CategoriesDTO;
import com.alkemy.ong.exception.AlreadyExistsException;
import com.alkemy.ong.exception.ParamNotFoundException;
import com.alkemy.ong.mapper.CategoriesMapper;
import com.alkemy.ong.model.Categories;
import com.alkemy.ong.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public CategoriesDTO update(Long id, CategoriesDTO dto) {

        Optional<Categories> result = categoriesRepository.findById(id);
        if (result.isPresent()) {
            Categories entity = categoriesMapper.converToModel(dto);
            entity.setId(id);
            Categories entityUpdated = categoriesRepository.save(entity);

            return categoriesMapper.converToDTO(entityUpdated);
        } else {
            throw new ParamNotFoundException("Requested category was not found.");
        }
    }

    @Transactional
    @Override
    public CategoriesDTO addCategories(CategoriesDTO categoriesDto) {
        if (categoriesRepository.findByName(categoriesDto.getName()).isPresent()) {
            throw new AlreadyExistsException("category already exists");
        }
        return categoriesMapper.converToDTO(categoriesRepository.save(categoriesMapper.converToModel(categoriesDto)));

    }
}
