package com.maxautomacao.notificacaosenha.persistence.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.maxautomacao.notificacaosenha.modelo.Notificacao;
import com.maxautomacao.notificacaosenha.net.Result;

public class NotificacaoService {
	private final String url = "http://192.168.1.105/notificacaosenha/notificacoes/";
	private com.maxautomacao.notificacaosenha.net.ConnectServer conexao;

	public NotificacaoService(Context context) {
		this.conexao = new com.maxautomacao.notificacaosenha.net.ConnectServer(context);
	}

	public List<Notificacao> getNotificacaoes() {
		List<Notificacao> notificacoes = new ArrayList<Notificacao>();
		String servico = this.url + "getListaJson";
		Log.i("servoço : ",servico);
		this.conexao.setURL(servico);
		conexao.execute();
		Boolean pronto = false;
		Log.i("Atenção", "iniciando o processo :" + pronto);
		while (!pronto) {
			pronto = conexao.isOK();
		}
		String dados = conexao.getDados();
		JSONObject notificacaoJson;
		try {
			Log.i("teste", dados);
			JSONArray notificacaoArray = Result.getJsonArray(dados, "list");
			for (int i = 0; i < notificacaoArray.length(); i++) {
				notificacaoJson = new JSONObject(notificacaoArray.getString(i));
				notificacoes.add(new Notificacao(notificacaoJson.getInt("ID"),notificacaoJson.getInt("Status"), notificacaoJson.getString("Senha"),notificacaoJson.getString("Mensagem"),notificacaoJson.getString("Motivo")));
			}
		} catch (Exception e) {
			Log.e("erro", e.getMessage());
		}
		return notificacoes;
	}
	
	public void enviarSenha(Notificacao notificacao) {
		List<Notificacao> notificacoes = new ArrayList<Notificacao>();
		String parametro = "?id="+notificacao.getId()+"&senha="+notificacao.getSenha();
		String servico = this.url + "enviarSenha"+parametro;
		Log.i("servoço : ",servico);
		this.conexao.setURL(servico);
		conexao.execute();
		Boolean pronto = false;
		Log.i("Atenção", "iniciando o processo :" + pronto);
		while (!pronto) {
			pronto = conexao.isOK();
		}
		String dados = conexao.getDados();
		JSONObject notificacaoJson;
		try {
			Log.i("teste", dados);
			
		} catch (Exception e) {
			Log.e("erro", e.getMessage());
		}
	}

}
