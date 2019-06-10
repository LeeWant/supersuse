package com.lee.supersuse.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class User {
    private Integer userId;
    private Integer clazzId;
    private String name;
    private String password;
    private String number;
    private Integer sex;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;
    private String phone;
    private Integer type;
    private Date loginTime;
    private Date lastLogin;
    private Integer isDelete;
    private String numberOld;

    public User() {
    }

}
