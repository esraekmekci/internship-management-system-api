package com.ceng316.internshipmanagementsystemapi.repos;

import com.ceng316.internshipmanagementsystemapi.entities.Secretary;
import com.ceng316.internshipmanagementsystemapi.entities.User;

public interface SecretaryRepository extends JpaRepository<User, Long>, UserRepository {
}
