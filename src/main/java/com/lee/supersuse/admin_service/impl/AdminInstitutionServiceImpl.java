package com.lee.supersuse.admin_service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lee.supersuse.admin_service.AdminInstitutionService;
import com.lee.supersuse.enums.ErrorEnum;
import com.lee.supersuse.exception.JsonException;
import com.lee.supersuse.exception.ResultException;
import com.lee.supersuse.mapper.AdminInstitutionMapper;
import com.lee.supersuse.mapper.AdminUserMapper;
import com.lee.supersuse.pojo.Clazz;
import com.lee.supersuse.pojo.Department;
import com.lee.supersuse.pojo.Institute;
import com.lee.supersuse.pojo.Major;
import com.lee.supersuse.utils.InstUtil;
import com.lee.supersuse.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 学院管理服务
 */
@Service
@Slf4j
@Transactional
public class AdminInstitutionServiceImpl implements AdminInstitutionService {

    @Resource
    AdminInstitutionMapper adminInstitutionMapper;
    @Resource
    AdminUserMapper adminUserMapper;

    @Override
    public List<Map<String, Object>> getMajorsByDeptCode(String deptCode) {
        return adminInstitutionMapper.selectMajorByDeptCode(deptCode);
    }

    @Override
    public Integer deleteAllInsts(String[] instCodes) {
        Integer count = 0;
        for (String instCode : instCodes) {
            //调用批量删除的方法
            count += this.deleteInstByCode(instCode);
        }
        return count;
    }

    @Override
    public Integer deleteInstByCode(String instCode) {
        //删除专业
        if (instCode.equals("ALLINS")) {
            throw new JsonException(ErrorEnum.ALLINS_CANT_DELETE);
        }
        return adminInstitutionMapper.deleteInstByCode(instCode);
    }

    @Override
    public Integer deleteAllDepts(String[] deptCodes) {
        Integer count = 0;
        for (String deptCode : deptCodes) {
            //调用批量删除的方法
            count += this.deleteDeptByCode(deptCode);
        }
        return count;
    }

    @Override
    public Integer deleteDeptByCode(String deptCode) {
        //删除专业
        if (deptCode.equals("ALLDEP")) {
            throw new JsonException(ErrorEnum.ALLDEPT_CANT_DELETE);
        }
        return adminInstitutionMapper.deleteDeptByCode(deptCode);
    }

    @Override
    public Integer updateDepartment(Department department) {
        //检查是否重名
        InstUtil.checkNameWhenUpdate(department.getName(),department.getDeptCodeOld(), adminInstitutionMapper);
        //ALLMAJ不能被修改
        if (department.getDeptCodeOld().equals("ALLDEP"))
            throw new JsonException(ErrorEnum.ALLDEPT_CANT_UPDATE);
        //新旧代码一致则不需要判断数据库是否重复
        log.info("oldCode:" + department.getDeptCodeOld());
        log.info("newCode:" + department.getDeptCode());
        if (department.getDeptCodeOld().equals(department.getDeptCode())) {
            return adminInstitutionMapper.updateDeptInfo(department);
        } else {
            Department d = adminInstitutionMapper.selectDeptsByDeptCode(department.getDeptCode());
            if (d != null) {
                return 0;
            }
        }
        return adminInstitutionMapper.updateDeptInfo(department);
    }

    @Override
    public Map<String, Object> getCodesByDeptCode(String deptCode) {
        return adminInstitutionMapper.selectCodesByDeptCode(deptCode);
    }

    @Override
    public Integer addDept(Department department) {
        //检查是否重名
        InstUtil.checkName(department.getName(), adminInstitutionMapper);
        Department d = adminInstitutionMapper.selectDeptsByDeptCode(department.getDeptCode());
        if (d != null) {
            throw new JsonException(ErrorEnum.DEPT_ERROR);
        } else {
            return adminInstitutionMapper.insertDept(department);
        }
    }

    @Override
    public Integer deleteAllMajors(String[] majoCodes) {
        Integer count = 0;
        for (String majoCode : majoCodes
        ) {
            //调用单独删除的方法
            log.info("majoCode:" + majoCode);
            if (majoCode.equals("ALLMAJ"))
                throw new JsonException(ErrorEnum.ALLMAJOR_CANT_DELETE);
            count += adminInstitutionMapper.deleteMajorByCode(majoCode);
        }
        return count;
    }

