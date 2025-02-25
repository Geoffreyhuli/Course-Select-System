package com.auggie.student_server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

/**
 * @Auther: auggie
 * @Date: 2022/2/15 10:00
 * @Description: Department
 * @Version 1.0.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("Department")
public class Department {
    private String deptId;        // 院系ID，对应数据库中的 dept_id
    private String deptName;      // 院系名称，对应数据库中的 dept_name
    private String address;       // 地址，对应数据库中的 address
    private String phoneCode;     // 电话代码，对应数据库中的 phone_code
}