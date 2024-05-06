package com.ceng316.internshipmanagementsystemapi.repos;

import com.ceng316.internshipmanagementsystemapi.entities.Coordinator;
import com.ceng316.internshipmanagementsystemapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoordinatorRepository extends JpaRepository<Coordinator, Long> {
    Coordinator findByEmail(String email);
}
