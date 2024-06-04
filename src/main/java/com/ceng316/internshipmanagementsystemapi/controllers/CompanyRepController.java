package com.ceng316.internshipmanagementsystemapi.controllers;

import com.ceng316.internshipmanagementsystemapi.entities.Announcement;
import com.ceng316.internshipmanagementsystemapi.entities.CompanyRep;

import com.ceng316.internshipmanagementsystemapi.entities.User;
import com.ceng316.internshipmanagementsystemapi.requests.AnnouncementRequest;
import com.ceng316.internshipmanagementsystemapi.responses.ApplicationForCompanyResponse;
import com.ceng316.internshipmanagementsystemapi.services.CompanyRepService;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyRepController {
    CompanyRepService companyRepService;

    public CompanyRepController(CompanyRepService companyRepService) {
        this.companyRepService = companyRepService;
    }

    @GetMapping
    public List<CompanyRep> getAllCompanyReps() {
        return companyRepService.getAllCompanyReps();
    }

    @GetMapping("/approved")
    public List<CompanyRep> getAllApprovedCompanyReps() {
        return companyRepService.getAllApprovedCompanyReps();
    }

    @GetMapping("/pending")
    public List<CompanyRep> getAllPendingCompanyReps() {return companyRepService.getAllPendingCompanyReps(); }


    @GetMapping("/token/{token}")
    public User getCompanyRepByToken(@PathVariable String token){
        return companyRepService.getCompanyRepByToken(token);
    }

    @GetMapping("/{id}")
    public CompanyRep getCompanyRep(@PathVariable Long id) {
        return companyRepService.getCompanyRep(id);
    }

    @DeleteMapping("/{email}")
    public void deleteCompanyRep(@PathVariable String email) {
        companyRepService.deleteCompanyRep(email);
    }

    @GetMapping("/name/{name}")
    public CompanyRep getCompanyByName(@PathVariable String name) {
        return companyRepService.getCompanyByName(name);
    }

    @GetMapping("/{companyId}/applicants")
    public List<ApplicationForCompanyResponse> getAppliedStudents(@PathVariable Long companyId) {
        return companyRepService.getAppliedStudents(companyId);
    }

    @GetMapping("/{companyId}/interns")
    public List<ApplicationForCompanyResponse> getInterns(@PathVariable Long companyId) {
        return companyRepService.getInterns(companyId);
    }

    @PutMapping("/{companyId}/approveApplicationLetter")
    public void approveApplicationLetter(@PathVariable Long companyId, @RequestParam Long applicationId){
        companyRepService.approveApplicationLetter(companyId, applicationId);
    }

    @PutMapping("/{companyId}/rejectApplicationLetter")
    public void rejectApplicationLetter(@PathVariable Long companyId, @RequestParam Long applicationId){
        companyRepService.rejectApplicationLetter(companyId, applicationId);
    }

    @PostMapping("/{companyId}/makeAnnouncement")
    public void makeAnnouncement(@PathVariable Long companyId, @RequestBody AnnouncementRequest announcement){
        companyRepService.makeAnnouncement(companyId, announcement);
    }

    @GetMapping("/{companyId}/announcements")
    public List<Announcement> getAnnouncements(@PathVariable Long companyId){
        return companyRepService.getAnnouncements(companyId);
    }

    @DeleteMapping("/{companyId}/deleteAnnouncement")
    public void deleteAnnouncement(@PathVariable Long companyId, @RequestParam Long announcementId){
        companyRepService.deleteAnnouncement(companyId, announcementId);
    }

    @GetMapping("/{companyId}/downloadApplicationLetter")
    public ResponseEntity<Resource> downloadApplicationLetter(@PathVariable Long companyId,
                                                              @RequestParam Long studentId) {
        return companyRepService.downloadApplicationLetter(companyId, studentId);
    }
    @GetMapping("/{companyId}/downloadApplicationForm")
    public ResponseEntity<Resource> downloadApplicationForm(@PathVariable Long companyId,
                                                              @RequestParam Long studentId) {
        return companyRepService.downloadApplicationForm(companyId, studentId);
    }

    @PostMapping("/{companyId}/uploadApplicationForm")
    public String downloadApplicationForm(@PathVariable Long companyId,
                                                            @RequestParam Long studentId,
                                                            @RequestParam MultipartFile file) throws Exception {
        try {
            companyRepService.uploadApplicationForm(file, companyId, studentId);
            return "File uploaded";
        }
        catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }
}
