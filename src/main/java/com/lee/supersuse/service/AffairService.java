package com.lee.supersuse.service;

import com.lee.supersuse.pojo.Affair;
import com.lee.supersuse.pojo.AffairView;
import com.lee.supersuse.pojo.MyFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface AffairService {
    /**
     * 添加一个新的事宜
     * @param affair
     */
    void insertAffair(Affair affair);

    /**
     * 添加一个附件
     * @param myFile
     */
    void insertFile(MyFile myFile);

    /**
     * 查询当前事务所拥有的文件
     * File类是自定义的File
     * @return
     */
    List<MyFile> selectAffairFiles(String affId);

    /**
     * 查询对应id的文件信息
     * @param fileId
     * @return
     */
    MyFile selectAffairFile(String fileId);

    /**
     * 根据affId获取Affair
     * @param affId
     * @return
     */
    Affair getAffairById(String affId,HttpSession session);

    /**
     * 获取所有的Affair
     * @param isDelete
     * @return
     */
    List<Affair> getAffairs(Integer isDelete);


    /**
     * 根据affairId管理affair
     * @param affairId
     * @return
     */
//    ModelAndView manageAffair(String affairId);


}
