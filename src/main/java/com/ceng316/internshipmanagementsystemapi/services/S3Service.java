package com.ceng316.internshipmanagementsystemapi.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Log4j2
public class S3Service {

    private final AmazonS3 s3client;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    public S3Service(AmazonS3 s3client) {
        this.s3client = s3client;
    }

    public void uploadFile(String keyName, MultipartFile file) throws IOException {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());
        PutObjectResult putObjectResult = s3client.putObject(bucketName, keyName, file.getInputStream(), metadata);
    }

    public void deleteFile(String fileName) {
        s3client.deleteObject(new DeleteObjectRequest(bucketName, fileName));
    }


    public S3Object getFile(String keyName) {
        return s3client.getObject(bucketName, keyName);
    }

    public void deleteFile(String keyName) {
        s3client.deleteObject(bucketName, keyName);
    }
    
}
