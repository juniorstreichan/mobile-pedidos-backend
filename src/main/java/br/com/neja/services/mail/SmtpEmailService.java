package br.com.neja.services.mail;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SmtpEmailService extends AbstractEmailService {

	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private JavaMailSender javamailSender;

	private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);

	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("Simulando Email");
		System.out.println("\n\n\n\n  ##########   " + mailSender + " ######## \n\n\n\n");
		mailSender.send(msg);
		LOG.info(" Email Enviado com sucesso!");
	}

	public void sendHtmlEmail(MimeMessage msg) {
		LOG.info("Simulando Email");
		javamailSender.send(msg);
		LOG.info(" Email Enviado com sucesso!");

	}
}
