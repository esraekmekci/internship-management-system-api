package com.ceng316.internshipmanagementsystemapi.services;

import com.ceng316.internshipmanagementsystemapi.controllers.S3Controller;
import com.ceng316.internshipmanagementsystemapi.entities.*;
import com.ceng316.internshipmanagementsystemapi.repos.ApplicationRepository;
import com.ceng316.internshipmanagementsystemapi.repos.StudentRepository;
import com.ceng316.internshipmanagementsystemapi.responses.ApplicationResponse;
import com.ceng316.internshipmanagementsystemapi.security.JwtTokenProvider;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    StudentRepository studentRepo;
    CompanyRepService companyService;
    S3Controller s3Controller;
    JwtTokenProvider jwtTokenProvider;
    ApplicationRepository applicationRepo;

    public StudentService(StudentRepository studentRepo, S3Controller s3Controller, JwtTokenProvider jwtTokenProvider, CompanyRepService companyService, ApplicationRepository applicationRepo) {
        this.studentRepo = studentRepo;
        this.s3Controller = s3Controller;
        this.jwtTokenProvider = jwtTokenProvider;
        this.companyService = companyService;
        this.applicationRepo = applicationRepo;
    }

    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    public Student getStudent(Long id) {
        return studentRepo.findById(id).orElse(null);
    }

    public Student getStudentByToken(String token) {
        Long userId = jwtTokenProvider.getUserIdFromJwt(token);
        return studentRepo.findById(userId).orElse(null);
    }

    public List<ApplicationResponse> getAppliedCompanies(Long studentId) {
        List<Application> applications =  applicationRepo.findByStudentId(studentId);
        List<ApplicationResponse> applicationResponses = new ArrayList<>();
        for (Application application : applications) {
            ApplicationResponse applicationResponse = new ApplicationResponse();
            applicationResponse.setCompanyName(application.getCompany().getCompanyName());
            applicationResponse.setApplicationStatus(application.getApplicationStatus());
            applicationResponse.setApplicationId(application.getId());
            applicationResponses.add(applicationResponse);
        }
        return applicationResponses;
    }

    public boolean isApplied(Long studentId, String companyName) {
        Application application = applicationRepo.findByStudentIdAndCompanyId(studentId, companyName);
        return ! (application == null);
    }

    public void uploadApplicationLetter(MultipartFile file, String companyName, Long studentId) throws Exception {
        try {
            if (isApplied(studentId, companyName)) {
                throw new Exception("Student has already applied to this company");
            }
            else {
                String fileName = "1_TR_SummerPracticeApplicationLetter2023_" + companyName + "_" + studentId + ".docx";
                Application application = new Application();
                application.setStudent(getStudent(studentId));
                application.setCompany(companyService.getCompanyByName(companyName));
                application.setApplicationStatus("Application Letter Pending");
                applicationRepo.save(application);
    //            studentRepo.findAppliedCompanies(studentId).add(companyService.getCompanyByName(companyName));
    //            studentRepo.save(getStudent(studentId));
                System.out.println(applicationRepo.findByStudentId(studentId));
                s3Controller.uploadFile(file, fileName);
            }
        }
        catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    public ResponseEntity<Resource> downloadApplicationLetter(Long studentId, String companyName) {
        try {
            String fileName = "1_TR_SummerPracticeApplicationLetter2023_" + companyName + "_" + studentId + ".docx";
            return s3Controller.downloadFile(fileName);
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

}
