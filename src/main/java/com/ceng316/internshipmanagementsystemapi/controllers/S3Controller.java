package com.ceng316.internshipmanagementsystemapi.controllers;

import com.amazonaws.services.s3.model.S3Object;
import com.ceng316.internshipmanagementsystemapi.services.S3Service;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.MimeType;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
public class S3Controller {

    private final S3Service s3Service;

    public S3Controller(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @GetMapping
    public String health() {
        return "UP";
    }

    @PostMapping(path = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam String fileName) throws IOException {
        try {
            if (fileName == null || fileName.isBlank()) {
                s3Service.uploadFile(file.getOriginalFilename(), file);
            }
            else {
                s3Service.uploadFile(fileName, file);
            }
            return "File uploaded";
        }
        catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
        try {
            S3Object s3Object = s3Service.getFile(fileName);
            InputStreamResource resource = new InputStreamResource(s3Object.getObjectContent());
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(s3Object.getObjectMetadata().getContentType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                    .body(resource);
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/view/{fileName}")
    public ResponseEntity<InputStreamResource> viewFile(@PathVariable String fileName) {
        S3Object s3Object = s3Service.getFile(fileName);
        InputStreamResource resource = new InputStreamResource(s3Object.getObjectContent());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(s3Object.getObjectMetadata().getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\""+fileName+"\"")
                .body(resource);
    }

    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName) {
        try {
            s3Service.deleteFile(fileName);
            return ResponseEntity.ok("File deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}
