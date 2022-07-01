package com.atguigu.book.controller;


import com.atguigu.book.pojo.Cart;
import com.atguigu.book.pojo.User;
import com.atguigu.book.service.CartItemService;
import com.atguigu.book.service.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class UserController {

    private UserService userService ;
    private CartItemService cartItemService ;

    //用户登陆验证
    public String login(String uname , String pwd , HttpSession session){

        User user = userService.login(uname, pwd);
        if(user!=null){
            Cart cart = cartItemService.getCart(user);
            user.setCart(cart);
            session.setAttribute("currUser",user);
            return "redirect:book.do";
        }
        //如果没有登陆成功，重新回到login登录页面
        return "user/login";
    }

    //用户注册
    public String regist(String verifyCode , String uname , String pwd , String email , HttpSession session , HttpServletResponse response) throws IOException {
        Object kaptchaCodeObj = session.getAttribute("KAPTCHA_SESSION_KEY");
        if(kaptchaCodeObj==null || !verifyCode.equals(kaptchaCodeObj)){
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            //out.println("<script language='javascript'>alert('验证码不正确！');window.location.href='page.do?operate=page&page=user/regist';</script>");
            out.println("<script language='javascript'>alert('验证码不正确！');</script>");
            //return "user/regist";
            return "user/regist";
        }else{
            if(verifyCode.equals(kaptchaCodeObj)){
                userService.regist(new User(uname , pwd , email));
                return "user/login";
            }
        }
        return "user/login";
    }

    //检验用户名是否已经被注册
    public String ckUname(String uname){
        User user = userService.getUser(uname);
        if(user!=null){
            //用户名已经被占用，不可以注册
            return "json:{'uname':'1'}";
            //return "ajax:1";
        }else{
            //用户名可以注册
            return "json:{'uname':'0'}";
            //return "ajax:0";
        }
    }
}
