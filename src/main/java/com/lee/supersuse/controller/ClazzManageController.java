package com.lee.supersuse.controller;

import com.alibaba.fastjson.JSON;
import com.lee.supersuse.pojo.Clazz;
import com.lee.supersuse.pojo.UserEditView;
import com.lee.supersuse.pojo.UserLoginView;
import com.lee.supersuse.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/manage/clazz")
@Slf4j
public class ClazzManageController {

    @Autowired
    private ClazzService clazzService;

    @RequestMapping
    public String clazz(Model model,
                        HttpSession session) {
        //需要从session中获取用户的id
        UserLoginView userLoginView = (UserLoginView) session.getAttribute("userLoginView");
        //查询出班级列表
        List<Clazz> clazzList = clazzService.selectClazzByTeacherId(userLoginView.getUserId());
        //查询出班级列表对应的学年和专业
        Set<String> years = new HashSet<>();
        Set<String> majors = new HashSet<>();
        for (Clazz c : clazzList) {
            years.add(c.getYear());
            majors.add(c.getMajorName());
        }
        model.addAttribute("years", years);
        model.addAttribute("majors", majors);
        log.info("查询出的班级：" + clazzList.toString());
        model.addAttribute("clazzList", clazzList);
        return "manage/classList";
    }

    @ResponseBody
    @RequestMapping("/editInfo")
    public boolean editInfo(@RequestParam Integer clazzId,
                            @RequestParam String remark,
                            @RequestParam Integer monitorId) {
        log.info("进入方法：/clazz/editInfo \r 参数：" + clazzId +
                "," + remark + "," + monitorId);
        return clazzService.editClazzInfo(clazzId, remark, monitorId);
    }

    @ResponseBody
    @RequestMapping("/getStudents")
    public String getStudents(@RequestParam Integer clazzId) {
        log.info("进入方法：/clazz/getStudents");
        List<UserEditView> students = clazzService.getStudents(clazzId);
        String result = JSON.toJSONString(students);
        log.info("返回数据：" + result);
        return result;
    }

    @RequestMapping("/{clazzId}")
    public String clazzInfo(@PathVariable Integer clazzId,
                            Model model,
                            HttpSession session) {
        UserLoginView userLoginView = (UserLoginView) session.getAttribute("userLoginView");
        model.addAllAttributes(clazzService.getClazzInfo(clazzId, userLoginView));
        log.info(model.toString());
        return "manage/classInfo";
    }


}
