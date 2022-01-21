package com.alkemy.ong.utility;

import com.alkemy.ong.dto.ActivitiesDTO;
import com.alkemy.ong.entities.ActivitiesEntity;

import java.util.ArrayList;
import java.util.List;

public class ActivitiesMapper {

    public ActivitiesEntity activityDTO2Entity(ActivitiesDTO dto) {

        ActivitiesEntity entity = new ActivitiesEntity();

        entity.setName(dto.getName());
        entity.setContent(dto.getContent());
        entity.setImage(dto.getImage());

        return entity;
    }


    public ActivitiesDTO activityEntity2DTO(ActivitiesEntity entity) {

        ActivitiesDTO dto = new ActivitiesDTO();

        dto.setName(entity.getName());
        dto.setContent(entity.getContent());
        dto.setImage(entity.getImage());
        dto.setCreationDate(entity.getCreationDate().toString());

        return dto;
    }

    public List<ActivitiesDTO> activityEntityList2DTOList(List<ActivitiesEntity> entities) {

        List<ActivitiesDTO> dtos = new ArrayList();
        for (ActivitiesEntity entity : entities) {
            dtos.add(activityEntity2DTO(entity));
        }
        return dtos;
    }
}
