
package com.alkemy.ong.services;

import com.alkemy.ong.entities.Users;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        Users user = userService.findByMail(string);
        System.out.println("Correo enviado: "  + string);
        if (user != null) {
            
            System.out.println("El mail Existe");
            System.out.println(user.getEmail());
            System.out.println(user.getPassword());
            return userBuilder(user);
        } else {
            System.out.println("Error de Usuario");
            throw new UsernameNotFoundException("Usuario no registrado");
        }

        
    }


    

    private User userBuilder(Users user) {
        String username = user.getEmail();
        String password = user.getPassword();       
//        boolean enabled = true;
//        boolean accountNonExpired = true;
//        boolean credentialsNonExpired = true;
//        boolean accountNonLocked = true;
        List<GrantedAuthority> authorities = Collections.emptyList();
        return new User(username, password, authorities);
//        return new User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
}

