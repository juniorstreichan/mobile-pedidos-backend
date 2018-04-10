package br.com.neja.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.neja.domain.Categoria;
import br.com.neja.dto.CategoriaDTO;
import br.com.neja.repositories.CategoriaRepository;
import br.com.neja.services.exception.DataIntegrityException;
import br.com.neja.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository repo;

	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id:" + id + " Tipo: " + Categoria.class.getSimpleName()));
	}

	public List<Categoria> findAll() {
		return repo.findAll();
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Categoria update(Categoria obj) {
		Categoria newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		Categoria c = find(id);
		try {
			repo.deleteById(c.getId());
		} catch (DataIntegrityViolationException ex) {
			throw new DataIntegrityException(
					"Não é possível excluir a categoria" + c.getNome() + " pois ela possui produtos");
		}

	}
	
	public Page<Categoria> findPage(
//			 parametros
			Integer page,
			Integer linesPerPage,
			String orderBy,
			String direction
//			fim parametros
			){
		PageRequest pageRequest =  PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return repo.findAll(pageRequest);
	}
	
	public Categoria fromDTO(CategoriaDTO obj){
		return new  Categoria(obj.getId(),obj.getNome()) ;
	}
	
	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());

	}
}














