package com.lee.supersuse.mapper;

import com.lee.supersuse.pojo.Affair;
import com.lee.supersuse.pojo.AffairView;
import com.lee.supersuse.pojo.MyFile;

import java.util.Date;
import java.util.List;

public interface AffairMapper {

    /**
     * 添加一个新的事宜
     * @param affair
     */
    void insertAffair(Affair affair);

    /**
     * 添加事务的附件
     * @param myFile
     */
    void insertFile(MyFile myFile);

    /**
     * 分页查询Affair
     * @return
     */
    List<Affair> selectAffairs(Integer isDelete);

    /**
     * 查询当前事务所拥有的文件
     * File类是自定义的File
     * @return
     */
    List<MyFile> selectAffairFiles(String affId);

    /**
     * 查询一个文件的信息
     * @param fileId
     * @return
     */
    MyFile selectAffairFile(String fileId);

    /**
     * 根据affId获取Affair
     * @param affId
     * @return
     */
    Affair selectAffairById(String affId);

    /**
     * 根据affId获取Affair的学院路径
     * @param affId
     * @return
     */
    String selectAffairInstPathById(String affId);
    /**
     * 统计事务数量
     * @param startDate
     * @param endDate
     * @return
     */
    Integer countAffairByDate(Date startDate,Date endDate);

    /**
     * 统计ip访问量
     * @return
     */
    Integer countAllVisitNum();

    /**
     * 删除Affair通过affId
     * @param affId
     * @return
     */
    Integer deleteAffair(String affId);

}
