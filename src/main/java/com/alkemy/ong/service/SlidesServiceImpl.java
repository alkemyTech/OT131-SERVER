package com.alkemy.ong.service;

import com.alkemy.ong.dto.SlidesUpdateDto;
import com.alkemy.ong.dto.SlidesUpdateResponseDTO;
import com.alkemy.ong.dto.SlidesDTO;

import com.alkemy.ong.dto.SlidesListDto;
import com.alkemy.ong.dto.SlidesResponseDTO;
import com.alkemy.ong.exception.ParamNotFoundException;
import com.alkemy.ong.mapper.SlidesMapper;
import com.alkemy.ong.model.Organizations;
import com.alkemy.ong.model.Slides;
import com.alkemy.ong.repository.OrganizationsRepository;
import com.alkemy.ong.repository.SlidesRepository;
import com.alkemy.ong.util.BASE64DecodedMultipartFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.alkemy.ong.util.Constants.BAD_ORG_ID;

@Service
public class SlidesServiceImpl implements SlidesService {

    @Autowired
    private SlidesRepository slidesRepository;
    @Autowired
    private OrganizationsRepository organizationsRepository;
    @Autowired
    private SlidesMapper slidesMapper;
    @Autowired
    private AmazonS3Service amazonS3Service;

    @Override
    @Transactional
    public SlidesResponseDTO save(SlidesDTO dto) {

        Slides entity = slidesMapper.dto2Entity(dto);

        if (entity.getOrder() == null) {
            entity.setOrder((int) (slidesRepository.count() + 1));
        }

        Optional<Organizations> org = organizationsRepository.findById(entity.getOrganization().getId());
        if (org.isEmpty() || !org.get().getIsActive()) {
            throw new ParamNotFoundException(BAD_ORG_ID);
        }

        MultipartFile decodedImage = decodeBase64Image2MultipartFile(dto.getImage64());
        entity.setImageUrl(amazonS3Service.uploadFile(decodedImage).getFileUrl());

        Slides entityUpdated = slidesRepository.save(entity);
        return slidesMapper.entity2ResponseDTO(entityUpdated);
    }

    private MultipartFile decodeBase64Image2MultipartFile(String image64) {

        String[] baseString = image64.split(",");

        byte[] byteArray = new byte[0];
        byteArray = Base64.getDecoder().decode(baseString[1]);

        for (int i = 0; i < byteArray.length; ++i) {
            if (byteArray[i] < 0) {
                byteArray[i] += 256;
            }
        }
        return new BASE64DecodedMultipartFile(byteArray, baseString[0]);

    }

    @Override
    public List<SlidesListDto> getAll() {
        return slidesRepository.findByOrderByOrderAsc()
                .stream().map(slide -> new SlidesListDto(
                slide.getImageUrl(),
                slide.getOrder()))
                .collect(Collectors.toList());
    }

    @Override
    public SlidesUpdateResponseDTO update(Long id, SlidesUpdateDto dto) {
        Optional<Slides> result = slidesRepository.findById(id);
        if (result.isEmpty() || !result.get().isActive()) {
            return null;
        }

        Slides entity = result.get();
        entity.setDateModified(dto.getDateModifed());
        entity.setImageUrl(dto.getImageUrl());
        entity.setOrder(dto.getOrder());
        entity.setText(dto.getText());
        entity.setOrganization(organizationsRepository.getById(dto.getOrganization().getId()));

        Slides entityUpdate = slidesRepository.save(entity);

        return slidesMapper.entity3ResponseDTO(entityUpdate);
    }

    public void delete(Long id) throws Exception {
        Optional<Slides> slide = slidesRepository.findById(id);
        if (slide.isPresent()) {
            slide.get().setActive(false);
            slidesRepository.save(slide.get());
        } else {
            throw new Exception("slide not found");
        }
    }

    public SlidesResponseDTO findById(Long id) {
        Optional<Slides> slides = slidesRepository.findById(id);
        if (slides.isPresent()) {
            SlidesResponseDTO slidesResponseDTO = slidesMapper.entity2ResponseDTO(slides.get());
            return slidesResponseDTO;
        } else {
            throw new ParamNotFoundException("Slides with id=" + id + " not found.");
        }
    }
}
