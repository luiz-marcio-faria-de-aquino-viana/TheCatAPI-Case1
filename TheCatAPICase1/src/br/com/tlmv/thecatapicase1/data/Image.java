/*
 * Copyright(C) TLMV Consultoria e Sistemas Ltda. Todos os direitos reservados.
 *
 * Image.java
 * Autor: Luiz Marcio Faria de Aquino Viana, 24/10/2022
 * revisoes: ...
 *
 */

package br.com.tlmv.thecatapicase1.data;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.tlmv.thecatapicase1.AppDefs;
import br.com.tlmv.thecatapicase1.AppMain;
import br.com.tlmv.thecatapicase1.utils.JSONUtil;

// {
//   "id":"0XYvRd7oD",
//   "width":1204,
//   "height":1445,
//   "url":"https://cdn2.thecatapi.com/images/0XYvRd7oD.jpg",
//   "local_file":"/home/lmarcio/Images/0XYvRd7oD.jpg",
//   "breeds":[ {
//     "weight":{"imperial":"7  -  10","metric":"3 - 5"},
//     "id":"abys",
//     "name":"Abyssinian",
//     "temperament":"Active, Energetic, Independent, Intelligent, Gentle",
//     "origin":"Egypt",
//     "country_codes":"EG",
//     "country_code":"EG",
//     "life_span":"14 - 15",
//     "wikipedia_url":"https://en.wikipedia.org/wiki/Abyssinian_(cat)" } ]
// }
public class Image extends BaseObject
{
//Private
	private String id;
	private Integer width;
	private Integer height;
	private String url;
	private String localFile;
	private ArrayList<Breed> lsBreed = new ArrayList<Breed>();

//Public
	
	public Image() {
		super(AppDefs.BASE_OBJTYPE_IMAGE, "");
				
		this.id = "-1";
		this.width = 0;
		this.height = 0;
		this.url = "";
		this.localFile = "";
		this.lsBreed = new ArrayList<Breed>();
	}

	public Image(
		String id,
		Integer width,
		Integer height,
		String url,
		List<Breed> lsBread) {
		super();
		
		this.id = id;
		this.width = width;
		this.height = height;
		this.url = url;
		this.localFile = id + AppDefs.EXT_JPG;
		this.lsBreed.addAll(lsBreed);
		
		this.baseInit(AppDefs.BASE_OBJTYPE_IMAGE, this.url);
	}

	public Image(String strJSON)
		throws Exception
	{
		JSONObject jsonObj = new JSONObject(strJSON);
		this.fromJSONObject(jsonObj);
	}

	public Image(JSONObject jsonObj)
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
	
	public String toStrJSON() 
	{
		String strJSON = "{ " +
			"\"id\":\"" + this.id + "\"," +
			"\"width\":" + Integer.toString(this.width) + "," +
			"\"height\":" + Integer.toString(this.height) + "," +
			"\"url\":\"" + this.url + "\"," +
			"\"local_file\":\"" + this.localFile + "\"," +
			"\"breeds\":[ ";
		
		for(int i = 0; i < this.lsBreed.size(); i++) {
			Breed oBreed = this.lsBreed.get(i);
			if(i == 0)
				strJSON += "{ " + oBreed.toStrJSON() + " }";
			else
				strJSON += ",{ " + oBreed.toStrJSON() + " }";
		}			

		strJSON += " ] }";
		
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
		this.width = JSONUtil.safeIntFromJSON(jsonObj, "width", 0);
		this.height = JSONUtil.safeIntFromJSON(jsonObj, "height", 0);
		this.url = JSONUtil.safeStrFromJSON(jsonObj, "url", "");
		this.localFile = id + AppDefs.EXT_JPG;
		
		JSONArray jsonArrBreed = JSONUtil.safeJSONArrFromJSON(jsonObj, "breeds");
		if(jsonArrBreed != null) {
			int jsonArrBreedSz = jsonArrBreed.length();
			
			for(int i = 0; i < jsonArrBreedSz; i++) {
				JSONObject jsonObjBreed = jsonArrBreed.getJSONObject(i);
				Breed oBreed = new Breed(jsonObjBreed);
				this.lsBreed.add(oBreed);
			}
		}
		
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

	public ArrayList<Breed> getLsBreed() {
		return lsBreed;
	}

	public void setLsBreed(ArrayList<Breed> lsBreed) {
		this.lsBreed = lsBreed;
	}

	public String getLocalFile() {
		return localFile;
	}

	public void setLocalFile(String localFile) {
		this.localFile = localFile;
	}
			
}
