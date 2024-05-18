package com.ceng316.internshipmanagementsystemapi.repos;

import com.ceng316.internshipmanagementsystemapi.entities.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    List<Announcement> findByStatus(String status);

    @Query("SELECT a FROM Announcement a WHERE a.companyRep.companyid = :companyId")
    List<Announcement> findByCompanyId(Long companyId);
}