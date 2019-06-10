package com.lee.supersuse.service.impl;

import com.lee.supersuse.mapper.ClazzMapper;
import com.lee.supersuse.mapper.UserMapper;
import com.lee.supersuse.pojo.User;
import com.lee.supersuse.service.UserManageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
@Transactional
public class UserManageServiceImpl implements UserManageService {

    @Resource
    UserMapper userMapper;
    @Resource
    ClazzMapper clazzMapper;

    /**
     * 批量添加用户
     * @param users
     */
    @Override
    public Integer addStudents(List<User> users) {
        Integer count = 0;
        for (User user0:users
             ) {
            User user = new User();
            user.setType(2);
            user.setPassword("123");
            user.setNumber(user0.getNumber());
            user.setName(user0.getName());
            user.setClazzId(user0.getClazzId());
            log.info("插入数据："+user.toString());
            count += userMapper.insertUser(user);
        }
        log.info("一共"+users.size()+"条用户数据，成功插入"+count+"条数据，其中重复数据"+(users.size()-count)+"条");
        return count;
    }

    /**
     * 单独添加
     * @param user
     * @return
     */
    @Override
    public Integer addStudent(User user,Integer clazzId) {
        //封装数据
        user.setType(2);
        user.setPassword("123");
        user.setClazzId(clazzId);
        if(user.getUserId() != null){
            return userMapper.updateUserInfo(user);
        }
        return userMapper.insertUser(user);
    }


    @Override
    public void deleteUser(Integer userId,Integer clazzId,Integer monitorId) {
        //当被删除的用户是班长时
        if(userId == monitorId){
            //先将班级管理员清除
            clazzMapper.updateMonitorId(clazzId,null);
        }
        userMapper.deleteUserById(userId);
    }

    @Override
    public void deleteUserBatch(List<Integer> userIds, Integer clazzId, Integer monitorId) {
        for (Integer userId:userIds
             ) {
            //如果被删除用户为班长的执行
            if(userId == monitorId){
                //先将班级管理员清除
                clazzMapper.updateMonitorId(clazzId,null);
            }
            userMapper.deleteUserById(userId);
        }
    }
}
