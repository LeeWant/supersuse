package com.lee.supersuse.enums;

public enum ErrorEnum implements CodeEnum {
    DENIED_ERROR(401,"您没有权限访问当前页面"),
    NOT_FOUND_ERROR(404,"页面没有找到"),
    DATA_ERROR(-1,"数据插入错误，主键重复"),
    FILE_ERROR(301,"文件内容不符合要求"),
    FILE_UPLOAD_ERROR(302,"文件上传失败"),
    FILE_NOT_TYPE(302,"文件格式不符合要求"),
    UNKNOWN_ERROR(502,"未知错误"),
    CLAZZ_EXIST(601,"班级已存在"),
    MAJOR_ERROR(602,"该专业代码已被使用"),
    DELETE_ERROR(603,"存在关联主键"),
    ALLMAJOR_CANT_DELETE(604,"ALLMAJ不能被删除"),
    ALLDEPT_CANT_DELETE(605,"ALLDEP不能被删除"),
    ALLINS_CANT_DELETE(605,"ALLINS不能被删除"),
    ALLMAJOR_CANT_UPDATE(606,"ALLMAJ不能被修改"),
    ALLDEPT_CANT_UPDATE(607,"ALLDEP不能被修改"),
    ALLINS_CANT_UPDATE(607,"ALLINS不能被修改"),
    DEPT_ERROR(606,"该系代码已被使用"),
    USER_ERROR(607,"工号或学号已被使用"),
    INS_ERROR(608,"学院代码已被使用"),
    NAME_DONE(609,""),
    ;


    ErrorEnum(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }

    private Integer code;
    private String msg;

    @Override
    public String getMsg() {
        return msg;
    }
    @Override
    public Integer getCode() {
        return code;
    }

    public ErrorEnum setMsg(String msg){
        this.msg = msg;
        return this;
    }
}
