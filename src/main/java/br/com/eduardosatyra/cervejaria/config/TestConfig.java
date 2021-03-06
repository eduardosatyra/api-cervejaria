package br.com.eduardosatyra.cervejaria.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;

import br.com.eduardosatyra.cervejaria.services.DBService;
import br.com.eduardosatyra.cervejaria.services.EmailService;
import br.com.eduardosatyra.cervejaria.services.MockEmailService;

/**
 * @author eduardosatyra
 *
 */

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DBService dbService;

	@Bean
	public boolean instantiateDatabase() throws ParseException {
		dbService.instantiateTestDataBase();
		return true;
	}

	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}
	
}