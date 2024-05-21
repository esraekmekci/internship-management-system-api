package com.ceng316.internshipmanagementsystemapi.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class StudentSGKResponse {

    private Long studentId;
    private String studentEmail;
    private String studentName;
    private String sgkDocumentStatus;
    
}
