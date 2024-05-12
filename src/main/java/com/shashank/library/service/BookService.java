package com.shashank.library.service;

import com.shashank.library.domain.Book;
import com.shashank.library.domain.Review;


import java.util.List;
import java.util.Set;

public interface BookService {

    public void addBook(Book book);
    public List<Book> getAllBooks();
    public Book getBook(Integer Id);

    public void deleteBook(Integer Id);
    public Book updateBook(Integer Id, Book book);

    public void addReview(String bookId, Review review);

}