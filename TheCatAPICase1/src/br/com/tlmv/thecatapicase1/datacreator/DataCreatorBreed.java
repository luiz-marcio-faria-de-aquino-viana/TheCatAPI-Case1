/*
 * Copyright(C) TLMV Consultoria e Sistemas Ltda. Todos os direitos reservados.
 *
 * DataCreatorBreed.java
 * Autor: Luiz Marcio Faria de Aquino Viana, 25/10/2022
 * revisoes: ...
 *
 */

package br.com.tlmv.thecatapicase1.datacreator;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.tlmv.thecatapicase1.AppContext;
import br.com.tlmv.thecatapicase1.AppDataManager;
import br.com.tlmv.thecatapicase1.AppDefs;
import br.com.tlmv.thecatapicase1.AppError;
import br.com.tlmv.thecatapicase1.AppMain;
import br.com.tlmv.thecatapicase1.data.Breed;
import br.com.tlmv.thecatapicase1.nosql.BreedTable;
import br.com.tlmv.thecatapicase1.services.BreedService;

public class DataCreatorBreed extends DataCreatorBase
{
//Private
	private int szTblBreed = -1;
		
//Public
	public static final String DATA_CREATOR_NAME = "DATA_CREATOR_BREED";
		
	public DataCreatorBreed() {
		super(DataCreatorBreed.DATA_CREATOR_NAME);
	}

	/* Methodes */
	
	public boolean dataCreatorForAllBreeds() {
		boolean bResult = false;
		
		try {
			AppMain.getApp().getErr().writeLog(this.getClass().getName(), "dataCreatorForAllBreeds", "Starting data creator for all Breeds...");

			AppError err = AppMain.getApp().getErr();
			AppDataManager dataMan = AppMain.getApp().getDataMan(); 
			
			BreedService service = new BreedService();
			JSONArray aJSON = service.requestAllBreedsJSON();
	
			BreedTable tblBreed = dataMan.getTblBreed();
			for(int i = 0; i < aJSON.length(); i++) {
				JSONObject jsonObj = (JSONObject)aJSON.get(i);
				
				Breed oBreed = new Breed(jsonObj);
				tblBreed.putObj(oBreed);
			}
			
			this.szTblBreed = tblBreed.getTableSize();
			
			String dbgmsg = "BreedTableSize = " + this.szTblBreed; 
			err.writeDebug(this.getClass().getName(), "dataCreatorForAllBreeds", dbgmsg);
						
			tblBreed.debugAll(AppDefs.DEBUG_LEVEL_DEBUG);
			tblBreed.saveData();
			
			bResult = true;
		}
		catch(Exception e) {
			AppMain.getApp().getErr().writeError(this.getClass().getName(), "dataCreatorForAllBreeds", e.getMessage());
			e.printStackTrace();
		}

		AppMain.getApp().getErr().writeLog(this.getClass().getName(), "dataCreatorForAllBreeds", "Data creator for all Breeds finished!");
		return bResult;
	}

	/* EXECUTE */
	
	@Override
	public boolean executeDataCreator() {
		if( !this.dataCreatorForAllBreeds() )
			return false;
		return true;
	}
	
}
