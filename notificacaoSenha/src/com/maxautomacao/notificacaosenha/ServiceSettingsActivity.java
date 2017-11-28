package com.maxautomacao.notificacaosenha;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ServiceSettingsActivity extends Activity {

	private SharedPreferences sharedPreferences;
	private EditText txtServidor;
	private Button btnGravar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service_settings);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.service_settings, menu);
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		StringBuilder builder = new StringBuilder();
		builder.append(sharedPreferences.getString("servidor", ""));
		btnGravar = (Button) findViewById(R.id.btnGravar);
		txtServidor = (EditText) findViewById(R.id.txtServidor);
		txtServidor.setText(builder.toString());
		addEvento();
		return true;
	}

	private void gravar() {
		String servidor = txtServidor.getText().toString();
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(getString(R.xml.preferences), servidor);
		editor.commit();
	}
	
	private void addEvento() {
		this.btnGravar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				gravar();
			}
		});
		
	}

}
