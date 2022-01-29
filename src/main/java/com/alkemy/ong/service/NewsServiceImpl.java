package com.alkemy.ong.service;

import com.alkemy.ong.dto.NewsDTO;
import com.alkemy.ong.mapper.NewsMapper;
import com.alkemy.ong.model.Categories;
import com.alkemy.ong.model.News;
import com.alkemy.ong.repository.CategoriesRepository;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.util.ParamNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class NewsServiceImpl implements NewsService{

    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private CategoriesRepository categoriesRepository;

    @Autowired
    private NewsMapper newsMapper;

    @Override
    public NewsDTO publicDataNew(String name){
        Optional<News> nw = newsRepository.findByName(name);
        return newsMapper.converToDTO(nw.get());
    }

    @Override
    public void deleteNew(Long id){

        Optional<News> answer = newsRepository.findById(id);
        News nw = answer.get();
        nw.setIsActivated(false);
        newsRepository.save(nw);
    }

    @Transactional
    public NewsDTO save(NewsDTO dto) {

        News entity = newsMapper.converToModel(dto);

        Optional<Categories> categoriesResult = categoriesRepository.findById(dto.getIdCategory());
        if (categoriesResult.isEmpty())
            throw new ParamNotFoundException("The News must have a valid category id");

        entity.setCategory(categoriesResult.get());
        News entitySaved = newsRepository.save(entity);

        return newsMapper.converToDTO(entitySaved);
    }
}
