package com.travel.childern.login.admin.view;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/adminlogin")
public class AdminLoginView {

    @RequestMapping(value = "")
    public String login(){
        return "/admin/admin/login";
    }

}
