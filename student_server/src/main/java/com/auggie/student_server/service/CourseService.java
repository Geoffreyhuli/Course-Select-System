package com.auggie.student_server.service;

import com.auggie.student_server.entity.Course;
import com.auggie.student_server.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CourseService {
    @Autowired
    private CourseMapper courseMapper;

    public List<Course> findBySearch(Map<String, String> map) {
        String courseId = null;
        Integer lowBound = null;
        Integer highBound = null;
        Integer fuzzy = 0;
        String courseName = null;

        if (map.containsKey("courseId")) {
            courseId = map.get("courseId");
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
        if (map.containsKey("courseName")) {
            courseName = map.get("courseName");
        }
        if (map.containsKey("fuzzy")) {
            fuzzy = map.get("fuzzy").equals("true") ? 1 : 0;
        }

        System.out.println("查询课程 map：" + map);
        System.out.println(courseId + " " + courseName + " " + fuzzy + " " + lowBound + " " + highBound);
        return courseMapper.findBySearch(courseId, courseName, fuzzy, lowBound, highBound);
    }

    public List<Course> findBySearch(String courseId) {
        Map<String, String> map = new HashMap<>();
        if (courseId != null) {
            map.put("courseId", courseId);
        }
        return findBySearch(map);
    }

    public List<Course> findById(String courseId) {
        return findBySearch(courseId);
    }

    public boolean updateById(Course course) {
        return courseMapper.updateById(course);
    }

    public boolean insertCourse(Course course) {
        return courseMapper.insertCourse(course);
    }

    public boolean deleteById(String courseId) {
        return courseMapper.deleteById(courseId);
    }
}