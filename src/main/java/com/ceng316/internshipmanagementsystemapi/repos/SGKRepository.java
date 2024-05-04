package com.ceng316.internshipmanagementsystemapi.repos;

import com.ceng316.internshipmanagementsystemapi.entities.Document;
import com.ceng316.internshipmanagementsystemapi.entities.SGKCertificate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SGKRepository extends JpaRepository<SGKCertificate, Long> {
//    List<Document> findAllByStatusAndType();
}