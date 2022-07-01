package com.atguigu.book.pojo;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

public class Cart {
    private Map<Integer, CartItem> cartItemMap;      //购物车中购物车项的集合 , 这个Map集合中的key是Book的id
    private Double totalMoney;                     //购物车的总金额
    private Integer totalCount;                    //购物车中购物项的数量，总共有多少项，而不是物品总件数
    private Integer totalBookCount;                //购物车中书本的总数量，而不是购物车项的总数量

    public Cart() {
    }

    public Map<Integer, CartItem> getCartItemMap() {
        return cartItemMap;
    }

    public void setCartItemMap(Map<Integer, CartItem> cartItemMap) {
        this.cartItemMap = cartItemMap;
    }

//    public Double getTotalMoney() {
//        totalMoney = 0.0;
//        //如果购物车中有东西，将购物车每一项从map中取出来，根据cartItem确定数的价格和购买的数量
//        if (cartItemMap != null && cartItemMap.size() > 0) {
//            Set<Map.Entry<Integer, CartItem>> entries = cartItemMap.entrySet();//取出购物车中的所有键值对
//            for (Map.Entry<Integer, CartItem> cartItemEntry : entries) {
//                CartItem cartItem = cartItemEntry.getValue();//每一个购物车项
//                totalMoney = totalMoney + cartItem.getBook().getPrice() * cartItem.getBuyCount();
//            }
//        }
//        return totalMoney;
//    }
    public Double getTotalMoney() {
        totalMoney = 0.0;
        BigDecimal bigDecimalTotalMoney = new BigDecimal("" + totalMoney);
        //如果购物车中有东西，将购物车每一项从map中取出来，根据cartItem确定数的价格和购买的数量
        if (cartItemMap != null && cartItemMap.size() > 0) {
            Set<Map.Entry<Integer, CartItem>> entries = cartItemMap.entrySet();//取出购物车中的所有键值对
            for (Map.Entry<Integer, CartItem> cartItemEntry : entries) {
                CartItem cartItem = cartItemEntry.getValue();//每一个购物车项
                Double xj = cartItem.getXj();
                bigDecimalTotalMoney = bigDecimalTotalMoney.add(new BigDecimal("" + xj));
            }
        }
        return bigDecimalTotalMoney.doubleValue();
    }

    /**
     * @return 总共的购物车项，是有几类图书，不是有几本图书
     */
    public Integer getTotalCount() {
        totalCount = 0;
        if (cartItemMap != null && cartItemMap.size() > 0) {
            totalCount = cartItemMap.size();
        }
        return totalCount;
    }

    /**
     * @return 购物车中每一本书的总数量
     */
    public Integer getTotalBookCount() {
        totalBookCount = 0;
        if (cartItemMap != null && cartItemMap.size() > 0) {
            for (CartItem cartItem : cartItemMap.values()) {
                totalBookCount = totalBookCount + cartItem.getBuyCount();//每一购物车项它的数量
            }
        }
        return totalBookCount;
    }
}
