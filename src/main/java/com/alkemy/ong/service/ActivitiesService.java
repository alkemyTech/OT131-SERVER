package com.alkemy.ong.service;

import com.alkemy.ong.dto.ActivitiesDTO;
import com.alkemy.ong.exception.ParamNotFoundException;
import com.alkemy.ong.model.Activities;

import java.util.List;


public interface ActivitiesService {

    ActivitiesDTO save(ActivitiesDTO dto) throws ParamNotFoundException;

    List<ActivitiesDTO> getAllActives();

    ActivitiesDTO update(Long id, ActivitiesDTO dto) throws ParamNotFoundException;

    void delete(Long id);

}
