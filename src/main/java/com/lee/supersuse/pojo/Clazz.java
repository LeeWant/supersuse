package com.lee.supersuse.pojo;

import lombok.Data;

import java.util.Date;

/**
 * 班级信息
 */
@Data
public class Clazz {
    //班级id
    private Integer clazzId;
    //专业代码
    private String majoCode;
    //专业名
    private String majorName;
    //班级编号
    private String clazzNumber;
    //入学年份
    private String year;
    //辅导员id
    private Integer teacherId;
    //辅导员姓名
    private String teacherName;
    //班级管理员id
    private Integer monitorId;
    //班级管理员姓名
    private String monitorName;
    //班级学生数量
    private Integer studentNum;
    //备注
    private String remark;
    //注册时间
    private Date loginTime;
    //是否活跃
    private Integer isActivity;

}
