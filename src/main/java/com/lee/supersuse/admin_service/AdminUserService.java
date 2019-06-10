package com.lee.supersuse.admin_service;

import com.github.pagehelper.PageInfo;
import com.lee.supersuse.pojo.User;

import java.util.List;
import java.util.Map;

public interface AdminUserService {
    /**
     * 更新用户信息
     * @param user
     * @return
     */
    Integer updateUserInfo(User user);

    /**
     * 访问修改用户界面
     * @param userId
     * @return
     */
    User editUserById(Integer userId);
    /**
     * 重置用户密码
     * @param userId
     * @return
     */
    Integer repassUser(Integer userId);

    /**
     * 分页获取学生列表
     * @param pageNum
     * @param pageSize
     * @param search
     * @return
     */
    @Deprecated
    PageInfo getStudentListByPage(Integer pageNum, Integer pageSize, String search);

    /**
     * 分页获取教师列表
     * @param pageNum
     * @param pageSize
     * @param search
     * @return
     */
    @Deprecated
    PageInfo getTeacherListByPage(Integer pageNum, Integer pageSize, String search);

    /**
     * 分页获取学生列表
     * @param page
     * @param limit
     * @param map
     * @return
     */
    PageInfo getStudentListByPage(Integer page,Integer limit,Map map);

    /**
     * 分页获取所有的教师信息
     * @param page
     * @param limit
     * @param map
     * @return
     */
    PageInfo getTeacherListByPage(Integer page,Integer limit,Map map);

    /**
     * 根据用户type获取用户信息
     * @param type
     * @return
     */
    List<Map<String,Object>> getAllUserByType(Integer type);

    /**
     * 停用用户
     * @param userId
     * @return
     */
    Integer stopUserStatus(Integer userId,Integer isDelete);

    /**
     * 批量删除用户
     * @param userIds
     * @return
     */
    Integer deleteUsers(Integer[] userIds);
    /**
     * 删除用户
     * @param userId
     * @return
     */
    Integer deleteUser(Integer userId);

    /**
     * 添加用户
     * @param user
     * @param type
     * @return
     */
    Integer addUser(User user,Integer type);

}
