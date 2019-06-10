package com.lee.supersuse.service.impl;

import com.lee.supersuse.mapper.MajorMapper;
import com.lee.supersuse.pojo.Department;
import com.lee.supersuse.pojo.Institute;
import com.lee.supersuse.pojo.Major;
import com.lee.supersuse.service.MajorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MajorServiceIml implements MajorService {

    @Resource
    private MajorMapper majorMapper;

    /**
     * 查询所有学院
     * @return
     */
    @Override
    public List<Institute> selectAllInstitute() {
        return majorMapper.selectAllInstitute();
    }

    /**
     * 查询所有系
     * @return
     */
    @Override
    public List<Department> selectAllDepartment() {
        return majorMapper.selectAllDepartment();
    }

    /**
     * 级联查询学院下的所有系
     * @param instCode
     * @return
     */
    @Override
    public List<Department> selectDepartmentByInstCode(String instCode) {
        List<Department> departments = new ArrayList<>();
        if(instCode != null){
            departments = majorMapper.selectDepartmentByInstCode(instCode);
            if(instCode.equals("ALLINS")){
                //防止重复出现'所有部门'
                return departments;
            }else {
                //将所有部门选项加入
                departments.add(0,new Department("ALLDEP","ALLINS","不限系"));
                return departments;
            }
        }
        return departments;
    }

    /**
     * 查询对应系代码下的所有专业
     * @param deptCode
     * @return
     */
    @Override
    public List<Major> selectMajorByDeptCode(String deptCode) {
        List<Major> majors = new ArrayList<>();
        if(deptCode != null){
            majors = majorMapper.selectMajorByDeptCode(deptCode);
            if(deptCode.equals("ALLDEP")){
                //防止重复出现'所有系'
                return majors;
            }else {
                //将所有专业选项加入
                majors.add(0,new Major("ALLMAJ","ALLDEP","不限专业"));
                return majors;
            }
        }




        return majorMapper.selectMajorByDeptCode(deptCode);
    }

    /**
     * 查询所有专业
     * @return
     */
    @Override
    public List<Major> selectAllMajor() {
        return majorMapper.selectAllMajor();
    }


}
