package com.lee.supersuse.mapper;

import com.lee.supersuse.pojo.AffairView;
import com.lee.supersuse.pojo.Ip;
import com.lee.supersuse.pojo.UserLoginView;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用于视图查询的Mapper
 */
public interface ViewMapper {
    /**
     * 用户登录验证
     * @param logincode
     * @param password
     * @return
     */
    UserLoginView userLoginCheck(@Param("number") String logincode,
                                 @Param("password") String password);

    /**
     * 查询所有未被删除的Affair与其对应的发布人名称信息
     * @param isDelete
     * @return
     */
    List<AffairView> selectAffairViewsByPage(@Param("isDelete") Integer isDelete,
                                             @Param("search") String search);
    /**
     * 查询userId发布的所有未被删除的Affair与信息
     * @param isDelete
     * @return
     */
    List<AffairView> selectAffairViewsByUserId(@Param("isDelete") Integer isDelete,
                                             @Param("userId") Integer userId);

    /**
     * 查询所有未被删除的AffairView
     * @param isDelete
     * @param search 模糊查询关键字
     * @param userLoginView 用户视图
     * @return
     */
    List<AffairView> selectAffairViewsByPageAndRole(@Param("isDelete") Integer isDelete,
                                                    @Param("search") String search,
                                                    @Param("user") UserLoginView userLoginView);

    /**
     * 记录每次访问的用户
     * @param ip
     * @return
     */
    Integer insertIpVisit(Ip ip);
}
