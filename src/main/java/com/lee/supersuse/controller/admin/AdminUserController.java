package com.lee.supersuse.controller.admin;

import com.github.pagehelper.PageInfo;
import com.lee.supersuse.admin_service.AdminInstitutionService;
import com.lee.supersuse.admin_service.AdminUserService;
import com.lee.supersuse.enums.ErrorEnum;
import com.lee.supersuse.exception.WebException;
import com.lee.supersuse.pojo.Result;
import com.lee.supersuse.pojo.User;
import com.lee.supersuse.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminUserController {


    @Autowired
    AdminUserService adminUserService;
    @Autowired
    AdminInstitutionService adminInstitutionService;

    @RequestMapping("/teacher-add")
    public String addTeacher(){
        return "/admin/teacher-add";
    }

    @RequestMapping("user/teacher")
    @ResponseBody
    public Result addTeacher(@RequestBody User user){
        log.info("接收到的用户信息："+user.toString());
        //type = 4为教师用户
        Integer count = adminUserService.addUser(user,4);
        if(count == 1){
            return ResultUtil.success();
        }else {
            return ResultUtil.error(ErrorEnum.DATA_ERROR);
        }
    }

    @RequestMapping("/student-list")
    public String getStudentList(Model model){
        List<Map<String,Object>> insts = adminInstitutionService.getAllInsts();
        model.addAttribute("instituteList",insts);
        List<String> years = adminInstitutionService.getClazzYears();
        model.addAttribute("years",years);
        return "/admin/student-list";
    }

    @RequestMapping("/student-list/json")
    @ResponseBody
    public Result getStudentJson(@RequestParam Integer page,
                                 @RequestParam Integer limit,
                                 @RequestParam Map map){
        PageInfo pageInfo = adminUserService.getStudentListByPage(page,limit,map);
        return ResultUtil.success(pageInfo.getList(),((Long)pageInfo.getTotal()).intValue());
    }
    @RequestMapping("/teacher-list")
    public String getTeacherList(Model model){
        return "/admin/teacher-list";
    }

    @RequestMapping("/teacher-list/json")
    @ResponseBody
    public Result getTeacherJson(@RequestParam Integer page,
                                 @RequestParam Integer limit,
                                 @RequestParam Map map){
        PageInfo pageInfo = adminUserService.getTeacherListByPage(page,limit,map);
        return ResultUtil.success(pageInfo.getList(),((Long)pageInfo.getTotal()).intValue());
    }

//    @RequestMapping("/student-list/{pageNum}")
//    public String studentList(Model model,
//                              @PathVariable(required = false) Integer pageNum,
//                              @RequestParam(required = false) String search){
//        PageInfo pageInfo = null;
//        if(pageNum == null || pageNum < 1){
//            pageInfo = adminUserService.getStudentListByPage(1,5,search);
//        }else {
//            pageInfo = adminUserService.getStudentListByPage(pageNum,5,search);
//        }
//        if(pageInfo.getPages() < 1){
//            pageInfo.setPages(1);
//        }
//        model.addAttribute("pageInfo",pageInfo);
//        log.info("学生分页信息"+pageInfo.toString());
//        log.info("分页表信息"+pageInfo.getList().toString());
//        List<Map<String,Object>> page = pageInfo.getList();
//        for (Map<String,Object> m : page){
//            log.info("map中的学生数据"+m.toString());
//        }
//        return "/admin/student-list";
//    }
//    @RequestMapping("/teacher-list/{pageNum}")
//    public String teacherList(Model model,
//                              @PathVariable(required = false) Integer pageNum,
//                              @RequestParam(required = false) String search){
//        PageInfo pageInfo = null;
//        if(pageNum == null || pageNum < 1){
//            pageInfo = adminUserService.getTeacherListByPage(1,5,search);
//        }else {
//            pageInfo = adminUserService.getTeacherListByPage(pageNum,5,search);
//        }
//        if(pageInfo.getPages() < 1){
//            pageInfo.setPages(1);
//        }
//        model.addAttribute("pageInfo",pageInfo);
//        log.info("教师分页信息"+pageInfo.toString());
//        List<Map<String,Object>> page = pageInfo.getList();
//        log.info("分页表信息"+pageInfo.getList().toString());
//        for (Map<String,Object> m : page){
//            log.info("map中的教师数据"+m.toString());
//        }
//        return "/admin/teacher-list";
//    }


    @RequestMapping("/user/stop")
    @ResponseBody
    public Result stopUser(@RequestParam Integer userId,
                           @RequestParam Integer isDelete){
        //更新用户的状态
        Integer count = adminUserService.stopUserStatus(userId,isDelete);
        return ResultUtil.success(count);
    }

    @RequestMapping("/user/delete")
    @ResponseBody
    public Result deleteUser(@RequestParam Integer id){
        log.info("进入删除用户方法被删除用户："+id.toString());
        adminUserService.deleteUser(id);
        return ResultUtil.success();
    }
    @RequestMapping("/user/deleteAll")
    @ResponseBody
    public Result deleteUser(@RequestParam Integer[] ids){
        log.info("进入删除用户方法被删除用户："+ids.toString());
        adminUserService.deleteUsers(ids);
        return ResultUtil.success();
    }

    @RequestMapping("/user-edit/{userId}")
    public String userEdit(@PathVariable Integer userId,
                           Model model){
        User user = adminUserService.editUserById(userId);
        model.addAttribute("user",user);
        if (user!= null && user.getType() == 2)
            return "/admin/student-edit";
        else if (user != null && user.getType() == 4){
            return "/admin/teacher-edit";
        }else throw new WebException(ErrorEnum.NOT_FOUND_ERROR);
    }
    @RequestMapping("/user/edit")
    @ResponseBody
    public Result userEdit(@RequestBody User user){
        Integer count = adminUserService.updateUserInfo(user);
        if(count > 0){
            return ResultUtil.success(count);
        }else {
            return ResultUtil.error(ErrorEnum.USER_ERROR);
        }

    }

    @RequestMapping("/user/repass")
    @ResponseBody
    public Result repassUser(@RequestBody Integer userId){
        Integer count = adminUserService.repassUser(userId);
        return ResultUtil.success(count);
    }

}
