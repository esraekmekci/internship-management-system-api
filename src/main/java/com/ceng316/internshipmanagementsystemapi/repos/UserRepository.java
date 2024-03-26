package com.ceng316.internshipmanagementsystemapi.repos;

import com.ceng316.internshipmanagementsystemapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public  interface UserRepository extends JpaRepository<User, Long> {

    void createUser();
    User findUserByName(String name);
}
