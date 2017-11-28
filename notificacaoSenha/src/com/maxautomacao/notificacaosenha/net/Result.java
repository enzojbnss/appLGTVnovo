package com.maxautomacao.notificacaosenha.net;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class Result {
	
	public static JSONArray getJsonArray(String dados, String raiz)
			throws Exception {
		return new JSONObject(dados).getJSONArray(raiz);
	}

	public static JSONObject getObject(String dados, String raiz)
			throws Exception {
		return new JSONObject(dados).getJSONObject(raiz);
	}
	
	
	public static Document getXml(String dados) throws Exception {
		DocumentBuilderFactory db = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = db.newDocumentBuilder();
		return builder.parse(new InputSource(new StringReader(dados)));
	}

}
