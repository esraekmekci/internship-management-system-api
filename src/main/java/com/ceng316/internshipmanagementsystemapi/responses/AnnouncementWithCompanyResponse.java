package com.ceng316.internshipmanagementsystemapi.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementWithCompanyResponse {
    private String rep_name;
    private String comp_name;
    private Long announcement_id;
    private Long rep_id;
    private String title;
    private String description;
    private String status;
    private Date date;
}
