package com.ohgiraffers.datajpa.student.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_courses")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Courses {

    @Id
    private int coursesCode;
    
    private String coursesName;
}
