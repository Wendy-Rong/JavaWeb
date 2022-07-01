package com.atguigu.book.service;

import com.atguigu.book.pojo.Cart;
import com.atguigu.book.pojo.CartItem;
import com.atguigu.book.pojo.User;

import java.util.List;

public interface CartItemService {
    //如果当前购物车没有这本书，添加这本书的购物车项
    void addCartItem(CartItem cartItem);
    //如果当前购物车有这本书，更新这本书的数量
    void updateCartItem(CartItem cartItem);
    //判断当前用户的购物车中有没有这本书，有则+1，无则add
    void addOrUpdateCartItem(CartItem cartItem , Cart cart);

    //获取指定用户的所有购物车项列表（需要注意的是，这个方法内部查询的时候，会将book的详细信息设置进去）
    List<CartItem> getCartItemList(User user);

    //加载特定用户的购物车信息
    Cart getCart(User user);
}
