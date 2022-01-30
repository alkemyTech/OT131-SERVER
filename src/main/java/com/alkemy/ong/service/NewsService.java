package com.alkemy.ong.service;

import com.alkemy.ong.dto.NewsDTO;

public interface NewsService {

    public NewsDTO publicDataNew(String name);
    public void deleteNew(Long id);
}
