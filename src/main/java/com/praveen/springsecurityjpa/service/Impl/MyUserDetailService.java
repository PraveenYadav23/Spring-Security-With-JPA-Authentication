package com.praveen.springsecurityjpa.service.Impl;

import com.praveen.springsecurityjpa.entity.MyUserDetails;
import com.praveen.springsecurityjpa.entity.UserEntity;
import com.praveen.springsecurityjpa.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        logger.info("\n\n\n SECURITY STEP 3 :::: from loadUserByUsername method in ---> " + getClass().getName() + "\n\n");
        Optional<UserEntity> user =   userRepository.findByUserName(userName);

        user.orElseThrow(
                () -> new UsernameNotFoundException("Not Found: " + userName)
        );

        return user.map(MyUserDetails::new).get();
    }
}
