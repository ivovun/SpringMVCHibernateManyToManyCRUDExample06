package com.websystique.springmvc.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.model.User;
import com.websystique.springmvc.service.UserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String ssoId)
            throws UsernameNotFoundException {
        User user = userService.findBySSO(ssoId);
        if (user == null) {
            throw new UsernameNotFoundException("Username not found");
        }
        return user;
    }
}