    @Override
    public Integer deleteMajorByCode(String majoCode) {
        //删除专业
        if (majoCode.equals("ALLMAJ")) {
            throw new JsonException(ErrorEnum.ALLMAJOR_CANT_DELETE);
        }
        return adminInstitutionMapper.deleteMajorByCode(majoCode);
    }

    @Override
    public Integer updateMajor(Major major) {
        //检查是否重名
        InstUtil.checkNameWhenUpdate(major.getName(),major.getMajoCodeOld(), adminInstitutionMapper);
        //ALLMAJ不能被修改
        if (major.getMajoCodeOld().equals("ALLMAJ"))
            throw new JsonException(ErrorEnum.ALLMAJOR_CANT_UPDATE);
        //新旧代码一致则不需要判断数据库是否重复
        if (major.getMajoCodeOld().equals(major.getMajoCode())) {
            return adminInstitutionMapper.updateMajorInfo(major);
        } else {
            Major c = adminInstitutionMapper.selectMajorByMajorCode(major.getMajoCode());
            if (c != null) {
                return 0;
            }
        }
        return adminInstitutionMapper.updateMajorInfo(major);
    }

    @Override
    public List<Map<String, Object>> getDeptsByInstCode(String instCode) {
        return adminInstitutionMapper.selectDeptsByInstCode(instCode);
    }

    @Override
    public Map<String, Object> getCodesByMajoCode(String majoCode) {
        return adminInstitutionMapper.selectCodesByMajorCode(majoCode);
    }

    @Override
    public PageInfo getAllInstByPage(Integer page, Integer limit, Map map) {
        //因为PageHelper计算页码时默认为0页开始
        page -= 1;
        PageHelper.offsetPage(page * limit, limit);
        List<Map<String, Object>> insts = adminInstitutionMapper.selectAllInsts(map);
        return new PageInfo(insts);
    }

    @Override
    public Institute getInstByCode(String instCode) {
        return adminInstitutionMapper.selectInstCodeInfo(instCode);
    }

    @Override
    public Integer updateInst(Institute institute) {
        //检查是否重名
        InstUtil.checkNameWhenUpdate(institute.getName(),institute.getInstCodeOld(), adminInstitutionMapper);
        //ALLINS不能被修改
        if (institute.getInstCode().equals("ALLINS"))
            throw new JsonException(ErrorEnum.ALLINS_CANT_UPDATE);
        //新旧代码一致则不需要判断数据库是否重复
        log.info("oldCode:" + institute.getInstCodeOld());
        log.info("newCode:" + institute.getInstCodeOld());
        if (institute.getInstCodeOld().equals(institute.getInstCode())) {
            return adminInstitutionMapper.updateInstInfo(institute);
        } else {
            Institute i = adminInstitutionMapper.selectInstCodeInfo(institute.getInstCode());
            if (i != null) {
                throw new JsonException(ErrorEnum.INS_ERROR);
            }
        }
        return adminInstitutionMapper.updateInstInfo(institute);
    }

    @Override
    public Integer addInst(Institute institute) {
        //检查是否重名
        InstUtil.checkName(institute.getName(), adminInstitutionMapper);
        //检查代码是否已被使用
        Institute i = adminInstitutionMapper.selectInstCodeInfo(institute.getInstCode());
        if (i != null) {
            throw new JsonException(ErrorEnum.INS_ERROR);
        }
        return adminInstitutionMapper.insertInst(institute);
    }

    @Override
    public List<Map<String, Object>> getAllInsts() {
        return adminInstitutionMapper.selectAllInsts(null);
    }

    @Override
    public PageInfo getAllDeptByPage(Integer page, Integer limit, Map map) {
        //因为PageHelper计算页码时默认为0页开始
        page -= 1;
        PageHelper.offsetPage(page * limit, limit);
        List<Map<String, Object>> insts = adminInstitutionMapper.selectAllDepts(map);
        return new PageInfo(insts);
    }

