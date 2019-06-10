package com.lee.supersuse.controller;

import com.alibaba.fastjson.JSON;
import com.lee.supersuse.enums.ErrorEnum;
import com.lee.supersuse.exception.JsonException;
import com.lee.supersuse.exception.WebException;
import com.lee.supersuse.pojo.Department;
import com.lee.supersuse.pojo.Major;
import com.lee.supersuse.service.MajorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UtilController {

    private static Logger logger = LoggerFactory.getLogger(UtilController.class);

    @Autowired
    private MajorService majorService;

    /**
     * 错误页面
     * @return
     */
    @RequestMapping("/myError")
    public String error(Model model){
        throw new WebException(ErrorEnum.DENIED_ERROR);
    }



    @RequestMapping("/demo/error/{num}")
    public String test(@PathVariable Integer num){
        String s = null;
        if(num == 1){
            logger.error("num："+1);
            throw new WebException(ErrorEnum.NOT_FOUND_ERROR);
        }else if(num == 2){
            logger.error("num："+2);
            throw new JsonException(ErrorEnum.NOT_FOUND_ERROR);
        }else if(num == 3){
            logger.error("num："+3);
            s.length();
        }
        logger.error("success num："+num);
        return "/success";
    }

    /**
     * 下拉列表框改变触发的级联查询，查询学院下的所有系
     * @param instCode 学院代码
     * @return
     */
    @RequestMapping(value = "/getDept",method = RequestMethod.POST)
    @ResponseBody
    public List<Department> getDept(@RequestParam String instCode){
        logger.info("-----------进入getDept方法-------------");
        List<Department> departments = majorService.selectDepartmentByInstCode(instCode);
        logger.info(JSON.toJSONString(departments.toString()));
        return departments;
    }
    /**
     * 下拉列表框改变触发的级联查询，查询系下的所有专业
     * @param deptCode 系代码
     * @return
     */
    @RequestMapping(value = "/getMajor",method = RequestMethod.POST)
    @ResponseBody
    public List<Major> getMajor(@RequestParam String deptCode){
        logger.info("-----------进入getMajor方法-------------");
        List<Major> majors = majorService.selectMajorByDeptCode(deptCode);
        logger.info(JSON.toJSONString(majors));
        return majors;
    }
}
