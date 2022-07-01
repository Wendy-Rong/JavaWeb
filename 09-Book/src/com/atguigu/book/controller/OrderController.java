package com.atguigu.book.controller;

import com.atguigu.book.pojo.OrderBean;
import com.atguigu.book.pojo.User;
import com.atguigu.book.service.OrderService;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class OrderController {

    private OrderService orderService ;

    //结账功能，就是生成一个订单，将所有的数据从session中获取或者新建之后set上去
    public String checkout(HttpSession session){
        User user =(User)session.getAttribute("currUser");
        if (user.getCart().getTotalBookCount() == 0){
            return "index";
        }

        OrderBean orderBean = new OrderBean() ;

        LocalDateTime localDateTime = LocalDateTime.now();
        int year = localDateTime.getYear();
        int month = localDateTime.getMonth().getValue();
        int day = localDateTime.getDayOfMonth();
        int hour = localDateTime.getHour();
        int min = localDateTime.getMinute();
        int sec = localDateTime.getSecond();
        //订单编号是32位全球唯一随机数+年月日时分秒
        String orderNo = UUID.randomUUID().toString()+"_"+year+month+day+hour+min+sec;
        orderBean.setOrderNo(orderNo);
        session.setAttribute("OrderNo",orderNo);

        orderBean.setOrderDate(localDateTime);


        orderBean.setOrderUser(user);
        orderBean.setOrderMoney(user.getCart().getTotalMoney());
        orderBean.setOrderStatus(0);

        orderService.addOrderBean(orderBean);

        return "cart/checkout" ;
    }

    //查看订单列表
    public String getOrderList(HttpSession session){
        User user =(User)session.getAttribute("currUser");

        List<OrderBean> orderList = orderService.getOrderList(user);
        user.setOrderList(orderList);

        session.setAttribute("currUser",user);

        return "order/order" ;
    }
}
