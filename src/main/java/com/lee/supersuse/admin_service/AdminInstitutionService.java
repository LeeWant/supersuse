package com.lee.supersuse.admin_service;

import com.github.pagehelper.PageInfo;
import com.lee.supersuse.pojo.Clazz;
import com.lee.supersuse.pojo.Department;
import com.lee.supersuse.pojo.Institute;
import com.lee.supersuse.pojo.Major;

import java.util.List;
import java.util.Map;

public interface AdminInstitutionService {
    /**
     * 根据系代码获取专业信息
     * @return
     */
    List<Map<String,Object>> getMajorsByDeptCode(String deptCode);
    /**
     * 批量删除学院
     * @param instCodes
     * @return
     */
    Integer deleteAllInsts(String[] instCodes);
    /**
     * 删除学院
     * @return
     */
    Integer deleteInstByCode(String instCode);
    /**
     * 批量删除系
     * @param deptCodes
     * @return
     */
    Integer deleteAllDepts(String[] deptCodes);

    /**
     * 根据deptCode删除系
     * @param deptCode
     * @return
     */
    Integer deleteDeptByCode(String deptCode);
    /**
     * 更新系
     * @param department
     * @return
     */
    Integer updateDepartment(Department department);
    /**
     * 根据DeptCode查询InstCode和DeptCode
     * @param deptCode
     * @return
     */
    Map<String,Object> getCodesByDeptCode(String deptCode);
    /**
     * 添加一个新的系
     * @param department
     * @return
     */
    Integer addDept(Department department);
    /**
     * 批量删除专业
     * @param majoCodes
     * @return
     */
    Integer deleteAllMajors(String[] majoCodes);
    /**
     * 删除专业根据专业代码
     * @param majoCode
     * @return
     */
    Integer deleteMajorByCode(String majoCode);
    /**
     * 更新专业信息
     * @param major
     * @return
     */
    Integer updateMajor(Major major);

    /**
     * 获取系信息通过学院代码
     * @param instCode
     * @return
     */
    List<Map<String,Object>> getDeptsByInstCode(String instCode);
    /**
     * 获取当前专业的上级代码
     * @param majoCode
     * @return
     */
    Map<String,Object> getCodesByMajoCode(String majoCode);

    /**
     * 分页获取所有的学院
     * @param page
     * @param limit
     * @param map
     * @return
     */
    PageInfo getAllInstByPage(Integer page, Integer limit, Map map);

    /**
     * 修改学院信息
     * @param instCode
     * @return
     */
    Institute getInstByCode(String instCode);

    /**
     * 更新学院，只能更新名称
     * @return
     */
    Integer updateInst(Institute institute);

    /**
     * 插入一个新的学院
     * @return
     */
    Integer addInst(Institute institute);

    /**
     * 获取所有的学院信息
     * @return
     */
    List<Map<String,Object>> getAllInsts();

    /**
     * 分页获取所有的部门信息
     * @param page
     * @param limit
     * @param map
     * @return
     */
    PageInfo getAllDeptByPage(Integer page,Integer limit,Map map);

    /**
     * 获取所有的系信息
     * @return
     */
    List<Map<String,Object>> getAllDepts();

    /**
     * 分页获取所有的专业信息
     * @param page
     * @param limit
     * @param map
     * @return
     */
    PageInfo getAllMajoByPage(Integer page,Integer limit,Map map);

    /**
     * 分页获取所有的班级信息
     * @param page
     * @param limit
     * @return
     */
    PageInfo getAllClazzByPage(Integer page,Integer limit,Map map);

    /**
     * 获取所有的学年信息
     * @return
     */
    List<String> getClazzYears();

    /**
     * 获取初班级的始化代码
     * @param clazzId
     * @return
     */
    Map<String,Object> getCodesByClazzId(Integer clazzId);

    /**
     * 删除班级
     * @param clazzId
     * @return
     */
    Map<String,Integer> deleteClazz(Integer clazzId);

    /**
     * 批量删除班级
     * @param clazzIds
     * @return
     */
    Map<String,Integer> deleteClazzAll(Integer[] clazzIds);

    /**
     * 新增班级
     * @param clazz
     * @return
     */
    Integer addClazz(Clazz clazz);

    /**
     * 更新班级信息
     * @param clazz
     * @return
     */
    Integer updateClazz(Clazz clazz);

    /**
     * 添加专业
     * @param major
     * @return
     */
    Integer addMajor(Major major);

    /**
     * 查询专业通过专业代码
     * @param majoCode
     * @return
     */
    Major getMajorByMajoCode(String majoCode);
}
