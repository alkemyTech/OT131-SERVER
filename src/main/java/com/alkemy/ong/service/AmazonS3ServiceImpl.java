package com.alkemy.ong.service;

import com.alkemy.ong.dto.AWSResponseDTO;
import com.alkemy.ong.exception.AmazonS3IOException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import static com.alkemy.ong.util.Constants.ERR_AWS_NOT_FOUND;
import static com.alkemy.ong.util.Constants.ERR_AWS_NOT_SAVED;

@Service
public class AmazonS3ServiceImpl implements AmazonS3Service {

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${aws.s3.bucket_name}")
    private String bucketName;
    @Value("${aws.s3.endpoint_url}")
    private String endpointUrl;

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    private String generateFileName(MultipartFile multiPart) {
        return new Date().getTime() + "-" + multiPart.getOriginalFilename().replaceAll(" ", "_");
    }

    private void uploadFileToS3Bucket(String fileName, File file) {
        amazonS3.putObject(new PutObjectRequest(bucketName, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    @Override
    public AWSResponseDTO uploadFile(MultipartFile multipartFile) {
        String fileUrl = "";

        try {
            File file = convertMultiPartToFile(multipartFile);

            String fileName = this.generateFileName(multipartFile);

            fileUrl = endpointUrl + fileName;
            this.uploadFileToS3Bucket(fileName, file);

            file.delete();
        } catch (FileNotFoundException e) {
            throw new AmazonS3IOException(ERR_AWS_NOT_FOUND);
        } catch (IOException e) {
            throw new AmazonS3IOException(ERR_AWS_NOT_SAVED);
        }
        AWSResponseDTO response = new AWSResponseDTO(fileUrl);
        return response;
    }
}
