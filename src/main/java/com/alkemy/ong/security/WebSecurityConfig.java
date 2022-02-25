package com.alkemy.ong.security;

import com.alkemy.ong.service.UsersDetailsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.alkemy.ong.util.JwtAuthFilter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.alkemy.ong.util.Constants.*;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UsersDetailsServiceImp usersDetailsServiceImp;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.cors().and().csrf().disable()
                .addFilterBefore(new JwtAuthFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(URL_AUTH_REQUEST).permitAll() // Any user can access /auth/login and /auth/register
                .antMatchers(AUTH_ONLY_ADMINS).hasAuthority("ROLE_ADMIN") // Only Admins can access other locations in /auth/**
                .antMatchers(HttpMethod.GET,
                        REQ_MAPP_ACTIVITIES,
                        REQ_MAPP_ORG,
                        REQ_MAPP_CATEGORIES,
                        REQ_MAPP_NEWS,
                        REQ_MAPP_NEWS + REQ_MAPP_ID).hasAnyAuthority(AUTHENTICATED_ROLES) // Only authenticated roles can access GET methods
                .antMatchers(HttpMethod.PUT,
                        REQ_MAPP_MEMBERS + "/**").hasAnyAuthority(AUTHENTICATED_ROLES)
                .antMatchers(HttpMethod.POST, URL_ORG_SECURITY).hasAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.DELETE).hasAuthority("ROLE_ADMIN")
                .antMatchers(SWAGGER_SECURITY).permitAll()
                .antMatchers(REQ_MAPP_ACTIVITIES, REQ_MAPP_ACTIVITIES + REQ_MAPP_ID,
                        REQ_MAPP_ORG, REQ_MAPP_ORG + REQ_MAPP_ID,
                        REQ_MAPP_CATEGORIES, REQ_MAPP_CATEGORIES + REQ_MAPP_ID,
                        REQ_MAPP_MEMBERS, REQ_MAPP_MEMBERS + REQ_MAPP_ID,
                        REQ_MAPP_NEWS, REQ_MAPP_NEWS + REQ_MAPP_ID,
                        REQ_MAPP_SLIDES, REQ_MAPP_SLIDES + REQ_MAPP_ID,
                        REQ_MAPP_TESTIMONIALS, REQ_MAPP_TESTIMONIALS + REQ_MAPP_ID,
                        REQ_MAPP_CONTACTS, REQ_MAPP_CONTACTS + REQ_MAPP_ID,
                        REQ_MAPP_GET_LIST_USER).hasAuthority("ROLE_ADMIN")
                // Only admins can access other methods
                .antMatchers("/public/**").permitAll() // All users can access endpoints in /public/**
                .anyRequest().authenticated() // Only authenticated users can access the rest of endpoints
                .and()
                .exceptionHandling()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
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
