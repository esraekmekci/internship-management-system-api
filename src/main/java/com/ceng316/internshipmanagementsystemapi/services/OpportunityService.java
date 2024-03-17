package com.ceng316.internshipmanagementsystemapi.services;
import com.ceng316.internshipmanagementsystemapi.repos.OpportunityRepository;

public class OpportunityService {
    OpportunityRepository opportunityRepo;
    CoordinatorService coordinatorService;
    AmazonS3 s3Client;

    void getAllOpportunities() {}
    void getOpportunity(Long id) {}
    void uploadOpportunity() {}
    void deleteOpportunity(Long id) {}
    void notifyCoordinator() {}
}
