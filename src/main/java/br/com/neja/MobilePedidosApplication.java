package br.com.neja;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.neja.services.S3Service;

@SpringBootApplication
public class MobilePedidosApplication implements CommandLineRunner {



	@Autowired
	private S3Service s3;
	
	public static void main(String[] args) {
		SpringApplication.run(MobilePedidosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
//		s3.uploadFile("C:\\Users\\Junior.erdmann\\Pictures\\Pictures\\grafiti02.jpg");
		
	}
}
