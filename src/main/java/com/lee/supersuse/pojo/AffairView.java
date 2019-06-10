package com.lee.supersuse.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class AffairView {
    private String affId;
    private Integer userId;
    private String name;
    private String levelName;
    private String title;
    private Date releaseTime;

    public AffairView() {
    }

}
