package com.atguigu.qqzone.controller;

import com.atguigu.qqzone.pojo.HostReply;
import com.atguigu.qqzone.pojo.Reply;
import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;
import com.atguigu.qqzone.service.HostReplyService;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

public class HostReplyController {

    private HostReplyService hostReplyService;

    //分析它所需要的参数：id自增、context是输入的,date是现在时间，author和reply可以从session作用域中获取
    public String addHostReply(String content, Integer replyId, HttpSession session) {
        //先从作用域中获取当前作者
        UserBasic userBasic = (UserBasic) session.getAttribute("userBasic");
        //根据除自增列的四个参数创建一个hostReply对象
        HostReply hostReply = new HostReply(content, LocalDateTime.now(), userBasic, new Reply(replyId));
        //调用服务层实现添加功能
        hostReplyService.addHostReply(hostReply);
        //重定向之后返回日志详情页面
        Topic topic = (Topic) session.getAttribute("topic");
        return "redirect:topic.do?operate=topicDetail&id=" + topic.getId();
    }
}
