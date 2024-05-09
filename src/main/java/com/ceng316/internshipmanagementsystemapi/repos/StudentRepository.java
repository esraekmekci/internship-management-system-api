package com.ceng316.internshipmanagementsystemapi.repos;

import com.ceng316.internshipmanagementsystemapi.entities.CompanyRep;
import com.ceng316.internshipmanagementsystemapi.entities.SGKCertificate;
import com.ceng316.internshipmanagementsystemapi.entities.Student;
import com.ceng316.internshipmanagementsystemapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByEmail(String email);
//    List<CompanyRep> findAppliedCompanies();

//    CompanyRep findCompanyRep(Long id);

//    SGKCertificate findSGKCertificate(Long id);

}
