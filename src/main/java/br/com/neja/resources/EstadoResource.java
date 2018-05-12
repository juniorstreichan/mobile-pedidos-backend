package br.com.neja.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.neja.dto.CidadeDTO;
import br.com.neja.dto.EstadoDTO;
import br.com.neja.services.CidadeService;
import br.com.neja.services.EstadoService;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {

	@Autowired
	private EstadoService service;

	@Autowired
	private CidadeService cidadeService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<EstadoDTO>> findAll() {
		List<EstadoDTO> listaRet = service.findAll().stream().map(obj -> new EstadoDTO(obj))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listaRet);
	}

	@RequestMapping(value="/{idEstado}/cidades",method = RequestMethod.GET)
	public ResponseEntity<List<CidadeDTO>> findCidades(@PathVariable Integer idEstado) {
		List<CidadeDTO> listaRet = cidadeService.findByEstado(idEstado).stream().map(obj -> new CidadeDTO(obj))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listaRet);
	}

}
