package com.travel.childern.controller.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.travel.childern.model.Comment;
import com.travel.childern.repo.CommentRepository;
import com.travel.childern.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/adcommentservice")
@RestController
public class AdCommnetController {

    @Autowired
    private CommentRepository commentRepository;

    @RequestMapping(value = "/yes")
    public JSONObject yes(int id){

        Comment comment = commentRepository.getOne(id);

        if(comment != null){

            comment.setEnable(true);
            commentRepository.save(comment);
            return Result.Result(200,"",":");
        }else{

            return Result.Result(404,"","");
        }
    }

    @RequestMapping(value = "/no")
    public JSONObject no(int id){

        Comment comment = commentRepository.getOne(id);

        if(comment != null){

            comment.setEnable(false);
            commentRepository.save(comment);
            return Result.Result(200,"",":");
        }else{

            return Result.Result(404,"","");
        }
    }





}
