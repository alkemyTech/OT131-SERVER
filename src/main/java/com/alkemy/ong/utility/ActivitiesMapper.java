package com.alkemy.ong.utility;

import com.alkemy.ong.dto.ActivitiesDTO;
import com.alkemy.ong.entities.ActivitiesEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class ActivitiesMapper {

    /**
     * Converts an ActivityDTO into an ActivityEntity
     * @param dto
     * @return entity
     */
    public ActivitiesEntity dto2Entity(ActivitiesDTO dto) {

        ActivitiesEntity entity = new ActivitiesEntity();

        entity.setName(dto.getName());
        entity.setContent(dto.getContent());
        entity.setImage(dto.getImage());

        return entity;
    }

    /**
     * Converts an ActivityEntity into an ActivityDTO
     * @param entity
     * @return dto
     */
    public ActivitiesDTO entity2DTO(ActivitiesEntity entity) {

        ActivitiesDTO dto = new ActivitiesDTO();

        dto.setId(entity.getId());
        dto.setName(entity.getName( ));
        dto.setContent(entity.getContent());
        dto.setImage(entity.getImage());
        dto.setCreationDate(entity.getCreationDate().toString());

        return dto;
    }

    /**
     * Converts a list of ActivitiesEntity into a List of ActivitiesDTO
     * @param entities
     * @return
     */
    public List<ActivitiesDTO> entityList2DTOList(List<ActivitiesEntity> entities) {

        List<ActivitiesDTO> dtos = new ArrayList();
        for (ActivitiesEntity entity : entities) {
            dtos.add(entity2DTO(entity));
        }
        return dtos;
    }

    /**
     * Updates an ActivityEntity with the attributes of the received ActivitiesDTO
     * @param entity
     * @param dto
     * @return
     */
    public ActivitiesEntity updateDTO2Entity(ActivitiesEntity entity, ActivitiesDTO dto) {

        entity.setName(dto.getName());
        entity.setContent(dto.getContent());
        entity.setImage(dto.getImage());
        return entity;
    }
}
