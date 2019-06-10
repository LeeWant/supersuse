package com.lee.supersuse.mapper;

import com.lee.supersuse.pojo.User;
import com.lee.supersuse.pojo.UserEditView;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    /**
     * 插入用户
     * 当存在时自动忽略
     * @param user
     */
    Integer insertUser(User user);

    /**
     * 查询一个用户通过用户名
     *
     * @param username
     */
    User selectUserByName(String username);

    /**
     * 查询编辑信息所返回的用户视图数据
     *
     * @param userId
     * @return
     */
    UserEditView selectEditViewByUserId(Integer userId);

    /**
     * 更新密码
     * @param userId
     * @param newPassword
     */
    Integer updatePassword(@Param("userId") Integer userId,
                        @Param("oldPassword") String oldPassword,
                        @Param("newPassword") String newPassword);

    /**
     * 删除用户
     * @param userId
     */
    void deleteUserById(Integer userId);

    /**
     * 更新用户数据
     * @param user
     * @return
     */
    Integer updateUserInfo(User user);

}
