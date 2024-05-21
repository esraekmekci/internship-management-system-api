package com.ceng316.internshipmanagementsystemapi.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "students")
public class Student extends User {
    @Builder(builderMethodName = "studentBuilder")
    public Student(Long studentID, String name, String email, String password, String role, String grade, String internshipStatus, String nationality) {
        super(name, email, password, role);
        this.studentID = studentID;
        this.grade = grade;
        this.internshipStatus = internshipStatus;
        this.nationality = nationality;
    }

    @Id
    Long studentID;

    String grade;

    String internshipStatus;

    String nationality;


    @Override
    public Long getSubclassId() {
        return studentID;
    }
}
