package com.lee.supersuse.controller;

import com.lee.supersuse.pojo.*;
import com.lee.supersuse.service.MajorService;
import com.lee.supersuse.service.ViewService;
import com.lee.supersuse.utils.IpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 登录控制器
 */
@Controller
public class LoginController {

    @Autowired
    private ViewService viewService;
    @Autowired
    private MajorService majorService;
    //定义一个全局的记录器，通过LoggerFactory获取
    private final static Logger logger = LoggerFactory.getLogger(LoginController.class);


    @RequestMapping("/login")
    public String login() {
        return "login";
    }
    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/loginCheck")
    public ModelAndView loginCheck(@RequestParam String logincode,
                                @RequestParam String password,
                                @RequestParam(required = false) Integer type,
                                ModelAndView modelAndView,
                                HttpSession session,
                                   HttpServletRequest request){
        logger.info("----进入LoginController控制器:index()方法----");
        UserLoginView userLoginView = viewService.userLoginCheck(logincode,password,type);
        if(userLoginView != null){
            //说明查询成功用户名密码正确，用户存在
            session.setAttribute("userLoginView",userLoginView);
            //存放ip访问记录
            Ip ip = IpUtil.getIp(userLoginView,request);
            viewService.insertIpVisit(ip);
            //封装DateCountView数据
            DateCountView dateCountView = viewService.getCountView();
            logger.info("数量统计:"+dateCountView.toString());
            session.setAttribute("count",dateCountView);
            logger.info("当前登录用户："+userLoginView.getName());
            modelAndView.setViewName("redirect:/index");
            for (String code : userLoginView.getRoleCodes()){
                if(code != null && code.equals("ADMIN")){
                    modelAndView.setViewName("redirect:/admin/index");
                    break;
                }
            }
        }else {
            //查询失败返回登陆页面
            logger.info("登陆失败：密码或账号错误");
            modelAndView.addObject("errmsg","用户名或密码错误");
            modelAndView.setViewName("login");
        }
        return modelAndView;
    }



    @RequestMapping("loginOut")
    public String loginOut(HttpSession session){
        UserLoginView userLoginView = (UserLoginView) session.getAttribute("userLoginView");
        if(userLoginView != null){
            logger.info("用户登出:"+userLoginView.getName());
            session.invalidate();
        }
        return "redirect:login";
    }
}
