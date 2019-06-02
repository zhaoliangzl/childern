package com.travel.childern.controller.admin.controller;


import com.alibaba.fastjson.JSONObject;
import com.travel.childern.model.Banner;
import com.travel.childern.repo.BannersRepository;
import com.travel.childern.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/adbannersservice")
public class AdBannersController {

    @Autowired
    private BannersRepository bannersRepository;

    @RequestMapping("/add")
    public JSONObject add(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("enable") int enable
            ){
        Banner banners = new Banner();
        banners.setTitle(title);
        banners.setContent(content);
        banners.setEnable(enable == 1? true:false);

        Banner banners1 = bannersRepository.save(banners);

        if(banners1 != null){
            return Result.Result(200,"添加成功","");
        }else{
            return Result.Result(400,"添加失败","");
        }
    }

    @RequestMapping(value = "/update")
    public JSONObject edit(
            @RequestParam("id") int id,
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("enable") int enable
    ){
        Banner banners = bannersRepository.findOne(id);
        if(banners != null){
            banners.setTitle(title);
            banners.setContent(content);
            banners.setEnable(enable == 1? true:false);
            Banner banners1 = bannersRepository.save(banners);

            if(banners1 != null){
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
        Banner banners =  bannersRepository.getOne(id);
        if(banners == null){
            return Result.Result(400,"用户不存在","");
        }else {
            bannersRepository.delete(id);
            return Result.Result(200,"删除成功","");
        }


    }


}
