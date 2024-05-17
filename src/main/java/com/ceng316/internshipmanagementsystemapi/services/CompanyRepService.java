package com.ceng316.internshipmanagementsystemapi.services;

import com.ceng316.internshipmanagementsystemapi.entities.Application;
import com.ceng316.internshipmanagementsystemapi.entities.CompanyRep;
import com.ceng316.internshipmanagementsystemapi.repos.ApplicationRepository;
import com.ceng316.internshipmanagementsystemapi.repos.CompanyRepRepository;
import com.ceng316.internshipmanagementsystemapi.responses.ApplicationForCompanyResponse;
import com.ceng316.internshipmanagementsystemapi.security.JwtTokenProvider;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CompanyRepService {
    CompanyRepRepository companyRepRepo;
    JwtTokenProvider jwtTokenProvider;
    ApplicationRepository applicationRepo;

    public CompanyRepService(CompanyRepRepository companyRepRepo,JwtTokenProvider jwtTokenProvider, ApplicationRepository applicationRepo) {
        this.companyRepRepo = companyRepRepo;
        this.jwtTokenProvider = jwtTokenProvider;
        this.applicationRepo = applicationRepo;
    }

    public List<CompanyRep> getAllCompanyReps() {
        return companyRepRepo.findAll();
    }

    public List<CompanyRep> getAllApprovedCompanyReps() {
        return companyRepRepo.findByAccountStatus("APPROVED");
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
}
