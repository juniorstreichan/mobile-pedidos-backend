package br.com.neja.services;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.neja.domain.Categoria;
import br.com.neja.repositories.CategoriaRepository;
import br.com.neja.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository repo;

	public Categoria buscar(Integer id)     {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Objeto não encontrado! Id:"+id+" Tipo: "+Categoria.class.getSimpleName())
		);
	}
	
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
}
