package com.alkemy.ong;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alkemy.ong.mail.WelcomeEmail;
import com.alkemy.ong.service.EmailService;

@SpringBootApplication
public class OngApplication implements CommandLineRunner{

	@Autowired EmailService sendGridEmailService;
	
	@Autowired 
	WelcomeEmail welcomeEmail;
	
	public static void main(String[] args) {
		SpringApplication.run(OngApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//sendGridEmailService.getEmail()
		
		this.sendGridEmailService.sendWelcomeEmail("matiicastagno007@gmail.com", "aalkymers@gmail.com", welcomeEmail.getSubject(), welcomeEmail.getBody());
		
	}

}
