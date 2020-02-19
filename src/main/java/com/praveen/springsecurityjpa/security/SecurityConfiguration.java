package com.praveen.springsecurityjpa.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    private final Logger logger = LogManager.getLogger(this.getClass());
    // Authentication Setup
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        logger.info("\n\n\n SECURITY STEP  1:::: from Authenticate Config method in ---> " + getClass().getName() + "\n\n");
        auth.userDetailsService(userDetailsService);
    }

    // Authorization Setup
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.info("\n\n\n SECURITY STEP 2 :::: from Authorize Config method in ---> " + getClass().getName() + "\n\n");
        http.authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasAnyRole("ADMIN","USER")
                .antMatchers("/").permitAll()
                .and().formLogin();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
