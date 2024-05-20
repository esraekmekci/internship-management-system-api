package com.ceng316.internshipmanagementsystemapi.controllers;

import com.ceng316.internshipmanagementsystemapi.entities.Application;
import com.ceng316.internshipmanagementsystemapi.entities.Coordinator;
import com.ceng316.internshipmanagementsystemapi.responses.ApplicationForCompanyResponse;
import com.ceng316.internshipmanagementsystemapi.responses.ApplicationForCoordinatorResponse;
import com.ceng316.internshipmanagementsystemapi.services.CoordinatorService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;


@RestController
@RequestMapping("/coordinator")
public class CoordinatorController {
    CoordinatorService coordinatorService;

    public CoordinatorController(CoordinatorService coordinatorService) {this.coordinatorService = coordinatorService;}

    @GetMapping("/token/{token}")
    public Coordinator getCoordinatorByToken(@PathVariable String token){
        return coordinatorService.getCoordinatorByToken(token);
    }

    @PutMapping("/approveCompanyAccount")
    public void approveCompanyAccount(@RequestParam Long companyId){
        coordinatorService.approveCompanyAccount(companyId);
    }

    @PutMapping("/rejectCompanyAccount")
    public void rejectCompanyAccount(@RequestParam Long companyId){
        coordinatorService.rejectCompanyAccount(companyId);
    }

    @PutMapping("/approveAnnouncement")
    public void approveAnnouncement(@RequestParam Long announcementId){
        coordinatorService.approveAnnouncement(announcementId);
    }

    @PutMapping("/rejectAnnouncement")
    public void rejectAnnouncement(@RequestParam Long announcementId){
        coordinatorService.rejectAnnouncement(announcementId);
    }

    @PostMapping("/uploadGuidelines")
    public String uploadGuidelines(@RequestParam MultipartFile file) {
        try {
            coordinatorService.uploadGuidelines(file);
            return "File uploaded";
        }
        catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @GetMapping("/studentApplications")
    public List<ApplicationForCoordinatorResponse> getAppliedStudents() {
        return coordinatorService.getStudents();
    }

    @PutMapping("/approveApplicationForm")
    public void approveApplicationLetter(@RequestBody Long applicationId){
        coordinatorService.approveApplicationForm(applicationId);
    }

    @PutMapping("/rejectApplicationForm")
    public void rejectApplicationLetter(@RequestParam Long applicationId){
        coordinatorService.rejectApplicationForm(applicationId);
    }


}
