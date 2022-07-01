package com.atguigu.book.dao;

import com.atguigu.book.pojo.Book;

import java.util.List;

public interface BookDAO {
    //获取所有有效的图书列表，这里暂时先不考虑按照价格范围查询，并且分页的功能
    List<Book> getBookList();
    Book getBook(Integer id);
}
