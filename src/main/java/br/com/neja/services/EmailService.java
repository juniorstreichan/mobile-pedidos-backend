package br.com.neja.services;

import br.com.neja.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;
public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	void sendEmail(SimpleMailMessage msg);
}
