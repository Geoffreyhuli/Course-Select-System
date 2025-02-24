package com.auggie.student_server.service;

import com.auggie.student_server.entity.Student;
import com.auggie.student_server.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;

    // 分页查询学生
    public List<Student> findByPage(Integer num, Integer size) {
        // num：第几页，size：一页多大
        // num：从零开始
        List<Student> studentList = studentMapper.findAll();
        ArrayList<Student> list = new ArrayList<>();

        int start = size * num;
        int end = size * (num + 1);
        int sz = studentList.size();

        for (int i = start; i < end && i < sz; i++) {
            list.add(studentList.get(i));
        }

        return list;
    }

    // 根据条件查询学生
    public List<Student> findBySearch(String studentId, String name, Integer fuzzy) {
        Student student = new Student();
        student.setStudentId(studentId);
        student.setName(name);
        fuzzy = (fuzzy == null) ? 0 : fuzzy;

        System.out.println("查询学生：" + student + "，模糊模式：" + fuzzy);
        return studentMapper.findBySearch(student, fuzzy);
    }

    // 获取学生总数
    public Integer getLength() {
        return studentMapper.findAll().size();
    }

    // 根据学生ID查询学生信息
    public Student findById(String studentId) {
        return studentMapper.findById(studentId);
    }

    // 更新学生信息
    public boolean updateById(Student student) {
        return studentMapper.updateById(student);
    }

    // 保存学生信息
    public boolean save(Student student) {
        return studentMapper.save(student);
    }

    // 根据学生ID删除学生
    public boolean deleteById(String studentId) {
        return studentMapper.deleteById(studentId);
    }
}