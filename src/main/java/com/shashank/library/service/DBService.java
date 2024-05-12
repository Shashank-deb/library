package com.shashank.library.service;

import com.shashank.library.domain.Book;

import java.util.List;

public interface DBService {
    public Book addBook(Book book);

    public List<Book> getAllBook();
}
