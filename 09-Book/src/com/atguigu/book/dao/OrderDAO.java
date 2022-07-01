package com.atguigu.book.dao;

import com.atguigu.book.pojo.OrderBean;
import com.atguigu.book.pojo.User;

import java.util.List;

public interface OrderDAO {
    //添加订单
    void addOrderBean(OrderBean orderBean);
    //获取指定用户的订单列表
    List<OrderBean> getOrderList(User user);
    //获取订单数量，这个数量等于所有的该用户订单项的图书数量的总和
    Integer getOrderTotalBookCount(OrderBean orderBean);
}
