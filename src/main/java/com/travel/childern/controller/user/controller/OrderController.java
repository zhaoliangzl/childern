package com.travel.childern.controller.user.controller;


import com.alibaba.fastjson.JSONObject;
import com.travel.childern.model.Contact;
import com.travel.childern.model.Goods;
import com.travel.childern.model.Order;
import com.travel.childern.model.OrderAndContact;
import com.travel.childern.repo.ContactRepository;
import com.travel.childern.repo.GoodsRepository;
import com.travel.childern.repo.OrderRepository;
import com.travel.childern.utils.Result;
import com.travel.childern.utils.Utils;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequestMapping(value = "/order")
@RestController
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private GoodsRepository goodsRepository;

    @RequestMapping(value = "/add")
    public JSONObject add(

            @RequestParam("goodsid")int goodsId,
            @RequestParam("name")String name,
            @RequestParam("peonum")int peoNum,
            @RequestParam("peostr")String peostr,
            HttpSession session
    ){
        Object u = session.getAttribute("user_id");
        if(u == null){
            return Result.Result(400,"用户没有登陆","");
        }else{
            int userId = (int)u;

            String [] peos = peostr.split("-");

            String ids = null;

            for (String ps :peos) {
                String []p = ps.split(":");

                int type = Integer.parseInt(p[0]);
                String namex = p[1];
                int age = Integer.parseInt(p[2]);
                String phone = p[3];

                Contact c = contactRepository.findContactByTypeAndAgeAndPhoneAndName(type,age,phone,namex);
                if(c!= null){
                    ids = Utils.StringToArrayType(ids,c.getId()+"");
                }else{
                    Contact contact = new Contact();
                    contact.setAge(age);
                    contact.setName(namex);
                    contact.setPhone(phone);
                    contact.setType(type);
                    Contact contact1 = contactRepository.save(contact);
                    ids = Utils.StringToArrayType(ids,contact1.getId()+"");
                }
            }

            Order order = new Order();

            Goods goods = goodsRepository.getOne(goodsId);

            order.setGoodsId(goodsId);
            order.setGoTime(goods.getTime());
            order.setGoodsName(goods.getName());
            order.setUserId(userId);
            order.setLineId(goods.getLineId());
            order.setOrderSn(new Date().getTime()+"");
            order.setOrderStatus(1);
            order.setTime(Utils.getNowDate());
            order.setContacts(ids);
            order.setContactName(name);
            order.setPeoNum(peoNum);
            Order order1 = orderRepository.save(order);

            if(order1 == null){
                return Result.Result(403,"保存出错","");
            }else{
                return Result.Result(200,"","");
            }
        }
    }


    /**
     *  if(x == 1){
     *             url = '/order/wait';
     *         }
     *         if(x==2){
     *             url = '/order/confirm';
     *         }
     *         if(x==3){
     *             url = '/order/waitcomment';
     *         }
     *         if(x==4){
     *             url = '/order/finish';
     *         }
     *         if(x==4){
     *             url = '/order/cancel';
     *         }
     * */
    @RequestMapping(value = "/wait")
    public JSONObject waitOrder(int userid){

        List<Order> orders= orderRepository.findAllByUserIdAndOrderStatus(userid,1);

        List<OrderAndContact> orderAndContacts = new ArrayList<>();

        for (Order order: orders) {

            OrderAndContact orderAndContact = new OrderAndContact();

            orderAndContact.setOrder(order);

            String[]cs = order.getContacts().split(",");

            List<Contact> contacts = new ArrayList<>();

            for (String c:cs) {

                Contact contact = contactRepository.findOne(Integer.parseInt(c));

                contacts.add(contact);
            }
            orderAndContact.setContacts(contacts);

            orderAndContacts.add(orderAndContact);
        }

        return Result.Result(200,"",orderAndContacts);
    }

    //待评价
    @RequestMapping(value = "/confirm")
    public JSONObject confirm(int userid){

        List<Order> orders= orderRepository.findAllByUserIdAndOrderStatus(userid,2);

        List<OrderAndContact> orderAndContacts = new ArrayList<>();

        for (Order order: orders) {

            OrderAndContact orderAndContact = new OrderAndContact();

            orderAndContact.setOrder(order);

            String[]cs = order.getContacts().split(",");

            List<Contact> contacts = new ArrayList<>();

            for (String c:cs) {

                Contact contact = contactRepository.findOne(Integer.parseInt(c));

                contacts.add(contact);
            }
            orderAndContact.setContacts(contacts);

            orderAndContacts.add(orderAndContact);
        }
        return Result.Result(200,"",orderAndContacts);
    }

    @RequestMapping(value = "/waitcomment")
    public JSONObject waitcomment(int userid){

        List<Order> orders= orderRepository.findAllByUserIdAndOrderStatus(userid,3);
        List<OrderAndContact> orderAndContacts = new ArrayList<>();

        for (Order order: orders) {

            OrderAndContact orderAndContact = new OrderAndContact();

            orderAndContact.setOrder(order);

            String[]cs = order.getContacts().split(",");

            List<Contact> contacts = new ArrayList<>();

            for (String c:cs) {

                Contact contact = contactRepository.findOne(Integer.parseInt(c));

                contacts.add(contact);
            }
            orderAndContact.setContacts(contacts);

            orderAndContacts.add(orderAndContact);
        }
        return Result.Result(200,"",orderAndContacts);
    }

    @RequestMapping(value = "/finish")
    public JSONObject finish(int userid){

        List<Order> orders= orderRepository.findAllByUserIdAndOrderStatus(userid,4);
        List<OrderAndContact> orderAndContacts = new ArrayList<>();

        for (Order order: orders) {

            OrderAndContact orderAndContact = new OrderAndContact();

            orderAndContact.setOrder(order);

            String[]cs = order.getContacts().split(",");

            List<Contact> contacts = new ArrayList<>();

            for (String c:cs) {

                Contact contact = contactRepository.findOne(Integer.parseInt(c));

                contacts.add(contact);
            }
            orderAndContact.setContacts(contacts);

            orderAndContacts.add(orderAndContact);
        }
        return Result.Result(200,"",orderAndContacts);
    }
    //已经取消的订单
    @RequestMapping(value = "/cancel")
    public JSONObject cancel(int userid){

        List<Order> orders= orderRepository.findAllByUserIdAndOrderStatus(userid,5);
        List<OrderAndContact> orderAndContacts = new ArrayList<>();

        for (Order order: orders) {

            OrderAndContact orderAndContact = new OrderAndContact();

            orderAndContact.setOrder(order);

            String[]cs = order.getContacts().split(",");

            List<Contact> contacts = new ArrayList<>();

            for (String c:cs) {

                Contact contact = contactRepository.findOne(Integer.parseInt(c));

                contacts.add(contact);
            }
            orderAndContact.setContacts(contacts);

            orderAndContacts.add(orderAndContact);
        }
        return Result.Result(200,"",orderAndContacts);
    }

    @RequestMapping(value = "/cancelorder")
    public JSONObject cancelorder(int id){
        Order order  = orderRepository.getOne(id);

        if(order != null){

            order.setOrderStatus(5);

            Order order1 = orderRepository.save(order);
            if(order1 != null){
                return Result.Result(200,"","");
            }else{
                return Result.Result(405,"取消失败","");
            }

        }else{
            return Result.Result(404,"订单不存在","");
        }

    }

}
