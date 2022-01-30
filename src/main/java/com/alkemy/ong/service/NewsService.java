package com.alkemy.ong.service;



import com.alkemy.ong.dto.NewsDTO;
import com.alkemy.ong.exception.ParamNotFoundException;

public interface NewsService{

    public NewsDTO publicDataNew(String name);
    public void deleteNew(Long id);
    NewsDTO update(Long id, NewsDTO dto) throws ParamNotFoundException;
    NewsDTO save(NewsDTO dto) throws ParamNotFoundException;
}
