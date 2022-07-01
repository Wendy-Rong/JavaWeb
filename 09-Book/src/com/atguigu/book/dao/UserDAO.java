package com.atguigu.book.dao;

import com.atguigu.book.pojo.User;

public interface UserDAO {
    //用户登录，根据用户名和密码是否能匹配到
    User getUser(String uname , String pwd );
    //注册用户
    void addUser(User user);
    //验证用户名是否已经注册
    User getUser(String uname);
}
