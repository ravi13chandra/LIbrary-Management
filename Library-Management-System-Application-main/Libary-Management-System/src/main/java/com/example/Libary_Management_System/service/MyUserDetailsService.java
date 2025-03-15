package com.example.Libary_Management_System.service;

import com.example.Libary_Management_System.enity.MyUserDetails;
import com.example.Libary_Management_System.enity.Users;
import com.example.Libary_Management_System.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(username);

        if(user == null) {
            throw new UsernameNotFoundException("Username not found.. enter valid");
        }

        MyUserDetails myUserDetails = new MyUserDetails(user);
        return myUserDetails;
    }
}
