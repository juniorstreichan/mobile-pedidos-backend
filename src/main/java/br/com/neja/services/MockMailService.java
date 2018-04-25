package br.com.neja.services;

 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockMailService extends AbstractMailService {

	private static final Logger LOG =  LoggerFactory.getLogger(MockMailService.class);

	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("Simulacao de Email");
		LOG.info(msg.toString());
		LOG.info("Email Enviado com sucesso!");
	}

}
