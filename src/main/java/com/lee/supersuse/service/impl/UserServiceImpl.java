package com.lee.supersuse.service.impl;

import com.lee.supersuse.mapper.UserMapper;
import com.lee.supersuse.pojo.User;
import com.lee.supersuse.pojo.UserEditView;
import com.lee.supersuse.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class UserServiceImpl implements UserService  {
    @Resource
    private UserMapper userMapper;

    @Override
    public void addUser(User user) {
        this.userMapper.insertUser(user);
    }

    @Override
    public User selectUserByName(String username) {
        return this.userMapper.selectUserByName(username);
    }

    @Override
    public UserEditView getUserEditViewByUserId(Integer userId) {
        if (userId != null) {
            UserEditView userEditView = userMapper.selectEditViewByUserId(userId);
            return userEditView;
        } else {
            return null;
        }
    }

    @Override
    public boolean checkPassword(Integer userId,String oldPassword, String newPassword) {
        //修改密码
        Integer i = userMapper.updatePassword(userId,oldPassword,newPassword);
        if(i == 1){
            return true;
        }else {
            return false;
        }
    }


}
