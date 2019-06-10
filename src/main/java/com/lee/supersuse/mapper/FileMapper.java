package com.lee.supersuse.mapper;

import com.lee.supersuse.pojo.MyFile;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface FileMapper {


    /**
     * 查询事务对应的File
     * @param affId
     * @return
     */
    List<Map<String,Object>> selectFilesByAffId(String affId);

    /**
     * 查询所有的File
     * @return
     */
    List<Map<String,Object>> selectAllFiles(Map map);

    /**
     * 更新文件的删除状态
     * @param fileId
     * @param isDelete
     * @return
     */
    Integer updateFileStatus(@Param("fileId") String fileId, @Param("isDelete") Integer isDelete);

    /**
     * 删除
     * @param fileId
     * @return
     */
    Integer deleteFileById(String fileId);

    /**
     * 通过AffId删除文件
     * @param affId
     * @return
     */
    Integer deleteFilesByAffId(String affId);

    /**
     * 查找文件通过fileId
     * @param fileId
     * @return
     */
    MyFile selectFileByFileId(String fileId);

    /**
     * 添加事务的附件
     * @param myFile
     */
    Integer insertFile(MyFile myFile);

}
