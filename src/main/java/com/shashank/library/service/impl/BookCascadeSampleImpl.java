package com.shashank.library.service.impl;


import com.shashank.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookCascadeSampleImpl {

    @Autowired
    BookRepository bookRepository;

    public void testCascade(Integer id){

        bookRepository.deleteById(id);


    }
}