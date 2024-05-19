package com.ceng316.internshipmanagementsystemapi.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationForCoordinatorResponse {
    private Long applicationId;
    private String companyName;
    private Long companyId;
    private String studentName;
    private Long studentId;
    private String applicationStatus;
}
