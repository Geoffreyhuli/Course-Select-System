package com.auggie.student_server.controller;

import com.auggie.student_server.entity.CourseTeacherInfo;
import com.auggie.student_server.entity.SCTInfo;
import com.auggie.student_server.entity.StudentCourseTeacher;
import com.auggie.student_server.service.SCTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/SCT")
public class SCTcontroller {
    @Autowired
    private SCTService sctService;

    // 保存选课记录
    @PostMapping("/save")
    public String save(@RequestBody StudentCourseTeacher studentCourseTeacher) {
        if (sctService.isSCTExist(studentCourseTeacher)) {
            return "禁止重复选课";
        }
        System.out.println("正在保存 sct 记录：" + studentCourseTeacher);
        return sctService.save(studentCourseTeacher) ? "选课成功" : "选课失败，联系管理员";
    }

    // 根据学生ID和学期查询选课信息
    @GetMapping("/findBySid/{studentId}/{semester}")
    public List<CourseTeacherInfo> findBySid(@PathVariable String studentId, @PathVariable String semester) {
        return sctService.findBySid(studentId, semester);
    }

    // 查询所有学期
    @GetMapping("/findAllTerm")
    public List<String> findAllTerm() {
        return sctService.findAllTerm();
    }

    // 删除选课记录
    @PostMapping("/deleteBySCT")
    public boolean deleteBySCT(@RequestBody StudentCourseTeacher studentCourseTeacher) {
        System.out.println("正在删除 sct 记录：" + studentCourseTeacher);
        return sctService.deleteBySCT(studentCourseTeacher);
    }

    // 根据条件查询选课信息
    @PostMapping("/findBySearch")
    public List<SCTInfo> findBySearch(@RequestBody Map<String, String> map) {
        return sctService.findBySearch(map);
    }

    // 根据学生ID、课程ID、教师ID和学期查询选课信息
    @GetMapping("/findById/{studentId}/{courseId}/{staffId}/{semester}")
    public SCTInfo findById(@PathVariable String studentId,
                            @PathVariable String courseId,
                            @PathVariable String staffId,
                            @PathVariable String semester) {
        return sctService.findByIdWithTerm(studentId, courseId, staffId, semester);
    }

    // 更新成绩
    @GetMapping("/updateById/{studentId}/{courseId}/{staffId}/{semester}/{normalScore}/{testScore}/{totalScore}")
    public boolean updateById(@PathVariable String studentId,
                              @PathVariable String courseId,
                              @PathVariable String staffId,
                              @PathVariable String semester,
                              @PathVariable Float normalScore,
                              @PathVariable Float testScore,
                              @PathVariable Float totalScore) {
        return sctService.updateById(studentId, courseId, staffId, semester,normalScore,testScore,totalScore );
    }

    // 根据学生ID、课程ID、教师ID和学期删除选课记录
    @GetMapping("/deleteById/{studentId}/{courseId}/{staffId}/{semester}")
    public boolean deleteById(@PathVariable String studentId,
                              @PathVariable String courseId,
                              @PathVariable String staffId,
                              @PathVariable String semester) {
        return sctService.deleteById(studentId, courseId, staffId, semester);
    }
}