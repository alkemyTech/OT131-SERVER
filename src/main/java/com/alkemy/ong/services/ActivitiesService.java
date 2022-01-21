package com.alkemy.ong.services;

import com.alkemy.ong.dto.ActivitiesDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ActivitiesService extends Service {

    ActivitiesDTO save(ActivitiesDTO dto);

    List<ActivitiesDTO> getAll();

    void delete(Long id);

    ActivitiesDTO update(Long id, ActivitiesDTO dto);

}
