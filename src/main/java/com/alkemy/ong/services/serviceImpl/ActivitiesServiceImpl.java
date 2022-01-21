package com.alkemy.ong.services.serviceImpl;

import com.alkemy.ong.dto.ActivitiesDTO;
import com.alkemy.ong.entities.ActivitiesEntity;
import com.alkemy.ong.exceptions.ActivitiesException;
import com.alkemy.ong.repositories.ActivitiesRepository;
import com.alkemy.ong.services.ActivitiesService;
import com.alkemy.ong.utility.ActivitiesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.util.List;

@Service
public class ActivitiesServiceImpl implements ActivitiesService {

    @Autowired
    private ActivitiesMapper activitiesMapper;
    @Autowired
    private ActivitiesRepository activitiesRepository;

    public ActivitiesDTO save(ActivitiesDTO dto) {

        validation(dto);

        ActivitiesEntity entity = activitiesMapper.activityDTO2Entity(dto);
        ActivitiesEntity entitySaved = activitiesRepository.save(entity);

        return activitiesMapper.activityEntity2DTO(entitySaved);
    }

    public List<ActivitiesDTO> getAll() {

        List<ActivitiesEntity> entities = activitiesRepository.findAll();
        return activitiesMapper.activityEntityList2DTOList(entities);
    }

    public ActivitiesDTO update(Long id, ActivitiesDTO dto) {

        return null;
    }

    private void validation(ActivitiesDTO dto) throws ActivitiesException {

        if (dto.getName() == null || dto.getName().isEmpty())
            throw new ActivitiesException("Name cannot be null.");

        if (dto.getContent() == null || dto.getContent().isEmpty())
            throw new ActivitiesException("Content cannot be null.");

        if (dto.getImage() == null || dto.getImage().isEmpty())
            throw new ActivitiesException("The image cannot be null.");
    }
}
