package com.ceng316.internshipmanagementsystemapi.services;

import com.ceng316.internshipmanagementsystemapi.controllers.S3Controller;
import com.ceng316.internshipmanagementsystemapi.controllers.StudentController;
import com.ceng316.internshipmanagementsystemapi.entities.Announcement;
import com.ceng316.internshipmanagementsystemapi.entities.Application;
import com.ceng316.internshipmanagementsystemapi.entities.CompanyRep;
import com.ceng316.internshipmanagementsystemapi.entities.SGKFile;
import com.ceng316.internshipmanagementsystemapi.repos.AnnouncementRepository;
import com.ceng316.internshipmanagementsystemapi.repos.ApplicationRepository;
import com.ceng316.internshipmanagementsystemapi.repos.CompanyRepRepository;
import com.ceng316.internshipmanagementsystemapi.repos.SGKRepository;
import com.ceng316.internshipmanagementsystemapi.requests.AnnouncementRequest;
import com.ceng316.internshipmanagementsystemapi.responses.ApplicationForCompanyResponse;
import com.ceng316.internshipmanagementsystemapi.security.JwtTokenProvider;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CompanyRepService {
    CompanyRepRepository companyRepRepo;
    JwtTokenProvider jwtTokenProvider;
    ApplicationRepository applicationRepo;
    AnnouncementRepository announcementRepo;
    StudentController studentController;
    SGKRepository sgkRepo;
    S3Controller s3Controller;

    @Autowired
    public CompanyRepService(CompanyRepRepository companyRepRepo,JwtTokenProvider jwtTokenProvider, ApplicationRepository applicationRepo, AnnouncementRepository announcementRepo, SGKRepository sgkRepo, @Lazy StudentController studentController, S3Controller s3Controller){
        this.companyRepRepo = companyRepRepo;
        this.jwtTokenProvider = jwtTokenProvider;
        this.applicationRepo = applicationRepo;
        this.announcementRepo = announcementRepo;
        this.sgkRepo = sgkRepo;
        this.studentController = studentController;
        this.s3Controller = s3Controller;
    }


    public List<CompanyRep> getAllCompanyReps() {
        return companyRepRepo.findAll();
    }

    public List<CompanyRep> getAllApprovedCompanyReps() {
        return companyRepRepo.findByAccountStatus("APPROVED");
    }

    public List<CompanyRep> getAllPendingCompanyReps() {
        return companyRepRepo.findByAccountStatus("PENDING");
    }


    public CompanyRep getCompanyRep(Long id) {
        return companyRepRepo.findById(id).orElse(null);
    }

    public CompanyRep getCompanyRepByToken(String token) {
        Long userId = jwtTokenProvider.getUserIdFromJwt(token);
        return companyRepRepo.findById(userId).orElse(null);
    }

    public CompanyRep getCompanyByName(String name) {
        return companyRepRepo.findByCompanyName(name);
    }

    public List<ApplicationForCompanyResponse> getAppliedStudents(Long companyId) {
        List<Application> applications =  applicationRepo.findByCompanyId(companyId);
        List<ApplicationForCompanyResponse> applicationResponses = new ArrayList<>();
        for (Application application : applications) {
            if (application.getApplicationStatus().equals("Application Letter Rejected")) {
                continue;
            }
            ApplicationForCompanyResponse applicationResponse = new ApplicationForCompanyResponse();
            applicationResponse.setStudentName(application.getStudent().getName());
            applicationResponse.setStudentId(application.getStudent().getStudentID());
            applicationResponse.setApplicationStatus(application.getApplicationStatus());
            applicationResponse.setApplicationId(application.getId());
            applicationResponses.add(applicationResponse);
        }
        return applicationResponses;
    }

    public List<ApplicationForCompanyResponse> getInterns(Long companyId) {
        List<Application> applications =  applicationRepo.findByCompanyId(companyId);
        List<ApplicationForCompanyResponse> applicationResponses = new ArrayList<>();
        for (Application application : applications) {
            if (application.getApplicationStatus().equals("Application Letter Rejected") || application.getApplicationStatus().equals("Application Letter Pending")) {
                continue;
            }
            ApplicationForCompanyResponse applicationResponse = new ApplicationForCompanyResponse();
            applicationResponse.setStudentName(application.getStudent().getName());
            applicationResponse.setStudentId(application.getStudent().getStudentID());
            applicationResponse.setApplicationStatus(application.getApplicationStatus());
            applicationResponse.setApplicationId(application.getId());
            applicationResponses.add(applicationResponse);
        }
        return applicationResponses;
    }

    public void approveApplicationLetter(Long companyId, Long applicationId) {
        try {
            Application application = applicationRepo.findById(applicationId).orElse(null);
            assert application != null;
            if (!Objects.equals(application.getCompany().getCompanyid(), companyId)) {
                throw new RuntimeException("Application does not belong to this company");
            }
            application.setApplicationStatus("Application Letter Approved");
            applicationRepo.save(application);
        }
        catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    public void rejectApplicationLetter(Long companyId, Long applicationId) {
        try {
            Application application = applicationRepo.findById(applicationId).orElse(null);
            assert application != null;
            if (!Objects.equals(application.getCompany().getCompanyid(), companyId)) {
                throw new RuntimeException("Application does not belong to this company");
            }
            application.setApplicationStatus("Application Letter Rejected");
            applicationRepo.save(application);
        }
        catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    public void makeAnnouncement(Long companyId, AnnouncementRequest announcement) {
        try {
            CompanyRep companyRep = companyRepRepo.findById(companyId).orElse(null);
            assert companyRep != null;
            Announcement newAnnouncement = new Announcement();
            newAnnouncement.setCompanyRep(companyRep);
            newAnnouncement.setTitle(announcement.getTitle());
            newAnnouncement.setDescription(announcement.getDescription());
            newAnnouncement.setStatus("pending");
            newAnnouncement.setUploadDate(new java.sql.Date(System.currentTimeMillis()));

            announcementRepo.save(newAnnouncement);
        }
        catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    public List<Announcement> getAnnouncements(Long companyId) {
        return announcementRepo.findByCompanyId(companyId);
    }

    public void deleteAnnouncement(Long companyId, Long announcementId) {
        try {
            Announcement announcement = announcementRepo.findById(announcementId).orElse(null);
            assert announcement != null;
            if (!Objects.equals(announcement.getCompanyRep().getCompanyid(), companyId)) {
                throw new RuntimeException("Announcement does not belong to this company");
            }
            announcementRepo.delete(announcement);
        }
        catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    public ResponseEntity<Resource> downloadApplicationLetter(Long companyId, Long studentId) {
        CompanyRep companyRep = companyRepRepo.findById(companyId).orElse(null);
        assert companyRep != null;
        return studentController.downloadApplicationLetter(studentId, companyRep.getCompanyName());
    }

    public ResponseEntity<Resource> downloadApplicationForm(Long companyId, Long studentId) {
        CompanyRep companyRep = companyRepRepo.findById(companyId).orElse(null);
        assert companyRep != null;
        return studentController.downloadApplicationForm(studentId, companyRep.getCompanyName());
    }

    public void uploadApplicationForm(MultipartFile file, Long companyId, Long studentId) throws Exception {
        try {
            CompanyRep companyRep = companyRepRepo.findById(companyId).orElse(null);
            assert companyRep != null;
            String fileName = "SummerPracticeApplicationForm2023_" + companyRep.getCompanyName() + "_" + studentId + "_CompanyEdition";

            Application application = applicationRepo.findByStudentIdAndCompanyId(studentId, companyRep.getCompanyName());
            application.setApplicationStatus("Application Form Sent to Coordinator");
            applicationRepo.save(application);

            s3Controller.uploadFile(file, fileName);
        }
        catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }
}
