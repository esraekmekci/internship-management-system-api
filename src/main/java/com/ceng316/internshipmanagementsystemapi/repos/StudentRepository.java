package com.ceng316.internshipmanagementsystemapi.repos;

import com.ceng316.internshipmanagementsystemapi.entities.CompanyRep;
import com.ceng316.internshipmanagementsystemapi.entities.SGKCertificate;
import com.ceng316.internshipmanagementsystemapi.entities.Student;
import com.ceng316.internshipmanagementsystemapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByEmail(String email);

//    SGKCertificate findSGKCertificate(Long id);

}
