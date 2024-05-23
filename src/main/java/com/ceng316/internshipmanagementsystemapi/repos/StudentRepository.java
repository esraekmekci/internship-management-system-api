package com.ceng316.internshipmanagementsystemapi.repos;

import com.ceng316.internshipmanagementsystemapi.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByEmail(String email);

    @Query("SELECT s " +
            "FROM Student s " +
            "JOIN Application a ON s.studentID = a.student.studentID " +
            "JOIN CompanyRep cr ON a.company.companyid = cr.companyid " +
            "WHERE s.nationality = :nationality " +
            "AND a.applicationStatus = :internshipStatus " +
            "AND cr.companyAddress LIKE %:companyAddress ")
    List<Student> findByNationalityAndInternshipStatusAndCompanyAddress(String nationality, String internshipStatus, String companyAddress);
//    SGKCertificate findSGKCertificate(Long id);

}
