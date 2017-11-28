package com.maxautomacao.notificacaosenha.net;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.message.BasicNameValuePair;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

@TargetApi(Build.VERSION_CODES.CUPCAKE)
public class ConnectServer extends AsyncTask<Void, Void, Void> {

	private static final String encolding = "UTF-8";
	private String URL;
	private Context context;
	private String dados;
	private ProgressDialog dialog;
	private HttpClient httpClient = null;
	private List<NameValuePair> parametros = new ArrayList<NameValuePair>();
	private Boolean status = false;
	private MetodoEnvio metodoEnvio;

	public ConnectServer(Context context) {
		this.context = context;
		this.metodoEnvio = MetodoEnvio.GET;
	}

	public ConnectServer(String URL, Context context) {
		this.URL = URL;
		this.context = context;
		this.metodoEnvio = MetodoEnvio.GET;
	}

	public void addParamentro(String nome, String valor) {
		this.parametros.add(new BasicNameValuePair(nome, valor));
	}

	public void setParametros(List<NameValuePair> parametros) {
		this.parametros = parametros;
	}

	public MetodoEnvio getMetodoEnvio() {
		return metodoEnvio;
	}

	public void setMetodoEnvio(MetodoEnvio metodoEnvio) {
		this.metodoEnvio = metodoEnvio;
	}

	public String getDados() {
		return dados;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public boolean verificaConectividade(String nomeServico, Activity activity) {
		ConnectivityManager connectivityManager = (ConnectivityManager) activity
				.getSystemService(nomeServico);
		Boolean conectado = false;
		try {
			if ((connectivityManager.getActiveNetworkInfo() != null)
					&& (connectivityManager.getActiveNetworkInfo()
							.isAvailable())) {
				conectado = connectivityManager.getActiveNetworkInfo()
						.isConnected();
			}
		} catch (Exception e) {
			Log.e("erro", e.getMessage());
			System.out.println(e.getMessage());
		}
		Log.i("teste", "" + conectado);
		return conectado;
	}

	public String getRESTFileContent(String url) {
		HttpGet httpget = new HttpGet(this.URL);
		try {
			this.httpClient = FabricaDeHttpClient.getHttpClient();
			Log.i("conctando", "TENTANDO ESTABENCER COEX�O COM SERVIDORs");
			HttpResponse response = this.httpClient.execute(httpget);
			HttpEntity entity = response.getEntity();
			Log.i("conctando", "entity carregado");
			if (entity != null) {
				InputStream instream = entity.getContent();
				String result = toString(instream);
				instream.close();
				return result;
			}
		} catch (Exception e) {
			System.out.println("erro na conex�o :" + e.getMessage());
			Log.e("NGVL", "Falha ao acessar Web service", e);
		}
		return null;
	}

	private String toString(InputStream is) throws IOException {
		byte[] bytes = new byte[1024];
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int lidos;
		while ((lidos = is.read(bytes)) > 0) {
			baos.write(bytes, 0, lidos);
		}
		return new String(baos.toByteArray());
	}

	public String getRESTFileContent(String url,
			List<NameValuePair> parametros, MetodoEnvio metodo) {
		String result = "";
		try {
			HttpClient httpclient = FabricaDeHttpClient.getHttpClient();
			HttpResponse response;
			HttpEntity entity;
			Log.i("metodo", String.valueOf(metodo.getId()));
			switch (metodo.getId()) {
			case 1:
				HttpPost post = new HttpPost(url);
				post.setEntity(new UrlEncodedFormEntity(parametros));
				response = httpclient.execute(post);
				entity = response.getEntity();
				result = getResult(entity);
				break;
			case 2:
				HttpGet get = new HttpGet(url);
				((HttpResponse) get).setEntity(new UrlEncodedFormEntity(
						this.parametros));
				get.setHeader("Content-Type","application/json");
				get.setHeader("Accept-Encoding","application/json");
				response = httpclient.execute(get);
				entity = response.getEntity();
				result = getResult(entity);
				break;
			case 3:
				HttpPut put = new HttpPut(url);
				put.setEntity(new UrlEncodedFormEntity(this.parametros));
				response = httpclient.execute(put);
				entity = response.getEntity();
				result = getResult(entity);
				break;
			case 4:
				HttpDelete delete = new HttpDelete(url);
				((HttpResponse) delete).setEntity(new UrlEncodedFormEntity(
						this.parametros));
				response = httpclient.execute(delete);
				entity = response.getEntity();
				result = getResult(entity);
				break;
			}
			return result;
		} catch (Exception e) {
			System.out.println("erro na conex�o :" + e.getMessage());
			Log.e("NGVL", "Falha ao acessar Web service", e);
			return null;
		}

	}

	private String getResult(HttpEntity entity) throws Exception {
		String result = "";
		if (entity != null) {
			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(
					parametros);
			formEntity.setContentEncoding(encolding);
			InputStream instream = entity.getContent();
			result = instream.toString();
			instream.close();
		}
		return result;
	}

	public void carregarDados() {
		this.status = false;
		String result = "";
		if (this.parametros.isEmpty()) {
			result = this.getRESTFileContent(this.URL);
			Log.i("teste parametros", "sem parametros");
		} else {
			result = this.getRESTFileContent(this.URL, this.parametros,
					this.metodoEnvio);
			Log.i("teste parametros", "com parametros");
		}
		if (result != null) {
			this.dados = result;
		} else {
			this.dados = "";
		}
		this.status = true;
	}

	public Boolean isOK() {
		return this.status;
	}

	@Override
	protected Void doInBackground(Void... arg0) {
		carregarDados();
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		this.dialog.dismiss();
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		this.dialog = ProgressDialog.show(this.context,
				"Sincriniza��o de dados",
				"Iniciando acesso a servidor de rede!");
	}

	@Override
	protected void onProgressUpdate(Void... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
	}

}
