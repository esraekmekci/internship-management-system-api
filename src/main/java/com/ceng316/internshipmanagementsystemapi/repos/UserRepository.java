package com.ceng316.internshipmanagementsystemapi.repos;

import com.ceng316.internshipmanagementsystemapi.entities.User;

public  interface UserRepository extends JpaRepository<User, Long> {

    void createUser();
    User findUserByName(String name);
}
