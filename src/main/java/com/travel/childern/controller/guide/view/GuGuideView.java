package com.travel.childern.controller.guide.view;

import com.travel.childern.model.Guide;
import com.travel.childern.repo.GuideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jws.WebParam;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/guiders")
@Controller
public class GuGuideView {


    @Autowired
    private GuideRepository guideRepository;

    @RequestMapping(value = "")
    public String index(Model model, HttpSession session){
        session.setAttribute("guide_id",2);
        model.addAttribute("guide_id",session.getAttribute("guide_id"));
        return "/guide/guide/index";
    }

    @RequestMapping(value = "/list")
    public String list(@RequestParam("id") int id,Model model){
        model.addAttribute("guide",guideRepository.getOne(id));
        return "/guide/guide/list";
    }

    @RequestMapping(value = "/update")
    public String update(
            @RequestParam("id")int adminId,
            Model model
    ){
        model.addAttribute("guide",guideRepository.findOne(adminId));
        return "/guide/guide/edit";
    }



}
