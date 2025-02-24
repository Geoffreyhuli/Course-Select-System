package com.auggie.student_server.mapper;

import com.auggie.student_server.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TeacherMapper {

    // 查询所有教师
    public List<Teacher> findAll();

    // 根据教师ID查询教师信息
    public Teacher findById(@Param("staffId") String staffId);

    // 根据条件查询教师
    public List<Teacher> findBySearch(@Param("staffId") String staffId,
                                      @Param("name") String name,
                                      @Param("fuzzy") Integer fuzzy);

    // 更新教师信息
    public boolean updateById(@Param("teacher") Teacher teacher);

    // 插入教师信息
    public boolean save(@Param("teacher") Teacher teacher);

    // 根据教师ID删除教师
    public boolean deleteById(@Param("staffId") String staffId);
}