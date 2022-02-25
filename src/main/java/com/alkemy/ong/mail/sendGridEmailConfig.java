package com.alkemy.ong.mail;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import com.sendgrid.SendGrid;

import org.springframework.beans.factory.annotation.Value;

@Configuration
public class sendGridEmailConfig {

    @Value("${spring.sendgrid.api-key}")
    private String key;

    @Bean
    public SendGrid getSendgris() {

        return new SendGrid(key);
    }
}
