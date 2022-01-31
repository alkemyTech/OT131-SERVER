package com.alkemy.ong.controller;

import com.alkemy.ong.dto.AWSResponseDTO;
import com.alkemy.ong.service.AmazonS3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/storage/")
public class AmazonS3Controller {

    @Autowired
    private AmazonS3Service amazonS3Service;

    @PostMapping("/uploadFile")
    public ResponseEntity uploadFile(@RequestPart(value = "file") MultipartFile file) {
        AWSResponseDTO response = amazonS3Service.uploadFile(file);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
