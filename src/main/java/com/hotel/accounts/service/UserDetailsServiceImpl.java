package com.hotel.accounts.service;
import com.hotel.accounts.entity.GuestAccountEntity;
import com.hotel.accounts.repository.GuestAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private GuestAccountRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        GuestAccountEntity guest = repository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));

        return User.builder()
                .username(guest.getEmail())
                .password(guest.getPassword())
                .roles("GUEST") // or another role
                .build();
    }
}