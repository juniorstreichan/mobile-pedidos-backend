package br.com.neja.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.neja.domain.Cliente;

public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotEmpty(message="Nome:Preenchimento Obrigatório!")
	@Length(max=120 ,min=5,message="Nome:Tamanho entre 5 e 120 caracteres!")
	private String nome;
	
	
	@NotEmpty(message="Email:Preenchimento Obrigatório!")
	@Email(message="Email Inválido!")
	private String email;

	public ClienteDTO(Integer id, String nome, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
	}
	
	public ClienteDTO(Cliente obj) {
	 
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.email = obj.getEmail();
		
	}

	public ClienteDTO() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	
}
