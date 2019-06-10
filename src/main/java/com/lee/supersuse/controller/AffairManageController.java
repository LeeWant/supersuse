package com.lee.supersuse.controller;

import com.lee.supersuse.pojo.AffairView;
import com.lee.supersuse.pojo.UserLoginView;
import com.lee.supersuse.service.AffairManageService;
import com.lee.supersuse.service.AffairService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/manage/affair")
public class AffairManageController {

    @Autowired
    AffairManageService affairManageService;
    /**
     * 事务管理控制器，普通用户仅能管理自己发布的事务
     * @param session
     * @return
     */
    @RequestMapping()
    public String affair(HttpSession session,
                               Model model){
        //获取登录的用户信息
        UserLoginView userLoginView = (UserLoginView) session.getAttribute("userLoginView");
        //该用户发布的所有事务
        List<AffairView> affairViewList = affairManageService.getAffairsByUserId(userLoginView.getUserId());
        model.addAttribute("affairList",affairViewList);
        log.info(userLoginView.getName()+"发布的所有事务："+affairViewList.toString());
        return "/manage/affairList";
    }

    @RequestMapping("/delete/{affId}")
    public String deleteId(@PathVariable String affId){
        log.info("进入AffairDelete方法");
        affairManageService.deleteAffair(affId);
        log.info("退出AffairDelete方法");
        return "redirect:/manage/affair";
    }

    @RequestMapping("/{affId}")
    public ModelAndView manageAffair(@PathVariable String affId,
                                     ModelAndView mv){
        return mv;
    }
}
