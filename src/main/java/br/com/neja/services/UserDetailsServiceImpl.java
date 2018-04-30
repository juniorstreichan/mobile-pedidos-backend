package br.com.neja.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.neja.domain.Cliente;
import br.com.neja.security.UserSS;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private ClienteService clienteService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Cliente tmpCli = clienteService.findByEmail(email);

		if (tmpCli == null)
			throw new UsernameNotFoundException(email);

		return new UserSS(tmpCli);
	}

}
