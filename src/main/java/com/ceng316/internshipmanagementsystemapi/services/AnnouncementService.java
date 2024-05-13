package com.ceng316.internshipmanagementsystemapi.services;

import com.ceng316.internshipmanagementsystemapi.controllers.S3Controller;
import com.ceng316.internshipmanagementsystemapi.entities.Announcement;
import com.ceng316.internshipmanagementsystemapi.repos.AnnouncementRepository;
import com.ceng316.internshipmanagementsystemapi.repos.StudentRepository;
import com.ceng316.internshipmanagementsystemapi.security.JwtTokenProvider;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AnnouncementService {
    AnnouncementRepository announcementRepo;

    public AnnouncementService(AnnouncementRepository announcementRepo) {
        this.announcementRepo = announcementRepo;
    }

    public List<Announcement> getAllAnnouncements() {
        return announcementRepo.findAll();
    }

    public List<Announcement> getByStatus(String status) {
        return announcementRepo.findByStatus(status);
    }

    void changeStatus(Announcement announcement,String newStatus) {
        announcement.setStatus(newStatus);
    }

    void updateDocument() {
    }

    void downloadDocument() {
    }
}
