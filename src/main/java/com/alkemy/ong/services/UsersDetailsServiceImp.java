
package com.alkemy.ong.services;

import com.alkemy.ong.model.Users;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UsersDetailsServiceImp implements UserDetailsService {

    @Autowired
    IUsersService userService;

    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        Users user = userService.findByMail(string);
        if (user != null) {
            return userBuilder(user);
        } else {
            throw new UsernameNotFoundException("Unregistered user");
        }

        
    }


    private User userBuilder(Users user) {
        String username = user.getEmail();
        String password = user.getPassword();       
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        List<GrantedAuthority> authorities = Collections.emptyList();
        return new User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
}

