package com.ceng316.internshipmanagementsystemapi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "students")
public class Student extends User {
    @Builder(builderMethodName = "studentBuilder")
    public Student(Long studentID, String name, String email, String password, String role, String grade, String internshipStatus) {
        super(name, email, password, role);
        this.studentID = studentID;
        this.grade = grade;
        this.internshipStatus = internshipStatus;
    }

    @Id
    Long studentID;

    String grade;

    String internshipStatus; // can this be boolean or enum?

    @Override
    public Long getSubclassId() {
        return studentID;
    }
}
