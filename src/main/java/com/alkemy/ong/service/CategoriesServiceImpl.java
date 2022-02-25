package com.alkemy.ong.service;

import com.alkemy.ong.dto.CategoriesDTO;
import com.alkemy.ong.dto.PagesDTO;
import com.alkemy.ong.exception.AlreadyExistsException;
import com.alkemy.ong.exception.ParamNotFoundException;
import com.alkemy.ong.mapper.CategoriesMapper;
import com.alkemy.ong.model.Categories;
import com.alkemy.ong.repository.CategoriesRepository;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import static com.alkemy.ong.util.Constants.*;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriesServiceImpl implements CategoriesService {

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Autowired
    private CategoriesMapper categoriesMapper;
    private ModelMapper mapper = new ModelMapper();

    @Override
    @Transactional
    public CategoriesDTO save(CategoriesDTO categoriesDTO) {

        if (categoriesRepository.findByName(categoriesDTO.getName()).isPresent()) {
            throw new AlreadyExistsException(NAME_EXIST);
        } else {
            Categories entity = mapper.map(categoriesDTO, Categories.class);
            Categories entitySaved = categoriesRepository.save(entity);

            return mapper.map(entitySaved, CategoriesDTO.class);
        }

    }

    @Override
    public CategoriesDTO publicDataCategory(String name) {
        Optional<Categories> cat = categoriesRepository.findByName(name);
        return categoriesMapper.converToDTO(cat.get());
    }

    @Override
    public String deleteCategory(Long id) throws Exception {
        Optional<Categories> category = categoriesRepository.findById(id);
        if (category.isEmpty()) {
            throw new ParamNotFoundException(ENTITY_NOT_FOUND);
        }
        category.get().setActivated(false);
        categoriesRepository.save(category.get());
        return "Eliminado";
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

    @Override
    public List<String> getAllByName() {
        List<CategoriesDTO> listDto = listAllDto();
        List<String> listName = new ArrayList();
        for (CategoriesDTO category : listDto) {
            listName.add(category.getName());
        }
        return listName;
    }

    public List<Categories> listAll() {
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

    @Transactional
    @Override
    public CategoriesDTO addCategories(CategoriesDTO categoriesDto) {
        if (categoriesRepository.findByName(categoriesDto.getName()).isPresent()) {
            throw new AlreadyExistsException("category already exists");
        }
        return categoriesMapper.converToDTO(categoriesRepository.save(categoriesMapper.converToModel(categoriesDto)));
    }

    @Override
    public CategoriesDTO detailCategory(Long id) throws Exception {

        return categoriesMapper.converToDTO(categoriesRepository.getById(id));
    }

    @Override
    public PagesDTO<CategoriesDTO> getAll(Integer page) {
        if (page < 0) {
            throw new ParamNotFoundException(WRONG_PAGE_NUMBER);
        }
        Pageable property = PageRequest.of(page, PAGE_SIZE);
        Page<Categories> pageCategory = categoriesRepository.findAll(property);

        return responsePage(pageCategory);
    }

    @Override
    public PagesDTO<CategoriesDTO> responsePage(Page<Categories> page) {
        if (page.isEmpty()) {
            throw new ParamNotFoundException(PAGE_NOT_FOUND);
        }
        Page<CategoriesDTO> pagesCategories = new PageImpl<CategoriesDTO>(
                page.getContent().stream().map(categ -> mapper.map(categ, CategoriesDTO.class))
                        .collect(Collectors.toList()),
                PageRequest.of(page.getNumber(), page.getSize()),
                page.getTotalElements());
        return new PagesDTO<CategoriesDTO>(pagesCategories, CATEGORIES_PAGE_URL);

    }

}
