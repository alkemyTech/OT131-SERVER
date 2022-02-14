package com.alkemy.ong.service;

import com.alkemy.ong.dto.CommentsDTO;
import com.alkemy.ong.dto.CommentsResponseDTO;
import com.alkemy.ong.dto.AllCommentsResponseDTO;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface CommentsService {

    CommentsResponseDTO update(Long id, CommentsDTO dto, String authorization) throws AccessDeniedException;
    
    List<AllCommentsResponseDTO> getNewAndAllComment(Long id);
}
