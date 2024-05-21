package com.ohgiraffers.datajpa.student.repository;


import com.ohgiraffers.datajpa.student.entity.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CoursesRepository extends JpaRepository<Courses, Integer> {
    @Query(value="SELECT courses_code, courses_name"
            + " FROM tbl_courses ORDER BY courses_code"
            , nativeQuery = true)
    List<Courses> findAllCourses();
}
