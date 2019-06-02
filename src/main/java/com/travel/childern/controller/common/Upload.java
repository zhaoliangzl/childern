package com.travel.childern.controller.common;


import com.alibaba.fastjson.JSONObject;
import com.travel.childern.model.Settings;
import com.travel.childern.repo.SettingRepository;
import com.travel.childern.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping(value = "/common")
public class Upload {

    @Autowired
    private SettingRepository settingRepository;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public JSONObject imgForTiku(@RequestParam("myfile") MultipartFile file, HttpServletRequest request){


        Settings setting = settingRepository.findOne(1);


        Calendar c = Calendar.getInstance();
        long time = c.getTimeInMillis();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        if (!file.isEmpty()) {
            try {
                // 这里只是简单例子，文件直接输出到项目路径下。
                // 实际项目中，文件需要输出到指定位置，需要在增加代码处理。
                // 还有关于文件格式限制、文件大小限制，详见：中配置。


                //新增判断文件类型

                String type= file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);

                System.out.println(type);

                if(!(type.equals("jpg") || type.equals("png") || type.equals("GIF") || type.equals("gif") || type.equals("JPG") || type.equals("JPEG") || type.equals("PNG"))){

                    return Result.Result(403,"请上传jpg、png、gif类型得文件","");

                }

                File temp =new File(setting.getImgUploadDir()+"/"+year+"/"+month);

                if (!temp.exists()) temp.mkdirs();

                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File(setting.getImgUploadDir()+"/"+year+"/"+month+"/"+time+"."+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1))));
                out.write(file.getBytes());
                out.flush();
                out.close();


            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return Result.Result(402,"上传失败"+e.getMessage(),"");
            } catch (IOException e) {
                e.printStackTrace();
                return Result.Result(401,"上传失败"+e.getMessage(),"");
            }

            return Result.Result(200,"上传成功",setting.getImgUrl()+"/"+year+"/"+month+"/"+time+"."+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1));

        } else {
            return Result.Result(400,"上传失败,因为文件是空的","");
        }

    }


}
