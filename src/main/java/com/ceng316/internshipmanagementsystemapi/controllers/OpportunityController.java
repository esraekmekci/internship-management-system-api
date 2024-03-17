package com.ceng316.internshipmanagementsystemapi.controllers;
import com.ceng316.internshipmanagementsystemapi.services.DocumentService;

import com.ceng316.internshipmanagementsystemapi.services.OpportunityService;
import org.springframework.core.io.Resource;

import org.springframework.http.ResponseEntity;

public class OpportunityController {
    OpportunityService opportunityService;

    void getAllOpportunities() {}
    void getOpportunity(Long id) {}
    void uploadOpportunity() {}
    void deleteOpportunity(Long id) {}
}
