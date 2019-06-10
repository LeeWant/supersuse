package com.lee.supersuse.admin_service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lee.supersuse.admin_service.AdminAffairService;
import com.lee.supersuse.mapper.AdminAffairMapper;
import com.lee.supersuse.mapper.FileMapper;
import com.lee.supersuse.pojo.MyFile;
import com.lee.supersuse.utils.DeleteFileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional
public class AdminAffairServiceImpl implements AdminAffairService {

    //导入文件储存地址
    @Value("${my.path}")
    private String path;

    @Resource
    AdminAffairMapper adminAffairMapper;
    @Resource
    FileMapper fileMapper;

    @Override
    public List<Map<String,Object>> getAllAffairs() {
        return adminAffairMapper.selectAllAffairs();
    }

    @Override
    public PageInfo getAllAffairsByPage(Integer page, Integer limit,Map map) {
        //因为PageHelper计算页码时默认为0页开始
        page -= 1;
        PageHelper.offsetPage(page * limit, limit);
        List<Map<String,Object>> affairs = adminAffairMapper.selectAllAffairsBySearch(map);
        return new PageInfo(affairs);
    }

    @Override
    public Integer changeAffairStatus(String affId, Integer isDelete) {
        //更改上传的isDelete状态
        log.info("更改事务状态："+isDelete);
        if(isDelete == 1){
            isDelete = 0;
        }else {
            isDelete = 1;
        }
        //更改文件的可见状态
        return adminAffairMapper.updateAffairStatus(affId,isDelete);
    }

    @Override
    public Integer deleteAffair(String affId) {
        //删除事务的同时删除文件
        fileMapper.deleteFilesByAffId(affId);
        Integer count = adminAffairMapper.deleteAffairById(affId);
        //删除实际存在的文件
        DeleteFileUtil.deleteDirectory(path+ File.separator+affId);
        return count;
    }

    @Override
    public Integer deleteAffairs(List<String> affIds) {
        //count记录被删除的数据的数量
        Integer count = 0;
        for (String aff:affIds
             ) {
            count += this.deleteAffair(aff);
        }
        return count;
    }

    @Override
    public PageInfo getAllFiles(Integer page, Integer limit, Map map) {
        //因为PageHelper计算页码时默认为0页开始
        page -= 1;
        PageHelper.offsetPage(page * limit, limit);
        List<Map<String,Object>> files = fileMapper.selectAllFiles(map);
        return new PageInfo(files);
    }

    @Override
    public Integer changeFileStatus(String fileId, Integer isDelete) {
        //更改上传的isDelete状态
        if(isDelete == 1){
            isDelete = 0;
        }else {
            isDelete = 1;
        }
        //更改文件的可见状态
        return fileMapper.updateFileStatus(fileId,isDelete);
    }

    @Override
    public Integer deleteFile(String fileId) {
        //删除文件
        MyFile myFile = fileMapper.selectFileByFileId(fileId);
        Integer count = fileMapper.deleteFileById(fileId);
        //删除实际存在的文件
        DeleteFileUtil.deleteFile(myFile.getFilePath());
        return count;
    }

    @Override
    public Integer deleteFiles(List<String> fileIds) {
        Integer count = 0;
        for (String fileId:fileIds
             ) {
            //计算被删除的数量
            count += this.deleteFile(fileId);
        }
        return count;
    }

    @Override
    public Integer insertFile(MyFile file) {
        //向数据库插入一个新文件
        return fileMapper.insertFile(file);
    }

}
