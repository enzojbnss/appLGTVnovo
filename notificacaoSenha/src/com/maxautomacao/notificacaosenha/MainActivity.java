package com.maxautomacao.notificacaosenha;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.maxautomacao.notificacaosenha.dao.NotificacaoDAO;
import com.maxautomacao.notificacaosenha.modelo.Notificacao;

public class MainActivity extends Activity {

	
	private ArrayAdapter<String> adapterNotificacoes;
	private List<Notificacao> notificacoes;
	private ListView lstNotificacoes;
	private TextView txtStatusLista;
	private NotificacaoDAO dao = new NotificacaoDAO(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.lstNotificacoes = (ListView) findViewById(R.id.lstNotificacoes);
		this.txtStatusLista = (TextView) findViewById(R.id.txtStatusLista);
		atualiza();
		addEvento();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		if (adapterNotificacoes.equals(null) || adapterNotificacoes.isEmpty()) {
			Dialog dialog = new Dialog(this);
			dialog.setTitle("adapter vazio!");
			dialog.show();
		} else {
			Dialog dialog = new Dialog(this);
			/*
			 * Dialog dialog = new Dialog(this);
			 * dialog.setTitle("adapter possui dados!"); dialog.show();
			 */
			try {
				this.lstNotificacoes.setAdapter(adapterNotificacoes);
			} catch (NullPointerException e) {
				if (this.lstNotificacoes == null) {
					dialog.setTitle("lstCentroCusto é nulo!");
					dialog.show();
				}
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub	
		if(item.getItemId()== R.xml.preferences){
			startActivity(new Intent(this,ServiceSettingsActivity.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void initAdapters(List<Notificacao> lista) {
		this.adapterNotificacoes = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item);
		this.adapterNotificacoes
				.setDropDownViewResource(android.R.layout.simple_spinner_item);
		Log.i("lista vazio", "" + lista.isEmpty());
		this.adapterNotificacoes.add(" ");
		String status;
		if (lista.isEmpty()) {
			
			status = "Não há nenhuma notificação pendente";
		} else {
			if (lista.size() == 1) {
				status = "Existe 1 notificação pendente";
			} else {
				status = "Existem " + lista.size() + " notificações pendente";
			}
		}
		Log.i("lista vazio", status);
		this.txtStatusLista.setText(status);
		for (Notificacao notificacao : lista) {
			try {
				this.adapterNotificacoes.add(notificacao.getMotivo());
			} catch (Exception e) {
				Log.e("erro", e.getMessage());
			}
		}

	}

	private void addEvento() {
		this.lstNotificacoes.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view,
					int posicao, long id) {
				posicao--;
				Notificacao notificacao = notificacoes.get(posicao);
				Intent intent = new Intent(MainActivity.this,
						NotificacaoActivity.class);
				intent.putExtra("notificacao", notificacao);
				startActivityForResult(intent, 1);
			}
		});

	}
	public void atualiza(){
		this.notificacoes = dao.getLista();
		this.initAdapters(this.notificacoes);
	}
	
	

}
