package com.maxautomacao.notificacaosenha.net;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.util.Log;

public class FabricaDeHttpClient {
	
	private static final int HTTP_TIMEOUT = 30000;
	
	public static HttpClient getHttpClient() throws Exception {
			Log.e("inicializando http", "criando instancia do HttpClient ,,,");
			HttpClient httpClient = new DefaultHttpClient();
			HttpParams httpParametros = httpClient.getParams();
			HttpConnectionParams.setConnectionTimeout(httpParametros,
					HTTP_TIMEOUT);
			HttpConnectionParams.setSoTimeout(httpParametros, HTTP_TIMEOUT);
			ConnManagerParams.setTimeout(httpParametros, HTTP_TIMEOUT);
		return httpClient;
	}

}
