package com.alkemy.ong.service;

import com.alkemy.ong.dto.ActivitiesDTO;
import com.alkemy.ong.model.Activities;
import com.alkemy.ong.exception.ParamNotFoundException;
import com.alkemy.ong.repository.ActivitiesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ActivitiesServiceImp implements ActivitiesService {

    @Autowired
    private ActivitiesRepository activitiesRepository;
    private ModelMapper mapper = new ModelMapper();


    @Transactional
    public ActivitiesDTO save(ActivitiesDTO dto) {

        Activities entity = mapper.map(dto, Activities.class);
        entity.setCreatedDate(LocalDate.now());
        Activities entitySaved = activitiesRepository.save(entity);

        return mapper.map(entitySaved, ActivitiesDTO.class);
    }

    @Transactional(readOnly = true)
    public List<ActivitiesDTO> getAllActives() {

        List<ActivitiesDTO> dtos = new ArrayList();
        for (Activities entity : activitiesRepository.findByIsActiveTrue()) {
            dtos.add(mapper.map(entity, ActivitiesDTO.class));
        }
        return dtos;
    }

    @Transactional
    public ActivitiesDTO update(Long id, ActivitiesDTO dto) {

        Optional<Activities> result = activitiesRepository.findById(id);
        if (result.isPresent()){
            Activities entity = mapper.map(dto, Activities.class);
            entity.setId(id);
            entity.setCreatedDate(result.get().getCreatedDate());
            entity.setModifiedDate(LocalDate.now());
            Activities entityUpdated = activitiesRepository.save(entity);

            return mapper.map(entityUpdated, ActivitiesDTO.class);
        } else {
            throw new ParamNotFoundException("Requested activity was not found.");
        }
    }

    @Transactional
    public void delete(Long id) {

        Optional<Activities> result = activitiesRepository.findById(id);
        if (result.isPresent()) {
            Activities entity = result.get();
            entity.setActive(false);
            entity.setDeletedDate(LocalDate.now());
            activitiesRepository.save(entity);
        } else {
            throw new ParamNotFoundException("Requested activity was not found.");
        }
    }
}
