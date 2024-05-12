package com.shashank.library.service.impl;


import com.shashank.library.domain.Book;
import com.shashank.library.domain.Review;
import com.shashank.library.repository.BookRepository;
import com.shashank.library.repository.ReviewRepository;
import com.shashank.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class BookServiceImpl implements BookService {


    //    @Autowired
    BookRepository bookRepository;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Autowired
    ReviewRepository reviewRepository;


    @Autowired
    BookValidation bookValidation;

    public void setBookValidation(BookValidation bookValidation) {
        this.bookValidation = bookValidation;
    }


    public void addBook(Book book) {

        if (!bookValidation.validateBook(book)) {

            throw new IllegalArgumentException("Book is not valid");
        }

        if (book.getTitle().contains("ABC")) {
            throw new IllegalStateException("Title cannot be ABC");
        }


        bookRepository.save(book);
    }


    // Manual ref for merging entities
//    public List<BookResponse> getAllBooks(){
//
//        List<Book> books=bookRepository.findAll();
//        List<BookResponse> bookResponses=new ArrayList<BookResponse>();
//        for(Book book:books){
//            List<Review> reviews=reviewRepository.findByBookId(book.getId());
//            bookResponses.add( BookResponse.builder().cost(book.getCost()).title(book.getTitle()).author(book.getAuthor()).rating(book.getRating()).reviewList(reviews).build());
//
//        }
//        // for each book, populate the reviews
//       return bookResponses;
//    }

    public List<Book> getAllBooks() {

        // iterate all books. add top 10 rated books in redis via redistemplate.

        List<Book> bookList = bookRepository.findAll();
        if (bookList.isEmpty()) {
            throw new IllegalStateException("books list cannot be empty");
        }

        return bookList;
    }


    public Book getBook(Integer Id) {
        //check redisTemplate.get("book"+Id). if found then return. else get from db

        return bookRepository.findById(Id).orElse(null);
    }

    public void deleteBook(Integer id) {
        bookRepository.deleteById(id);
    }

    public Book updateBook(Integer Id, Book book) {
        Optional<Book> originalBook = bookRepository.findById(Id);
        if (originalBook.isPresent()) {
            bookRepository.save(book);
        }
        return book;
    }

    @Override
    public void addReview(String bookId, Review review) {
//       Book book=bookMap.getOrDefault(bookId,null);
//
//       if(book!=null)
//       {
//         //  book.getReviewList().add(review);
//       }
//       bookMap.put(bookId,book);
    }

}