/*
 * Copyright(C) TLMV Consultoria e Sistemas Ltda. Todos os direitos reservados.
 *
 * BreedImage.java
 * Autor: Luiz Marcio Faria de Aquino Viana, 24/10/2022
 * revisoes: ...
 *
 */

package br.com.tlmv.thecatapicase1.data;

import org.json.JSONObject;

import br.com.tlmv.thecatapicase1.AppDefs;
import br.com.tlmv.thecatapicase1.AppMain;

// "image":{
//   "id":"0XYvRd7oD",
//   "width":1204,
//   "height":1445,
//   "url":"https://cdn2.thecatapi.com/images/0XYvRd7oD.jpg" }
public class BreedImage extends BaseObject
{
//Private
	private static final long serialVersionUID = 202210250925L;

	private String id;
	private Integer	width;
	private Integer height;
	private String url;
	
//Public
	
	public BreedImage() {
		super(AppDefs.BASE_OBJTYPE_BREEDIMAGE, "");
		
		this.id = "-1";
		this.width = 0;
		this.height = 0;
		this.url = "";
	}

	public BreedImage(
		String id,
		Integer	width,
		Integer height,
		String url) {
		super();
		
		this.id = id;
		this.width = width;
		this.height = height;
		this.url = url;
		
		this.baseInit(AppDefs.BASE_OBJTYPE_BREEDIMAGE, this.url);
	}

	public BreedImage(String strJSON)
		throws Exception
	{
		JSONObject jsonObj = new JSONObject(strJSON);
		this.fromJSONObject(jsonObj);
	}

	public BreedImage(JSONObject jsonObj)
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
			"\"image\":{ " +
				"\"id\":\"" + this.id + "\"," +
				"\"width\":" + this.width + "," +
				"\"height\":" + this.height + "," +
				"\"url\":\"" + this.url + "\"" +
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
		this.id = jsonObj.getString("id");
		this.width = jsonObj.getInt("width");
		this.height = jsonObj.getInt("height");
		this.url = jsonObj.getString("url");
		
		this.setBaseDescription(this.url);
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

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
