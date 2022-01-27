package com.alkemy.ong;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import static com.alkemy.ong.util.Constants.*;

import com.alkemy.ong.service.EmailServiceImp;

@EntityScan(basePackages = {"com.alkemy.ong.model"})
@ComponentScan(basePackages = {"com.*"})
@EnableJpaRepositories(basePackages = {"com.alkemy.ong.repository"})
@EnableTransactionManagement
@EnableWebMvc
@RepositoryRestController
@EnableAutoConfiguration
// @EnableJpaAuditing
// @EnableCaching //Hablitando el cache


@SpringBootApplication
public class OngApplication implements CommandLineRunner{

	@Autowired 
	private EmailServiceImp sendGridEmailService;
	
	public static void main(String[] args) {
		SpringApplication.run(OngApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
	
		
		//this.sendGridEmailService.sendWelcomeEmail(MAIL_ONG, "matiicastagno007@gmail.com");
		
	}

}
