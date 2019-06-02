package com.travel.childern.controller.admin.controller;


import com.alibaba.fastjson.JSONObject;
import com.travel.childern.model.Admin;
import com.travel.childern.repo.AdminRepository;
import com.travel.childern.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping(value = "/adminservice")
@RestController
@Service
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @RequestMapping(value = "/add")
    public JSONObject add(
            @RequestParam("username")String username,
            @RequestParam("password")String password,
            @RequestParam("name")String name
    ){
        System.out.println(adminRepository);
        Admin adminTmp = adminRepository.findAdminByUsername(username);
        if(adminTmp == null){
            adminTmp = new Admin();
            adminTmp.setUsername(username);
            adminTmp.setPassword(password);
            adminTmp.setName(name);
            Admin resAdmin = adminRepository.save(adminTmp);
            if(resAdmin != null){
                return Result.Result(200,"添加成功","");
            }else{
                return Result.Result(401,"添加失败","");
            }
        }else{
            //现在用户名已经重复
            return Result.Result(400,"用户名重复","");
        }
    }

    @RequestMapping(value = "/update")
    public JSONObject update(
            @RequestParam("adminid")int adminId,
            @RequestParam("username")String username,
            @RequestParam("password")String password,
            @RequestParam("name")String name
    ){

        Admin adminTmp = adminRepository.findOne(adminId);
        if(adminTmp == null){
            return Result.Result(400,"用户名不存在","");
        }else{
            adminTmp.setUsername(username);
            adminTmp.setPassword(password);
            adminTmp.setName(name);
            Admin resAdmin = adminRepository.save(adminTmp);
            if(resAdmin != null){
                return Result.Result(200,"修改成功","");
            }else{
                return Result.Result(401,"修改失败","");
            }
        }
    }

    //密码修改处理
    @RequestMapping(value = "/changePwdx")
    public JSONObject changePwd(@RequestParam("adminName") String admin,
                            @RequestParam("password") String password,
                            @RequestParam("password1") String password1,
                            HttpServletRequest request) {
        //adminName1为操作的admin,adminName2为被操作的admin

        Admin adminTmp = adminRepository.findAdminByUsername(admin);

        if (adminTmp != null) {

            if (adminTmp.getPassword().equals(password)) {

                adminTmp.setPassword(password1);
                Admin admin1 = adminRepository.save(adminTmp);

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

    @RequestMapping(value = "/del")
    public JSONObject del(@RequestParam("id") int id,
                      HttpServletRequest request) {
        //更改用户标志

        Admin admin1 =  adminRepository.getOne(id);
        if(admin1 == null){
            return Result.Result(400,"用户不存在","");
        }else {
            adminRepository.delete(id);
            return Result.Result(200,"删除成功","");
        }


    }

}
