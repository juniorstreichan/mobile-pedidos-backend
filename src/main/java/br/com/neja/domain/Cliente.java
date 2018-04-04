package br.com.neja.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.neja.domain.enums.TipoCliente;

public class Cliente {

	private Integer id;
	private String nome;
	private String email;
	private String cpfOuCnpj;
	private TipoCliente tipo;
	private List<Endereco> enderecos = new ArrayList<>();
	private Set<String> telefones= new HashSet<>();
	
	
	public Cliente(Integer id, String nome, String email, String cpfOuCnpj, TipoCliente tipo) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipo = tipo;
	}
	
	public Cliente() {
		
	}
	
	
}
