package com.ceng316.internshipmanagementsystemapi.services;

import com.ceng316.internshipmanagementsystemapi.controllers.S3Controller;
import com.ceng316.internshipmanagementsystemapi.entities.Application;
import com.ceng316.internshipmanagementsystemapi.entities.Coordinator;
import com.ceng316.internshipmanagementsystemapi.repos.AnnouncementRepository;
import com.ceng316.internshipmanagementsystemapi.repos.ApplicationRepository;
import com.ceng316.internshipmanagementsystemapi.repos.CompanyRepRepository;
import com.ceng316.internshipmanagementsystemapi.repos.CoordinatorRepository;
import com.ceng316.internshipmanagementsystemapi.responses.ApplicationForCoordinatorResponse;
import com.ceng316.internshipmanagementsystemapi.security.JwtTokenProvider;
import com.ceng316.internshipmanagementsystemapi.entities.SGKFile;
import com.ceng316.internshipmanagementsystemapi.repos.SGKRepository;
import com.ceng316.internshipmanagementsystemapi.services.S3Service;


import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CoordinatorService {
    private final AnnouncementRepository announcementRepository;
    private final CompanyRepRepository companyRepRepository;
    private final ApplicationRepository applicationRepository;
    CoordinatorRepository coordinatorRepo;
    SGKRepository sgkRepo;
    AnnouncementService announcementService;
    CompanyRepService companyRepService;
    S3Service s3Service;
    S3Controller s3Controller;
    JwtTokenProvider jwtTokenProvider;

    public CoordinatorService(CoordinatorRepository coordinatorRepo, ApplicationRepository applicationRepository, SGKRepository sgkRepo, CompanyRepService companyRepService,
                              S3Controller s3Controller, S3Service s3Service, AnnouncementService announcementService, JwtTokenProvider jwtTokenProvider, AnnouncementRepository announcementRepository, CompanyRepRepository companyRepRepository) {
        this.coordinatorRepo = coordinatorRepo;
        this.s3Controller = s3Controller;
        this.announcementService = announcementService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.announcementRepository = announcementRepository;
        this.companyRepService = companyRepService;
        this.companyRepRepository = companyRepRepository;
        this.applicationRepository = applicationRepository;
        this.sgkRepo = sgkRepo;
        this.s3Service = s3Service;
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


    public boolean checkGuideline() {
        return s3Service.checkFileExists("SummerPracticeGuidelines.pdf");
    }


    public ResponseEntity<Resource> downloadGuideline() {
        return s3Controller.downloadFile("SummerPracticeGuidelines.pdf");
    }


    public void uploadGuidelines(MultipartFile file) {
        try {
            s3Controller.uploadFile(file, "SummerPracticeGuidelines.pdf");
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void deleteGuidelines() {
        try {
            s3Controller.deleteFile("SummerPracticeGuidelines.pdf");
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public List<ApplicationForCoordinatorResponse> getStudents() {
        List<Application> applications =  applicationRepository.findAll();
        List<ApplicationForCoordinatorResponse> applicationResponses = new ArrayList<>();
        for (Application application : applications) {

            if(
                    application.getApplicationStatus().equals("Application Form Sent to Company") ||
                    application.getApplicationStatus().equals("Application Form Sent to Coordinator") ||
                    application.getApplicationStatus().equals("Application Form Approved")||
                    application.getApplicationStatus().equals("Application Form Rejected")||
                    application.getApplicationStatus().equals("SGK Document Pending")||
                    application.getApplicationStatus().equals("SGK Document Uploaded")
            ) {
                System.out.println(application.getApplicationStatus());
                ApplicationForCoordinatorResponse applicationResponse = new ApplicationForCoordinatorResponse();

                applicationResponse.setStudentName(application.getStudent().getName());
                applicationResponse.setStudentId(application.getStudent().getStudentID());
                applicationResponse.setApplicationStatus(application.getApplicationStatus());
                applicationResponse.setApplicationId(application.getId());
                applicationResponse.setCompanyName(application.getCompany().getCompanyName());
                applicationResponse.setCompanyId(application.getCompany().getCompanyid());

                applicationResponses.add(applicationResponse);
                System.out.println(applicationResponse.getStudentName());
            }
        }


        return applicationResponses;
    }


    public void approveApplicationForm(Long applicationId) {
        try {
            Application application = applicationRepository.findById(applicationId).orElse(null);
            assert application != null;
            assert Objects.equals(application.getApplicationStatus(), "Application Form Sent to Coordinator");

            if (application.getStudent().getNationality().equals("Turkish") && application.getCompany().getCompanyAddress().endsWith("TR")) {
                application.setApplicationStatus("SGK Document Pending");
                SGKFile sgkFile = new SGKFile(application.getStudent().getStudentID(), "Unavailable");
                sgkRepo.save(sgkFile);
            }

            application.setApplicationStatus("Application Form Approved");
            applicationRepository.save(application);
        }
        catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    public void rejectApplicationForm(Long applicationId) {
        try {
            Application application = applicationRepository.findById(applicationId).orElse(null);
            assert application != null;
            assert Objects.equals(application.getApplicationStatus(), "Application Form Sent to Coordinator");
            application.setApplicationStatus("Application Form Rejected");
            applicationRepository.save(application);
        }
        catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }



}
