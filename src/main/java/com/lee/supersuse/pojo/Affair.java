package com.lee.supersuse.pojo;

import lombok.Data;

import java.util.Date;


@Data
public class Affair {

    private String affId;
    private Integer userId;
    private String title;
    private Integer level;
    private String comment;
    private Date releaseTime;
    private String instCode;
    private String deptCode;
    private String majoCode;
    private Integer isDelete;
    private String status;
    private String instPath;
    private String name;
}

