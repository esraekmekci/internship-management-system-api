package com.ceng316.internshipmanagementsystemapi.repos;

import com.ceng316.internshipmanagementsystemapi.entities.Coordinator;
import com.ceng316.internshipmanagementsystemapi.entities.User;

public interface CoordinatorRepository extends JpaRepository<User, Long>, UserRepository {

}
