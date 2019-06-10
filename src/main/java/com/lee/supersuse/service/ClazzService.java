package com.lee.supersuse.service;

import com.lee.supersuse.pojo.Clazz;
import com.lee.supersuse.pojo.UserEditView;
import com.lee.supersuse.pojo.UserLoginView;

import java.util.List;
import java.util.Map;

/**
 * 用于班级的服务接口
 */
public interface ClazzService {

    List<Clazz> selectClazzByTeacherId(Integer teacherId);

    boolean editClazzInfo(Integer clazzId,String remark,Integer userId);

    List<UserEditView> getStudents(Integer clazzId);

    Map<String,Object> getClazzInfo(Integer clazzId, UserLoginView userLoginView);

}
