package br.com.neja.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Cidade implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_CIDADE")
	private Integer id;
	
	@Column(unique=true, nullable=false,name="COD_IBGE")
	private Integer codIBGE;
	
	@Column(name="NOME_CIDADE")
	private String nome;
	
	@ManyToOne
	@JoinColumn(name="ID_ESTADO")
	private Estado estado;

	public Cidade() {

	}

	public Cidade(Integer codIBGE, String nome, Estado estado) {
		super();
		this.codIBGE = codIBGE;
		this.nome = nome;
		this.estado = estado;
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

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
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
		Cidade other = (Cidade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
