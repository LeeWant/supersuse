package com.lee.supersuse.controller.admin;

import com.github.pagehelper.PageInfo;
import com.lee.supersuse.admin_service.AdminInstitutionService;
import com.lee.supersuse.admin_service.AdminUserService;
import com.lee.supersuse.enums.ErrorEnum;
import com.lee.supersuse.exception.JsonException;
import com.lee.supersuse.pojo.*;
import com.lee.supersuse.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 学院管理控制器
 */
@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminInstitutionController {

    @Autowired
    AdminInstitutionService adminInstitutionService;
    @Autowired
    AdminUserService adminUserService;



    @RequestMapping("/inst-list")
    public String getInstList() {
        return "/admin/inst-list";
    }

    @RequestMapping("/inst-edit/{instCode}")
    public String getInstInfo(@PathVariable String instCode,
                              Model model) {
        Institute institute = adminInstitutionService.getInstByCode(instCode);
        model.addAttribute("institute", institute);
        model.addAttribute("instCode",instCode);
        return "/admin/inst-edit";
    }

    @RequestMapping("/inst/edit")
    @ResponseBody
    public Result getInstInfo(@RequestBody Institute institute) {
        log.info("获取到的学院信息：" + institute.toString());
        Integer count = adminInstitutionService.updateInst(institute);
        return ResultUtil.success(count);
    }

    @RequestMapping("/inst-add")
    public String addInst() {
        return "/admin/inst-add";
    }

    @RequestMapping("/inst/add")
    @ResponseBody
    public Result addInst(@RequestBody Institute institute) {
        log.info("获取到的学院信息：" + institute.toString());
        Integer count = adminInstitutionService.addInst(institute);
        if (count > 0) {
            return ResultUtil.success(count);
        } else {
            return ResultUtil.error(ErrorEnum.DATA_ERROR);
        }
    }

    @RequestMapping("/inst-list/json")
    @ResponseBody
    public Result getInstListJson(@RequestParam Integer page,
                                  @RequestParam Integer limit,
                                  @RequestParam Map map) {
        //分页获取Inst
        PageInfo pageInfo = adminInstitutionService.getAllInstByPage(page, limit, map);
        return ResultUtil.success(pageInfo.getList(), ((Long) pageInfo.getTotal()).intValue());
    }

    @RequestMapping("/inst/delete")
    @ResponseBody
    public Result editInst(@RequestParam String instCode){
        log.info("即将删除的系代码："+instCode);
        Integer count = adminInstitutionService.deleteInstByCode(instCode);
        log.info("删除学院的数量:"+count);
        if (count > 0){
            return ResultUtil.success(count);
        }else {
            return ResultUtil.error(ErrorEnum.DELETE_ERROR);
        }
    }
    @RequestMapping("/inst/deleteAll")
    @ResponseBody
    public Result deleteInstAll(@RequestParam String[] instCodes){
        log.info("即将删除的专业代码："+instCodes.toString());
        Integer count = adminInstitutionService.deleteAllInsts(instCodes);
        if (count > 0){
            return ResultUtil.success(count);
        }else {
            return ResultUtil.error(ErrorEnum.DELETE_ERROR);
        }
    }


    @RequestMapping("/dept-list")
    public String getDeptList(Model model) {
        log.info("进入系列表dept-list");
        List<Map<String, Object>> insts = adminInstitutionService.getAllInsts();
        model.addAttribute("instituteList", insts);
        return "/admin/dept-list";
    }

    @RequestMapping("/dept-list/json")
    @ResponseBody
    public Result getDeptList(@RequestParam Integer page,
                              @RequestParam Integer limit,
                              @RequestParam Map map) {
        log.info("获取系信息dept-list/json");
        //分页获取所有的系信息
        PageInfo pageInfo = adminInstitutionService.getAllDeptByPage(page, limit, map);
        return ResultUtil.success(pageInfo.getList(), ((Long) pageInfo.getTotal()).intValue());
    }

    @RequestMapping("/dept-add")
    public String addDept(Model model){
        //初始化学院下拉列表框
        List<Map<String, Object>> insts = adminInstitutionService.getAllInsts();
        model.addAttribute("instituteList", insts);
        return "/admin/dept-add";
    }
    @RequestMapping("/dept/add")
    @ResponseBody
    public Result addDept(@RequestBody Department department){
        log.info("即将添加的系信息："+department.toString());
        Integer count = adminInstitutionService.addDept(department);
        if(count == 1){
            return ResultUtil.success(count);
        }else {
            return ResultUtil.error(ErrorEnum.DEPT_ERROR);
        }
    }

    @RequestMapping("/dept-edit/{deptCode}")
    public String editDept(Model model,
                           @PathVariable String deptCode){
        //初始化信息专业对应的学院-系-专业代码
        Map<String,Object> codes = adminInstitutionService.getCodesByDeptCode(deptCode);
        model.addAttribute("codes",codes);
        //初始化学院下拉列表框
        List<Map<String, Object>> insts = adminInstitutionService.getAllInsts();
        model.addAttribute("instituteList", insts);
        return "/admin/dept-edit";
    }
    @RequestMapping("/dept/edit")
    @ResponseBody
    public Result editDept(@RequestBody Department department){
        log.info("department信息："+department.toString());
        Integer count = adminInstitutionService.updateDepartment(department);
        if (count > 0){
            return ResultUtil.success(count);
        }else {
            return ResultUtil.error(ErrorEnum.DEPT_ERROR);
        }
    }

    @RequestMapping("/dept/delete")
    @ResponseBody
    public Result editDept(@RequestParam String deptCode){
        log.info("即将删除的系代码："+deptCode);
        Integer count = adminInstitutionService.deleteDeptByCode(deptCode);
        log.info("删除系的数量:"+count);
        if (count > 0){
            return ResultUtil.success(count);
        }else {
            return ResultUtil.error(ErrorEnum.DELETE_ERROR);
        }
    }
    @RequestMapping("/dept/deleteAll")
    @ResponseBody
    public Result deleteDeptAll(@RequestParam String[] deptCodes){
        log.info("即将删除的专业代码："+deptCodes.toString());
        Integer count = adminInstitutionService.deleteAllDepts(deptCodes);
        if (count > 0){
            return ResultUtil.success(count);
        }else {
            return ResultUtil.error(ErrorEnum.DELETE_ERROR);
        }
    }

    @RequestMapping("/majo-list")
    public String getMajoList(Model model) {
        log.info("进入系列表majo-list");
        //获取学院
        List<Map<String, Object>> insts = adminInstitutionService.getAllInsts();
        model.addAttribute("instituteList", insts);
        return "/admin/major-list";
    }

    @RequestMapping("/majo-list/json")
    @ResponseBody
    public Result getMajoList(@RequestParam Integer page,
                              @RequestParam Integer limit,
                              @RequestParam Map map) {
        log.info("获取系信息majo-list/json");
        //分页获取所有的系信息
        PageInfo pageInfo = adminInstitutionService.getAllMajoByPage(page, limit, map);
        return ResultUtil.success(pageInfo.getList(), ((Long) pageInfo.getTotal()).intValue());
    }


    @RequestMapping("/majo-add")
    public String addMajo(Model model){
        //初始化学院下拉列表框
        List<Map<String, Object>> insts = adminInstitutionService.getAllInsts();
        model.addAttribute("instituteList", insts);
        return "/admin/major-add";
    }
    @RequestMapping("/majo/add")
    @ResponseBody
    public Result addMajo(@RequestBody Major major){
        log.info("即将添加的专业信息："+major.toString());
        Integer count = adminInstitutionService.addMajor(major);
        if(count == 1){
            return ResultUtil.success(count);
        }else {
            return ResultUtil.error(ErrorEnum.MAJOR_ERROR);
        }
    }

    @RequestMapping("/majo-edit/{majoCode}")
    public String editMajo(Model model,
                           @PathVariable String majoCode){
        //初始化信息专业对应的学院-系-专业代码
        Map<String,Object> codes = adminInstitutionService.getCodesByMajoCode(majoCode);
        model.addAttribute("codes",codes);
        //初始化学院下拉列表框
        List<Map<String, Object>> insts = adminInstitutionService.getAllInsts();
        model.addAttribute("instituteList", insts);
        //初始化系下拉列表框
        List<Map<String,Object>> deptList = adminInstitutionService.getDeptsByInstCode((String) codes.get("instCode"));
        model.addAttribute("deptList",deptList);
        return "/admin/major-edit";
    }
    @RequestMapping("/majo/edit")
    @ResponseBody
    public Result editMajo(@RequestBody Major major){
        log.info("major信息："+major.toString());
        Integer count = adminInstitutionService.updateMajor(major);
        if (count > 0){
            return ResultUtil.success(count);
        }else {
            return ResultUtil.error(ErrorEnum.MAJOR_ERROR);
        }
    }
    @RequestMapping("/majo/delete")
    @ResponseBody
    public Result deleteMajor(@RequestParam String majoCode){
        log.info("即将删除的专业代码："+majoCode);
        Integer count = adminInstitutionService.deleteMajorByCode(majoCode);
        log.info("删除的数据:"+count);
        if (count > 0){
            return ResultUtil.success(count);
        }else {
            return ResultUtil.error(ErrorEnum.DELETE_ERROR);
        }
    }
    @RequestMapping("/majo/deleteAll")
    @ResponseBody
    public Result deleteMajorAll(@RequestParam String[] majoCodes){
        log.info("即将删除的专业代码："+majoCodes.toString());
        Integer count = adminInstitutionService.deleteAllMajors(majoCodes);
        if (count > 0){
            return ResultUtil.success(count);
        }else {
            return ResultUtil.error(ErrorEnum.DELETE_ERROR);
        }
    }

    @RequestMapping("/clazz-list")
    public String getClazzList(Model model) {
        //初始化班级列表页面，需要传递初始化的级联查询信息和学年信息
        List<Map<String, Object>> insts = adminInstitutionService.getAllInsts();
        model.addAttribute("instituteList", insts);
        List<String> years = adminInstitutionService.getClazzYears();
        model.addAttribute("years", years);
        return "/admin/clazz-list";
    }

    @RequestMapping("/clazz-list/json")
    @ResponseBody
    public Result getClazzInfo(@RequestParam Integer page,
                               @RequestParam Integer limit,
                               @RequestParam Map map) {
        //分页获取班级信息
        PageInfo pageInfo = adminInstitutionService.getAllClazzByPage(page, limit, map);
        return ResultUtil.success(pageInfo.getList(), ((Long) pageInfo.getTotal()).intValue());
    }

    @RequestMapping("/clazz-add")
    public String addClazz(Model model) {
        List<Map<String, Object>> inst = adminInstitutionService.getAllInsts();
        model.addAttribute("instituteList", inst);
        //获取教师下拉列表初始化信息
        List<Map<String, Object>> teachers = adminUserService.getAllUserByType(4);
        model.addAttribute("teachers", teachers);
        return "/admin/clazz-add";
    }

    @RequestMapping("/clazz/add")
    @ResponseBody
    public Result addClazz(@RequestBody Clazz clazz) {
        clazz.setClazzNumber(clazz.getYear() + clazz.getClazzNumber());
        log.info("当前新增的班级信息：" + clazz.toString());
        Integer count = adminInstitutionService.addClazz(clazz);
        if (count != 1) {
            //插入失败返回的Json数据
            return ResultUtil.error(ErrorEnum.CLAZZ_EXIST);
        }
        return ResultUtil.success();
    }
    @RequestMapping("/clazz-edit/{clazzId}")
    public String editClazz(@PathVariable Integer clazzId,Model model){
        Map<String,Object> codes = adminInstitutionService.getCodesByClazzId(clazzId);
        model.addAttribute("codes",codes);
        //初始化学院下拉列表框
        List<Map<String, Object>> insts = adminInstitutionService.getAllInsts();
        model.addAttribute("instituteList", insts);
        //初始化专业下拉框
        List<Map<String,Object>> majoList = adminInstitutionService.getMajorsByDeptCode((String)codes.get("deptCode"));
        model.addAttribute("majoList",majoList);
        //初始化系下拉列表框
        List<Map<String,Object>> deptList = adminInstitutionService.getDeptsByInstCode((String) codes.get("instCode"));
        model.addAttribute("deptList",deptList);
        //获取教师下拉列表初始化信息
        List<Map<String, Object>> teachers = adminUserService.getAllUserByType(4);
        model.addAttribute("teachers", teachers);
        return "/admin/clazz-edit";
    }

    @RequestMapping("/clazz/edit")
    @ResponseBody
    public Result editClazz(@RequestBody Clazz clazz){
        Integer count = adminInstitutionService.updateClazz(clazz);
        if (count > 0){
            return ResultUtil.success();
        }else throw new JsonException(ErrorEnum.UNKNOWN_ERROR);
    }

    /**
     * 删除班级并同时删除该班级学生的方法
     *
     * @param clazzId
     * @return
     */
    @RequestMapping("/clazz/delete")
    @ResponseBody
    public Result deleteClazz(@RequestParam Integer clazzId) {
        log.info("被删除的班级id:" + clazzId);
        //回传删除的班级和学生数量
        Map countMap = adminInstitutionService.deleteClazz(clazzId);
        return ResultUtil.success(countMap);
    }

    /**
     * 批量删除班级和班级对应的学生
     *
     * @param clazzIds
     * @return
     */
    @RequestMapping("/clazz/deleteAll")
    @ResponseBody
    public Result deleteClazzAll(@RequestParam Integer[] clazzIds) {
        log.info("进入删除用户方法被删除班级：" + clazzIds.toString());
        Map countMap = adminInstitutionService.deleteClazzAll(clazzIds);
        return ResultUtil.success(countMap);
    }

}
