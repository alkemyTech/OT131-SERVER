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
    
    @GetMapping("/{id}")
    public ResponseEntity<NewsDTO> getById(@PathVariable Long id, @Valid RequestBody NewsDTO){
        return ResponseEntity.ok().body(newsService.findById(id));
    }
        
}
