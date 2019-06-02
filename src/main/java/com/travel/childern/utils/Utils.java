package com.travel.childern.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {


    public static String StringToArrayType(String str,String var){

        if(str == null || str.equals("")){
            return var;
        }else{
            return str+","+var;
        }
    }

    public static String getNowDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
//        ParsePosition pos = new ParsePosition(8);
//        Date currentTime_2 = formatter.parse(dateString, pos);
        return dateString;
    }
}
