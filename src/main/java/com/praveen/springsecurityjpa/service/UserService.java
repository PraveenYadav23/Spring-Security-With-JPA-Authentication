package com.praveen.springsecurityjpa.service;

import com.praveen.springsecurityjpa.response.LoginResponse;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService {

    public LoginResponse getUserDetails(String email);
}
