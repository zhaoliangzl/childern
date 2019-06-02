package com.travel.childern.controller.admin.view;


import com.alibaba.fastjson.JSONObject;
import com.travel.childern.model.Admin;
import com.travel.childern.repo.AdminRepository;
import com.travel.childern.repo.OrderRepository;
import com.travel.childern.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminView {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private OrderRepository orderRepository;

    @RequestMapping(value = "")
    public String index(HttpSession session,Model model){

        model.addAttribute("adminName",(String) session.getAttribute("adminName"));
        return "/admin/admin/index";
    }


    @RequestMapping(value = "/welcome")
    public String welcome(Model model){

        int num1 = orderRepository.countAllByOrderStatus(1);
        int num2 = orderRepository.countAllByOrderStatus(2);
        int num3 = orderRepository.countAllByOrderStatus(3);
        int num4 = orderRepository.countAllByOrderStatus(4);
        int num5 = orderRepository.countAllByOrderStatus(5);

        model.addAttribute("num1",num1);
        model.addAttribute("num2",num2);
        model.addAttribute("num3",num3);
        model.addAttribute("num4",num4);
        model.addAttribute("num5",num5);
        model.addAttribute("numall",num1+num2+num3+num4+num5);
        //最近7天的订单情况
        int position = 7;
        int [] numForAll = new int[position];

        String[] dates = new String[position];

        for (int i = 0; i < position; i++) {

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_MONTH, 0-i);
            Date currentTime = calendar.getTime();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = formatter.format(currentTime);
            dates[position-i-1] = dateString;
            numForAll[i] = orderRepository.findIOrderByTimeLike(dateString);
        }

        //开始逐个查询

        int [] numFor1 = new int[position];
        for (int i = 0; i < position; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_MONTH, 0-i);
            Date currentTime = calendar.getTime();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = formatter.format(currentTime);
            numFor1[i] = orderRepository.findIOrderByTimeLikeAndOrderStatus(dateString,1);
        }

        //开始逐个查询

        int [] numFor2 = new int[position];
        for (int i = 0; i < position; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_MONTH, 0-i);
            Date currentTime = calendar.getTime();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = formatter.format(currentTime);
            numFor2[i] = orderRepository.findIOrderByTimeLikeAndOrderStatus(dateString,2);
        }

        //开始逐个查询

        int [] numFor3 = new int[position];
        for (int i = 0; i < position; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_MONTH, 0-i);
            Date currentTime = calendar.getTime();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = formatter.format(currentTime);
            numFor3[i] = orderRepository.findIOrderByTimeLikeAndOrderStatus(dateString,3);
        }

        //开始逐个查询
        int [] numFor4 = new int[position];
        for (int i = 0; i < position; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_MONTH, 0-i);
            Date currentTime = calendar.getTime();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = formatter.format(currentTime);
            numFor4[i] = orderRepository.findIOrderByTimeLikeAndOrderStatus(dateString,4);
        }

        //开始逐个查询
        int [] numFor5 = new int[position];
        for (int i = 0; i < position; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_MONTH, 0-i);
            Date currentTime = calendar.getTime();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = formatter.format(currentTime);
            numFor5[i] = orderRepository.findIOrderByTimeLikeAndOrderStatus(dateString,5);
        }

        model.addAttribute("numForAll",numForAll);
        model.addAttribute("numFor1",numFor1);
        model.addAttribute("numFor2",numFor2);
        model.addAttribute("numFor3",numFor3);
        model.addAttribute("numFor4",numFor4);
        model.addAttribute("numFor5",numFor5);
        model.addAttribute("datas",dates);
        return "/admin/admin/welcome";
    }

    @RequestMapping(value = "/add")
    public String add(){
        return "/admin/admin/add";
    }

    @RequestMapping(value = "/update")
    public String update(
            @RequestParam("id")int adminId,
            Model model
    ){
        model.addAttribute("admin",adminRepository.findOne(adminId));
        return "/admin/admin/update";
    }

    @RequestMapping(value = "/changepwd")
    public String changepwd(){

        return "/admin/admin/changepwd";
    }

    @RequestMapping(value = "/quit")
    public String quit(HttpSession session){

        Object admin = session.getAttribute("admin");
        Object adminName =session.getAttribute("adminName");

        if(admin != null && adminName != null){

            session.removeAttribute("admin");
            session.removeAttribute("adminName");

        }

        return "redirect:/admin";
    }

    @RequestMapping(value = "/list")
    public String list(Model model,HttpSession session){

        List<Admin> admins = adminRepository.findAll();

        model.addAttribute("list",admins);

        int admin_id = (int)session.getAttribute("admin_id");

        model.addAttribute("admin_id",admin_id);
        return "/admin/admin/list";
    }
}
