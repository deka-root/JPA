package com.ohgiraffers.datajpa.student.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_student")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentCode;

    private String studentName;

    private int studentAge;

    private int coursesCode;

    private String enrollStatus;

    public void modifyStudentName(String studentName) {
        this.studentName = studentName;
    }

}
