package br.com.neja.domain.enums;

public enum TipoCliente {

	PESSOA_FISICA(1, "Pessoa Física"), PESSOA_JURIDICA(2, "Pessoa Jurídica");

	private Integer cod;
	private String descicao;

	private TipoCliente(Integer cod, String descicao) {
		this.cod = cod;
		this.descicao = descicao;
	}

	public Integer getCod() {
		return cod;
	}

	public String getDescicao() {
		return descicao;
	}

	public static TipoCliente toEnum(Integer cod) {

		if (cod == null) {
			return null;
		}

		for (TipoCliente tipo : TipoCliente.values()) {
			if (cod.equals(tipo.getCod())) {
				return tipo;
			}
		}

		throw new IllegalArgumentException("Codigo de tipo inválido: " + cod);
	}

}
