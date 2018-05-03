package br.com.neja.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.neja.domain.Cliente;
import br.com.neja.services.exception.ObjectNotFoundException;
import br.com.neja.services.mail.EmailService;

@Service
public class AuthService {

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private BCryptPasswordEncoder crypt;

	@Autowired
	private EmailService emailService;

	private Random random = new Random();

	public void sendNewPassword(String email) {

		Cliente cliente = clienteService.findByEmail(email);
		if (cliente == null)
			throw new ObjectNotFoundException("Email não encontrado");

		String newPass = newPassword();
		cliente.setSenha(crypt.encode(newPass));

		cliente = clienteService.updateForgotEmail(cliente);
		emailService.sendNewPasswordEmail(cliente, newPass);

	}

	private String newPassword() {

		char[] vet = new char[10];

		for (int i = 0; i < vet.length; i++) {
			vet[i] = randomChar();
		}

		return new String(vet);
	}

	private char randomChar() {

		int opt = random.nextInt(3);

		switch (opt) {
		case 0:
			return (char) (random.nextInt(10) + 48) ;
		case 1:
			return (char) (random.nextInt(26) + 65) ;
		case 2:
			return (char) (random.nextInt(26) + 97) ;
		default:
			return 0;

		}

	}

}
