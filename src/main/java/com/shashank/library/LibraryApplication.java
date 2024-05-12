package com.shashank.library;

import com.shashank.library.domain.Book;
import com.shashank.library.domain.Genre;
import com.shashank.library.domain.Review;
import com.shashank.library.repository.BookRepository;
import com.shashank.library.service.impl.BookCascadeSampleImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

//import java.util.ArrayList;
//import java.util.List;


@SpringBootApplication
public class LibraryApplication implements CommandLineRunner {




	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}


	@Autowired
	ApplicationContext context;

	@Autowired
	BookRepository bookRepository;

//	@Autowired
//	BookCascadeSampleImpl bookCascadeSample;
//
//
//	@Autowired
//	RedisTemplate<String,Object> redisTemplate;


	@Override
	public void run(String... args) throws Exception {
//		bookCascadeSample.testCascade(3);
//		Book book = new Book();
////		book.setTitle("Harry potter 1");
//		book.setTitle("testCascade");
//		book.setAuthor("JK Rowling");
//		book.setGenre(Genre.FANTASY);
//		book.setRating(5.0);
//		book.setCost(500.0);
//		book.setYear(2000);
//
//		List<Review> reviews = new ArrayList<>();
//		reviews.add(new Review());
//
//
//		redisTemplate.opsForValue().set("myJavaKey","myJavaValue");
//		System.out.println(redisTemplate.opsForValue().get("myJavaKey"));
//
//		redisTemplate.opsForList().rightPush("list",1);
//		redisTemplate.opsForList().rightPush("list",2);
//		redisTemplate.opsForList().rightPush("list",3);
//		redisTemplate.opsForList().rightPush("list",4);
//
//		System.out.println(redisTemplate.opsForList().leftPop("list"));
//		System.out.println(redisTemplate.opsForList().rightPop("list"));
//		redisTemplate.opsForHash().put("book",book.getTitle(),book);


//		bookRepository.save(book);
//
//		List<Book> books = bookRepository.findAll();

//		books=bookRepository.findByAuthor("JK Rowling");

//		books = bookRepository.findByTitleLike("testCascade");
//
//		books = bookRepository.findByTitleLike("Harry%");

//		if (!CollectionUtils.isEmpty(books)) {
//			Book b = books.get(0);
//			bookCascadeSample.testCascade(b.getId());
//
//
//			for (Book book1 : books) {
//				System.out.println(book1);
//			}
//
//
//			//		for(Book book1:books){
////			System.out.println(book1.getReviewList());
////			System.out.println(book1);
////		}
//
//		}



	}

	/***
	 *
	 * Steps for hibernate:
	 * 1. add the spring data jpa maven dependency.
	 * 2. add the configurations of url,username,password in application.properties.
	 * 3. Annotate you domain with @Table and @Entity annotation.
	 * 4. Add an iD to the domain with desired ID generation type.
	 * 5. Create repository for each domain and let it extend the JPARepository.
	 * 6. write your custom queries to the interface.
	 * 7. Start using the repository for DB related task in the service layer.
	 *
	 *
	 *
	 * */





	/***
	 * Assignments
	 *
	 * update all the controllers to accept resource
	 * check all the functionality
	 * create some custom methods in repository
	 * implement the users functionality
	 * create a user
	 * create a domain for user
	 * CRUD for user.
	 * Proper exception handling
	 *
	 *
	 * */
}
