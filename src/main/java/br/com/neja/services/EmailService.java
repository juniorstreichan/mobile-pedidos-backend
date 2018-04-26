package br.com.neja.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import br.com.neja.domain.Pedido;
@Service
public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	void sendEmail(SimpleMailMessage msg);
}
