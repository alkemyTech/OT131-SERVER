package com.alkemy.ong.service;



import com.alkemy.ong.dto.NewsDTO;
import com.alkemy.ong.exception.ParamNotFoundException;

public interface NewsService{


    NewsDTO publicDataNew(String name);
    void deleteNews(Long id);
    NewsDTO update(Long id, NewsDTO dto) throws ParamNotFoundException;
    NewsDTO save(NewsDTO dto) throws ParamNotFoundException;
    NewsDTO findById(Long Id);
}
