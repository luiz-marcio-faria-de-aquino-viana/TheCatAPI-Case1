/*
 * Copyright(C) TLMV Consultoria e Sistemas Ltda. Todos os direitos reservados.
 *
 * DataCreatorBase.java
 * Autor: Luiz Marcio Faria de Aquino Viana, 25/10/2022
 * revisoes: ...
 *
 */

package br.com.tlmv.thecatapicase1.datacreator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.tlmv.thecatapicase1.AppDefs;
import br.com.tlmv.thecatapicase1.AppError;
import br.com.tlmv.thecatapicase1.AppMain;

public class DataCreatorBase 
{
//Private
	private String dataCreatorName = "DATACREATOR";
	
//Public
	
	public DataCreatorBase(String dataCreatorName) {
		this.dataCreatorName = dataCreatorName;
	}

	/* Methodes */

	public boolean executeDataCreator() {
		String errmsg = String.format(AppError.ERR_DATA_CREATOR_NOT_IMPLEMENTED, this.dataCreatorName);
		AppMain.getApp().getErr().writeError(this.getClass().getName(), "dataCreatorForAllBreeds", errmsg);
		return false;
	}

	/* Getters/Setters */
	
	public String getDataCreatorName() {
		return dataCreatorName;
	}

	public void setDataCreatorName(String dataCreatorName) {
		this.dataCreatorName = dataCreatorName;
	}

}
