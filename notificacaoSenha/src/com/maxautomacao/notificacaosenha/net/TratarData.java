package com.maxautomacao.notificacaosenha.net;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TratarData {

	public static String getData(String dataTexto) {
		try {
			Integer dia = Integer.valueOf(Integer.parseInt(dataTexto.substring(
					0, 2)));
			Integer mes = Integer.valueOf(Integer.parseInt(dataTexto.substring(
					3, 5)));
			Integer ano = Integer.valueOf(Integer.parseInt(dataTexto.substring(
					6, 10)));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar data = Calendar.getInstance();
			data.set(ano.intValue(), mes.intValue() - 1, dia.intValue());
			String dataFormatada = sdf.format(data.getTime());
			return dataFormatada;
		} catch (Exception e) {
			return "";
		}
	}

	public static String getAno(String dataTexto) {
		Integer dia = Integer.valueOf(Integer.parseInt(dataTexto
				.substring(0, 2)));
		Integer mes = Integer.valueOf(Integer.parseInt(dataTexto
				.substring(3, 5)));
		Integer ano = Integer.valueOf(Integer.parseInt(dataTexto.substring(6,
				10)));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		Calendar data = Calendar.getInstance();
		data.set(ano.intValue(), mes.intValue() - 1, dia.intValue());
		String dataFormatada = sdf.format(data.getTime());
		return dataFormatada;
	}

	public static String getMes(String dataTexto) {
		Integer dia = Integer.valueOf(Integer.parseInt(dataTexto
				.substring(0, 2)));
		Integer mes = Integer.valueOf(Integer.parseInt(dataTexto
				.substring(3, 5)));
		Integer ano = Integer.valueOf(Integer.parseInt(dataTexto.substring(6,
				10)));
		SimpleDateFormat sdf = new SimpleDateFormat("MM");
		Calendar data = Calendar.getInstance();
		data.set(ano.intValue(), mes.intValue() - 1, dia.intValue());
		String dataFormatada = sdf.format(data.getTime());
		return dataFormatada;
	}

	public static String getDataFormatada(String dataTexto) {
		try {
			Integer ano = Integer.valueOf(Integer.parseInt(dataTexto.substring(
					0, 4)));
			Integer mes = Integer.valueOf(Integer.parseInt(dataTexto.substring(
					5, 7)));
			Integer dia = Integer.valueOf(Integer.parseInt(dataTexto.substring(
					8, 10)));
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Calendar data = Calendar.getInstance();
			data.set(ano.intValue(), mes.intValue() - 1, dia.intValue());
			String dataFormatada = sdf.format(data.getTime());
			return dataFormatada;
		} catch (Exception e) {
			return "";
		}

	}

	public static Integer getSemestre(String dataTexto) {
		Integer mes = Integer.valueOf(Integer.parseInt(getMes(dataTexto)));
		Integer semestre;
		if (mes.intValue() < 7)
			semestre = Integer.valueOf(1);
		else
			semestre = Integer.valueOf(2);
		return semestre;
	}

	public static String getDataFormatada(Date hoje) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar data = Calendar.getInstance();
		data.setTime(hoje);
		String dataFormatada = sdf.format(data.getTime());
		return dataFormatada;
	}
}
