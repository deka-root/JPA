package com.ohgiraffers.datajpa.student.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StudentDTO {

    private int studentCode;

    private String studentName;

    private int studentAge;

    private int coursesCode;

    private String enrollStatus;


}
