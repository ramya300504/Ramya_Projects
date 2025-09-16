package com.hexaware.cozyhaven.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hexaware.cozyhaven.entity.User;
import com.hexaware.cozyhaven.repository.UserRepository;



@Service
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = repository.findByEmail(email);
        
        return user.map(UserInfoUserDetails::new)
                   .orElseThrow(() -> new UsernameNotFoundException("Email not found: " + email));
    }
}
