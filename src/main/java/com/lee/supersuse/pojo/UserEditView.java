package com.lee.supersuse.pojo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 该类用于用户查看个人信息时所返回的信息
 */
@Data
public class UserEditView {


    private Integer userId;
    private String name;
    private Integer userType;
    private String userCode;
    private Date birth;
    private String clazz;
    private Integer type;
    private Integer sex;
    private String phone;
    private String number;
    private Integer clazzId;

    //用户的角色
    private List<String> userRole;
    //为教师时显示所管理的班级
    private List<String> clazzList;

}
