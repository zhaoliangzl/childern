package com.travel.childern.controller.admin.view;

import com.travel.childern.model.Guide;
import com.travel.childern.model.Line;
import com.travel.childern.repo.LineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/lineadmin")
public class AdLineView {

    @Autowired
    private LineRepository lineRepository;

    @RequestMapping(value = "/add")
    public String add(){
        return "/admin/line/add";
    }

    @RequestMapping(value = "/update")
    public String update(
            @RequestParam("id")int adminId,
            Model model
    ){
        model.addAttribute("line",lineRepository.findOne(adminId));
        return "/admin/line/edit";
    }

    @RequestMapping(value = "/list")
    public String list(Model model){

        List<Line> admins = lineRepository.findAll();

        model.addAttribute("list",admins);
        return "/admin/line/list";
    }


}
