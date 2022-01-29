package com.alkemy.ong.service;

import com.alkemy.ong.dto.NewsDTO;

public interface NewsService{

    NewsDTO publicDataNew(String name);
    void deleteNew(Long id);
    NewsDTO save(NewsDTO dto);
}
