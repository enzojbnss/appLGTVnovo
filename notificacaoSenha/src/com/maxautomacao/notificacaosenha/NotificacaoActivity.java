package com.maxautomacao.notificacaosenha;

import com.maxautomacao.notificacaosenha.dao.NotificacaoDAO;
import com.maxautomacao.notificacaosenha.modelo.Notificacao;

import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class NotificacaoActivity extends Activity {

	private Notificacao notificacao;
	private EditText txtSenha;
	private TextView txtMotivo, txtMenssagem;
	private Button btnEnviar;
	private Button btnCancelar;
	private NotificacaoDAO dao = new NotificacaoDAO(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notificacao);
		Intent intent = getIntent();
		notificacao = (Notificacao) intent.getSerializableExtra("notificacao");
		txtSenha = (EditText) findViewById(R.id.txtSenha);
		txtSenha.setText(notificacao.getSenha());
		txtMotivo = (TextView) findViewById(R.id.txtMotivo);
		txtMotivo.setText(notificacao.getMotivo());
		txtMenssagem = (TextView) findViewById(R.id.txtMenssagem);
		txtMenssagem.setText(notificacao.getMensagem());
		btnEnviar = (Button) findViewById(R.id.btnEnviar);
		btnCancelar = (Button) findViewById(R.id.btnCancelar);
		addEvento();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.notificacao, menu);
		return true;
	}

	private void enviarSenha() {
		String senha = txtSenha.getText().toString();
		if (senha.isEmpty()) {
			Toast.makeText(
					this,
					"Favor digite a senha, pois o campo encontra-se vazio!",
					Toast.LENGTH_SHORT).show(); 
		} else {
			notificacao.setSenha(senha);
			this.dao.enviarSenha(notificacao);
			Toast.makeText(
					this,
					"A senha foi enviada para validação e sairá em breve da lista de pendencia!",
					Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(NotificacaoActivity.this,
					MainActivity.class);
			startActivityForResult(intent, 1);
		}
	}
	
	private void cancelar(){
		Toast.makeText(
				this,
				"Operação cancelada pelo usuário!",
				Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(NotificacaoActivity.this,
				MainActivity.class);
		startActivityForResult(intent, 1);
	}

	private void addEvento() {
		this.btnEnviar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				enviarSenha();
			}
		});
		this.btnCancelar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				cancelar();
			}
		});
	}

}
