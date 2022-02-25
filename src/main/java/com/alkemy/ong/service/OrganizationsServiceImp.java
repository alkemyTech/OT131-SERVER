package com.alkemy.ong.service;

import static com.alkemy.ong.util.Constants.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alkemy.ong.dto.OrganizationsAllDTO;
import com.alkemy.ong.dto.OrganizationsDTO;
import com.alkemy.ong.exception.ParamNotFoundException;
import com.alkemy.ong.model.Organizations;
import com.alkemy.ong.repository.OrganizationsRepository;
import com.alkemy.ong.repository.SlidesRepository;

@Service
public class OrganizationsServiceImp implements OrganizationsService {

    @Autowired
    private OrganizationsRepository organizationRepository;

    @Autowired
    private SlidesRepository slidesRepository;

    private ModelMapper mapper = new ModelMapper();

    @Override
    public List<OrganizationsDTO> listOrganizations() {
        List<OrganizationsDTO> listOrganization = new ArrayList<>();
        organizationRepository.findByIsActiveTrue().forEach(
                (o) -> listOrganization.add(mapper.map(o, OrganizationsDTO.class)));
        return listOrganization;
    }

    @Override
    public OrganizationsDTO publicDataOrganization(String name) {
        Optional<Organizations> result = organizationRepository.findByName(name);

        if (result.isPresent()) 
            return mapper.map(result.get(), OrganizationsDTO.class);
        else 
            throw new ParamNotFoundException(ENTITY_NOT_FOUND);
    }

    @Override
    @Transactional
    public OrganizationsAllDTO saveOrganization(OrganizationsAllDTO organizationDto) throws Exception {

        if (organizationRepository.findByName(organizationDto.getName()).isPresent()) {
            throw new ParamNotFoundException(NAME_EXIST);
        }

        Organizations entity = mapper.map(organizationDto, Organizations.class);

        entity.setIsActive(true);
        entity.setSlide(slidesRepository.findByOrganizationId(entity.getId()).get());

        Organizations entitySaves = organizationRepository.save(entity);

        return mapper.map(entitySaves, OrganizationsAllDTO.class);
    }

    @Override
    public OrganizationsAllDTO updateDataOrganization(OrganizationsAllDTO entity, long id) throws Exception {
        Optional<Organizations> org = organizationRepository.findById(id);
        if (!org.isPresent()) {
            throw new ParamNotFoundException(ENTITY_NOT_FOUND);
        }
        org.get().setName(entity.getName());
        org.get().setAddres(entity.getAddres());
        org.get().setAboutUsText(entity.getAboutUsText());
        org.get().setPhone(entity.getPhone());
        org.get().setEmail(entity.getEmail());
        org.get().setWelcomeText(entity.getWelcomeText());
        org.get().setImages(entity.getImages());
        org.get().setSlide(entity.getSlides());
        org.get().setFacebookUrl(entity.getFacebookUrl());
        org.get().setInstagramUrl(entity.getInstagramUrl());
        org.get().setLinkedinUrl(entity.getLinkedinUrl());
        organizationRepository.save(org.get());

        return mapper.map(org.get(), OrganizationsAllDTO.class);
    }

}
