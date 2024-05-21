package com.ohgiraffers.datajpa.student.controller;

import com.ohgiraffers.datajpa.common.Pagenation;
import com.ohgiraffers.datajpa.common.PagingButton;
import com.ohgiraffers.datajpa.student.dto.CoursesDTO;
import com.ohgiraffers.datajpa.student.dto.StudentDTO;
import com.ohgiraffers.datajpa.student.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /* 특정 학생 목록 보기 */
    @GetMapping("/{studentCode}")
    public String findStudentbyCode(/* @PathVariable */ @RequestParam  int studentCode, Model model) {
        StudentDTO resultStudent = studentService.findStudentByCode(studentCode);

        model.addAttribute("student", resultStudent);

        return "student/detail";
    }

    /* IllegalArgumentException 발생 시 에러 페이지 이동 */
    @GetMapping("/error")
    public String showErrorPage() {
        return "student/error";
    }

    /* 학생 전체 조회 */
    @GetMapping("/list")
    public String findStudentList(Model model, @PageableDefault Pageable pageable) {

        log.info("pageable : {}", pageable);

        Page<StudentDTO> studentList = studentService.findStudentList(pageable);

        log.info("조회한 내용 목록 : {}", studentList.getContent());
        log.info("총 페이지 수 : {}", studentList.getTotalPages());
        log.info("총 학생 수 : {}", studentList.getTotalElements());
        log.info("해당 페이지에 표시 될 요소 수 : {}", studentList.getSize());
        log.info("해당 페이지에 실제 요소 수 : {}", studentList.getNumberOfElements());
        log.info("첫 페이지 여부 : {}", studentList.isFirst());
        log.info("마지막 페이지 여부 : {}", studentList.isLast());
        log.info("정렬 방식 : {}", studentList.getSort());
        log.info("여러 페이지 중 현재 인덱스 : {}", studentList.getNumber());

        PagingButton paging = Pagenation.getPagingButtonInfo(studentList);

        model.addAttribute("studentList", studentList);
        model.addAttribute("paging", paging);


        return "student/list";
    }

    /* 쿼리 메소드 사용 */
    @GetMapping("/querymethod")
    public void queryMethodPage() {}

    @GetMapping("/search")
    public String findByStudentAge(@RequestParam Integer studentAge, Model model) {

        List<StudentDTO> studentList = studentService.findByStudentAge(studentAge);

        model.addAttribute("studentList", studentList);
        model.addAttribute("studentAge", studentAge);

        return "student/searchResult";
    }

    /* 학생 등록 페이지 이동 */
    @GetMapping("/regist")
    public void registPage() {}

    /* 강의 목록 조회 */
    @GetMapping("/courses")
    @ResponseBody
    public List<CoursesDTO> findCoursesList() {
        return studentService.findAllCourses();
    }

    /* 학생 등록 */
    @PostMapping("/regist")
    public String registNewStudent(@ModelAttribute StudentDTO studentDTO) {

        studentService.registNewStudent(studentDTO);

        return "redirect:/student/list";
    }

    /* 학생 수정 페이지 이동 */
    @GetMapping("/modify")
    public void modifyPage() {}

    /* 학생 수정 */
    @PostMapping("/modify")
    public String modifyStudent(StudentDTO modifyStudent) {

        studentService.modifyStudent(modifyStudent);

        return "redirect:/student/" + modifyStudent.getStudentCode();
    }

    /* 학생 삭제 페이지 이동 */
    @GetMapping("/delete")
    public void deletePage() {}

    /* 학생 삭제 */
    @PostMapping("/delete")
    public String deleteStudent(@RequestParam Integer studentCode) {
        studentService.deleteStudent(studentCode);
        return "redirect:/student/list";
    }

}
