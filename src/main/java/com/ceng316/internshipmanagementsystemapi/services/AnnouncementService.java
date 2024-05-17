package com.ceng316.internshipmanagementsystemapi.services;

import com.ceng316.internshipmanagementsystemapi.controllers.S3Controller;
import com.ceng316.internshipmanagementsystemapi.entities.Announcement;
import com.ceng316.internshipmanagementsystemapi.entities.CompanyRep;
import com.ceng316.internshipmanagementsystemapi.repos.AnnouncementRepository;
import com.ceng316.internshipmanagementsystemapi.repos.StudentRepository;
import com.ceng316.internshipmanagementsystemapi.responses.AnnouncementWithCompanyResponse;
import com.ceng316.internshipmanagementsystemapi.security.JwtTokenProvider;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AnnouncementService {
    AnnouncementRepository announcementRepo;
    CompanyRepService companyRepService;

    public AnnouncementService(AnnouncementRepository announcementRepo, CompanyRepService companyRepService) {
        this.announcementRepo = announcementRepo;
        this.companyRepService = companyRepService;
    }

    public List<AnnouncementWithCompanyResponse> getAllAnnouncements() {
        List<Announcement>  announcements = announcementRepo.findAll();
        List<AnnouncementWithCompanyResponse> announcementWithCompanyResponse = new ArrayList<>();
        for (Announcement announcement : announcements) {
            CompanyRep rep = companyRepService.getCompanyRep(announcement.getCompanyRep().getCompanyid());
            AnnouncementWithCompanyResponse ac = new AnnouncementWithCompanyResponse();
            ac.setRep_name(rep.getName());
            ac.setAnnouncement_id(announcement.getId());
            ac.setDate(announcement.getUploadDate());
            ac.setComp_name(rep.getCompanyName());
            ac.setRep_id(rep.getCompanyid());
            ac.setStatus(announcement.getStatus());
            ac.setTitle(announcement.getTitle());
            ac.setDescription(announcement.getDescription());
            announcementWithCompanyResponse.add(ac);
        }

        return announcementWithCompanyResponse;
    }

    public List<Announcement> getByStatus(String status) {
        return announcementRepo.findByStatus(status);
    }

    public Announcement getById(Long id) {
        return announcementRepo.findById(id).orElse(null);
    }

    void changeStatus(Announcement announcement,String newStatus) {
        announcement.setStatus(newStatus);
    }

}
