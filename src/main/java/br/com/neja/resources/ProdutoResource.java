package br.com.neja.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.neja.domain.Produto;
import br.com.neja.dto.ProdutoDTO;
import br.com.neja.resources.util.URL;
import br.com.neja.services.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService service;
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<Produto> find(@PathVariable  Integer id) {
		Produto obj =  service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			// parametros
			@RequestParam(defaultValue = "", value = "nome") String nome,
			@RequestParam(defaultValue = "", value = "categorias") String categorias,
			@RequestParam(defaultValue = "0", value = "page") Integer page,
			@RequestParam(defaultValue = "24", value = "linesPerPage") Integer linesPerPage,
			@RequestParam(defaultValue = "nome", value = "orderBy") String orderBy,
			@RequestParam(defaultValue = "ASC", value = "direction") String direction
	// fim parametros
	) {
		Page<Produto> list = service.search(URL.decodeParam(nome),URL.decodeIntList(categorias),page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listDTO = list.map(obj -> new ProdutoDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}

}
