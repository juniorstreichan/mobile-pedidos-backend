package br.com.neja.services;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.neja.domain.Pedido;
import br.com.neja.repositories.PedidoRepository;
import br.com.neja.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository repo;

	public Pedido buscar(Integer id)     {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Objeto não encontrado! Id:"+id+" Tipo: "+Pedido.class.getSimpleName())
		);
	}
}
