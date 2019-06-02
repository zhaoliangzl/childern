package com.travel.childern.controller.admin.view;

import com.travel.childern.model.Banner;
import com.travel.childern.model.Comment;
import com.travel.childern.model.Contact;
import com.travel.childern.model.Order;
import com.travel.childern.repo.CommentRepository;
import com.travel.childern.repo.ContactRepository;
import com.travel.childern.repo.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/adorder")
@Controller
public class AdOrderView {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private CommentRepository commentRepository;

    @RequestMapping(value = "/add")
    public String add(){
        return "/admin/order/add";
    }

    @RequestMapping(value = "/update")
    public String update(
            @RequestParam("id")int orderId,
            Model model
    ){
        Order order = orderRepository.findOne(orderId);
        model.addAttribute("status",order.getOrderStatus());
        model.addAttribute("order",order);
        //
        String[]contacts = order.getContacts().split(",");

        List<Contact> contacts1 = new ArrayList<>();

        for (String c:contacts) {
            System.out.println("添加"+c);
            Contact contact =contactRepository.getOne(Integer.parseInt(c));
            System.out.println(contact);
            contacts1.add(contact);

        }
        if(order.getIsCommnet() != null){
            Comment comment = commentRepository.getOne(order.getIsCommnet());
            if(order.getOrderStatus() == 4){
                model.addAttribute("comment",comment);
            }

            if(comment.isEnable()){
                model.addAttribute("isEnable",1);
            }else{
                model.addAttribute("isEnable",0);
            }
        }
        model.addAttribute("contacts",contacts1);

        return "/admin/order/edit";
    }

    @RequestMapping(value = "/list")
    public String list(Model model){

        List<Order> orders = orderRepository.findAll();

        model.addAttribute("list",orders);
        return "/admin/order/list";
    }
    @RequestMapping(value = "/waitlist")
    public String waitlist(Model model){
        List<Order> orders = orderRepository.findAllByOrderStatus(1);
        model.addAttribute("status",1);
        model.addAttribute("list",orders);
        return "/admin/order/list";
    }

    @RequestMapping(value = "/confirmlist")
    public String confirmlist(Model model){
        List<Order> orders = orderRepository.findAllByOrderStatus(2);
        model.addAttribute("status",2);
        model.addAttribute("list",orders);
        return "/admin/order/list";
    }

    @RequestMapping(value = "/waitcommentlist")
    public String waitcommentlist(Model model){
        List<Order> orders = orderRepository.findAllByOrderStatus(3);
        model.addAttribute("status",3);
        model.addAttribute("list",orders);
        return "/admin/order/list";
    }

    @RequestMapping(value = "/finishlist")
    public String finishlist(Model model){
        List<Order> orders = orderRepository.findAllByOrderStatus(4);
        model.addAttribute("status",4);
        model.addAttribute("list",orders);
        return "/admin/order/list";
    }

    @RequestMapping(value = "/cancellist")
    public String cancellist(Model model){
        List<Order> orders = orderRepository.findAllByOrderStatus(5);
        model.addAttribute("status",5);
        model.addAttribute("list",orders);
        return "/admin/order/list";
    }

}
