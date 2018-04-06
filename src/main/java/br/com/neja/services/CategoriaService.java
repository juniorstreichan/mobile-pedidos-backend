package br.com.neja.services;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.neja.domain.Categoria;
import br.com.neja.repositories.CategoriaRepository;
import br.com.neja.services.exception.DataIntegrirtyException;
import br.com.neja.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository repo;

	public Categoria find(Integer id)     {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Objeto não encontrado! Id:"+id+" Tipo: "+Categoria.class.getSimpleName())
		);
	}
	
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}


	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}


	public void delete(Integer id) {
		Categoria c = find(id);
		try {
			repo.deleteById(c.getId());
		} catch (DataIntegrityViolationException ex) {
			throw new DataIntegrirtyException("Não é possível excluir a categoria"+c.getNome()+" pois ela possui produtos");
		}
		
	}
}


















