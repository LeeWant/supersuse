package com.lee.supersuse.service;

import com.lee.supersuse.pojo.Department;
import com.lee.supersuse.pojo.Institute;
import com.lee.supersuse.pojo.Major;

import java.util.List;

public interface MajorService {

    /**
     * 查询所有的学院
     * @return
     */
    List<Institute> selectAllInstitute();

    /**
     * 查询所有的系
     * @return
     */
    List<Department> selectAllDepartment();

    /**
     * 通过学院编号查询该学院所拥有的系
     * @param instCode
     * @return
     */
    List<Department> selectDepartmentByInstCode(String instCode);

    /**
     * 查询所有的专业
     * @return
     */
    List<Major> selectAllMajor();

    /**
     * 通过系编号查询所属该系的所有专业
     * @param deptCode
     * @return
     */
    List<Major> selectMajorByDeptCode(String deptCode);




}
