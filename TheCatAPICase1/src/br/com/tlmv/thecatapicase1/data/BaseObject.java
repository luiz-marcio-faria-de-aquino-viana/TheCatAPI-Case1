/*
 * Copyright(C) TLMV Consultoria e Sistemas Ltda. Todos os direitos reservados.
 *
 * BaseObject.java
 * Autor: Luiz Marcio Faria de Aquino Viana, 24/10/2022
 * revisoes: ...
 *
 */

package br.com.tlmv.thecatapicase1.data;

import java.io.Serializable;

import org.json.JSONObject;

import br.com.tlmv.thecatapicase1.AppDefs;
import br.com.tlmv.thecatapicase1.utils.UuidUtil;

public class BaseObject implements Serializable
{
//Private
	private static final long serialVersionUID = 202210250904L;

	private String baseObjectId = AppDefs.BASE_OBJID_NONE;
	private Integer baseObjectType = AppDefs.BASE_OBJTYPE_NONE;
	private String baseDescription = "";
	
//Public
	
	public BaseObject() {
		this.baseInit(UuidUtil.generateUUID(), AppDefs.BASE_OBJTYPE_NONE, "");
	}

	public BaseObject(Integer objectType) {
		this.baseInit(baseObjectType, "");
	}

	public BaseObject(Integer baseObjectType, String baseDescription) {
		this.baseInit(baseObjectType, baseDescription);
	}

	public BaseObject(String baseObjectId, Integer baseObjectType, String baseDescription) {
		this.baseInit(baseObjectId, baseObjectType, baseDescription);
	}
	
	/* Methodes */
	
	public void baseInit(Integer baseObjectType, String baseDescription) {
		this.baseObjectId = UuidUtil.generateUUID();
		this.baseObjectType = baseObjectType;
		this.baseDescription = baseDescription;		
	}
	
	public void baseInit(String baseObjectId, Integer baseObjectType, String baseDescription) {
		this.baseObjectId = baseObjectId;
		this.baseObjectType = baseObjectType;
		this.baseDescription = baseDescription;		
	}
	
	public void init(JSONObject jsonObj) {
		/* nothing todo! */
	}

	@Override
	public String toString() {
		return this.baseDescription;
	}
	
	public void debug(int debugLevel) {
		/* nothing todo! */
	}

	/* Getters/Setters */

	public String getBaseObjectId() {
		return baseObjectId;
	}

	public void setBaseObjectId(String baseObjectId) {
		this.baseObjectId = baseObjectId;
	}

	public String getBaseDescription() {
		return baseDescription;
	}

	public void setBaseDescription(String baseDescription) {
		this.baseDescription = baseDescription;
	}

	public Integer getBaseObjectType() {
		return baseObjectType;
	}

	public void setBaseObjectType(Integer baseObjectType) {
		this.baseObjectType = baseObjectType;
	}
	
}
