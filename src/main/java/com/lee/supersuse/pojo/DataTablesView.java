package com.lee.supersuse.pojo;

import java.util.List;

/**
 * 自定义的类，用于配合前端DataTables框架
 * @param <T>
 */
public class DataTablesView<T> {
    //DataTables需要的数据
    private Integer draw;
    //DataTables需要的数据
    private Long recordsTotal;
    //DataTables需要的数据
    private Long recordsFiltered;
    private List<T> data;


    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public Long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DataTablesView{" +
                "draw=" + draw +
                ", recordsTotal=" + recordsTotal +
                ", recordsFiltered=" + recordsFiltered +
                ", data=" + data +
                '}';
    }
}
