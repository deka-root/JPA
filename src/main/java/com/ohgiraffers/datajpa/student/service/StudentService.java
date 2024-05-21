package com.ohgiraffers.datajpa.student.service;

import com.ohgiraffers.datajpa.student.dto.CoursesDTO;
import com.ohgiraffers.datajpa.student.dto.StudentDTO;
import com.ohgiraffers.datajpa.student.entity.Courses;
import com.ohgiraffers.datajpa.student.entity.Student;
import com.ohgiraffers.datajpa.student.repository.CoursesRepository;
import com.ohgiraffers.datajpa.student.repository.StudentRepository;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.Sort.*;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final CoursesRepository coursesRepository;
    private final ModelMapper modelMapper;

    /* 1. findById */
    public StudentDTO findStudentByCode(int studentCode) {
        Student foundStudent = studentRepository.findById(studentCode)
                .orElseThrow(IllegalArgumentException::new);

        return modelMapper.map(foundStudent, StudentDTO.class);
    }

    /* 2. findAll : Sort */
    public List<StudentDTO> findStudentList() {
        List<Student> studentList = studentRepository.findAll(by("studentCode").descending());

        return studentList.stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .toList();
    }

    /* 3. findAll : Pageable */
    public Page<StudentDTO> findStudentList(Pageable pageable) {

        pageable = PageRequest.of(
                pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                by("studentCode").descending()
        );
        Page<Student> studentList = studentRepository.findAll(pageable);

        return studentList.map(student -> modelMapper.map(student, StudentDTO.class));
    }

    /* 4. Query Method */
    public List<StudentDTO> findByStudentAge(Integer studentAge) {

        List<Student> studentList = studentRepository.findByStudentAgeGreaterThan(
                studentAge
                ,by("studentAge").descending()
        );
        return studentList.stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .toList();
    }

    /* 5. JPQL or Native Query */
    public List<CoursesDTO> findAllCourses() {

        List<Courses> coursesList = coursesRepository.findAllCourses();

        return coursesList.stream()
                .map(courses -> modelMapper.map(courses, CoursesDTO.class))
                .toList();
    }

    /* 6. save */
    @Transactional
    public void registNewStudent(StudentDTO studentDTO) {

        studentRepository.save(modelMapper.map(studentDTO, Student.class));
    }

    @Transactional
    public void modifyStudent(StudentDTO modifyStudent) {
        Student foundStudent
                = studentRepository.findById(modifyStudent.getStudentCode())
                .orElseThrow(IllegalArgumentException::new);
        foundStudent.modifyStudentName(modifyStudent.getStudentName());
    }

    @Transactional
    public void deleteStudent(Integer studentCode) {
        studentRepository.deleteById(studentCode);
    }

}
