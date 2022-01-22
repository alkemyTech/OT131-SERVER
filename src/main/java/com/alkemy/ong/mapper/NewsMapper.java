package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.NewsDTO;
import com.alkemy.ong.model.News;
import org.springframework.stereotype.Component;

@Component
public class NewsMapper {

    public NewsDTO modelToDTO(News nw){
        return new NewsDTO(nw.getName(), nw.getContent(), nw.getImage());
    }

    public News DTOToModel(NewsDTO newDTO){
        return new News(newDTO.getName(), newDTO.getContent(), newDTO.getImage());
    }
}
