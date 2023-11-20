/*
 * Copyright(C) TLMV Consultoria e Sistemas Ltda. Todos os direitos reservados.
 *
 * BreedWeight.java
 * Autor: Luiz Marcio Faria de Aquino Viana, 24/10/2022
 * revisoes: ...
 *
 */

package br.com.tlmv.thecatapicase1.data;

import org.json.JSONObject;

import br.com.tlmv.thecatapicase1.AppDefs;
import br.com.tlmv.thecatapicase1.AppMain;

// "weight":{
//	 "imperial":"7  -  10",
//	 "metric":"3 - 5"
// }
public class BreedWeight extends BaseObject
{
//Private
	private static final long serialVersionUID = 202210250919L;

	private String imperial;
	private String metric;

//Public
	
	public BreedWeight() {
		super(AppDefs.BASE_OBJTYPE_BREEDWEIGHT, "");
		
		this.imperial = "";
		this.metric = "";
	}

	public BreedWeight(String imperial, String metric) {
		super();
		
		this.imperial = imperial;
		this.metric = metric;
		
		this.baseInit(AppDefs.BASE_OBJTYPE_BREEDWEIGHT, this.metric);
	}

	public BreedWeight(String strJSON)
		throws Exception
	{
		JSONObject jsonObj = new JSONObject(strJSON);
		this.fromJSONObject(jsonObj);
	}

	public BreedWeight(JSONObject jsonObj)
		throws Exception
	{
		this.fromJSONObject(jsonObj);
	}
	
	/* Methodes */
	
	@Override
	public void init(JSONObject jsonObj) {
		try {
			this.fromJSONObject(jsonObj);
		}
		catch(Exception e) {
			AppMain.getApp().getErr().writeError(this.getClass().getName(), "init", e.getMessage());
			e.printStackTrace();
		}
	}
	
	public String toStrJSON() {
		String strJSON = 
			"\"weight\":{ " +
				"\"imperial\":\"" + this.imperial + "\"," +
				"\"metric\":\"" + this.metric + "\"" +
			" }";
		return strJSON;
	}

	public void fromStrJSON(String strJSON)
		throws Exception
	{
		JSONObject jsonObj = new JSONObject(strJSON);
		this.fromJSONObject(jsonObj);
	}
	
	public JSONObject toJSON() 
		throws Exception
	{
		String strJSON = this.toStrJSON();
		JSONObject jsonObj = new JSONObject(strJSON);
		return jsonObj;
	}

	public void fromJSONObject(JSONObject jsonObj)
		throws Exception
	{
		this.imperial = jsonObj.getString("imperial");
		this.metric = jsonObj.getString("metric");
		
		this.setBaseDescription(this.metric);
	}

	@Override
	public void debug(int debugLevel) {
		if( !AppMain.getApp().getErr().checkDebugLevel(debugLevel) ) return;
		
		String strJSON = this.toStrJSON(); 
		AppMain.getApp().getErr().writeDebug(this.getClass().getName(), "debug", strJSON);
	}
	
	/* Getters/Setters */
	
	public String getImperial() {
		return imperial;
	}

	public void setImperial(String imperial) {
		this.imperial = imperial;
	}

	public String getMetric() {
		return metric;
	}

	public void setMetric(String metric) {
		this.metric = metric;
	}
	
}
