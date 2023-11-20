/*
 * Copyright(C) TLMV Consultoria e Sistemas Ltda. Todos os direitos reservados.
 *
 * JSONUtil.java
 * Autor: Luiz Marcio Faria de Aquino Viana, 24/10/2022
 * revisoes: ...
 *
 */

package br.com.tlmv.thecatapicase1.utils;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.tlmv.thecatapicase1.AppDefs;
import br.com.tlmv.thecatapicase1.data.BaseObject;
import br.com.tlmv.thecatapicase1.data.Breed;
import br.com.tlmv.thecatapicase1.data.BreedImage;
import br.com.tlmv.thecatapicase1.data.BreedWeight;
import br.com.tlmv.thecatapicase1.data.Image;

public class JSONUtil 
{
//Public
	
	public static Object safeObjFromJSON(JSONObject jsonObj, String key, BaseObject defaultVal)
	{
		BaseObject oResult = defaultVal;
		
		try {
			if( jsonObj.has(key) ) { 
				JSONObject oJSONResult = jsonObj.getJSONObject(key);

				if(oResult != null) {
					if(oResult.getBaseObjectType() == AppDefs.BASE_OBJTYPE_BREED) {
						((Breed)oResult).init(oJSONResult);						
					}
					else if(oResult.getBaseObjectType() == AppDefs.BASE_OBJTYPE_BREEDIMAGE) {
						((BreedImage)oResult).init(oJSONResult);						
					}
					else if(oResult.getBaseObjectType() == AppDefs.BASE_OBJTYPE_BREEDWEIGHT) {
						((BreedWeight)oResult).init(oJSONResult);						
					}
					else if(oResult.getBaseObjectType() == AppDefs.BASE_OBJTYPE_IMAGE) {
						((Image)oResult).init(oJSONResult);						
					}
				}
			}
		}
		catch(Exception e) { }

		return oResult;
	}
	
	public static JSONArray safeJSONArrFromJSON(JSONObject jsonObj, String key)
	{
		JSONArray jsonArrResult = null;
		
		try {
			if( jsonObj.has(key) ) 
				jsonArrResult = jsonObj.getJSONArray(key);
		}
		catch(Exception e) { }

		return jsonArrResult;
	}
	
	public static JSONObject safeJSONObjFromJSON(JSONObject jsonObj, String key, JSONObject defaultVal)
	{
		JSONObject oJSONResult = defaultVal;
		
		try {
			if( jsonObj.has(key) ) 
				oJSONResult = jsonObj.getJSONObject(key);
		}
		catch(Exception e) { }

		return oJSONResult;
	}
	
	public static String safeStrFromJSON(JSONObject jsonObj, String key, String defaultVal)
	{
		String strResult = defaultVal;
		
		try {
			if( jsonObj.has(key) ) 
				strResult = jsonObj.getString(key);
		}
		catch(Exception e) { }

		return strResult;
	}
	
	public static Integer safeIntFromJSON(JSONObject jsonObj, String key, Integer defaultVal)
	{
		Integer result = defaultVal;
		
		try {
			if( jsonObj.has(key) ) 
				result = jsonObj.getInt(key);
		}
		catch(Exception e) { }

		return result;
	}
	
	public static Double safeDblFromJSON(JSONObject jsonObj, String key, Double defaultVal)
	{
		Double result = defaultVal;
		
		try {
			if( jsonObj.has(key) ) 
				result = jsonObj.getDouble(key);
		}
		catch(Exception e) { }

		return result;
	}
	
}
