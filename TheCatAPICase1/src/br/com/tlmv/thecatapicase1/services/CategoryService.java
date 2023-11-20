/*
 * Copyright(C) TLMV Consultoria e Sistemas Ltda. Todos os direitos reservados.
 *
 * CategoryService.java
 * Autor: Luiz Marcio Faria de Aquino Viana, 25/10/2022
 * revisoes: ...
 *
 */

package br.com.tlmv.thecatapicase1.services;

import org.json.JSONArray;

import br.com.tlmv.thecatapicase1.AppDefs;

public class CategoryService extends BaseService 
{
//Public
	
	/* Methodes */
	
	public String requestAllCategories() {
		String wsEndPoint = AppDefs.WS_ENDPOINT_CATEGORIES;
		
		String strJSON = this.requestData(wsEndPoint);
		return strJSON;
	}
	
	public JSONArray requestAllCategoriesJSON()
		throws Exception
	{
		String wsEndPoint = AppDefs.WS_ENDPOINT_CATEGORIES;
		
		String strJSON = this.requestData(wsEndPoint);
		JSONArray aJSON = new JSONArray(strJSON);
		return aJSON;
	}
	
}
