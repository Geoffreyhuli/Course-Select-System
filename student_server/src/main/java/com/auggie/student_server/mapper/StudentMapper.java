package com.auggie.student_server.mapper;

import com.auggie.student_server.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentMapper {

    // 查询所有学生
    public List<Student> findAll();

    // 根据学生ID查询学生信息
    public Student findById(@Param("studentId") String studentId);

    // 根据条件查询学生
    public List<Student> findBySearch(@Param("student") Student student, @Param("fuzzy") Integer fuzzy);

    // 更新学生信息
    public boolean updateById(@Param("student") Student student);

    // 插入学生信息
    public boolean save(@Param("student") Student student);

    // 根据学生ID删除学生
    public boolean deleteById(@Param("studentId") String studentId);
}