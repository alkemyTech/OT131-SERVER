package com.alkemy.ong.services.serviceImpl;

import com.alkemy.ong.dto.ActivitiesDTO;
import com.alkemy.ong.entities.ActivitiesEntity;
import com.alkemy.ong.utility.EntityException;
import com.alkemy.ong.repositories.ActivitiesRepository;
import com.alkemy.ong.services.ActivitiesService;
import com.alkemy.ong.utility.ActivitiesConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ActivitiesServiceImp implements ActivitiesService {

    // ATTRIBUTES - Mapper and Repository
    @Autowired
    private ActivitiesConverter activitiesConverter;
    @Autowired
    private ActivitiesRepository activitiesRepository;

    /**
     * Validates the received DTO and saves it as an Entity in activities table
     * @param dto
     * @return The DTO already saved
     */
    @Transactional
    public ActivitiesDTO save(ActivitiesDTO dto) {

        ActivitiesEntity entity = activitiesConverter.dto2Entity(dto);
        entity.setCreationDate(LocalDate.now());
        ActivitiesEntity entitySaved = activitiesRepository.save(entity);

        return activitiesConverter.entity2DTO(entitySaved);
    }

    /**
     * Returns a List of ActivitiesDTO with all Entities saved in activities table
     * @return
     */
    @Transactional(readOnly = true)
    public List<ActivitiesDTO> getAll() {

        List<ActivitiesEntity> entities = activitiesRepository.findAll();
        return activitiesConverter.entityList2DTOList(entities);
    }

    /**
     * Updates the attributes of the ActivityEntity related to the received id
     * with the new attributes in ActivitiesDTO
     * @param id
     * @param dto
     * @return
     */
    @Transactional
    public ActivitiesDTO update(Long id, ActivitiesDTO dto) {

        Optional<ActivitiesEntity> result = activitiesRepository.findById(id);
        if (result.isPresent()){
            ActivitiesEntity entity = activitiesConverter.updateDTO2Entity(result.get(), dto);
            ActivitiesEntity entityUpdated = activitiesRepository.save(entity);
            ActivitiesDTO dtoUpdated = activitiesConverter.entity2DTO(entityUpdated);

            return dtoUpdated;
        } else {
            throw new EntityException("Requested activity was not found.");
        }
    }
}
