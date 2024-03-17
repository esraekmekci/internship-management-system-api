package com.ceng316.internshipmanagementsystemapi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.sql.Date;

@Entity
@Data
@Table(name = "opportunities")
public class Opportunity {

    @Id
    int id;
    String status; // enum olabilir mi
    Date uploadDate;

    @OneToOne(mappedBy = "opportunities")
    CompanyRep companyRep;
}
