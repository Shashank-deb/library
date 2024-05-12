package com.shashank.library.repository;

import com.shashank.library.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String username);
}