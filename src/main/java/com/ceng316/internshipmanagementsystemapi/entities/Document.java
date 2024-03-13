package com.ceng316.internshipmanagementsystemapi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "documents")
public class Document {
    @Id
    int documentID;
    String documentName;
    String status; // enum olabilir mi
    String type; // enum olabilir mi
}
