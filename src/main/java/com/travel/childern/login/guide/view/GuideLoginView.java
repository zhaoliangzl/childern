package com.travel.childern.login.guide.view;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/guidelogin")
public class GuideLoginView {

    @RequestMapping(value = "/login")
    public String login(){
        return "/guide/guide/login";
    }

}
