package com.travel.childern.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 此类是路线和导游结合的商品类
 *
 *
 */


@Entity
@Table(name = "goods")
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "banners",columnDefinition="text")
    private String banners;

    @Column(name = "description",columnDefinition="longtext")
    private String description;

    @Column(name = "guide_list")
    private String guideList;

    @Column(name = "line_id")
    private int lineId;

    @Column(name = "begin_time")
    private String beginTime;

    @Column(name = "end_time")
    private String endTime;

    //当前路线人数
    @Column(name = "number")
    private int number;

    //订单指定的时间
    @Column(name = "time")
    private String time;

    //出行时间


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBanners() {
        return banners;
    }

    public void setBanners(String banners) {
        this.banners = banners;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGuideList() {
        return guideList;
    }

    public void setGuideList(String guideList) {
        this.guideList = guideList;
    }

    public int getLineId() {
        return lineId;
    }

    public void setLineId(int lineId) {
        this.lineId = lineId;
    }


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }



    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", banners='" + banners + '\'' +
                ", description='" + description + '\'' +
                ", guideList='" + guideList + '\'' +
                ", lineId=" + lineId +
                ", beginTime='" + beginTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", number=" + number +
                ", time='" + time + '\'' +
                '}';
    }
}
