package br.com.neja.domain.enums;

public enum EstadoPagamento {

	PENDENTE (1,"Pendente"),
	QUITADO(2,"Quitado"),
	CANCELADO(3,"Cancelado");
	
	private Integer cod;
	private String descicao;
	
	private EstadoPagamento(Integer cod, String descicao) {
		this.cod = cod;
		this.descicao = descicao;
	}
	

	public Integer getCod() {
		return cod;
	}

	public String getDescicao() {
		return descicao;
	}

	public static EstadoPagamento toEnum(Integer cod) {

		if (cod == null) {
			return null;
		}

		for (EstadoPagamento estado : EstadoPagamento.values()) {
			if (cod.equals(estado.getCod())) {
				return estado;
			}
		}

		throw new IllegalArgumentException("Codigo de estado inválido: " + cod);
	}

}
