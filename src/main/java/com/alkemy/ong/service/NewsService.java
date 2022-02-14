package com.alkemy.ong.service;

import com.alkemy.ong.dto.NewsDTO;
import com.alkemy.ong.dto.PagesDTO;
import com.alkemy.ong.exception.ParamNotFoundException;

public interface NewsService{

    NewsDTO publicDataNew(String name);
    void deleteNew(Long id);
    NewsDTO update(Long id, NewsDTO dto) throws ParamNotFoundException;
    NewsDTO save(NewsDTO dto) throws ParamNotFoundException;
    NewsDTO findById(Long Id);
    PagesDTO<NewsDTO> getAll(Integer page);
}
