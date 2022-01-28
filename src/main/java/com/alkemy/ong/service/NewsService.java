package com.alkemy.ong.service;

import com.alkemy.ong.dto.NewsDTO;

public interface NewsService{

    public NewsDTO publicDataNew(String name);
    public NewsDTO findById(Long Id);
    public void deleteNew(Long id);
}
