package br.com.neja.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.neja.domain.Categoria;
import br.com.neja.domain.Pedido;
import br.com.neja.domain.Produto;
import br.com.neja.repositories.ProdutoRepository;
import br.com.neja.services.exception.ObjectNotFoundException;

@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository repo;

//	@Autowired
//	private CategoriaRepository catRepo;

	public Produto find(Integer id) {
		Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id:" + id + " Tipo: " + Pedido.class.getSimpleName()));
	}

	public Page<Produto> search(
//			parametros
			String nome,
			List<Integer> ids,
			Integer page,
			Integer linesPerPage,
			String orderBy,
			String direction
//			fim parametros
			){
		PageRequest pageRequest =  PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias= new ArrayList<>();
		
		for (Integer id : ids) {
			categorias.add(new Categoria(id, null));
			}
		
		return repo.findDistinctByNomeContainingAndCategoriasIn(nome,categorias,pageRequest);
	}
}




















