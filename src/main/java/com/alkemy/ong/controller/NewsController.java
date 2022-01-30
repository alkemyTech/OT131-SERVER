package com.alkemy.ong.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
  
    @PostMapping
    public ResponseEntity<NewsDTO> save(@Valid @RequestBody NewsDTO dto) {

        NewsDTO updated = newsService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(updated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NewsDTO> update(@PathVariable Long id, @Valid @RequestBody NewsDTO dto) {
        NewsDTO result = newsService.update(id, dto);
        return ResponseEntity.ok().body(result);
    }
}