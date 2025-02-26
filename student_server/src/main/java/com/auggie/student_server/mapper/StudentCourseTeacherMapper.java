package com.auggie.student_server.mapper;

import com.auggie.student_server.entity.CourseTeacherInfo;
import com.auggie.student_server.entity.SCTInfo;
import com.auggie.student_server.entity.StudentCourseTeacher;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface StudentCourseTeacherMapper {

    // 根据学生ID和学期查询课程-教师信息
    public List<CourseTeacherInfo> findByStudentId(@Param("studentId") String studentId,
                                                   @Param("semester") String semester);

    // 根据条件查询学生-课程-教师信息
    public List<SCTInfo> findBySearch(@Param("studentId") String studentId,
                                      @Param("studentName") String studentName,
                                      @Param("sFuzzy") Integer sFuzzy,
                                      @Param("courseId") String courseId,
                                      @Param("courseName") String courseName,
                                      @Param("cFuzzy") Integer cFuzzy,
                                      @Param("staffId") String staffId,
                                      @Param("teacherName") String teacherName,
                                      @Param("tFuzzy") Integer tFuzzy,
                                      @Param("lowBound") Integer lowBound,
                                      @Param("highBound") Integer highBound,
                                      @Param("semester") String semester);

    // 查询所有学期
    @Select("SELECT DISTINCT semester FROM course_selection")
    public List<String> findAllTerm();

    // 根据学生-课程-教师关联信息查询
    @Select("SELECT * FROM course_selection WHERE student_id = #{sct.studentId} AND course_id = #{sct.courseId} AND staff_id = #{sct.staffId} AND semester = #{sct.semester}")
    public List<StudentCourseTeacher> findBySCT(@Param("sct") StudentCourseTeacher studentCourseTeacher);

    // 插入学生-课程-教师关联信息
    @Insert("INSERT INTO course_selection (student_id, course_id, staff_id, semester, normal_score, test_score, total_score) " +
            "VALUES (#{s.studentId}, #{s.courseId}, #{s.staffId}, #{s.semester}, #{s.normalScore}, #{s.testScore}, #{s.totalScore})")
    public boolean insert(@Param("s") StudentCourseTeacher studentCourseTeacher);

    // 更新成绩
    @Update("UPDATE course_selection SET normal_score = #{normalScore}, test_score = #{testScore} " +
            "WHERE student_id = #{studentId} AND course_id = #{courseId} AND staff_id = #{staffId} AND semester = #{semester}")
    public boolean updateById(@Param("studentId") String studentId,
                              @Param("courseId") String courseId,
                              @Param("staffId") String staffId,
                              @Param("semester") String semester,
                              @Param("normalScore") Float normalScore,
                              @Param("testScore") Float testScore,
                              @Param("totalScore") Float totalScore);


    // 删除学生-课程-教师关联信息
    @Delete("DELETE FROM course_selection WHERE student_id = #{sct.studentId} AND course_id = #{sct.courseId} AND staff_id = #{sct.staffId}")
    public boolean deleteBySCT(@Param("sct") StudentCourseTeacher sct);
}