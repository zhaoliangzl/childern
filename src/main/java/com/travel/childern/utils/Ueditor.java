package com.travel.childern.utils;

import com.travel.childern.model.Settings;
import com.travel.childern.repo.SettingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;


@Controller
public class Ueditor {


    @Autowired
    private SettingRepository settingRepository;

    @RequestMapping(value = "/ueditor")
    @ResponseBody
    public String ueditor(HttpServletRequest request, HttpServletResponse response) {

        String s = "{\n"+
                "            \"imageActionName\": \"uploadimage\",\n" +
                "                \"imageFieldName\": \"file\", \n" +
                "                \"imageMaxSize\": 2048000, \n" +
                "                \"imageAllowFiles\": [\".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"], \n" +
                "                \"imageCompressEnable\": true, \n" +
                "                \"imageCompressBorder\": 1600, \n" +
                "                \"imageInsertAlign\": \"none\", \n" +
                "                \"imageUrlPrefix\": \""+settingRepository.findOne(1).getHost()+"\",\n" +
                "                \"imagePathFormat\": \"/upload/img/{time}\" }";
        return s;

    }


    @RequestMapping(value = "/imgUpdate")
    @ResponseBody
    public String imgUpdate(MultipartFile file, HttpServletResponse response) {

        if (file.isEmpty()) {
            return "error";
        }

        response.setContentType("text/html; charset=UTF-8");

        //获取系统的图片上传路径
        Settings settings = settingRepository.findOne(1);

        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 这里我使用随机字符串来重新命名图片
        fileName = Calendar.getInstance().getTimeInMillis() + suffixName;
        // 这里的路径为Nginx的代理路径，这里是/data/images/xxx.png

        Calendar c = Calendar.getInstance();
        long time = c.getTimeInMillis();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);

        File temp =new File(settings.getImgUploadDir()+"/"+year+"/"+month);

        if (!temp.exists()) temp.mkdirs();

        File dest = new File(settings.getImgUploadDir()+"/"+year+"/"+month+"/"+ fileName);

//        System.out.println(systemSetting.getImgDir() +"/"+ fileName);

        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            //url的值为图片的实际访问地址 这里我用了Nginx代理，访问的路径是http://localhost/xxx.png
            String config = "{\"state\": \"SUCCESS\"," +
                    "\"url\": \"/upload/"+year+"/"+month+"/"+ fileName + "\"," +
                    "\"title\": \"" + fileName + "\"," +
                    "\"original\": \"" + fileName + "\"}";

            return config;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }

}
