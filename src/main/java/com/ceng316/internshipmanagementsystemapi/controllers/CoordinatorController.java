package com.ceng316.internshipmanagementsystemapi.controllers;

import com.ceng316.internshipmanagementsystemapi.entities.Announcement;
import com.ceng316.internshipmanagementsystemapi.entities.Document;
import com.ceng316.internshipmanagementsystemapi.entities.Student;
import com.ceng316.internshipmanagementsystemapi.services.CoordinatorService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/coordinator")
public class CoordinatorController {
    CoordinatorService coordinatorService;

    public CoordinatorController(CoordinatorService coordinatorService) {this.coordinatorService = coordinatorService;}

    public void approveAnnouncement(Announcement announcement){
        coordinatorService.approveAnnouncement(announcement);
    }

    public void rejectAnnouncement(Announcement announcement){
        coordinatorService.rejectAnnouncement(announcement);
    }

    @PostMapping("/coordinator/uploadGuidelines")
    public String uploadGuidelines(@RequestParam MultipartFile file,
                                          @PathVariable Long documentId) {
        try {
            coordinatorService.uploadGuidelines(file, documentId);
            return "File uploaded";
        }
        catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    void approveDocument(Document doc) {
    }

    void rejectDocument(Document doc) {
    }

    void getPendingDocuments() {
    }

}
