package com.ceng316.internshipmanagementsystemapi.repos;

import com.ceng316.internshipmanagementsystemapi.entities.SGKFile;
import com.ceng316.internshipmanagementsystemapi.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SGKRepository extends JpaRepository<SGKFile, Long> {
    SGKFile findByStudentId(Long studentId);
    @Modifying
    @Transactional
    @Query("UPDATE SGKFile sgk SET sgk.sgkDocumentStatus = :status WHERE sgk.studentId = :studentId")
    void updateSGKDocumentStatus(@Param("studentId") Long studentId, @Param("status") String status);
}
