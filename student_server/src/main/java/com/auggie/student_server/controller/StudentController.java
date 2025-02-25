package com.auggie.student_server.controller;

import com.auggie.student_server.entity.Student;
import com.auggie.student_server.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    // 添加学生
    @PostMapping("/addStudent")
    public boolean addStudent(@RequestBody Student student) {
        System.out.println("正在保存学生对象：" + student);
        return studentService.save(student);
    }

    // 学生登录验证
    @PostMapping("/login")
    public boolean login(@RequestBody Student student) {
        System.out.println("正在验证学生登录：" + student);
        Student s = studentService.findById(student.getStudentId());
        if (s == null || !s.getPassword().equals(student.getPassword())) {
            return false;
        }
        return true;
    }

    // 根据条件查询学生
    @PostMapping("/findBySearch")
    public List<Student> findBySearch(@RequestBody Student student) {
        Integer fuzzy = (student.getPassword() == null) ? 0 : 1;
        return studentService.findBySearch(student.getStudentId(), student.getName(), fuzzy);
    }

    // 根据学生ID查询学生信息
    @GetMapping("/findById/{studentId}")
    public Student findById(@PathVariable("studentId") String studentId) {
        System.out.println("正在查询学生信息 By id：" + studentId);
        return studentService.findById(studentId);
    }

    // 分页查询学生列表
    @GetMapping("/findByPage/{page}/{size}")
    public List<Student> findByPage(@PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("查询学生列表分页：" + page + " " + size);
        return studentService.findByPage(page, size);
    }

    // 获取学生总数
    @GetMapping("/getLength")
    public Integer getLength() {
        return studentService.getLength();
    }

    // 根据学生ID删除学生
    @GetMapping("/deleteById/{studentId}")
    public boolean deleteById(@PathVariable("studentId") String studentId) {
        System.out.println("正在删除学生 studentId：" + studentId);
        return studentService.deleteById(studentId);
    }

    // 更新学生信息
    @PostMapping("/updateStudent")
    public boolean updateStudent(@RequestBody Student student) {
        System.out.println("更新学生信息：" + student);
        return studentService.updateById(student);
    }
}