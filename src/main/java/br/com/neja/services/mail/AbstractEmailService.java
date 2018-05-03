package br.com.neja.services.mail;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import br.com.neja.domain.Cliente;
import br.com.neja.domain.Pedido;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;

	// @Autowired
	// private TemplateEngine engine;

	// @Autowired
	// private JavaMailSender javaMailSender;
	//


	@Override
	public void sendOrderConfirmationEmail(Pedido obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getCliente().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Confirmação do pedido " + obj.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		System.out.println("\n\n\n ########## 1" + sm.getFrom() + " 2" + sm.getSubject() + " 3" + sm.getBcc() + " 4"
				+ sm.getTo() + " ########## \n\n\n");

		return sm;
	}

	// @Override
	// public void sendOrderConfirmationHtmlEmail(Pedido obj) {
	// try {
	// MimeMessage mm = prepareMimeMessageFromPedido(obj);
	// sendHtmlEmail(mm);
	// } catch (Exception e) {
	// sendOrderConfirmationEmail(obj);
	// }
	//
	// }

	// protected MimeMessage prepareMimeMessageFromPedido(Pedido obj) throws
	// MessagingException {
	// MimeMessage mimeMessage = javaMailSender.createMimeMessage();
	// MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
	// mmh.setTo(obj.getCliente().getEmail());
	// mmh.setFrom(sender);
	// mmh.setSentDate(new Date(System.currentTimeMillis()));
	// mmh.setSubject("Pedido cadaqstrado com sucesso "+obj.getId());
	// mmh.setText(htmlFromTemplatePedido(obj),true);
	// return mimeMessage;
	// }

	// protected String htmlFromTemplatePedido(Pedido obj) {
	// Context context = new Context();
	// context.setVariable("pedido", obj);
	// return engine.process("email/confirmacao-pedido", context);
	//
	// }
	
	@Override
	public void sendNewPasswordEmail(Cliente cliente, String newPass) {
		SimpleMailMessage sm = prepareNewPasswordEmail(cliente,newPass);
		sendEmail(sm);
		
	}

	protected SimpleMailMessage prepareNewPasswordEmail(Cliente obj, String newPass) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Solicitação de nova Senha "+obj.getNome());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Nova Senha "+newPass);
		System.out.println("\n\n\n ########## 1" + sm.getFrom() + " 2" + sm.getSubject() + " 3" + sm.getBcc() + " 4"
				+ sm.getTo() + " ########## \n\n\n");

		return sm;
	}

}
