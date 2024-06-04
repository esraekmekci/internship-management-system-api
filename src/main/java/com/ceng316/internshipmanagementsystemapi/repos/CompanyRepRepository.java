package com.ceng316.internshipmanagementsystemapi.repos;

import com.ceng316.internshipmanagementsystemapi.entities.CompanyRep;
import com.ceng316.internshipmanagementsystemapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepRepository extends JpaRepository<CompanyRep, Long> {
    CompanyRep findByEmail(String email);

    List<CompanyRep> findByAccountStatus(String status);

    CompanyRep findByCompanyName(String name);

    void deleteByEmail(String email);
}
