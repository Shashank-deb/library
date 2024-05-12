package com.shashank.library.repository;

import com.shashank.library.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Integer> {

    List<Review> findByBookId(Integer bookId);
}