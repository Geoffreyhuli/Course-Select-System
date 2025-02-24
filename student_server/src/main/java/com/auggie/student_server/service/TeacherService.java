package com.auggie.student_server.service;

import com.auggie.student_server.entity.Teacher;
import com.auggie.student_server.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    // 根据条件查询教师
    public List<Teacher> findBySearch(Map<String, String> map) {
        String staffId = null;
        String name = null;
        Integer fuzzy = null;

        if (map.containsKey("staffId")) {
            staffId = map.get("staffId");
        }
        if (map.containsKey("name")) {
            name = map.get("name");
        }
        if (map.containsKey("fuzzy")) {
            fuzzy = map.get("fuzzy").equals("true") ? 1 : 0;
        }

        System.out.println(map);
        System.out.println("查询类型：" + staffId + ", " + name + ", " + fuzzy);
        return teacherMapper.findBySearch(staffId, name, fuzzy);
    }

    // 分页查询教师
    public List<Teacher> findByPage(Integer num, Integer size) {
        // num：第几页，size：一页多大
        // num：从零开始
        List<Teacher> teacherList = teacherMapper.findAll();
        ArrayList<Teacher> list = new ArrayList<>();

        int start = size * num;
        int end = size * (num + 1);
        int sz = teacherList.size();

        for (int i = start; i < end && i < sz; i++) {
            list.add(teacherList.get(i));
        }

        return list;
    }

    // 获取教师总数
    public Integer getLength() {
        return teacherMapper.findAll().size();
    }

    // 根据教师ID查询教师信息
    public Teacher findById(String staffId) {
        return teacherMapper.findById(staffId);
    }

    // 更新教师信息
    public boolean updateById(Teacher teacher) {
        return teacherMapper.updateById(teacher);
    }

    // 保存教师信息
    public boolean save(Teacher teacher) {
        return teacherMapper.save(teacher);
    }

    // 根据教师ID删除教师
    public boolean deleteById(String staffId) {
        return teacherMapper.deleteById(staffId);
    }
}