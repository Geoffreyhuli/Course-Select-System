package com.auggie.student_server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

/**
 * @Auther: auggie
 * @Date: 2022/2/8 16:11
 * @Description: Student
 * @Version 1.0.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("Student")
public class Student {
    private String studentId;      // 学生ID，对应数据库中的 student_id
    private String name;           // 学生姓名，对应数据库中的 name
    private String password;       // 密码，对应数据库中的 password
    private String sex;            // 性别，对应数据库中的 sex
    private String dateOfBirth;    // 出生日期，对应数据库中的 date_of_birth
    private String nativePlace;    // 籍贯，对应数据库中的 native_place
    private String mobilePhone;    // 手机号，对应数据库中的 mobile_phone
    private String deptId;         // 院系ID，对应数据库中的 dept_id
}