    @Override
    public List<Map<String, Object>> getAllDepts() {
        return adminInstitutionMapper.selectAllDepts(null);
    }

    @Override
    public PageInfo getAllMajoByPage(Integer page, Integer limit, Map map) {
        //因为PageHelper计算页码时默认为0页开始
        page -= 1;
        PageHelper.offsetPage(page * limit, limit);
        List<Map<String, Object>> majors = adminInstitutionMapper.selectAllMajors(map);
        return new PageInfo(majors);
    }

    @Override
    public PageInfo getAllClazzByPage(Integer page, Integer limit, Map map) {
        //因为PageHelper计算页码时默认为0页开始
        page -= 1;
        PageHelper.offsetPage(page * limit, limit);
        List<Map<String, Object>> majors = adminInstitutionMapper.selectAllClazz(map);
        return new PageInfo(majors);
    }

    @Override
    public List<String> getClazzYears() {
        return adminInstitutionMapper.selectClazzYears();
    }

    @Override
    public Map<String, Object> getCodesByClazzId(Integer clazzId) {
        return adminInstitutionMapper.selectCodesByClazzId(clazzId);
    }

    @Override
    public Map<String, Integer> deleteClazz(Integer clazzId) {
        Map<String, Integer> countMap = new HashMap<>();
        Integer clazzCount = 0;
        Integer studentCount = 0;
        if (clazzId != null) {
            //删除学生
            studentCount += adminUserMapper.deleteStudentByClazzId(clazzId);
            //删除班级
            clazzCount += adminInstitutionMapper.deleteClazzById(clazzId);
        }
        countMap.put("clazzCount", clazzCount);
        countMap.put("studentCount", studentCount);
        return countMap;
    }

    @Override
    public Map<String, Integer> deleteClazzAll(Integer[] clazzIds) {
        Integer clazzCount = 0;
        Integer studentCount = 0;
        //批量删除
        for (Integer clazzId : clazzIds) {
            Map<String, Integer> countMap = this.deleteClazz(clazzId);
            clazzCount += countMap.get("clazzCount");
            studentCount += countMap.get("studentCount");
        }
        Map<String, Integer> countMap = new HashMap<>();
        countMap.put("clazzCount", clazzCount);
        countMap.put("studentCount", studentCount);
        return countMap;
    }

    @Override
    public Integer addClazz(Clazz clazz) {
        //查询班级是否已经存在
        Clazz c = adminInstitutionMapper.selectClazzByMajorAndClazzNumber(clazz.getMajoCode(), clazz.getClazzNumber());
        if (c != null) {
            return 0;
        } else {
            return adminInstitutionMapper.insertClazz(clazz);
        }
    }

    @Override
    public Integer updateClazz(Clazz clazz) {

        clazz.setClazzNumber(clazz.getYear() + clazz.getClazzNumber());
        //获得原始clazz信息
        Clazz c = adminInstitutionMapper.selectClazzById(clazz.getClazzId());
        //查询是否存在班号冲突
        Clazz c1 = adminInstitutionMapper.selectClazzByMajorAndClazzNumber(clazz.getMajoCode(), clazz.getClazzNumber());
        //说明更新的是当前班级（没有更改班级号和专业）
        if (c.getMajoCode().equals(clazz.getMajoCode()) && c.getClazzNumber().equals(clazz.getClazzNumber())) {
            return adminInstitutionMapper.updateClazzInfo(clazz);
        } else if (c1 == null) {
            return adminInstitutionMapper.updateClazzInfo(clazz);
        } else throw new JsonException(ErrorEnum.CLAZZ_EXIST);
    }

    @Override
    public Integer addMajor(Major major) {
        //检查是否重名
        InstUtil.checkName(major.getName(), adminInstitutionMapper);
        //查询是否已存在该代码
        Major m = adminInstitutionMapper.selectMajorByMajorCode(major.getMajoCode());
        if (m != null) {
            return 0;
        } else {
            return adminInstitutionMapper.insertMajor(major);
        }
    }

    @Override
    public Major getMajorByMajoCode(String majoCode) {
        return adminInstitutionMapper.selectMajorByMajorCode(majoCode);
    }

}
