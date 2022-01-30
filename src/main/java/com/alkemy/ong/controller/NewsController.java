package com.alkemy.ong.controller;


import com.alkemy.ong.service.NewsService;
import static com.alkemy.ong.util.Constants.REQ_MAPP_DELETE_NEWS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import static com.alkemy.ong.util.Constants.REQ_MAPP_NEWS;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(REQ_MAPP_NEWS)
public class NewsController {

    @Autowired
    private NewsService newsService;


    @DeleteMapping(REQ_MAPP_DELETE_NEWS)
    public ResponseEntity<?> deleteNews(@Valid @PathVariable(value = "id", required = true) Long id ){
        newsService.deleteNew(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}