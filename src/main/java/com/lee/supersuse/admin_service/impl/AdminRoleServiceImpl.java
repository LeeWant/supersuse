package com.lee.supersuse.admin_service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lee.supersuse.admin_service.AdminRoleService;
import com.lee.supersuse.mapper.AdminRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional
public class AdminRoleServiceImpl implements AdminRoleService {

    @Resource
    AdminRoleMapper adminRoleMapper;

    @Override
    public PageInfo getLoginInfoByPage(Integer page, Integer limit) {
        //因为PageHelper计算页码时默认为0页开始
        page -= 1;
        PageHelper.offsetPage(page * limit, limit);
        List<Map<String, Object>> loginInfo = adminRoleMapper.selectLoginInfo();
        return new PageInfo(loginInfo);
    }
}
