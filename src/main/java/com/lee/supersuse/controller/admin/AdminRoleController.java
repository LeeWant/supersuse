package com.lee.supersuse.controller.admin;

import com.github.pagehelper.PageInfo;
import com.lee.supersuse.admin_service.AdminRoleService;
import com.lee.supersuse.pojo.Result;
import com.lee.supersuse.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminRoleController {

    @Autowired
    private AdminRoleService adminRoleService;

    @RequestMapping("/admin/loginInfo/{page}")
    @ResponseBody
    public Result getLoginInfo(@PathVariable Integer page){
        //分页获取班级信息
        PageInfo pageInfo = adminRoleService.getLoginInfoByPage(page, 10);
        return ResultUtil.success(pageInfo.getList(), ((Long) pageInfo.getTotal()).intValue(),pageInfo.getPages());
    }



}
