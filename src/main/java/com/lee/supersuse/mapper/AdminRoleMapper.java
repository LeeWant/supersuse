package com.lee.supersuse.mapper;

import java.util.List;
import java.util.Map;

public interface AdminRoleMapper {
    /**
     * 查询登录信息
     * @return
     */
    List<Map<String,Object>> selectLoginInfo();
}
