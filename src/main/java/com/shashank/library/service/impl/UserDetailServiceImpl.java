package com.shashank.library.service.impl;


import com.shashank.library.domain.User;
import com.shashank.library.exception.UserAlreadyExistsException;
import com.shashank.library.repository.UserRepository;
import com.shashank.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;


@Service
public class UserDetailServiceImpl implements  UserService {


    @Autowired
    UserRepository  userRepository;


    @Autowired
    PasswordEncoder passwordEncoder;


    public void setUserRepository(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    /**
     * Create the userRepository
     * Autowire here
     * fetch user data from the repository.
     * */


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser=userRepository.findByUsername(username);
        if(optionalUser.isPresent()){
            return optionalUser.get();
        }
        else{
            throw  new UsernameNotFoundException("User not found");
        }

    }


    @Override
    public void addUser(User user) {
        Optional<User> optionalUser=userRepository.findByUsername(user.getUsername());
        if(optionalUser.isEmpty()){
            user.setAuthority("USER");
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }else {
            throw new UserAlreadyExistsException("User already exists");
        }
    }
}