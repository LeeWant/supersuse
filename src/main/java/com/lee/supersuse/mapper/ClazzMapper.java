package com.lee.supersuse.mapper;

import com.lee.supersuse.pojo.Clazz;
import com.lee.supersuse.pojo.User;
import com.lee.supersuse.pojo.UserEditView;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用于班级管理的类
 */
public interface ClazzMapper {
    /**
     * 查询教师id管理的班级
     *
     * @param teacherId
     * @return
     */
    List<Clazz> selectClazzByTeacherId(Integer teacherId);

    /**
     * 更新班级信息
     *
     * @param clazzId
     * @param remark
     * @param monitorId
     * @return
     */
    Integer updateClazzInfo(@Param("clazzId") Integer clazzId,
                            @Param("remark") String remark,
                            @Param("monitorId") Integer monitorId);

    /**
     * 更新班级管理员
     *
     * @param clazzId
     * @param monitorId
     * @return
     */
    Integer updateMonitorId(@Param("clazzId") Integer clazzId,
                            @Param("monitorId") Integer monitorId);

    /**
     * 查询班级里的所有学生
     *
     * @param clazzId
     * @return
     */
    List<UserEditView> getStudentByClazzId(Integer clazzId);

    /**
     * 查询班级信息根据班级id
     *
     * @param clazzId
     * @return
     */
    Clazz selectClazzById(Integer clazzId);
}
