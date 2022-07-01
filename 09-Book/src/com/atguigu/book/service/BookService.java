package com.atguigu.book.service;

import com.atguigu.book.pojo.Book;

import java.util.List;

public interface BookService {
    //获取所有图书列表
    List<Book> getBookList();
    //根据Id获取每一本书
    Book getBook(Integer id);
}
