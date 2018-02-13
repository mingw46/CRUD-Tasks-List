package com.example.demo.services;

import com.example.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        if(username.trim().isEmpty()){
            throw new UsernameNotFoundException("username is empty");
        }

        User user = userService.findByUsernameOrEmail(username);

        if(user == null){
            throw new UsernameNotFoundException("User " + username  + " not found");
        }

        if(!user.isEnabled()){
            throw new UsernameNotFoundException("User " + username  + " is disabled");
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getGrantedAuthorities(user));
    }

    private List<GrantedAuthority> getGrantedAuthorities(User user){
        List<GrantedAuthority> listAuthorities = new ArrayList<GrantedAuthority>();
        listAuthorities.add(new SimpleGrantedAuthority(user.getRole()));
        return listAuthorities;
    }

}
