package com.alkemy.ong.services.serviceImpl;

import com.alkemy.ong.dto.ActivitiesDTO;
import com.alkemy.ong.entities.ActivitiesEntity;
import com.alkemy.ong.utility.EntityException;
import com.alkemy.ong.repositories.ActivitiesRepository;
import com.alkemy.ong.services.ActivitiesService;
import com.alkemy.ong.utility.ActivitiesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ActivitiesServiceImpl implements ActivitiesService {

    // ATTRIBUTES - Mapper and Repository
    @Autowired
    private ActivitiesMapper activitiesMapper;
    @Autowired
    private ActivitiesRepository activitiesRepository;

    /**
     * Validates the received DTO and saves it as an Entity in DB
     * @param dto
     * @return The DTO already saved
     */
    public ActivitiesDTO save(ActivitiesDTO dto) {

        validation(dto);

        ActivitiesEntity entity = activitiesMapper.dto2Entity(dto);
        entity.setCreationDate(LocalDate.now());
        ActivitiesEntity entitySaved = activitiesRepository.save(entity);

        return activitiesMapper.entity2DTO(entitySaved);
    }

    public List<ActivitiesDTO> getAll() {

        List<ActivitiesEntity> entities = activitiesRepository.findAll();
        return activitiesMapper.entityList2DTOList(entities);
    }

    public ActivitiesDTO update(Long id, ActivitiesDTO dto) {

        validation(dto);

        Optional<ActivitiesEntity> result = activitiesRepository.findById(id);
        if (result.isPresent()){
            ActivitiesEntity entity = activitiesMapper.updateDTO2Entity(result.get(), dto);
            ActivitiesEntity entityUpdated = activitiesRepository.save(entity);
            ActivitiesDTO dtoUpdated = activitiesMapper.entity2DTO(entityUpdated);

            return dtoUpdated;
        } else {
            throw new EntityException("Requested activity was not found.");
        }
    }

    private void validation(ActivitiesDTO dto) throws EntityException {

        if (dto.getName() == null || dto.getName().isEmpty())
            throw new EntityException("Name cannot be null.");

        if (dto.getContent() == null || dto.getContent().isEmpty())
            throw new EntityException("Content cannot be null.");

        if (dto.getImage() == null || dto.getImage().isEmpty())
            throw new EntityException("The image cannot be null.");
    }
}
