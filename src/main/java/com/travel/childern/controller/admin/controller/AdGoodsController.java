package com.travel.childern.controller.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.travel.childern.model.Goods;
import com.travel.childern.model.Guide;
import com.travel.childern.repo.GoodsRepository;
import com.travel.childern.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/adgoodsservice")
public class AdGoodsController {

    @Autowired
    private GoodsRepository goodsRepository;

    @RequestMapping("/add")
    public JSONObject add(
            @RequestParam("banners") String banners,
            @RequestParam("name") String name,
            @RequestParam("guide_list") String guide_list,
            @RequestParam("time") String time,
            @RequestParam("line") int line_id,
            @RequestParam("begintime") String begin_time,
            @RequestParam("endtime") String end_time,
            @RequestParam("description") String description
    ){
        Goods goods = new Goods();

        goods.setBanners(banners);
        goods.setDescription(description);
        goods.setName(name);
        goods.setGuideList(guide_list);
        goods.setLineId(line_id);
        goods.setBeginTime(begin_time);
        goods.setEndTime(end_time);
        goods.setTime(time);

        Goods goods1 = goodsRepository.save(goods);

        if(goods1 != null){
            return Result.Result(200,"添加成功","");
        }else{
            return Result.Result(400,"添加失败","");
        }
    }

    @RequestMapping(value = "/update")
    public JSONObject edit(
            @RequestParam("id") int id,
            @RequestParam("banners") String banners,
            @RequestParam("name") String name,
            @RequestParam("guide_list") String guide_list,
            @RequestParam("line") int line_id,
            @RequestParam("time") String time,
            @RequestParam("begintime") String begin_time,
            @RequestParam("endtime") String end_time,
            @RequestParam("description") String description
    ){
        Goods goods = goodsRepository.findOne(id);
        if(goods != null){
            goods.setBanners(banners);
            goods.setDescription(description);
            goods.setName(name);
            goods.setGuideList(guide_list);
            goods.setLineId(line_id);
            goods.setBeginTime(begin_time);
            goods.setEndTime(end_time);
            goods.setTime(time);

            Goods goods1 = goodsRepository.save(goods);

            if(goods1 != null){
                return Result.Result(200,"修改成功","");
            }else{
                return Result.Result(400,"修改失败","");
            }
        }else{
            return Result.Result(401,"数据不存在","");
        }
    }

    @RequestMapping(value = "/del")
    public JSONObject del(@RequestParam("id") int id
    ) {
        //更改用户标志
        Goods goods =  goodsRepository.getOne(id);
        if(goods == null){
            return Result.Result(400,"商品不存在","");
        }else {
            goodsRepository.delete(id);
            return Result.Result(200,"删除成功","");
        }


    }


}
