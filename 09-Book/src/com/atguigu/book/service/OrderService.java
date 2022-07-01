package com.atguigu.book.service;

import com.atguigu.book.pojo.OrderBean;
import com.atguigu.book.pojo.User;

import java.util.List;

public interface OrderService {
    //增加订单项，这里只传一个参数，未来还需要将user设置进来
    void addOrderBean(OrderBean orderBean);
    //获取某用户关联的订单信息
    List<OrderBean> getOrderList(User user);
}
