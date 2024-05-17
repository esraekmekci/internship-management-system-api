package com.ceng316.internshipmanagementsystemapi.controllers;

import com.ceng316.internshipmanagementsystemapi.entities.Announcement;
import com.ceng316.internshipmanagementsystemapi.entities.Coordinator;
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

    @GetMapping("/token/{token}")
    public Coordinator getCoordinatorByToken(@PathVariable String token){
        return coordinatorService.getCoordinatorByToken(token);
    }
    @PutMapping("/coordinator/approveAnnouncement")
    public void approveAnnouncement(@RequestParam Long announcementId){
        coordinatorService.approveAnnouncement(announcementId);
    }

    @PutMapping("/coordinator/rejectAnnouncement")
    public void rejectAnnouncement(@RequestParam Long announcementId){
        coordinatorService.rejectAnnouncement(announcementId);
    }

    @PostMapping("/coordinator/uploadGuidelines")
    public String uploadGuidelines(@RequestParam MultipartFile file) {
        try {
            coordinatorService.uploadGuidelines(file);
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
