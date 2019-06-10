package com.lee.supersuse.mapper;

import com.lee.supersuse.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AdminUserMapper {
    /**
     * 获取用户通过学号或工号
     * @param number
     * @return
     */
    User selectUserByNumber(String number);
    /**
     * 更新用户信息
     * @param user
     * @return
     */
    Integer updateUserInfo(User user);
    /**
     * 搜索用户
     * @param userId
     * @return
     */
    User selectUserById(Integer userId);
    /**
     * 重置用户密码
     * @param userId
     * @return
     */
    Integer updateUserPassword(@Param("userId") Integer userId,@Param("password") String password);

    /**
     * 通过用户类型获取用户信息
     * @param type
     * @param search
     * @return
     */
    List<Map<String,Object>> selectAllUserByType(@Param("type") Integer type, @Param("search") String search);

    /**
     * 停用用户
     * @param isDelete
     * @param userId
     * @return
     */
    Integer updateUserIsDelete(Integer isDelete,Integer userId);

    /**
     * 删除用户
     * @param userId
     * @return
     */
    Integer deleteUser(Integer userId);

    /**
     * 插入用户
     * @param user
     * @return
     */
    Integer insertUser(User user);

    /**
     * 分页查找所有学生
     * @param map
     * @return
     */
    List<Map<String,Object>> selectAllStudentByPage(Map map);

    /**
     * 分页查询所有的教师
     * @param map
     * @return
     */
    List<Map<String,Object>> selectAllTeacherByPage(Map map);

    /**
     * 删除该班级的所有学生
     * @param clazzId
     * @return
     */
    Integer deleteStudentByClazzId(Integer clazzId);
}
