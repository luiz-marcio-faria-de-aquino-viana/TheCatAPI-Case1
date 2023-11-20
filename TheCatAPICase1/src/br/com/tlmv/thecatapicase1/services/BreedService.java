/*
 * Copyright(C) TLMV Consultoria e Sistemas Ltda. Todos os direitos reservados.
 *
 * BreedService.java
 * Autor: Luiz Marcio Faria de Aquino Viana, 24/10/2022
 * revisoes: ...
 *
 */

package br.com.tlmv.thecatapicase1.services;

import org.json.JSONArray;

import br.com.tlmv.thecatapicase1.AppDefs;

public class BreedService extends BaseService 
{
//Public
	
	/* Methodes */
	
	public String requestAllBreeds() {
		String wsEndPoint = AppDefs.WS_ENDPOINT_BREEDS;
		
		String strJSON = this.requestData(wsEndPoint);
		return strJSON;
	}
	
	public JSONArray requestAllBreedsJSON()
		throws Exception
	{
		String wsEndPoint = AppDefs.WS_ENDPOINT_BREEDS;
		
		String strJSON = this.requestData(wsEndPoint);
		JSONArray aJSON = new JSONArray(strJSON);
		return aJSON;
	}
	
}
