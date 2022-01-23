package com.alkemy.ong.security;

import com.alkemy.ong.services.UsersDetailsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.alkemy.ong.util.Constants.NAME_PARAM_EMAIL;
import static com.alkemy.ong.util.Constants.NAME_PARAM_PASSWORD;
import static com.alkemy.ong.util.Constants.URL_LOGIN;
import static com.alkemy.ong.util.Constants.URL_LOGIN_SUCCESS;
import static com.alkemy.ong.util.Constants.URL_LOGIN_FAILURE;
import static com.alkemy.ong.util.Constants.URL_AUTH_REQUEST;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UsersDetailsServiceImp usersDetailsServiceImp;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests().antMatchers(URL_AUTH_REQUEST).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage(URL_LOGIN)
                    .permitAll()
                    .defaultSuccessUrl(URL_LOGIN_SUCCESS)
                    .failureUrl(URL_LOGIN_FAILURE)
                    .usernameParameter(NAME_PARAM_EMAIL)
                    .passwordParameter(NAME_PARAM_PASSWORD);

    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usersDetailsServiceImp).passwordEncoder(passwordEncoder());

    }

}
