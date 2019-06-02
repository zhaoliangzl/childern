package com.travel.childern.login.guide.controller;

import com.alibaba.fastjson.JSONObject;
import com.travel.childern.model.Guide;

import com.travel.childern.repo.GuideRepository;

import com.travel.childern.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/guideloginservice")
public class GuideLogin {

    @Autowired
    private GuideRepository guideRepository;

    @RequestMapping(value = "/login")
    public JSONObject tea_login_deal(@RequestParam("guide") String guide,
                                 @RequestParam("password") String password,
                                 @RequestParam("code") String code,
                                 HttpSession session) {

        String captchaId = (String) session.getAttribute("vrifyCode");

        System.out.println("guide = [" + guide + "], password = [" + password + "], code = [" + code + "], session = [" + session + "]");

        if (captchaId.equals(code)) {

            Guide guidex = guideRepository.findGuideByUsername(guide);



            if (guidex != null) {

                if (guidex.getPassword().equals(password)) {
                    session.setAttribute("guide", guide);
                    session.setAttribute("guideName", guidex.getName());

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
            @RequestParam("guideName") String guide,
            @RequestParam("password") String guide_pass,
            @RequestParam("Name") String name
            ) {

        Guide guide1 = new Guide();
        guide1.setUsername(guide);
        guide1.setPassword(guide_pass);
        guide1.setName(name);

        guideRepository.save(guide1);

        //保存成功
        return Result.Result(200,"添加成功","");
    }

    @RequestMapping(value = "/update")
    public JSONObject update(
            @RequestParam("id") int id,
            @RequestParam("guideName") String guide,
            @RequestParam("password") String guide_pass,
            @RequestParam("Name") String name) {

        Guide guide1 = guideRepository.getOne(id);
        guide1.setUsername(guide);
        guide1.setPassword(guide_pass);
        guide1.setName(name);

        guideRepository.save(guide1);

        //保存成功
        return Result.Result(200,"修改成功","");

    }

    //管理员删除
    @RequestMapping(value = "/del")
    public JSONObject del(@RequestParam("guideid") int guide_id,
                      HttpServletRequest request) {
        //guideName1为操作的guide,guideName2为被操作的guide
        Result result = new Result();

        //更改用户标志


        guideRepository.delete(guide_id);

        return Result.Result(200,"删除成功","");
    }

    //密码修改处理
    @RequestMapping(value = "changePwdx")
    public JSONObject changePwd(@RequestParam("guideName") String guide,
                            @RequestParam("password") String password,
                            @RequestParam("password1") String password1,
                            HttpServletRequest request) {
        //guideName1为操作的guide,guideName2为被操作的guide


        //更改用户标志
        Guide guide2 = guideRepository.findGuideByUsername(guide);

        if (guide2 != null) {

            if (guide2.getPassword().equals(password)) {

                guide2.setPassword(password1);
                Guide guide1 = guideRepository.save(guide2);

                if (guide1 != null) {

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
