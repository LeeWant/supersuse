package com.lee.supersuse.pojo;

import lombok.Data;

/**
 * 学院实体类
 */
@Data
public class Institute {
    //学院代码
    private String instCode;
    //学院名称
    private String name;

    private String instCodeOld;

    public Institute() {
    }


}
