package com.ceng316.internshipmanagementsystemapi.repos;

import com.ceng316.internshipmanagementsystemapi.entities.CompanyRep;
import com.ceng316.internshipmanagementsystemapi.entities.SGKCertificate;
import com.ceng316.internshipmanagementsystemapi.entities.User;

import java.util.List;

public interface StudentRepository extends JpaRepository<User, Long>, UserRepository {
    List<CompanyRep> findAppliedCompanies();

    CompanyRep findCompanyRep(Long id);

    SGKCertificate findSGKCertificate(Long id);

}
