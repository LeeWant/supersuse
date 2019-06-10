package com.lee.supersuse.pojo;

import java.util.List;

/**
 * 用户登录视图用于登录信息的权限验证
 */
public class UserLoginView {

    private Integer userId;
    private String name;
    private String number;
    private Integer sex;
    private String clazzNumber;
    private Integer type;
    private String instCode;
    private String deptCode;
    private String majoCode;

    //用户角色代码
    private List<String> roleCodes;
    //可以访问的菜单代码
    private List<String> menuCodes;
    //可以访问的资源代码
    private List<String> resCodes;


    @Override
    public String toString() {
        return "UserLoginView{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", sex=" + sex +
                ", clazzNumber='" + clazzNumber + '\'' +
                ", type=" + type +
                ", instCode='" + instCode + '\'' +
                ", deptCode='" + deptCode + '\'' +
                ", majoCode='" + majoCode + '\'' +
                ", roleCodes=" + roleCodes +
                ", menuCodes=" + menuCodes +
                ", resCodes=" + resCodes +
                '}';
    }

    public UserLoginView() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getClazzNumber() {
        return clazzNumber;
    }

    public void setClazzNumber(String clazzNumber) {
        this.clazzNumber = clazzNumber;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getInstCode() {
        return instCode;
    }

    public void setInstCode(String instCode) {
        this.instCode = instCode;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getMajoCode() {
        return majoCode;
    }

    public void setMajoCode(String majoCode) {
        this.majoCode = majoCode;
    }

    public List<String> getRoleCodes() {
        return roleCodes;
    }

    public void setRoleCodes(List<String> roleCodes) {
        this.roleCodes = roleCodes;
    }

    public List<String> getMenuCodes() {
        return menuCodes;
    }

    public void setMenuCodes(List<String> menuCodes) {
        this.menuCodes = menuCodes;
    }

    public List<String> getResCodes() {
        return resCodes;
    }

    public void setResCodes(List<String> resCodes) {
        this.resCodes = resCodes;
    }
}
