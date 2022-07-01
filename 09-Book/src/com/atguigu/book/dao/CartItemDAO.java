package com.atguigu.book.dao;

import com.atguigu.book.pojo.CartItem;
import com.atguigu.book.pojo.User;

import java.util.List;

public interface CartItemDAO {
    //新增购物车项，如果将购物车中没有的图书添加进来
    void addCartItem(CartItem cartItem);
    //修改特定的购物车项，如果新增的图书购物车中存在
    void updateCartItem(CartItem cartItem);
    //获取特定用户的所有购物车项
    List<CartItem> getCartItemList(User user);
    //删除特定的购物车项
    void delCartItem(CartItem cartItem);
}
