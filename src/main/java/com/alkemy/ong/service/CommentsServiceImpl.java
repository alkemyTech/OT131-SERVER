package com.alkemy.ong.service;

import com.alkemy.ong.dto.CommentsDTO;
import com.alkemy.ong.dto.CommentsResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentsServiceImpl implements CommentsService {

    @Override
    @Transactional
    public CommentsResponseDTO update(Long id, CommentsDTO dto, String authorization) {

        System.out.println(authorization);
        return null;
    }
}
