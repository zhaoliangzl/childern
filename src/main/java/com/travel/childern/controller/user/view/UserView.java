package com.travel.childern.controller.user.view;


import com.alibaba.fastjson.JSON;
import com.travel.childern.model.Comment;
import com.travel.childern.model.Goods;
import com.travel.childern.model.Guide;
import com.travel.childern.model.Line;
import com.travel.childern.repo.*;
import com.travel.childern.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jws.WebParam;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class UserView {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BannersRepository bannersRepository;

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private GuideRepository guideRepository;

    @Autowired
    private LineRepository lineRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CommentRepository commentRepository;

    @RequestMapping(value = "")
    public String index(Model model, HttpSession session){

        model.addAttribute("banners",bannersRepository.findAllByEnableIsTrue());
        model.addAttribute("goods",goodsRepository.findAll());
        model.addAttribute("guides",guideRepository.findAll());
        Object u = session.getAttribute("user_id");
        if(u == null){
            model.addAttribute("user",0);
        }else{
            model.addAttribute("user",1);
        }
        model.addAttribute("index",1);
        return "user/index/index";
    }


    @RequestMapping(value = "/goods")
    public String goods(Model model, HttpSession session){
        model.addAttribute("goods",goodsRepository.findAll());
        Object u = session.getAttribute("user_id");
        if(u == null){
            model.addAttribute("user",0);
        }else{
            model.addAttribute("user",1);
        }
        model.addAttribute("index",2);
        return "user/index/goods";
    }


    @RequestMapping(value = "/good")
    public String good(int id, Model model, HttpSession session){
        Goods goods = goodsRepository.getOne(id);
        //System.out.println(JSON.parse(goods.toString()));
        model.addAttribute("good", goods);
//        System.out.println(JSON.parse(goods.toString()));
        model.addAttribute("goods", goods.toString()+"");

        Line line = lineRepository.findOne(goods.getLineId());
        model.addAttribute("line",line);

        String[] lines = line.getLine().split(",");
        model.addAttribute("lines",lines);

        model.addAttribute("lineslength",lines.length);

        System.out.println(line.toString());

        String [] guides = goods.getGuideList().split(",");
        List<Guide> guideList = new ArrayList<>();

        for (String guideIdStr:guides) {
            guideList.add(guideRepository.getOne(Integer.parseInt(guideIdStr)));
        }
        model.addAttribute("guides",guideList);

        //获取评价列表
        List<Comment> comments = commentRepository.findAllByGoodsIdAndEnableIsTrue(id);

        model.addAttribute("comments",comments);
        //计算平均分数
        model.addAttribute("avg",commentRepository.getAverageScore());

        model.addAttribute("commentNum",comments.size());


        Object u = session.getAttribute("user_id");
        if(u == null){
            model.addAttribute("user",0);
        }else{
            model.addAttribute("user",1);
        }
        model.addAttribute("index",2);
        return "user/index/detail";
    }

    //用户购物车按钮
    @RequestMapping(value = "/cart")
    public String cart(int id,Model model,HttpSession session){

        Integer userId = (Integer)session.getAttribute("user_id");

        if(userId != null){
            model.addAttribute("user_id",userId);
            Goods goods = goodsRepository.getOne(id);
            model.addAttribute("goods",goods);
            model.addAttribute("line",lineRepository.getOne(goods.getLineId()));
            model.addAttribute("index",2);
            Object u = session.getAttribute("user_id");
            if(u == null){
                model.addAttribute("user",0);
            }else{
                model.addAttribute("user",1);
            }
            return "user/index/cart";

        }else{
            return "user/login/login";
        }

    }

    @RequestMapping(value = "/lines")
    public String lines(Model model,HttpSession session){
        model.addAttribute("index",3);
        model.addAttribute("lines",lineRepository.findAll());
        Object u = session.getAttribute("user_id");
        if(u == null){
            model.addAttribute("user",0);
        }else{
            model.addAttribute("user",1);
        }
        return "user/index/lines";
    }

    @RequestMapping(value = "/guides")
    public String guides(Model model,HttpSession session){
        model.addAttribute("index",4);
        model.addAttribute("guides",guideRepository.findAll());
        Object u = session.getAttribute("user_id");
        if(u == null){
            model.addAttribute("user",0);
        }else{
            model.addAttribute("user",1);
        }
        return "user/index/guides";
    }

    @RequestMapping(value = "/guide")
    public String guide(int id,Model model,HttpSession session){
        model.addAttribute("index",4);
        model.addAttribute("guide",guideRepository.getOne(id));
        Object u = session.getAttribute("user_id");
        if(u == null){
            model.addAttribute("user",0);
        }else{
            model.addAttribute("user",1);
        }
        return "user/index/guide";
    }

    @RequestMapping(value = "/about")
    public String about(Model model,HttpSession session){
        model.addAttribute("index",5);
        Object u = session.getAttribute("user_id");
        if(u == null){
            model.addAttribute("user",0);
        }else{
            model.addAttribute("user",1);
        }
        return "user/index/about";
    }

    @RequestMapping(value = "/me")
    public String me(Model model,HttpSession session){

        Integer userId = (Integer)session.getAttribute("user_id");
        if(userId != null){
            model.addAttribute("user_id",userId);

            model.addAttribute("num1",orderRepository.countAllByUserIdAndOrderStatus(userId,1));
            model.addAttribute("num2",orderRepository.countAllByUserIdAndOrderStatus(userId,2));
            model.addAttribute("num3",orderRepository.countAllByUserIdAndOrderStatus(userId,3));
            model.addAttribute("num4",orderRepository.countAllByUserIdAndOrderStatus(userId,4));
            model.addAttribute("num5",orderRepository.countAllByUserIdAndOrderStatus(userId,5));
            //model.addAttribute("goods",goodsRepository.getOne(id));
            model.addAttribute("index",6);
            return "user/index/me";

        }else{
            return "user/login/login";
        }

    }

    //评价订单
    @RequestMapping(value = "/comment")
    public String comment(int id,Model model){

        model.addAttribute("order_id",id);

        return "user/index/comment";

    }

}
