package com.lee.supersuse.service.impl;

import com.lee.supersuse.enums.ErrorEnum;
import com.lee.supersuse.exception.WebException;
import com.lee.supersuse.mapper.ClazzMapper;
import com.lee.supersuse.pojo.Clazz;
import com.lee.supersuse.pojo.UserEditView;
import com.lee.supersuse.pojo.UserLoginView;
import com.lee.supersuse.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 班级服务的接口实现
 */
@Service
@Slf4j
@Transactional
public class ClazzServiceImpl implements ClazzService {

    @Resource
    ClazzMapper clazzMapper;

    /**
     * 根据teacherId查询班级
     *
     * @param teacherId
     * @return
     */
    @Override
    public List<Clazz> selectClazzByTeacherId(Integer teacherId) {
        if (teacherId != null) {
            return clazzMapper.selectClazzByTeacherId(teacherId);
        } else return new ArrayList<Clazz>();
    }

    @Override
    public boolean editClazzInfo(Integer clazzId, String remark, Integer monitorId) {
        Integer count = clazzMapper.updateClazzInfo(clazzId, remark, monitorId);
        if (count > -1) {
            return true;
        } else return false;
    }

    @Override
    public List<UserEditView> getStudents(Integer clazzId) {
        return clazzMapper.getStudentByClazzId(clazzId);
    }

    @Override
    public Map<String, Object> getClazzInfo(Integer clazzId, UserLoginView userLoginView) {
        Clazz clazz = clazzMapper.selectClazzById(clazzId);
        //如果班级不存在抛出404错误
        if (clazz == null || clazzId != clazz.getClazzId()) {
            throw new WebException(ErrorEnum.NOT_FOUND_ERROR);
        }
        List<UserEditView> students = clazzMapper.getStudentByClazzId(clazzId);
        Map map = new HashMap();
        map.put("clazz", clazz);
        log.info("查询出的学生信息数量:" + students.size());
        map.put("students", students);
        map.put("studentsNum", students.size());
        log.info("方法getClazzInfo:" + clazz.toString());
        //进行权限判断
        if (!clazz.getTeacherId().equals(userLoginView.getUserId())) {
            for (String roleCode : userLoginView.getRoleCodes()) {
                //为管理员账号放行
                if ("ADMIN".equals(roleCode)) {
                    return map;
                }
            }
            //权限不足
            log.info("教师id："+clazz.getTeacherId() +"  登录用户id："+ userLoginView.getUserId());
            throw new WebException(ErrorEnum.DENIED_ERROR);
        } else return map;

    }
}
