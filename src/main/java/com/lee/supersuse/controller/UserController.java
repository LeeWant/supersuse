package com.lee.supersuse.controller;

import com.lee.supersuse.pojo.UserEditView;
import com.lee.supersuse.pojo.UserLoginView;
import com.lee.supersuse.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/editInfo")
    public String editInfo(HttpSession session,
                           Model model){
        //从session中获取登录的用户信息
        UserLoginView userLoginView = (UserLoginView) session.getAttribute("userLoginView");
        UserEditView userView = userService.getUserEditViewByUserId(userLoginView.getUserId());
        if(userView != null){
            model.addAttribute("userInfo",userView);
        }
        return "edit";
    }
    @RequestMapping("/checkPassword")
    @ResponseBody
    public boolean checkPassword(@RequestParam String oldPassword,
                                @RequestParam String newPassword,
                                HttpSession session){
        UserLoginView userLoginView = (UserLoginView) session.getAttribute("userLoginView");
        Integer userId = userLoginView.getUserId();
        log.info("userId："+userId);
        return userService.checkPassword(userId,oldPassword,newPassword);
    }
}
