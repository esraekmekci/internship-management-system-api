package com.ceng316.internshipmanagementsystemapi.repos;

import com.ceng316.internshipmanagementsystemapi.entities.CompanyRep;
import com.ceng316.internshipmanagementsystemapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepRepository extends JpaRepository<CompanyRep, Long> {
    CompanyRep findByEmail(String email);

    CompanyRep findByCompanyName(String name);

}
