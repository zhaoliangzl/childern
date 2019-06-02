package com.travel.childern.controller.admin.view;


import com.travel.childern.model.Banner;
import com.travel.childern.model.Guide;
import com.travel.childern.repo.BannersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping(value = "/adbannersadmin")
@Controller
public class AdBanners {

    @Autowired
    private BannersRepository bannersRepository;


    @RequestMapping(value = "/add")
    public String add(){
        return "/admin/banners/add";
    }

    @RequestMapping(value = "/update")
    public String update(
            @RequestParam("id")int adminId,
            Model model
    ){
        model.addAttribute("banners",bannersRepository.findOne(adminId));
        return "/admin/banners/edit";
    }

    @RequestMapping(value = "/list")
    public String list(Model model){

        List<Banner> banners = bannersRepository.findAll();

        model.addAttribute("list",banners);
        return "/admin/banners/list";
    }



}
