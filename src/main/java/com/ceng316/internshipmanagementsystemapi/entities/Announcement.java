package com.ceng316.internshipmanagementsystemapi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.sql.Date;

@Entity
@Data
@Table(name = "announcements")
public class Announcement {

    @Id
    int id;
    String status;
    Date uploadDate;

    @OneToOne(mappedBy = "announcements")
    Coordinator coordinator;

}
