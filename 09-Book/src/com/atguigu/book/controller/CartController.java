package com.atguigu.book.controller;

import com.atguigu.book.pojo.Book;
import com.atguigu.book.pojo.Cart;
import com.atguigu.book.pojo.CartItem;
import com.atguigu.book.pojo.User;
import com.atguigu.book.service.CartItemService;
import com.google.gson.Gson;

import javax.servlet.http.HttpSession;

public class CartController {

    private CartItemService cartItemService ;

    //加载当前用户的购物车信息
    public String index(HttpSession session){
        User user =(User)session.getAttribute("currUser");
        Cart cart = cartItemService.getCart(user);
        user.setCart(cart);
        session.setAttribute("currUser",user);
        return "cart/cart";
    }

    //加书到购物车中
    public String addCart(Integer bookId , HttpSession session){
        User user = (User)session.getAttribute("currUser");
        CartItem cartItem = new CartItem(new Book(bookId),1,user);
        //将指定的图书添加到当前用户的购物车中
        cartItemService.addOrUpdateCartItem(cartItem,user.getCart());

        //这里我们不要点击一本书就跳转到购物车，所以将这里的重定向页面改成index页面
        return "redirect:cart.do";
//        return "index";
    }

    //编辑购物车中的图书数量功能
    public String editCart(Integer cartItemId , Integer buyCount){
        cartItemService.updateCartItem(new CartItem(cartItemId , buyCount));
        return "redirect:cart.do";
//        return "";//json
    }

    //使用vue和axios加载当前用户的购物车信息
//    public String cartInfo(HttpSession session){
//        User user =(User)session.getAttribute("currUser");
//        Cart cart = cartItemService.getCart(user);
//
//        //调用Cart中的三个属性的get方法，目的是在此处计算这三个属性的值，否则这三个属性为null，
//        //导致的结果就是下一步的gson转化时，为null的属性会被忽略
//        cart.getTotalBookCount();
//        cart.getTotalCount();
//        cart.getTotalMoney();
//
//        //将java类转换成Json格式的
//        Gson gson = new Gson();
//        String cartJsonStr = gson.toJson(cart);
//        return "json:"+cartJsonStr ;
//    }
}
