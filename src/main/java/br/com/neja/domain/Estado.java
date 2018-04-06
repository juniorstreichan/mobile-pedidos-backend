package br.com.neja.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Estado implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_ESTADO")
	private Integer id;
	
	@Column(unique=true, nullable=false,name="COD_IBGE")
	private Integer codIBGE;
	
	@Column(name="NOME_ESTADO")
	private String nome;
	
	@Column(length=2)
	private String sigla;
	
	@JsonIgnore
	@OneToMany(mappedBy="estado")
	private List<Cidade> cidades = new ArrayList<>();

	public Estado(Integer codIBGE, String nome, String sigla) {
		super();
		this.codIBGE = codIBGE;
		this.nome = nome;
		this.sigla = sigla;
 
	}

	public Estado() {
		 
	}

	public Integer getCodIBGE() {
		return codIBGE;
	}

	public void setCodIBGE(Integer codIBGE) {
		this.codIBGE = codIBGE;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estado other = (Estado) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}