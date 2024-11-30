package com.dhanush.security.services;

import com.dhanush.security.dto.SecurityUser;
import com.dhanush.security.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public JpaUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        var u = userRepository.findUserByUsername(username);
        SecurityUser securityUser = null;
        if(u.isPresent()) {
            securityUser = new SecurityUser(u.get());
        } else {
            System.out.println("username not found");
            throw new UsernameNotFoundException("Username not found " + username);
        }
        return securityUser;
    }
}
