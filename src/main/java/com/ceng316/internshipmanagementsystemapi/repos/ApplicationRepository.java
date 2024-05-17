package com.ceng316.internshipmanagementsystemapi.repos;

import com.ceng316.internshipmanagementsystemapi.entities.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    @Query("SELECT a FROM Application a WHERE a.student.studentID = :studentId")
    List<Application> findByStudentId(Long studentId);

    @Query("SELECT a FROM Application a WHERE a.company.companyid = :companyId")
    List<Application> findByCompanyId(Long companyId);

    @Query("SELECT a FROM Application a WHERE a.student.studentID = :studentId AND a.company.companyName = :companyName")
    Application findByStudentIdAndCompanyId(Long studentId, String companyName);

}
