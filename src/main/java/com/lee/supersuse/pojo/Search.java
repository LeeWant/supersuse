package com.lee.supersuse.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Search {

    private Integer iid;
    private String sid;
    private Date start;
    private Date end;
    private String instCode;
    private String deptCode;
    private String majoCode;

}
