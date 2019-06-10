package com.lee.supersuse.service;

import com.lee.supersuse.pojo.User;

import java.util.List;

public interface UserManageService {

    /**
     * 批量插入用户
     * @param users
     */
    Integer addStudents(List<User> users);

    /**
     * 单独插入用户
     * @param user
     * @return
     */
    Integer addStudent(User user,Integer clazzId);

    /**
     * 删除用户
     * @param userId
     */
    void deleteUser(Integer userId,Integer clazzId,Integer monitorId);

    /**
     * 批量删除用户
     * @param userIds
     * @param clazzId
     * @param monitorId
     */
    void deleteUserBatch(List<Integer> userIds,Integer clazzId,Integer monitorId);



}
