package com.ohgiraffers.datajpa.student.repository;

import com.ohgiraffers.datajpa.student.entity.Student;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
/* JpaRepository<엔티티, Id타입> */
public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findByStudentAgeGreaterThan(Integer studentAge);

    List<Student> findByStudentAgeGreaterThanOrderByStudentAge(Integer studentAge);

    List<Student> findByStudentAgeGreaterThan(Integer studentAge, Sort sort);

}
