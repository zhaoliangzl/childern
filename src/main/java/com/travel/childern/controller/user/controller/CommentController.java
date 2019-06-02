package com.travel.childern.controller.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.travel.childern.model.Comment;
import com.travel.childern.model.Order;
import com.travel.childern.repo.CommentRepository;
import com.travel.childern.repo.OrderRepository;
import com.travel.childern.utils.Result;
import com.travel.childern.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/comment")
public class CommentController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CommentRepository commentRepository;

    @RequestMapping(value = "/add")
    public JSONObject add(
            @RequestParam("id")int id,
            @RequestParam("score")int score,
            @RequestParam("content")String content
    ){

        Order order = orderRepository.getOne(id);

        if(order != null){

            Comment comment = new Comment();

            comment.setOrderId(id);
            comment.setScore(score);
            comment.setContent(content);
            comment.setEnable(false);
            comment.setTime(Utils.getNowDate());
            comment.setGoodsId(order.getGoodsId());

            Comment comment1 = commentRepository.save(comment);

            Order order1 = orderRepository.getOne(id);

            order1.setOrderStatus(4);
            order1.setIsCommnet(comment1.getId());

            orderRepository.save(order1);

            if(comment1 != null){
                return Result.Result(200,"","");
            }else{
                return Result.Result(405,"评论失败","");
            }

        }else{
            return Result.Result(404,"订单不存在","");
        }


    }




}
