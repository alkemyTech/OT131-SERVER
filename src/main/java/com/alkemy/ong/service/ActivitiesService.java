package com.alkemy.ong.service;

import com.alkemy.ong.dto.ActivitiesDTO;
import com.alkemy.ong.util.EntityException;

import java.util.List;


public interface ActivitiesService {

    ActivitiesDTO save(ActivitiesDTO dto) throws EntityException;

    List<ActivitiesDTO> getAll();

    ActivitiesDTO update(Long id, ActivitiesDTO dto) throws EntityException;
}
