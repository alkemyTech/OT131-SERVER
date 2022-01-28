package com.alkemy.ong.controller;


import com.alkemy.ong.dto.NewsDTO;
import com.alkemy.ong.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.alkemy.ong.util.Constants.REQ_MAPP_NEWS;


@RestController
@RequestMapping(REQ_MAPP_NEWS)
public class NewsController {

    @Autowired
    private NewsService newsService;

    @PutMapping("/{id}")
    public ResponseEntity<NewsDTO> update(@PathVariable Long id, @Valid @RequestBody NewsDTO dto) {
        NewsDTO result = newsService.update(id, dto);
        return ResponseEntity.ok().body(result);
    }
}
