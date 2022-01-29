package com.alkemy.ong.service;

import com.alkemy.ong.dto.NewsDTO;
import com.alkemy.ong.exception.ParamNotFoundException;
import com.alkemy.ong.mapper.NewsMapper;
import com.alkemy.ong.model.News;
import com.alkemy.ong.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NewsServiceImpl implements NewsService{

    @Autowired
    private NewsRepository newsRepository;

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
    
    @Override
    public NewsDTO findById(Long id){
        Optional<News> news = newsRepository.findById(id);
        if(news.isPresent()){
            NewsDTO newsDto = newsMapper.converToDTO(news.get());
            return newsDto; 
        }else{
            throw new ParamNotFoundException("News with id=" + id + " not found.");
        }
    }
}
