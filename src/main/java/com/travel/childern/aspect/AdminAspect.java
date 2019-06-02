package com.travel.childern.aspect;


import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Aspect
@Component
public class AdminAspect {

    private final static Logger logger = LoggerFactory.getLogger(AdminAspect.class);

    //传统模式

//    @Before("execution(public * com.travel.childern.controller.admin(..))")
//    public void log(){
//
//        System.out.println("执行了/");
//        logger.info("执行了");
//    }
//
//
//    @After("execution(public * com.example.demo.Controller.FirstController.index(..))")
//    public void log1(){
//        System.out.println("执行后/");
//
//    }

    //使用pointcut模式，减少代码重复
    @Pointcut("execution(public * com.travel.childern.controller.admin..*(..))")
    public void point1(){

    }

    //获取request示例代码
    @Before("point1()")
    public void doBefore1() throws Exception{

        //获取HttpRequest对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = attributes.getRequest();
        HttpServletResponse httpServletResponse = attributes.getResponse();
        //获取当前url
        logger.info("url={}",httpServletRequest.getRequestURL());
        //获取当前方法
        logger.info("method={}",httpServletRequest.getMethod());
        //获取请求的ip
        logger.info("ip={}",httpServletRequest.getRemoteAddr());
        //
        //进行拦截
        System.out.println("管理员端拦截");

        //检查用户session
        HttpSession session = httpServletRequest.getSession();

        Object admin = session.getAttribute("admin");
        Object adminName = session.getAttribute("adminName");
        if(admin == null || adminName == null){
            System.out.println("管理员没有登陆");


            httpServletResponse.sendRedirect("/adminlogin");
//            return ;
//            session.setAttribute("admin","123");
//            session.setAttribute("adminName","admin");
        }else{
            System.out.println("管理员已经登陆");

        }
    }

    @After("point1()")
    public void deAfter1(){

    }


    @Pointcut("execution(public * com.travel.childern.controller.guide..*(..))")
    public void point2(){

    }

    //获取request示例代码
    @Before("point2()")
    public void doBefore2()throws  Exception{

        //获取HttpRequest对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = attributes.getRequest();

        HttpServletResponse httpServletResponse = attributes.getResponse();

        //获取当前url
        logger.info("url={}",httpServletRequest.getRequestURL());
        //获取当前方法
        logger.info("method={}",httpServletRequest.getMethod());
        //获取请求的ip
        logger.info("ip={}",httpServletRequest.getRemoteAddr());
        //
        //检查用户session
        HttpSession session = httpServletRequest.getSession();
        //进行拦截
        System.out.println("导游端拦截");
        Object admin = session.getAttribute("guide");
        Object adminName = session.getAttribute("guideName");
        if(admin == null || adminName == null){
            System.out.println("管理员没有登陆");


            httpServletResponse.sendRedirect("/guidelogin/login");
//            return ;
//            session.setAttribute("admin","123");
//            session.setAttribute("adminName","admin");
        }else{
            System.out.println("管理员已经登陆");

        }
    }

    @After("point2()")
    public void deAfter2(){

    }
    //可以拦截返回信息，对返回信息进行修改
    @Pointcut("execution(public * com.travel.childern.controller.user..*(..))")
    public void point3(){

    }

    //获取request示例代码
    @Before("point3()")
    public void doBefore3(){

        //获取HttpRequest对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = attributes.getRequest();

        //获取当前url
        logger.info("url={}",httpServletRequest.getRequestURL());
        //获取当前方法
        logger.info("method={}",httpServletRequest.getMethod());
        //获取请求的ip
        logger.info("ip={}",httpServletRequest.getRemoteAddr());
        //
        //进行拦截
        System.out.println("客户端拦截");
    }

    @After("point3()")
    public void deAfter3(){

    }
}
