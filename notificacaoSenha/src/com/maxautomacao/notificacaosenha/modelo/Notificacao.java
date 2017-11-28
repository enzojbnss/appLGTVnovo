package com.maxautomacao.notificacaosenha.modelo;

import java.io.Serializable;

public class Notificacao implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer status;
	private String senha;
	private String mensagem;
	private String motivo;

    public Notificacao()
    {
    }

    public Notificacao(Integer id, Integer status, String senha, String mensagem, String motivo)
    {
        this.id = id;
        this.status = status;
        this.senha = senha;
        this.mensagem = mensagem;
        this.motivo = motivo;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
    
    

}
