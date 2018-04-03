package br.com.neja.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.neja.domain.Categoria;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Categoria> listar() {
		List<Categoria> listRet= new ArrayList<>(); 
		listRet.add(new Categoria(1,"cat-top"));
		listRet.add( new Categoria(2,"desi"));
		listRet.add( new Categoria(3,"nejar"));
		
		return listRet;
		
	}
}
