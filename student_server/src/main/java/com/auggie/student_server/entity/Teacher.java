package com.auggie.student_server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

/**
 * @Auther: auggie
 * @Date: 2022/2/9 10:50
 * @Description: Teacher
 * @Version 1.0.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("Teacher")
public class Teacher {
    private String staffId;            // 教师ID，对应数据库中的 staff_id
    private String name;               // 教师姓名，对应数据库中的 name
    private String password;           // 密码，对应数据库中的 password
    private String sex;                // 性别，对应数据库中的 sex
    private String dateOfBirth;        // 出生日期，对应数据库中的 date_of_birth
    private String professionalRanks;  // 职称，对应数据库中的 professional_ranks
    private Double salary;             // 工资，对应数据库中的 salary
    private String deptId;             // 院系ID，对应数据库中的 dept_id
}