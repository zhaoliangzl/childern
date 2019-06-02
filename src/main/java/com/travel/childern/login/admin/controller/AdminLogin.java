package com.travel.childern.login.admin.controller;

import com.alibaba.fastjson.JSONObject;

import com.travel.childern.model.Admin;
import com.travel.childern.repo.AdminRepository;
import com.travel.childern.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping(value = "/adminloginservice")
public class AdminLogin {

    @Autowired
    private AdminRepository adminRepository;

    @RequestMapping(value = "/login")
    public JSONObject tea_login_deal(@RequestParam("admin") String admin,
                                 @RequestParam("password") String password,
                                 @RequestParam("code") String code,
                                 HttpSession session) {

        String captchaId = (String) session.getAttribute("vrifyCode");

        System.out.println("admin = [" + admin + "], password = [" + password + "], code = [" + code + "], session = [" + session + "]");

        if (captchaId.equals(code)) {

            Admin adminx = adminRepository.findAdminByUsername(admin);



            if (adminx != null) {

                if (adminx.getPassword().equals(password)) {
                    session.setAttribute("admin", admin);
                    session.setAttribute("admin_id", adminx.getId());
                    session.setAttribute("adminName", adminx.getName());

                    return Result.Result(404,"登陆成功","");
                } else {
                    return Result.Result(500,"密码错误","");
                }
            } else {
                return Result.Result(300,"用户不存在","");
            }
        } else {
            return Result.Result(400,"验证码错误","");

        }

    }

    @RequestMapping(value = "/add")
    public JSONObject add(
            @RequestParam("adminName") String admin,
            @RequestParam("password") String admin_pass,
            @RequestParam("Name") String name
            ) {

        Admin admin1 = new Admin();
        admin1.setUsername(admin);
        admin1.setPassword(admin_pass);
        admin1.setName(name);

        adminRepository.save(admin1);

        //保存成功
        return Result.Result(200,"添加成功","");
    }

    @RequestMapping(value = "/update")
    public JSONObject update(
            @RequestParam("id") int id,
            @RequestParam("adminName") String admin,
            @RequestParam("password") String admin_pass,
            @RequestParam("Name") String name) {

        Admin admin1 = adminRepository.getOne(id);
        admin1.setUsername(admin);
        admin1.setPassword(admin_pass);
        admin1.setName(name);

        adminRepository.save(admin1);

        //保存成功
        return Result.Result(200,"修改成功","");

    }

    //管理员删除
    @RequestMapping(value = "/del")
    public JSONObject del(@RequestParam("adminid") int admin_id,
                      HttpServletRequest request) {
        //adminName1为操作的admin,adminName2为被操作的admin
        Result result = new Result();

        //更改用户标志


        adminRepository.delete(admin_id);

        return Result.Result(200,"删除成功","");
    }

    //密码修改处理
    @RequestMapping(value = "changePwdx")
    public JSONObject changePwd(@RequestParam("adminName") String admin,
                            @RequestParam("password") String password,
                            @RequestParam("password1") String password1,
                            HttpServletRequest request) {
        //adminName1为操作的admin,adminName2为被操作的admin


        //更改用户标志
        Admin admin2 = adminRepository.findAdminByUsername(admin);

        if (admin2 != null) {

            if (admin2.getPassword().equals(password)) {

                admin2.setPassword(password1);
                Admin admin1 = adminRepository.save(admin2);

                if (admin1 != null) {

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
