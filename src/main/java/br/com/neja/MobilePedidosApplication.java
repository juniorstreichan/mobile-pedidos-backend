package br.com.neja;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MobilePedidosApplication implements CommandLineRunner {



	public static void main(String[] args) {
		SpringApplication.run(MobilePedidosApplication.class, args);
	}

	// START INICIAL DO BANCO
	@Override
	public void run(String... args) throws Exception {
	}
}
