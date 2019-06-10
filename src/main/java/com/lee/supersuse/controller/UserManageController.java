package com.lee.supersuse.controller;


import com.lee.supersuse.enums.ErrorEnum;
import com.lee.supersuse.exception.JsonException;
import com.lee.supersuse.exception.WebException;
import com.lee.supersuse.pojo.Result;
import com.lee.supersuse.pojo.User;
import com.lee.supersuse.pojo.UserLoginView;
import com.lee.supersuse.service.ClazzService;
import com.lee.supersuse.service.UserManageService;
import com.lee.supersuse.utils.ExcelUtil;
import com.lee.supersuse.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 用于用户管理的控制器，包括学生用户和教师用户
 */
@Controller
@RequestMapping("/manage")
@Slf4j
public class UserManageController {

    @Autowired
    UserManageService userManageService;
    @Autowired
    ClazzService clazzService;

    /**
     * 包括批量和单独添加
     * @param file
     * @param clazzId
     * @param student
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/student/add")
    public Result addStudents(@RequestParam(required = false) MultipartFile file,
                              @RequestParam Integer clazzId,
                              User student,
                              HttpSession session){
        UserLoginView userLoginView =(UserLoginView)session.getAttribute("userLoginView");
        //此处代码用于权限验证，暂时保留
//        if("此处"){
//
//        }
        //批量添加学生，文件不为空继续运行
        if(file != null && !file.isEmpty()){
            List<List<String>> list = ExcelUtil.readXlsx(file);
            List<User> users = new ArrayList<>();
            int i = 0;
            try {
                for (List<String> strs: list
                ) {
                    log.info("Excel表中数据"+(i++)+":"+strs.toString());
                    User user = new User();
                    user.setClazzId(clazzId);
                    user.setNumber(strs.get(0));
                    Integer.parseInt(strs.get(0));
                    user.setName(strs.get(1));
                    users.add(user);
                }
            }catch (Exception e){
                //捕获文件上传中出现的异常
                throw new JsonException(ErrorEnum.FILE_ERROR);
            }
            Integer count = userManageService.addStudents(users);
            String info = "一共"+users.size()+"条用户数据，成功插入"+count+"条数据，插入失败"+(users.size()-count)+"条(学生学号重复冲突)";
            return ResultUtil.success(info);
        }
        //单独添加学生
        else if(student != null && student.getClazzId()!=null){
            log.info("获取的学生信息："+student.toString());
            //将用户信息和班级传到服务层
            Integer count = userManageService.addStudent(student,clazzId);
            return ResultUtil.success("成功的条数："+count);
        }
        //未知错误捕获
        log.info("获取的学生信息："+student.toString());
        throw new WebException(ErrorEnum.UNKNOWN_ERROR);
    }

    /**
     * 删除方法，包括批量和单独
     * @param session
     * @param userId
     * @param userIds
     * @param clazzId
     * @param monitorId
     */
    @ResponseBody
    @RequestMapping("/user/delete")
    public void deleteUser(HttpSession session,
                           @RequestParam(required = false) Integer userId,
                           @RequestParam(required = false) String userIds,
                           @RequestParam Integer clazzId,
                           @RequestParam Integer monitorId){

        //删除一个用户的方法
        if(userId != null){
            log.info("单独删除方法--被删除的用户id:"+userId);
            userManageService.deleteUser(userId,clazzId,monitorId);
        }
        //批量删除用户的方法
        else if(userIds != null && userIds.length() >0){
            String[] userIdsArray = userIds.split(",");
            List<Integer> userIdList = new ArrayList<>();
            for (String str:userIdsArray
                 ) {
                userIdList.add(Integer.parseInt(str));
            }
            log.info("批量删除方法--被删除的用户id:"+userIdList.toString());
            userManageService.deleteUserBatch(userIdList,clazzId,monitorId);
        }
    }


}
