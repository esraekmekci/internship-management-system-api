package com.ceng316.internshipmanagementsystemapi.repos;

import com.ceng316.internshipmanagementsystemapi.entities.Document;
import com.ceng316.internshipmanagementsystemapi.entities.Opportunity;

import java.util.List;

public interface OpportunityRepository extends JpaRepository<Document, Long> {

    Opportunity findOpportunity(Long id);
    List<Document> findAllOpportunities();
    List<Document> findAllByStatusAndType();
}