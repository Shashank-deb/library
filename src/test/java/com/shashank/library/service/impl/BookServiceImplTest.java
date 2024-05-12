package com.shashank.library.service.impl;

import com.shashank.library.domain.Book;
import com.shashank.library.repository.BookRepository;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BookServiceImplTest {


    private static final Logger log = LoggerFactory.getLogger(BookServiceImplTest.class);
    List<Book> bookList = new ArrayList<>();

    @BeforeAll
    public static void atTheFirst() {
        System.out.println("Starting test");
    }


    @BeforeEach
    public void setup() {
        System.out.println("in the start");
        Book book = new Book();
        bookList.add(book);
    }


    @AfterAll
    public static void atTheLast() {
        System.out.println("all testcases ran successfully");
    }

    @AfterEach
    public void atTheLastOfEveryTestCase() {
        System.out.println("after each test case");
    }


    @Test
    void getAllBooks() {
        BookServiceImpl bookService = new BookServiceImpl();

        //mocking
        BookRepository bookRepository = mock(BookRepository.class);
        bookService.setBookRepository(bookRepository);


        //stubbing

        when(bookRepository.findAll()).thenReturn(bookList);
        assertEquals(bookList.size(), 1);
        bookService.getAllBooks();
    }


    @Test()
    public void testGetAllBooksEmptyTitle() {
        BookServiceImpl bookService = new BookServiceImpl();

        //mocking
        BookRepository bookRepository = mock(BookRepository.class);
        bookService.setBookRepository(bookRepository);


        //stubbing
        List<Book> bookList = new ArrayList<>();

        when(bookRepository.findAll()).thenReturn(bookList);

        assertThrows(IllegalStateException.class, () -> bookService.getAllBooks());


    }

    @Test()
    public void testGetAllBooksEmptyTitle2() {
        BookServiceImpl bookService = new BookServiceImpl();

        //mocking
        BookRepository bookRepository = mock(BookRepository.class);
        bookService.setBookRepository(bookRepository);


        //stubbing
        List<Book> bookList = new ArrayList<>();

        when(bookRepository.findAll()).thenReturn(bookList);

        assertThrows(IllegalStateException.class, () -> bookService.getAllBooks());


    }

    @Test
    public void testAddBookInvalidBook() {
        BookServiceImpl bookService = new BookServiceImpl();
        BookValidation bookValidation = mock(BookValidation.class);


        Book book = new Book();
        bookService.setBookValidation(bookValidation);
        when(bookValidation.validateBook(any(Book.class))).thenReturn(false);
//        bookService.addBook(book);
        assertThrows(IllegalArgumentException.class, () -> bookService.addBook(book));
    }



    @Test
    public void testAddBookInvalidBookWithSpy() {
        BookServiceImpl bookService = spy(new BookServiceImpl());
        BookValidation bookValidation = spy(new BookValidation());
        ValidationService validationService = new ValidationService();
        bookValidation.setValidationService(validationService);



        Book book = new Book();
        bookService.setBookValidation(bookValidation);
        doReturn(false).when(bookValidation).validateBook(any(Book.class));
//        when(bookValidation.validateBook(book)).thenReturn(false);
//        bookService.addBook(book);
        assertThrows(IllegalArgumentException.class, () -> bookService.addBook(book));
    }


    @Test
    public void testAddBookWithABCTitle() {
        BookServiceImpl bookService = new BookServiceImpl();
        BookValidation bookValidation = mock(BookValidation.class);


        Book book = new Book();
        book.setTitle("ABC");
        bookService.setBookValidation(bookValidation);
        when(bookValidation.validateBook(any(Book.class))).thenReturn(true);
//        bookService.addBook(book);
        assertThrows(IllegalStateException.class, () -> bookService.addBook(book));
    }


    @Test
    public void testAddBook() {
        BookServiceImpl bookService = new BookServiceImpl();
        BookValidation bookValidation = mock(BookValidation.class);


        Book book = new Book();
        book.setTitle("PQR");
//        spy(book);
        bookService.setBookValidation(bookValidation);
        when(bookValidation.validateBook(any(Book.class))).thenReturn(true);
//        when(any(Book.class).getTitle()).thenReturn("ABC");
        BookRepository bookRepository = mock(BookRepository.class);
        bookService.setBookRepository(bookRepository);
        bookService.addBook(book);
    }


    @Test
    public void testTemp(){
           List<String> list=new ArrayList<>();
           List<String> spyList=spy(list);


           assertEquals(0,spyList.size());
           doReturn(100).when(spyList).size();
           assertEquals(100,spyList.size());

    }


}

/***
 * Unit Testing->Testing of the code blocks a developer write.It covers all the scenarios of the method
 *       Developer should be writing this cases
 *
 *       Code - coverage to the build is determined by the number of lines covered by the test case
 *             - 100% code coverage is Ideal
 *
 *
 *      Mockito->Library helps in mocking the objects or beans .
 *
 *       1. Mock->we mock the object here in the test case
 *         -ex  : for a BookService , we need the mock of bookRepository so that the output of the bookRespository can be controlled
 *                the dependent bean is also handled in the mock
 *
 *
 *       2.Stub->we mock the behaviour of an object when the methods are called .
 *       -ex: for bookService the mocked bookRepository  , get a custom output of specific method
 *
 *
 *       3.Spy->To help stubbing when the object is not mocked.
 *       -ex:  for non-mocked objects, we can spy for them to call a method which are stubbed  and give stubbed output with them
 *
 *       Asserts
 *       ->  if a =5
 *
 *       assert(5,a)->pass
 *       assert(10,a)->fails
 *
 *       for JUNIT<5   , the exceptions are tested with the expected keyword in the annotation  with the value as type of the Exception
 *       for JUNIT5 > it has to be tested along with asserts itself.
 *
 *
 * Functional Testing->Testing of the functionality where the entier operation of the entity or resources are tested on the real scenarios
 *
 *
 */