/*
 * Copyright(C) TLMV Consultoria e Sistemas Ltda. Todos os direitos reservados.
 *
 * JSONUtil.java
 * Autor: Luiz Marcio Faria de Aquino Viana, 24/10/2022
 * revisoes: ...
 *
 */

package br.com.tlmv.thecatapicase1.utils;

import java.util.Date;

import br.com.tlmv.thecatapicase1.AppDefs;

public class UuidUtil 
{
//Private
	private static Long nextRequestId = AppDefs.DEF_REST_INITIAL_REQUEST_ID;
	
//Public
	
	public static String generateUUID() {
		Date dataAtual = new Date();
		
		long dataAtualMili = dataAtual.getTime();
		long rnd = Math.round(Math.random() * 1000000.0) % 10000L;
		
		String uuid = Long.toHexString(dataAtualMili) + "_" + Long.toHexString(rnd);
		return uuid;
	}

	public static Long generateRequestID() {
		return UuidUtil.nextRequestId++;
	}

}
