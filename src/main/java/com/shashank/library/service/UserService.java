package com.shashank.library.service;

import com.shashank.library.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;



public interface UserService extends UserDetailsService {

    public void addUser(User user);
}