package com.maxautomacao.notificacaosenha.net;

public enum MetodoEnvio {

	POST(1, "POST"), GET(2, "GET"), PUT(3, "PUT"), DELETE(4, "DELETE");

	private final Integer id;
	private final String descricao;

	private MetodoEnvio(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

}
