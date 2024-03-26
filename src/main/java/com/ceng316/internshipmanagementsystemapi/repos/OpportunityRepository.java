package com.ceng316.internshipmanagementsystemapi.repos;

import com.ceng316.internshipmanagementsystemapi.entities.Document;

import java.util.List;

public interface OpportunityRepository extends JpaRepository<Document, Long> {
    List<Document> findAllByStatusAndType();
}