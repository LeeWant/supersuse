package com.lee.supersuse.pojo;

import lombok.Data;

@Data
public class Department {

    private String deptCode;
    private String instCode;
    private String name;
    private String deptCodeOld;

    public Department(String deptCode, String instCode, String name) {
        this.deptCode = deptCode;
        this.instCode = instCode;
        this.name = name;
    }

    public Department() {
    }
}
