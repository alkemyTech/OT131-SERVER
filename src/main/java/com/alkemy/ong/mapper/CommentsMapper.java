package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.CommentsResponseDTO;
import com.alkemy.ong.model.Comments;
import org.springframework.stereotype.Component;

@Component
public class CommentsMapper {

    public CommentsResponseDTO entity2ResponseDTO(Comments entity) {
        return CommentsResponseDTO.builder()
                .id(entity.getId())
                .body(entity.getBody())
                .newsId(entity.getNews().getId())
                .userId(entity.getUsers().getId())
                .build();
    }

}
