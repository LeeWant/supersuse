package com.lee.supersuse.pojo;


import lombok.Data;

/**
 * 用于返回Json数据
 * @param <T>
 */
@Data
public class Result<T> {
    /** 错误码 */
    private Integer code;
    /** 错误信息 */
    private String msg;
    /**
     * 数据数量
     */
    private Integer count;
    /**
     * 总页数
     */
    private Integer pages;
    /** 数据内容 */
    private T data;
}
