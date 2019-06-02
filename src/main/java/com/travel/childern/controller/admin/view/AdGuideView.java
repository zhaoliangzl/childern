package com.travel.childern.controller.admin.view;


import com.travel.childern.model.Admin;
import com.travel.childern.model.Guide;
import com.travel.childern.repo.AdminRepository;
import com.travel.childern.repo.GuideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/guideadmin")
public class AdGuideView {

    @Autowired
    private GuideRepository guideRepository;


    @RequestMapping(value = "/add")
    public String add(){
        return "/admin/guide/add";
    }

    @RequestMapping(value = "/update")
    public String update(
            @RequestParam("id")int adminId,
            Model model
    ){
        model.addAttribute("guide",guideRepository.findOne(adminId));
        return "/admin/guide/edit";
    }

    @RequestMapping(value = "/list")
    public String list(Model model){

        List<Guide> admins = guideRepository.findAll();

        model.addAttribute("list",admins);
        return "/admin/guide/list";
    }
}
