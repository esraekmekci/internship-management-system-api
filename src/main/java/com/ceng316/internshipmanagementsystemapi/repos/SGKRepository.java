package com.ceng316.internshipmanagementsystemapi.repos;

import com.ceng316.internshipmanagementsystemapi.entities.SGKFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SGKRepository extends JpaRepository<SGKFile, Long> {
    // You can add custom methods here if needed, for example:
    SGKFile findByStudentId(Long studentId);
    List<SGKFile> findBySecretaryId(Long secretaryId);
}
