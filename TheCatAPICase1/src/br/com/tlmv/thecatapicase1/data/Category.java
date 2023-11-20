/*
 * Copyright(C) TLMV Consultoria e Sistemas Ltda. Todos os direitos reservados.
 *
 * Category.java
 * Autor: Luiz Marcio Faria de Aquino Viana, 25/10/2022
 * revisoes: ...
 *
 */

package br.com.tlmv.thecatapicase1.data;

import org.json.JSONObject;

import br.com.tlmv.thecatapicase1.AppDefs;
import br.com.tlmv.thecatapicase1.AppMain;
import br.com.tlmv.thecatapicase1.utils.JSONUtil;

// {
//   "id":5,
//   "name":"boxes"
// }
public class Category extends BaseObject
{
//Private
	private String id;
	private String name;

//Public
	
	public Category() {
		super(AppDefs.BASE_OBJTYPE_CATEGORY, "");
				
		this.id = "-1";
		this.name = "";
	}

	public Category(
		String id,
		String name) {
		super();
		
		this.id = id;
		this.name = name;

		this.baseInit(AppDefs.BASE_OBJTYPE_CATEGORY, name);
	}

	public Category(String strJSON)
		throws Exception
	{
		JSONObject jsonObj = new JSONObject(strJSON);
		this.fromJSONObject(jsonObj);
	}

	public Category(JSONObject jsonObj)
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
		String strJSON = "{ " +
			"\"id\":\"" + this.id + "\"," +
			"\"name\":\"" + this.name + "\" }";
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
		this.id = JSONUtil.safeStrFromJSON(jsonObj, "id", "-1");
		this.name = JSONUtil.safeStrFromJSON(jsonObj, "name", "");
		
		this.setBaseDescription(name);
	}

	@Override
	public void debug(int debugLevel) {
		if( !AppMain.getApp().getErr().checkDebugLevel(debugLevel) ) return;
		
		String strJSON = this.toStrJSON(); 
		AppMain.getApp().getErr().writeDebug(this.getClass().getName(), "debug", strJSON);
	}

	/* Getters/Setters */

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
			
}
