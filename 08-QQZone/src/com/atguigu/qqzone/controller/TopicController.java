package com.atguigu.qqzone.controller;

import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;
import com.atguigu.qqzone.service.TopicService;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TopicController {

    private TopicService topicService ;

    public String addTopic(String title,String content,HttpSession session){
        UserBasic userBasic = (UserBasic)session.getAttribute("userBasic");
        Topic topic = new Topic(title, content, LocalDateTime.now(), new UserBasic(userBasic.getId()));
        topicService.addTopic(topic);
        return "redirect:topic.do?operate=getTopicList";
    }

    public String topicDetail(Integer id , HttpSession session){
        Topic topic = topicService.getTopicById(id);

        session.setAttribute("topic",topic);
        return "frames/detail";
    }

    public String delTopic(Integer topicId){
        topicService.delTopic(topicId);
        return "redirect:topic.do?operate=getTopicList" ;
    }

    //当添加或者删除日志之后，重新获取用户和日志的关联之后再渲染
    public String getTopicList(HttpSession session){
        //从session中获取当前用户信息
        UserBasic userBasic = (UserBasic)session.getAttribute("userBasic");
        //再次查询当前用户关联的所有的日志
        List<Topic> topicList = topicService.getTopicList(userBasic);
        //设置一下关联的日志列表(因为之前session中关联的friend的topicList和此刻数据库中不一致）
        userBasic.setTopicList(topicList);
        //重新覆盖一下friend中的信息(为什么不覆盖userbasic中？因为main.html页面迭代的是friend这个key中的数据)
        session.setAttribute("friend",userBasic);
        return "frames/main";
    }
}
