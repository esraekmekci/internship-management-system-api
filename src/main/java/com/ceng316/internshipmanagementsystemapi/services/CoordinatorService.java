package com.ceng316.internshipmanagementsystemapi.services;

import com.ceng316.internshipmanagementsystemapi.controllers.S3Controller;
import com.ceng316.internshipmanagementsystemapi.entities.Coordinator;
import com.ceng316.internshipmanagementsystemapi.repos.AnnouncementRepository;
import com.ceng316.internshipmanagementsystemapi.repos.CompanyRepRepository;
import com.ceng316.internshipmanagementsystemapi.repos.CoordinatorRepository;
import com.ceng316.internshipmanagementsystemapi.security.JwtTokenProvider;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CoordinatorService {
    private final AnnouncementRepository announcementRepository;
    private final CompanyRepRepository companyRepRepository;
    CoordinatorRepository coordinatorRepo;
    AnnouncementService announcementService;
    CompanyRepService companyRepService;
    S3Controller s3Controller;
    JwtTokenProvider jwtTokenProvider;

    public CoordinatorService(CoordinatorRepository coordinatorRepo, CompanyRepService companyRepService, S3Controller s3Controller, AnnouncementService announcementService, JwtTokenProvider jwtTokenProvider, AnnouncementRepository announcementRepository, CompanyRepRepository companyRepRepository) {
        this.coordinatorRepo = coordinatorRepo;
        this.s3Controller = s3Controller;
        this.announcementService = announcementService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.announcementRepository = announcementRepository;
        this.companyRepService = companyRepService;
        this.companyRepRepository = companyRepRepository;
    }

    public Coordinator getCoordinatorByToken(String token){
        Long userId = jwtTokenProvider.getUserIdFromJwt(token);
        return coordinatorRepo.findById(userId).orElse(null);
    }

    public void approveCompanyAccount(Long companyId){
        companyRepService.getCompanyRep(companyId).setAccountStatus("APPROVED");
        companyRepRepository.save(companyRepService.getCompanyRep(companyId));
    }

    public void rejectCompanyAccount(Long companyId){
        companyRepService.getCompanyRep(companyId).setAccountStatus("REJECTED");
        companyRepRepository.save(companyRepService.getCompanyRep(companyId));
    }

    public void approveAnnouncement(Long announcementId){
        announcementService.getById(announcementId).setStatus("approved");
        announcementRepository.save(announcementService.getById(announcementId));
    }

    public void rejectAnnouncement(Long announcementId){
        announcementService.getById(announcementId).setStatus("rejected");
        announcementRepository.save(announcementService.getById(announcementId));
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

}
