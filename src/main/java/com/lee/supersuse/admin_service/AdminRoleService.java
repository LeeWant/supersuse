package com.lee.supersuse.admin_service;

import com.github.pagehelper.PageInfo;

public interface AdminRoleService {
    /**
     * 分页返回登录信息
     * @param page
     * @param limit
     * @return
     */
    PageInfo getLoginInfoByPage(Integer page,Integer limit);
}
