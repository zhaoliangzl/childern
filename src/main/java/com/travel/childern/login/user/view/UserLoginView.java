package com.travel.childern.login.user.view;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/userlogin")
public class UserLoginView {

    @RequestMapping(value = "/login")
    public String login(){
        return "/user/login/login";
    }

}
