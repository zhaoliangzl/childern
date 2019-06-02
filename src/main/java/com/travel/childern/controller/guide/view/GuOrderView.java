package com.travel.childern.controller.guide.view;

import com.travel.childern.model.Goods;
import com.travel.childern.model.Order;
import com.travel.childern.repo.GoodsRepository;
import com.travel.childern.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/gudierorder")
@Controller
public class GuOrderView {


    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private OrderRepository orderRepository;

    @RequestMapping(value = "/list")
    public String list(int id, Model model){
        //首先拿到用户id
        List<Goods> goodsList = goodsRepository.findGoodsByGuideListLike(id+"");

        List<Order> orderList = new ArrayList<>();

        int []goodsIds  = new int[goodsList.size()];
        int i = 0;
        for (Goods goods:goodsList) {
            //根据goods_id找到对应的订单
            List<Order> orders = orderRepository.findAllByGoodsId(goods.getId());
            orderList.addAll(orders);
            goodsIds[i++] = goods.getId();
        }

        model.addAttribute("list",orderList);
        return "/guide/order/list";
    }


}
