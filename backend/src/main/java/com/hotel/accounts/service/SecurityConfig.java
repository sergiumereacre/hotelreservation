package com.hotel.accounts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final UserDetailsServiceImpl userDetailsService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(UserDetailsServiceImpl userDetailsService, BCryptPasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user")
                .password(passwordEncoder.encode("password"))
                .roles("GUEST")
                .and()
                .withUser("admin")
                .password(passwordEncoder.encode("adminpassword"))
                .roles("Admin");
    }
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//                .antMatchers("/login", "/register", "/").permitAll()
//                .antMatchers("/StaffManagement").permitAll() // <-- Added this line
//                .antMatchers("/staff/**").hasRole("Admin")
//                .anyRequest().permitAll()
//                .and()
//                .httpBasic();
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .httpBasic().disable();  // This line disables HTTP Basic authentication
    }
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//            http
//                    .csrf().disable()
//                    .authorizeRequests()
//                    .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()  // <-- Add this line to permit all OPTIONS requests
//                    .antMatchers("/login", "/register", "/").permitAll()
//                    .antMatchers("/staff/**").hasRole("ADMIN")
//                    .anyRequest().permitAll()
//                    .and()
//                    .httpBasic();
//        }
}