package br.com.neja.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
public class Produto implements Serializable{
	
 
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_PRODUTO")
	private Integer id;
	
	@Column(name="NOME_PRODUTO")
	private String nome;
	
    @Digits(integer=10, fraction=2, message="Informe um valor válido")
    @DecimalMin("0.01")
	private BigDecimal preco;
	
    
    @JsonBackReference
	@ManyToMany
	@JoinTable(
			name="ITEM_CATEGORIA",
			joinColumns=@JoinColumn(name="ID_PRODUTO"),
			inverseJoinColumns=@JoinColumn(name="ID_CATEGORIA")
	)
	private List<Categoria> categorias = new ArrayList<>();
	
	public Produto(Integer id, String nome, BigDecimal preco) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}

	public Produto() {
	 
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

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	
	
	
}
