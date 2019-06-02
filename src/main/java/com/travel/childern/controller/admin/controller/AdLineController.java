package com.travel.childern.controller.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.travel.childern.model.Line;
import com.travel.childern.model.Line;
import com.travel.childern.repo.LineRepository;
import com.travel.childern.repo.LineRepository;
import com.travel.childern.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/adlineservice")
public class AdLineController {

    @Autowired
    private LineRepository lineRepository;

    @RequestMapping("/add")
    public JSONObject add(

            @RequestParam("name") String name,
            @RequestParam("line") String line,
            @RequestParam("description") String description
    ){
        Line Line = new Line();
        Line.setDescription(description);
        Line.setName(name);
        Line.setLine(line);
        Line Line1 = lineRepository.save(Line);
        if(Line1 != null){
            return Result.Result(200,"添加成功","");
        }else{
            return Result.Result(400,"添加失败","");
        }
    }

    @RequestMapping(value = "/update")
    public JSONObject edit(
            @RequestParam("id") int id,
            @RequestParam("name") String name,
            @RequestParam("line") String line,
            @RequestParam("description") String description
    ){
        Line Line = lineRepository.findOne(id);
        if(Line != null){

            Line.setDescription(description);
            Line.setName(name);
            Line.setLine(line);
            Line Line1 = lineRepository.save(Line);
            if(Line1 != null){
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
        Line Line =  lineRepository.getOne(id);
        if(Line == null){
            return Result.Result(400,"路线不存在","");
        }else {
            lineRepository.delete(id);
            return Result.Result(200,"删除成功","");
        }


    }
    
}
