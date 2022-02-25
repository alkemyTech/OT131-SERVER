package com.alkemy.ong.service;

import com.alkemy.ong.dto.CommentsDTO;
import com.alkemy.ong.dto.CommentsResponseDTO;
import com.alkemy.ong.dto.AllCommentsResponseDTO;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;

public interface CommentsService {

    CommentsResponseDTO create(Long news_id, String body, String authorization) throws AccessDeniedException;

    CommentsResponseDTO update(Long id, CommentsDTO dto, String authorization) throws AccessDeniedException;

    List<AllCommentsResponseDTO> getNewAndAllComment(Long id);

    Optional<List<String>> listComments() throws AccessDeniedException;

    void delete(Long id, String authorization) throws AccessDeniedException;
}
