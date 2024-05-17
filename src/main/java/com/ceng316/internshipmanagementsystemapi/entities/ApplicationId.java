package com.ceng316.internshipmanagementsystemapi.entities;

import jakarta.persistence.*;
import java.io.Serializable;

@Embeddable
public class ApplicationId implements Serializable {

    private Long studentId;
    private Long companyId;

    public ApplicationId() {}

    public ApplicationId(Long studentId, Long companyId) {
        this.studentId = studentId;
        this.companyId = companyId;
    }

    // hashCode, equals, getters, and setters
}
