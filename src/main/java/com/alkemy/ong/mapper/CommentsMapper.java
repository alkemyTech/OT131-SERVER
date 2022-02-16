package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.AllCommentsResponseDTO;
import com.alkemy.ong.dto.CommentsResponseDTO;
import com.alkemy.ong.model.Comments;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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

    public List<AllCommentsResponseDTO> allCommentsResponseDtoToCommnets(List<Comments> listComments) {
        List<AllCommentsResponseDTO> listAllCommentsResponseDto = new ArrayList();
        Iterator iterator = listComments.iterator();
        while (iterator.hasNext()) {
            Comments commentsNews;
            AllCommentsResponseDTO allCommentsResponseDto = new AllCommentsResponseDTO();
            commentsNews = (Comments) iterator.next();
            allCommentsResponseDto.setUserId(commentsNews.getUsers().getId());
            allCommentsResponseDto.setBody(commentsNews.getBody());
            allCommentsResponseDto.setDateCreated(commentsNews.getDateCreated().toString());
            listAllCommentsResponseDto.add(allCommentsResponseDto);
        }
        return listAllCommentsResponseDto;
    }

}
