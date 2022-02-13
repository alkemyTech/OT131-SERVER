package com.alkemy.ong.service;

import com.alkemy.ong.dto.CommentsDTO;
import com.alkemy.ong.dto.CommentsResponseDTO;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;

public interface CommentsService {

    CommentsResponseDTO update(Long id, CommentsDTO dto, String authorization) throws AccessDeniedException;

    Optional<List<String>> listComments() throws AccessDeniedException;
}
