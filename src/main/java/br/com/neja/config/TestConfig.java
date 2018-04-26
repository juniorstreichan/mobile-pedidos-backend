package br.com.neja.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.neja.services.DBService;
import br.com.neja.services.EmailService;
import br.com.neja.services.MockMailService;

@Configuration
@Profile("test")
public class TestConfig {
	@Autowired
	private DBService dbService;

	@Bean
	public boolean initDatabase() {
		
		dbService.initTestDatabase();
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new MockMailService();
	}
}
