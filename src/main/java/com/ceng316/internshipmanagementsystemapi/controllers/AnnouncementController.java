package com.ceng316.internshipmanagementsystemapi.controllers;

import com.ceng316.internshipmanagementsystemapi.entities.Announcement;
import com.ceng316.internshipmanagementsystemapi.responses.AnnouncementWithCompanyResponse;
import com.ceng316.internshipmanagementsystemapi.services.AnnouncementService;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/announcement")

public class AnnouncementController {
    AnnouncementService announcementService;

    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    @GetMapping
    public List<AnnouncementWithCompanyResponse> getAllAnnouncements() {
        return announcementService.getAllAnnouncements();
    }

    @GetMapping("/{status}")
    public List<Announcement> getByStatus(@PathVariable String status) {
        return announcementService.getByStatus(status);
    }
}
