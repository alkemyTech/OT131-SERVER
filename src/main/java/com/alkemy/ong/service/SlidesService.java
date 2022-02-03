package com.alkemy.ong.service;

import com.alkemy.ong.dto.SlidesDTO;
import com.alkemy.ong.dto.SlidesResponseDTO;

import java.io.IOException;

public interface SlidesService {

    SlidesResponseDTO save(SlidesDTO dto);
}
