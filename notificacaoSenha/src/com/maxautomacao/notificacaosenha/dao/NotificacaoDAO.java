package com.maxautomacao.notificacaosenha.dao;

import java.util.List;

import android.content.Context;

import com.maxautomacao.notificacaosenha.modelo.Notificacao;
import com.maxautomacao.notificacaosenha.persistence.service.NotificacaoService;

public class NotificacaoDAO {
	
	private NotificacaoService service;

	public NotificacaoDAO(Context context) {
		this.service = new NotificacaoService(context);
	}
	
	public List<Notificacao> getLista(){
		return this.service.getNotificacaoes();
	}
	
	public void enviarSenha(Notificacao notificacao) {
		this.service.enviarSenha(notificacao);
	}
	

}
