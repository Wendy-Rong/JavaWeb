package com.atguigu.book.service;

import com.atguigu.book.pojo.User;

public interface UserService {
    //用户登陆验证
    User login(String uname , String pwd );
    //用户注册，暂时没有考虑用户名和邮箱已经注册的情况
    void regist(User user);
    //获取用户名，验证用户名是否已经被注册
    User getUser(String uname);
}
