package com.lee.supersuse.service;

import com.github.pagehelper.PageInfo;
import com.lee.supersuse.pojo.DateCountView;
import com.lee.supersuse.pojo.Ip;
import com.lee.supersuse.pojo.UserLoginView;


public interface ViewService {


    /**
     * 查询所有未被删除的Affair与其对应的发布人名称信息
     * @param start
     * @param length
     * @param isDelete
     * @param search 用于模糊查询title
     * @return
     */
    PageInfo getAffairViewsByPage(Integer start, Integer length, Integer isDelete,String search,UserLoginView userLoginView);

    /**
     * 通过登录的学号或工号查询该用户所属角色以及该角色所拥有的资源代码
     * @param logincode
     * @param password
     * @return
     */
    UserLoginView userLoginCheck(String logincode, String password, Integer type);

    /**
     * insert访问用户IP信息
     * @param ip
     * @return
     */
    Integer insertIpVisit(Ip ip);

    /**
     * 获取数量统计View
     * @return
     */
    DateCountView getCountView();

}
