package com.lee.supersuse.admin_service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lee.supersuse.admin_service.AdminUserService;
import com.lee.supersuse.enums.ErrorEnum;
import com.lee.supersuse.exception.JsonException;
import com.lee.supersuse.exception.WebException;
import com.lee.supersuse.mapper.AdminUserMapper;
import com.lee.supersuse.mapper.UserMapper;
import com.lee.supersuse.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional
public class AdminUserServiceImpl implements AdminUserService {


    @Resource
    AdminUserMapper adminUserMapper;

    @Override
    public Integer updateUserInfo(User user) {
        if (user.getNumber().equals(user.getNumberOld())) {
            return adminUserMapper.updateUserInfo(user);
        } else {
            User u = adminUserMapper.selectUserByNumber(user.getNumber());
            if (u == null)
                return adminUserMapper.updateUserInfo(user);
        }
        throw new JsonException(ErrorEnum.USER_ERROR);
    }

    @Override
    public User editUserById(Integer userId) {
        return adminUserMapper.selectUserById(userId);
    }

    @Override
    public Integer repassUser(Integer userId) {
        //重置密码为123
        return adminUserMapper.updateUserPassword(userId, "123");
    }

    @Override
    @Deprecated
    public PageInfo getStudentListByPage(Integer pageNum, Integer pageSize, String search) {
        pageNum -= 1;
        PageHelper.offsetPage(pageNum * pageSize, pageSize);
        List<Map<String, Object>> students = adminUserMapper.selectAllUserByType(2, search);
        return new PageInfo(students);
    }

    @Override
    public PageInfo getStudentListByPage(Integer page, Integer limit, Map map) {
        //因为PageHelper计算页码时默认为0页开始
        page -= 1;
        PageHelper.offsetPage(page * limit, limit);
        List<Map<String, Object>> students = adminUserMapper.selectAllStudentByPage(map);
        return new PageInfo(students);
    }

    @Override
    public PageInfo getTeacherListByPage(Integer page, Integer limit, Map map) {
        //因为PageHelper计算页码时默认为0页开始
        page -= 1;
        PageHelper.offsetPage(page * limit, limit);
        List<Map<String, Object>> teachers = adminUserMapper.selectAllTeacherByPage(map);
        return new PageInfo(teachers);
    }

    @Override
    @Deprecated
    public PageInfo getTeacherListByPage(Integer pageNum, Integer pageSize, String search) {
        pageNum -= 1;
        PageHelper.offsetPage(pageNum * pageSize, pageSize);
        List<Map<String, Object>> students = adminUserMapper.selectAllUserByType(4, search);
        return new PageInfo(students);
    }

    @Override
    public List<Map<String, Object>> getAllUserByType(Integer type) {
        return adminUserMapper.selectAllUserByType(type, null);
    }

    @Override
    public Integer stopUserStatus(Integer userId, Integer isDelete) {
        if (isDelete == 1) {
            isDelete = 0;
        } else {
            isDelete = 1;
        }
        return adminUserMapper.updateUserIsDelete(isDelete, userId);
    }

    @Override
    public Integer deleteUsers(Integer[] userIds) {
        Integer count = 0;
        for (Integer uid : userIds
        ) {
            adminUserMapper.deleteUser(uid);
            count++;
        }
        return count;
    }

    @Override
    public Integer deleteUser(Integer userId) {
        return adminUserMapper.deleteUser(userId);
    }

    @Override
    public Integer addUser(User user, Integer type) {
        int count = 0;
        if (user != null) {
            user.setType(type);
            return adminUserMapper.insertUser(user);
        }
        return count;
    }
}
