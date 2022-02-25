package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.NewsDTO;
import com.alkemy.ong.model.News;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class NewsMapper {

    private ModelMapper mapper = new ModelMapper();

    public NewsDTO converToDTO(News nw) {
        return new NewsDTO(nw.getName(), nw.getContent(), nw.getImage(), nw.getCategory().getId(), nw.getDateCreated());
    }

    public News converToModel(NewsDTO newDTO) {
        return new News(newDTO.getName(), newDTO.getContent(), newDTO.getImage());
    }

    public List<NewsDTO> listNewsToDto(List<News> list) {
        return list.stream().map(news -> mapper.map(news, NewsDTO.class))
                .collect(Collectors.toList());
    }
}
