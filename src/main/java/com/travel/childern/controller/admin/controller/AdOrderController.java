package com.travel.childern.controller.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.travel.childern.model.Comment;
import com.travel.childern.model.Order;
import com.travel.childern.repo.CommentRepository;
import com.travel.childern.repo.OrderRepository;
import com.travel.childern.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/adorderservice")
@RestController
public class AdOrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CommentRepository commentRepository;

    @RequestMapping(value = "/confirm")
    public JSONObject confirm(int id,int type){

        Order order  = orderRepository.getOne(id);

        if(order != null){
            if (type == 1) {
                order.setOrderStatus(2);
            }else if(type == 2){
                order.setOrderStatus(5);
            }else if(type == 3){
                order.setOrderStatus(3);
            }else if(type == 10){
                //评论展示
                Comment comment = commentRepository.getOne(order.getIsCommnet());
                comment.setEnable(true);
                commentRepository.save(comment);
            }else if(type == 11){
                //评论展示
                Comment comment = commentRepository.getOne(order.getIsCommnet());
                comment.setEnable(false);
                commentRepository.save(comment);
            }


            Order order1 = orderRepository.save(order);
            if(order1 != null){
                return Result.Result(200,"","");
            }else{
                return Result.Result(405,"状态更新失败","");
            }

        }else{
            return Result.Result(404,"订单不存在","");
        }
    }

}
