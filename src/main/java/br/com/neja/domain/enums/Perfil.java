package br.com.neja.domain.enums;

public enum Perfil {

	ADMIN(1, "ROLE_ADMIN"),
	CLIENTE(2, "ROLE_CLIENTE");

	private Integer cod;
	private String descicao;

	private Perfil(Integer cod, String descicao) {
		this.cod = cod;
		this.descicao = descicao;
	}

	public Integer getCod() {
		return cod;
	}

	public String getDescicao() {
		return descicao;
	}

	public static Perfil toEnum(Integer cod) {

		if (cod == null) {
			return null;
		}

		for (Perfil tipo : Perfil.values()) {
			if (cod.equals(tipo.getCod())) {
				return tipo;
			}
		}

		throw new IllegalArgumentException("Codigo de tipo inválido: " + cod);
	}

}
