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
    public Student(Long studentID, String name, String email, String password, String role, String grade, String internshipStatus) {
        super(name, email, password, role);
        this.studentID = studentID;
        this.grade = grade;
        this.internshipStatus = internshipStatus;
    }

    @Id
    Long studentID;

    String grade;

    String internshipStatus;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "student_applications",
//            joinColumns = @JoinColumn(name = "studentid"),
//            inverseJoinColumns = @JoinColumn(name = "companyid"))
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    List<CompanyRep> appliedCompanies;


    @Override
    public Long getSubclassId() {
        return studentID;
    }
}
