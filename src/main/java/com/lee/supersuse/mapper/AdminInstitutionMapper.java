package com.lee.supersuse.mapper;

import com.lee.supersuse.pojo.Clazz;
import com.lee.supersuse.pojo.Department;
import com.lee.supersuse.pojo.Institute;
import com.lee.supersuse.pojo.Major;

import java.util.List;
import java.util.Map;

public interface AdminInstitutionMapper {
    /**
     * 查询是否存在同名学院、系、专业
     * @param name
     * @return
     */
    List<Map<String,Object>> selectInstCodeByName(String name);
    /**
     * 更新班级信息
     * @param clazz
     * @return
     */
    Integer updateClazzInfo(Clazz clazz);
    /**
     * 根据班级id查询班级信息
     * @param clazzId
     * @return
     */
    Clazz selectClazzById(Integer clazzId);
    /**
     * 根据系代码选择学院信息
     * @param deptCode
     * @return
     */
    List<Map<String,Object>> selectMajorByDeptCode(String deptCode);
    /**
     * 删除学院
     * @param instCode
     * @return
     */
    Integer deleteInstByCode(String instCode);
    /**
     * 删除系根据系代码
     * @param deptCode
     * @return
     */
    Integer deleteDeptByCode(String deptCode);
    /**
     * 更新Dept信息
     * @param department
     * @return
     */
    Integer updateDeptInfo(Department department);
    /**
     * 根据DeptCode查询InstCode和DeptCode
     * @param deptCode
     * @return
     */
    Map<String,Object> selectCodesByDeptCode(String deptCode);
    /**
     * 查询系根据系代码
     * @param deptCode
     * @return
     */
    Department selectDeptsByDeptCode(String deptCode);
    /**
     * 插入一个新的系
     * @param department
     * @return
     */
    Integer insertDept(Department department);
    /**
     * 删除专业
     * @param majoCode
     * @return
     */
    Integer deleteMajorByCode(String majoCode);
    /**
     * 更新Major信息
     * @param major
     * @return
     */
    Integer updateMajorInfo(Major major);
    /**
     * 获取系信息通过学院代码
     * @param instCode
     * @return
     */
    List<Map<String,Object>> selectDeptsByInstCode(String instCode);
    /**
     * 获取当前专业的上级代码
     * @param majoCode
     * @return
     */
    Map<String,Object> selectCodesByMajorCode(String majoCode);

    /**
     * 获取学院信息
     * @param map
     * @return
     */
    List<Map<String,Object>> selectAllInsts(Map map);

    /**
     * 获取学院信息
     * @param instCode
     * @return
     */
    Institute selectInstCodeInfo(String instCode);

    /**
     * 修改学院信息
     * @param institute
     * @return
     */
    Integer updateInstInfo(Institute institute);

    /**
     * 插入一个新的学院
     * @param institute
     * @return
     */
    Integer insertInst(Institute institute);

    /**
     * 获取所有的部门信息
     * @param map
     * @return
     */
    List<Map<String,Object>> selectAllDepts(Map map);

    /**
     * 获取所有的专业信息
     * @param map
     * @return
     */
    List<Map<String,Object>> selectAllMajors(Map map);

    /**
     * 获取所有的班级信息
     * @param map
     * @return
     */
    List<Map<String,Object>> selectAllClazz(Map map);

    /**
     * 获取班级编辑页面的初始化信息
     * @param clazzId
     * @return
     */
    Map<String,Object> selectCodesByClazzId(Integer clazzId);

    /**
     * 获取所有班级的学年
     * @return
     */
    List<String> selectClazzYears();

    /**
     * 删除班级通过Id
     * @param clazzId
     * @return
     */
    Integer deleteClazzById(Integer clazzId);

    /**
     * 通过专业代码和班级查询班级
     * @param majoCode
     * @param clazzNumber
     * @return
     */
    Clazz selectClazzByMajorAndClazzNumber(String majoCode,String clazzNumber);

    /**
     * 新增一个班级
     * @param clazz
     * @return
     */
    Integer insertClazz(Clazz clazz);

    /**
     * 添加一个新的专业
     * @param major
     * @return
     */
    Integer insertMajor(Major major);

    /**
     * 通过专业代码获取专业信息
     * @param majoCode
     * @return
     */
    Major selectMajorByMajorCode(String majoCode);
}
