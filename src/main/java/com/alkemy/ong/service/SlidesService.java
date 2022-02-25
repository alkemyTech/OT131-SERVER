package com.alkemy.ong.service;

import java.util.List;

import com.alkemy.ong.dto.SlidesUpdateDto;
import com.alkemy.ong.dto.SlidesUpdateResponseDTO;
import com.alkemy.ong.dto.SlidesDTO;
import com.alkemy.ong.dto.SlidesListDto;
import com.alkemy.ong.dto.SlidesResponseDTO;

public interface SlidesService {

    SlidesResponseDTO save(SlidesDTO dto);

    List<SlidesListDto> getAll();

    public SlidesUpdateResponseDTO update(Long id, SlidesUpdateDto dto);

    public void delete(Long id) throws Exception;

    public SlidesResponseDTO findById(Long id);
}
