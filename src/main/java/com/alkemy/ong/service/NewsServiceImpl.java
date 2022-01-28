package com.alkemy.ong.service;


import com.alkemy.ong.dto.NewsDTO;
import com.alkemy.ong.exception.ParamNotFoundException;
import com.alkemy.ong.mapper.NewsMapper;
import com.alkemy.ong.model.News;
import com.alkemy.ong.repository.NewsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class NewsServiceImpl implements NewsService{

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NewsMapper newsMapper;



    private ModelMapper mapper = new ModelMapper();

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

        News entity = mapper.map(dto, News.class);
        entity.setDateCreated(LocalDate.now());
        News entitySaved = newsRepository.save(entity);

        return mapper.map(entitySaved, NewsDTO.class);
    }

    @Transactional
    public NewsDTO update(Long id, NewsDTO dto) {

        Optional<News> result = newsRepository.findById(id);
        if (result.isPresent()){
            News entity = mapper.map(dto, News.class);
            News entityUpdated = result.get();
            entityUpdated.setDateModified(LocalDate.now());
            entityUpdated.setContent(entity.getContent());
            entityUpdated.setImage(entity.getImage());
            entityUpdated.setName(entity.getName());
            newsRepository.save(entityUpdated);

            return mapper.map(entityUpdated, NewsDTO.class);
        } else {
            throw new ParamNotFoundException("Requested new was not found.");
        }
    }
}
