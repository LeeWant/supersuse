package com.lee.supersuse.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class MyFile {
    private String fileId;
    private String affId;
    private String fileName;
    private String filePath;
    private Integer isDelete;
    private Date uploadTime;

}
