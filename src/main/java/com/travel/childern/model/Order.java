package com.travel.childern.model;


import javax.persistence.*;

@Entity
@Table(name = "userorder")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "order_sn")
    private String orderSn;


    @Column(name = "time")
    private String time;
    /**
     * 1.待确认
     * 2.已确认
     * 3.待评价
     * 4.已评价
     * 5.已取消
     * */

    @Column(name = "order_status")
    private int orderStatus;

    @Column(name = "line_id")
    private int lineId;

    @Column(name = "goods_id")
    private int goodsId;

    @Column(name = "goods_name")
    private String goodsName;

    @Column(name = "contacts")
    private String contacts;

    @Column(name = "is_comment")
    private Integer isCommnet;

    @Column(name = "go_time")
    private String goTime;

    //出行人数
    @Column(name = "peo_num")
    private int peoNum;

    //联系人姓名
    @Column(name = "contact_name")
    private String contactName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getLineId() {
        return lineId;
    }

    public void setLineId(int lineId) {
        this.lineId = lineId;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public Integer getIsCommnet() {
        return isCommnet;
    }

    public void setIsCommnet(Integer isCommnet) {
        this.isCommnet = isCommnet;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoTime() {
        return goTime;
    }

    public void setGoTime(String goTime) {
        this.goTime = goTime;
    }

    public int getPeoNum() {
        return peoNum;
    }

    public void setPeoNum(int peoNum) {
        this.peoNum = peoNum;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
}
