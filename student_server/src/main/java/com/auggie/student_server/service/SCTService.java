package com.auggie.student_server.service;

import com.auggie.student_server.entity.CourseTeacherInfo;
import com.auggie.student_server.entity.SCTInfo;
import com.auggie.student_server.entity.StudentCourseTeacher;
import com.auggie.student_server.mapper.StudentCourseTeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SCTService {
    @Autowired
    private StudentCourseTeacherMapper studentCourseTeacherMapper;

    // 根据学生ID和学期查询课程-教师信息
    public List<CourseTeacherInfo> findBySid(String studentId, String semester) {
        return studentCourseTeacherMapper.findByStudentId(studentId, semester);
    }

    // 查询所有学期
    public List<String> findAllTerm() {
        return studentCourseTeacherMapper.findAllTerm();
    }

    // 检查选课记录是否存在
    public boolean isSCTExist(StudentCourseTeacher studentCourseTeacher) {
        return studentCourseTeacherMapper.findBySCT(studentCourseTeacher).size() != 0;
    }

    // 保存选课记录
    public boolean save(StudentCourseTeacher studentCourseTeacher) {
        return studentCourseTeacherMapper.insert(studentCourseTeacher);
    }

    // 删除选课记录
    public boolean deleteBySCT(StudentCourseTeacher studentCourseTeacher) {
        return studentCourseTeacherMapper.deleteBySCT(studentCourseTeacher);
    }

    // 根据学生ID、课程ID、教师ID和学期删除选课记录
    public boolean deleteById(String studentId, String courseId, String staffId, String semester) {
        StudentCourseTeacher studentCourseTeacher = new StudentCourseTeacher();
        studentCourseTeacher.setStudentId(studentId);
        studentCourseTeacher.setCourseId(courseId);
        studentCourseTeacher.setStaffId(staffId);
        studentCourseTeacher.setSemester(semester);
        return studentCourseTeacherMapper.deleteBySCT(studentCourseTeacher);
    }

    // 根据学生ID、课程ID、教师ID和学期查询选课信息
    public SCTInfo findByIdWithTerm(String studentId, String courseId, String staffId, String semester) {
        return studentCourseTeacherMapper.findBySearch(
                studentId, null, 0,
                courseId, null, 0,
                staffId, null, 0,
                null, null, semester).get(0);
    }

    // 更新成绩
    public boolean updateById(String studentId, String courseId, String staffId, String semester, Integer grade) {
        return studentCourseTeacherMapper.updateById(studentId, courseId, staffId, semester, grade);
    }

    // 根据条件查询选课信息
    public List<SCTInfo> findBySearch(Map<String, String> map) {
        String studentId = null, courseId = null, staffId = null;
        String studentName = null, courseName = null, teacherName = null, semester = null;
        Integer sFuzzy = null, cFuzzy = null, tFuzzy = null;
        Integer lowBound = null, highBound = null;

        if (map.containsKey("studentId")) {
            studentId = map.get("studentId");
        }
        if (map.containsKey("courseId")) {
            courseId = map.get("courseId");
        }
        if (map.containsKey("staffId")) {
            staffId = map.get("staffId");
        }
        if (map.containsKey("studentName")) {
            studentName = map.get("studentName");
        }
        if (map.containsKey("teacherName")) {
            teacherName = map.get("teacherName");
        }
        if (map.containsKey("courseName")) {
            courseName = map.get("courseName");
        }
        if (map.containsKey("semester")) {
            semester = map.get("semester");
        }
        if (map.containsKey("sFuzzy")) {
            sFuzzy = map.get("sFuzzy").equals("true") ? 1 : 0;
        }
        if (map.containsKey("tFuzzy")) {
            tFuzzy = map.get("tFuzzy").equals("true") ? 1 : 0;
        }
        if (map.containsKey("cFuzzy")) {
            cFuzzy = map.get("cFuzzy").equals("true") ? 1 : 0;
        }
        if (map.containsKey("lowBound")) {
            try {
                lowBound = Integer.parseInt(map.get("lowBound"));
            } catch (Exception e) {
                // 忽略异常
            }
        }
        if (map.containsKey("highBound")) {
            try {
                highBound = Integer.parseInt(map.get("highBound"));
            } catch (Exception e) {
                // 忽略异常
            }
        }

        System.out.println("SCT 查询：" + map);
        return studentCourseTeacherMapper.findBySearch(
                studentId, studentName, sFuzzy,
                courseId, courseName, cFuzzy,
                staffId, teacherName, tFuzzy,
                lowBound, highBound, semester);
    }
}