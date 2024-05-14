package com.ceng316.internshipmanagementsystemapi.services;

import com.ceng316.internshipmanagementsystemapi.controllers.S3Controller;
import com.ceng316.internshipmanagementsystemapi.entities.Announcement;
import com.ceng316.internshipmanagementsystemapi.entities.Coordinator;
import com.ceng316.internshipmanagementsystemapi.entities.Document;
import com.ceng316.internshipmanagementsystemapi.entities.Student;
import com.ceng316.internshipmanagementsystemapi.repos.CoordinatorRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CoordinatorService {
    CoordinatorRepository coordinatorRepo;
    AnnouncementService announcementService;
    S3Controller s3Controller;

    public CoordinatorService(CoordinatorRepository coordinatorRepo, S3Controller s3Controller, AnnouncementService announcementService) {
        this.coordinatorRepo = coordinatorRepo;
        this.s3Controller = s3Controller;
        this.announcementService = announcementService;
    }

    public void approveAnnouncement(Long announcementId){
        announcementService.getById(announcementId).setStatus("approved");
    }

    public void rejectAnnouncement(Long announcementId){
        announcementService.getById(announcementId).setStatus("rejected");
    }

    public void uploadGuidelines(MultipartFile file) {
        try {
            String fileName = "SummerPracticeGuidelines.docx";
            s3Controller.uploadFile(file, fileName);
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    void approveDocument(Document doc) {
    }

    void getPendingDocuments() {
    }

    void rejectDocument(Document doc) {
    }



}
