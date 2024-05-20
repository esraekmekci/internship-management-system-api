package com.ceng316.internshipmanagementsystemapi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sgk_files")

public class SGKFile {

    @Id
    private Long studentId;

    @Column(nullable = false)
    private String sgkDocumentStatus; // Available, Unavailable

}
