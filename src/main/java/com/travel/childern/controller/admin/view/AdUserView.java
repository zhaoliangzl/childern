package com.travel.childern.controller.admin.view;


import com.travel.childern.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jws.WebParam;

@Controller
@RequestMapping(value = "/aduser")
public class AdUserView {

    @Autowired
    private UserRepository userRepository;


    @RequestMapping(value = "/list")
    public String list(Model model){
        model.addAttribute("users",userRepository.findAll());
        return "/admin/user/list";
    }

    @RequestMapping(value = "/update")
    public String edit(int id,Model model){
        model.addAttribute("user",userRepository.getOne(id));
        return "/admin/user/edit";
    }

}
