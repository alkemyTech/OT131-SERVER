package com.alkemy.ong.service;

import com.alkemy.ong.dto.SlidesDTO;
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
import java.util.Optional;

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

        if (entity.getOrder() == null)
            entity.setOrder((int) (slidesRepository.count() + 1));

        Optional<Organizations> org = organizationsRepository.findById(entity.getOrganizationId());
        if (org.isEmpty() || !org.get().isActive())
            throw new ParamNotFoundException(BAD_ORG_ID);

        MultipartFile decodedImage = decodeBase64Image2MultipartFile(dto.getImage64());
        entity.setImageUrl(amazonS3Service.uploadFile(decodedImage).getFileUrl());

        Slides entityUpdated = slidesRepository.save(entity);
        return slidesMapper.entity2ResponseDTO(entity);
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
}
