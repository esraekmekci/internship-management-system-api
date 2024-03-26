package com.ceng316.internshipmanagementsystemapi.repos;

import com.ceng316.internshipmanagementsystemapi.entities.CompanyRep;
import com.ceng316.internshipmanagementsystemapi.entities.User;

public interface CompanyRepRepository extends JpaRepository<User, Long>, UserRepository{



}
