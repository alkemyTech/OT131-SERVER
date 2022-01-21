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

    public ActivitiesEntity dto2Entity(ActivitiesDTO dto) {

        ActivitiesEntity entity = new ActivitiesEntity();

        entity.setName(dto.getName());
        entity.setContent(dto.getContent());
        entity.setImage(dto.getImage());

        return entity;
    }


    public ActivitiesDTO entity2DTO(ActivitiesEntity entity) {

        ActivitiesDTO dto = new ActivitiesDTO();

        dto.setId(entity.getId());
        dto.setName(entity.getName( ));
        dto.setContent(entity.getContent());
        dto.setImage(entity.getImage());
        dto.setCreationDate(entity.getCreationDate().toString());

        return dto;
    }

    public List<ActivitiesDTO> entityList2DTOList(List<ActivitiesEntity> entities) {

        List<ActivitiesDTO> dtos = new ArrayList();
        for (ActivitiesEntity entity : entities) {
            dtos.add(entity2DTO(entity));
        }
        return dtos;
    }

    public ActivitiesEntity updateDTO2Entity(ActivitiesEntity entity, ActivitiesDTO dto) {

        entity.setName(dto.getName());
        entity.setContent(dto.getContent());
        entity.setImage(dto.getImage());
        return entity;
    }

    private LocalDate string2LocalDate(String stringDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(stringDate, formatter);
        return date;
    }
}
