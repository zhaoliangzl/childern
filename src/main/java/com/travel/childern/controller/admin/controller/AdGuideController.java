package com.travel.childern.controller.admin.controller;


import com.alibaba.fastjson.JSONObject;

import com.travel.childern.model.Guide;
import com.travel.childern.repo.GuideRepository;
import com.travel.childern.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/adguideservice")
public class AdGuideController {

    @Autowired
    private GuideRepository guideRepository;

    @RequestMapping("/add")
    public JSONObject add(
            @RequestParam("username") String username,
            @RequestParam("name") String name,
            @RequestParam("password") String password,
            @RequestParam("phone") String phone,
            @RequestParam("header") String header,
            @RequestParam("sex") String sex,
            @RequestParam("brief") String brief,
            @RequestParam("description") String description
            ){
        System.out.println("username = [" + username + "], name = [" + name + "], password = [" + password + "], phone = [" + phone + "], header = [" + header + "], sex = [" + sex + "], brief = [" + brief + "], description = [" + description + "]");
        Guide guide = new Guide();
        guide.setBrief(brief);
        guide.setDescription(description);
        guide.setHeader(header);
        guide.setName(name);
        guide.setPassword(password);
        guide.setPhone(phone);
        guide.setUsername(username);
        guide.setSex(sex);

        Guide guide1 = guideRepository.save(guide);

        if(guide1 != null){
            return Result.Result(200,"添加成功","");
        }else{
            return Result.Result(400,"添加失败","");
        }
    }

    @RequestMapping(value = "/update")
    public JSONObject edit(
            @RequestParam("id") int id,
            @RequestParam("username") String username,
            @RequestParam("name") String name,
            @RequestParam("password") String password,
            @RequestParam("phone") String phone,
            @RequestParam("header") String header,
            @RequestParam("sex") String sex,
            @RequestParam("brief") String brief,
            @RequestParam("description") String description
    ){
        Guide guide = guideRepository.findOne(id);
        if(guide != null){
            guide.setBrief(brief);
            guide.setDescription(description);
            guide.setHeader(header);
            guide.setName(name);
            guide.setPassword(password);
            guide.setPhone(phone);
            guide.setUsername(username);
            guide.setSex(sex);
            Guide guide1 = guideRepository.save(guide);

            if(guide1 != null){
                return Result.Result(200,"修改成功","");
            }else{
                return Result.Result(400,"修改失败","");
            }
        }else{
            return Result.Result(401,"数据不存在","");
        }
    }

    @RequestMapping(value = "/del")
    public JSONObject del(@RequestParam("id") int id
                         ) {
        //更改用户标志
        Guide guide =  guideRepository.getOne(id);
        if(guide == null){
            return Result.Result(400,"用户不存在","");
        }else {
            guideRepository.delete(id);
            return Result.Result(200,"删除成功","");
        }


    }


}
