package com.alkemy.ong.service;

import com.alkemy.ong.dto.AWSResponseDTO;
import org.springframework.web.multipart.MultipartFile;

public interface AmazonS3Service {

    AWSResponseDTO uploadFile(MultipartFile multipartFile);
}
