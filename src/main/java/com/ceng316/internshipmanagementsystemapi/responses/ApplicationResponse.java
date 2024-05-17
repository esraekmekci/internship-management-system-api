package com.ceng316.internshipmanagementsystemapi.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationResponse {
    private Long applicationId;
    private String companyName;
    private String applicationStatus;
}
