package com.alkemy.ong.service;

import com.alkemy.ong.dto.CommentsDTO;
import com.alkemy.ong.dto.CommentsResponseDTO;

public interface CommentsService {

    CommentsResponseDTO update(Long id, CommentsDTO dto, String authorization);
}
