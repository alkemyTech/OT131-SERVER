package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.SlidesDTO;
import com.alkemy.ong.dto.SlidesResponseDTO;
import com.alkemy.ong.dto.SlidesUpdateResponseDTO;
import com.alkemy.ong.model.Slides;
import com.alkemy.ong.repository.OrganizationsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SlidesMapper {

    @Autowired
    OrganizationsRepository organizationsRepository;

    public Slides dto2Entity(SlidesDTO dto) {
        return Slides.builder()
                .text(dto.getText())
                .order(dto.getOrder())
                .organization(organizationsRepository.getById(dto.getOrganizationId()))
                .isActive(Boolean.TRUE)
                .build();
    }

    public SlidesResponseDTO entity2ResponseDTO(Slides entity) {
        return SlidesResponseDTO.builder()
                .id(entity.getId())
                .imageUrl(entity.getImageUrl())
                .text(entity.getText())
                .order(entity.getOrder())
                .organizationId(entity.getOrganization().getId())
                .build();
    }

    public SlidesUpdateResponseDTO entity3ResponseDTO(Slides entity) {
        return SlidesUpdateResponseDTO.builder()
                .imageUrl(entity.getImageUrl())
                .text(entity.getText())
                .order(entity.getOrder())
                .organization(organizationsRepository.getById(entity.getOrganization().getId()))
                .dateModifed(null)
                .build();
    }
}
