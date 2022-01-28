package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.NewsDTO;
import com.alkemy.ong.model.News;
import org.springframework.stereotype.Component;

@Component
public class NewsMapper {

    public NewsDTO converToDTO(News nw){
        //return new NewsDTO(nw.getName(), nw.getContent(), nw.getImage());
        return new NewsDTO(nw.getName(), nw.getContent(), nw.getImage(), nw.getCategory(), nw.getDateCreated());
    }

    public News converToModel(NewsDTO newDTO){
        return new News(newDTO.getName(), newDTO.getContent(), newDTO.getImage());
    }
}
