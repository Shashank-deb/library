package com.shashank.library.service.impl;

import com.shashank.library.domain.Book;
import com.shashank.library.domain.Review;
import com.shashank.library.repository.BookRepository;
import com.shashank.library.repository.ReviewRepository;
import com.shashank.library.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {


    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    BookRepository bookRepository;


    @Override
    public void addReview(Review review) {

        Optional<Book> bookOptional=bookRepository.findById(review.getBook().getId());
        if(bookOptional.isEmpty()){
            throw new IllegalArgumentException("Book Id does not exist");
        }
        review.setBook(bookOptional.get());
        reviewRepository.save(review); //reviewRepository
    }


    /***
     *
     * ///Homework
     *
     *
     * when a review is added to a book,
     *
     * call a book service or update a book to change the rating.
     *
     * you book rating is average of all the review rating.
     *
     *
     *
     * */
}