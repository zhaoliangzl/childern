package com.travel.childern.controller.admin.view;

import com.travel.childern.model.Goods;
import com.travel.childern.model.Guide;
import com.travel.childern.repo.GoodsRepository;
import com.travel.childern.repo.GuideRepository;
import com.travel.childern.repo.LineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/goodsadmin")
public class AdGoodsView {

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private GuideRepository guideRepository;

    @Autowired
    private LineRepository lineRepository;

    @RequestMapping(value = "/add")
    public String add(Model model){

        model.addAttribute("guides",guideRepository.findAll());
        model.addAttribute("lines",lineRepository.findAll());
        return "/admin/goods/add";
    }

    @RequestMapping(value = "/update")
    public String update(
            @RequestParam("id")int adminId,
            Model model
    ){
        model.addAttribute("goods",goodsRepository.findOne(adminId));
        model.addAttribute("guides",guideRepository.findAll());
        model.addAttribute("lines",lineRepository.findAll());
        return "/admin/goods/edit";
    }

    @RequestMapping(value = "/list")
    public String list(Model model){

        List<Goods> admins = goodsRepository.findAll();

        model.addAttribute("list",admins);
        return "/admin/goods/list";
    }
}
