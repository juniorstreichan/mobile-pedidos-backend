package br.com.neja.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.neja.services.validation.ClienteInsert;


@ClienteInsert
public class ClienteNewDTO implements Serializable {
	
 
	private static final long serialVersionUID = 1L;
	private static final int MIN_NOME = 5;
	private static final int MAX_NOME = 120;
	
	
	
	@NotEmpty(message="Nome:Preenchimento Obrigatório!")
	@Length(max=MAX_NOME ,min=MIN_NOME,message="Nome:Tamanho entre "+MIN_NOME+" e "+MAX_NOME+" caracteres!")
	private String nome;
	
	@NotEmpty(message="Email:Preenchimento Obrigatório!")
	@Email(message="Email Inválido!")
	private String email;
	private String cpfOuCnpj;
	private Integer tipo;
	
	private String logradouro;
	private String numero;
	private String complemento;
	private Integer cidade;
	private String bairro;
	@NotEmpty(message="CEP:Preenchimento Obrigatório!")
	private String cep;
	
	@NotEmpty(message="Telefone:Preenchimento Obrigatório do primeiro número de telefone!")
	private String telefone1;
	private String telefone2;
	private String telefone3;

	private List<String> telefones = new ArrayList<>();
	
	
	public ClienteNewDTO() {
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


	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}


	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}


	public Integer getTipo() {
		return tipo;
	}


	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}


	public String getLogradouro() {
		return logradouro;
	}


	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}


	public String getNumero() {
		return numero;
	}


	public void setNumero(String numero) {
		this.numero = numero;
	}


	public String getComplemento() {
		return complemento;
	}


	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}


	public Integer getCidade() {
		return cidade;
	}


	public void setCidade(Integer cidade) {
		this.cidade = cidade;
	}


	public String getBairro() {
		return bairro;
	}


	public void setBairro(String bairro) {
		this.bairro = bairro;
	}


	public String getCep() {
		return cep;
	}


	public void setCep(String cep) {
		this.cep = cep;
	}


	public String getTelefone1() {
		return telefone1;
	}


	public void setTelefone1(String telefone1) {
		this.telefones.add(telefone1);
		this.telefone1 = telefone1;
	}


	public String getTelefone2() {
		return telefone2;
	}


	public void setTelefone2(String telefone2) {
		if (telefone2!=null) {
			this.telefones.add(telefone2);
		}
		this.telefone2 = telefone2;
	}


	public String getTelefone3() {
		
		return telefone3;
	}


	public void setTelefone3(String telefone3) {
		if (telefone3!=null) {
			this.telefones.add(telefone3);
		}
		this.telefone3 = telefone3;
	}


	public List<String> getTelefones() {
		return telefones;
	}



	
	
	
	
	

}
