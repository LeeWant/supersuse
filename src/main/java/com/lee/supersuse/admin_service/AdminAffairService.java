package com.lee.supersuse.admin_service;

import com.github.pagehelper.PageInfo;
import com.lee.supersuse.pojo.MyFile;

import java.util.List;
import java.util.Map;

public interface AdminAffairService {

    /**
     * 分页获取所有的事务
     * @return
     */
    List<Map<String,Object>> getAllAffairs();

    /**
     * 分页获取affair list
     * @param page
     * @param limit
     * @param map 参数列表
     * @return
     */
    PageInfo getAllAffairsByPage(Integer page,Integer limit,Map map);

    /**
     * 改变事务的可见性
     * @param affId
     * @param isDelete
     * @return
     */
    Integer changeAffairStatus(String affId,Integer isDelete);

    /**
     * 删除指定Id的事务
     * @return
     */
    Integer deleteAffair(String affId);

    /**
     * 批量删除事务
     * @param affIds
     * @return
     */
    Integer deleteAffairs(List<String> affIds);

    /**
     * 获取所有的文件列表
     * @param page
     * @param limit
     * @param map 参数列表
     * @return
     */
    PageInfo getAllFiles(Integer page,Integer limit,Map map);

    /**
     * 更改文件的删除状态
     * @param fileId
     * @param isDelete
     * @return
     */
    Integer changeFileStatus(String fileId,Integer isDelete);

    /**
     * 根据文件id删除文件
     * @param fileId
     * @return
     */
    Integer deleteFile(String fileId);

    /**
     * 批量删除文件
     * @param fileIds
     * @return
     */
    Integer deleteFiles(List<String> fileIds);

    /**
     * 插入一个文件
     * @param file
     * @return
     */
    Integer insertFile(MyFile file);
}
