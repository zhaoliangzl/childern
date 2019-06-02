package com.travel.childern.controller.user.controller;


import com.alibaba.fastjson.JSONObject;
import com.travel.childern.repo.UserRepository;
import com.travel.childern.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/userservice")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/checklogin")
    public JSONObject checkLogin(HttpSession session){
        //检查用户是否登陆
        Object u = session.getAttribute("user");
        if(u == null){
            return Result.Result(400,"用户没有登陆","");
        }else{
            return Result.Result(200,"","");
        }

    }


}
