package com.ceng316.internshipmanagementsystemapi.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Data
@Table(name = "announcements")
public class Announcement {

    @Id
    Long id;
    String status;
    Date uploadDate;

    @ManyToOne
    CompanyRep companyRep;

}
