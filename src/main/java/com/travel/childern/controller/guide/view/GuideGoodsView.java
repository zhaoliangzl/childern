package com.travel.childern.controller.guide.view;

import com.travel.childern.model.Goods;
import com.travel.childern.repo.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(value = "/guidegoods")
@Controller
public class GuideGoodsView {

    @Autowired
    private GoodsRepository goodsRepository;

    @RequestMapping(value = "/list")
    public String list(int id, Model model){

        List<Goods> goods = goodsRepository.findGoodsByGuideListLike(id+"");

        model.addAttribute("list",goods);
        model.addAttribute("id",id);

        return "/guide/goods/list";
    }


}
