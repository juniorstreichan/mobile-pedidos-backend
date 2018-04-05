package br.com.neja.services;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.neja.domain.Cliente;
import br.com.neja.repositories.ClienteRepository;
import br.com.neja.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository repo;

	public Cliente buscar(Integer id)     {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Objeto não encontrado! Id:"+id+" Tipo: "+Cliente.class.getSimpleName())
		);
	}
}
