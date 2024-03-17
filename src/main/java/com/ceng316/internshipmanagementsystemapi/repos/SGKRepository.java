package com.ceng316.internshipmanagementsystemapi.repos;

import com.ceng316.internshipmanagementsystemapi.entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SGKRepository extends JpaRepository<Document, Long> {
    List<Document> findAllByStatusAndType();
}