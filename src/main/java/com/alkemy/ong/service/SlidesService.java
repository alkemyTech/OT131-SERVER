package com.alkemy.ong.service;

import java.util.List;

import com.alkemy.ong.dto.SlidesDTO;
import com.alkemy.ong.dto.SlidesListDto;
import com.alkemy.ong.dto.SlidesResponseDTO;

public interface SlidesService {

    SlidesResponseDTO save(SlidesDTO dto);
    List<SlidesListDto> getAll();
}
