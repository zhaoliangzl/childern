package com.travel.childern.login.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.travel.childern.model.User;

import com.travel.childern.repo.UserRepository;

import com.travel.childern.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/userloginservice")
public class UserLogin {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/login")
    public JSONObject tea_login_deal(@RequestParam("username") String user,
                                 @RequestParam("password") String password,
//                                 @RequestParam("code") String code,
                                 HttpSession session) {

//        String captchaId = (String) session.getAttribute("vrifyCode");

       // System.out.println("user = [" + user + "], password = [" + password + "], code = [" + code + "], session = [" + session + "]");

//        if (captchaId.equals(code)) {

            User userx = userRepository.findUserByUsername(user);

            if (userx != null) {

                if (userx.getPassword().equals(password)) {
                    session.setAttribute("user_id", userx.getId());
                    session.setAttribute("user_username", user);
                    session.setAttribute("user_name", userx.getName());

                    return Result.Result(200,"登陆成功","");
                } else {
                    return Result.Result(500,"密码错误","");
                }
            } else {
                return Result.Result(300,"用户不存在","");
            }
//        } else {
//            return Result.Result(400,"验证码错误","");
//
//        }

    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public JSONObject add(
            @RequestParam("username") String user,
            @RequestParam("password") String user_pass,
            @RequestParam("name") String name
            ) {

        User user1 = new User();
        user1.setUsername(user);
        user1.setPassword(user_pass);
        user1.setName(name);

        userRepository.save(user1);

        //保存成功
        return Result.Result(200,"添加成功","");
    }

    @RequestMapping(value = "/update")
    public JSONObject update(
            @RequestParam("id") int id,
            @RequestParam("userName") String user,
            @RequestParam("password") String user_pass,
            @RequestParam("Name") String name) {

        User user1 = userRepository.getOne(id);
        user1.setUsername(user);
        user1.setPassword(user_pass);
        user1.setName(name);

        userRepository.save(user1);

        //保存成功
        return Result.Result(200,"修改成功","");

    }

    //管理员删除
    @RequestMapping(value = "/del")
    public JSONObject del(@RequestParam("userid") int user_id,
                      HttpServletRequest request) {
        //userName1为操作的user,userName2为被操作的user
        Result result = new Result();

        //更改用户标志


        userRepository.delete(user_id);

        return Result.Result(200,"删除成功","");
    }

    //密码修改处理
    @RequestMapping(value = "changePwdx")
    public JSONObject changePwd(@RequestParam("userName") String user,
                            @RequestParam("password") String password,
                            @RequestParam("password1") String password1,
                            HttpServletRequest request) {
        //userName1为操作的user,userName2为被操作的user


        //更改用户标志
        User user2 = userRepository.findUserByUsername(user);

        if (user2 != null) {

            if (user2.getPassword().equals(password)) {

                user2.setPassword(password1);
                User user1 = userRepository.save(user2);

                if (user1 != null) {

                    //修改密码成功

                    return Result.Result(200,"修改成功","");
                } else {

                    return Result.Result(500,"修改失败","");
                }
            } else {
                return Result.Result(501,"原密码错误","");
            }
        } else {
            return Result.Result(502,"没有该用户","");

        }


    }
}
