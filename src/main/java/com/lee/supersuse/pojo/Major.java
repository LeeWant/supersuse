package com.lee.supersuse.pojo;

import lombok.Data;

@Data
public class Major {

    private String majoCode;
    private String deptCode;
    private String name;
    private String majoCodeOld;

    public Major() {
    }

    public Major(String majoCode, String deptCode, String name) {
        this.majoCode = majoCode;
        this.deptCode = deptCode;
        this.name = name;
    }

}
