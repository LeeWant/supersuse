package com.lee.supersuse.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("/index")
    public String index(){
        return "/admin/index";
    }

    @RequestMapping("/welcome")
    public String welcome(){
        return "/admin/welcome";
    }

}
