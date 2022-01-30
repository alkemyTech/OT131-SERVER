package com.alkemy.ong.service;

import com.alkemy.ong.dto.NewsDTO;
import com.alkemy.ong.mapper.NewsMapper;
import com.alkemy.ong.model.News;
import com.alkemy.ong.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;
   
    @Autowired
    private NewsMapper newsMapper;

    @Override
    public NewsDTO publicDataNew(String name) {
        Optional<News> nw = newsRepository.findByName(name);
        return newsMapper.converToDTO(nw.get());
    }

   
    
    @Override
    public void deleteNew(Long id){
        Optional<News> answer = newsRepository.findById(id);
        System.out.println(answer.toString());
        if(answer.isPresent()){
            News news = answer.get();
            news.setIsActivated(Boolean.FALSE);
            newsRepository.save(news);
        }else{
            throw new IllegalArgumentException("News does not exist");
        }

    }

}
