/*
 * Copyright(C) TLMV Consultoria e Sistemas Ltda. Todos os direitos reservados.
 *
 * DataCreatorCategory.java
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
import br.com.tlmv.thecatapicase1.data.Category;
import br.com.tlmv.thecatapicase1.nosql.CategoryTable;
import br.com.tlmv.thecatapicase1.services.BreedService;
import br.com.tlmv.thecatapicase1.services.CategoryService;

public class DataCreatorCategory extends DataCreatorBase
{
//Private
	private int szTblCategory = -1;
	
//Public
	public static final String DATA_CREATOR_NAME = "DATA_CREATOR_CATEGORY";
		
	public DataCreatorCategory() {
		super(DataCreatorCategory.DATA_CREATOR_NAME);
	}

	/* Methodes */
	
	public boolean dataCreatorForAllCategories() {
		boolean bResult = false;
		
		try {
			AppMain.getApp().getErr().writeLog(this.getClass().getName(), "dataCreatorForAllCategories", "Starting data creator for all Categories...");
			
			AppError err = AppMain.getApp().getErr();
			AppDataManager dataMan = AppMain.getApp().getDataMan(); 
			
			CategoryService service = new CategoryService();
			JSONArray aJSON = service.requestAllCategoriesJSON();
	
			CategoryTable tblCategory = dataMan.getTblCategory();
			for(int i = 0; i < aJSON.length(); i++) {
				JSONObject jsonObj = (JSONObject)aJSON.get(i);
				
				Category oCategory = new Category(jsonObj);
				tblCategory.putObj(oCategory);

				String dbgmsg = String.format("Category: %s...", oCategory.getName());
				err.writeDebug(this.getClass().getName(), "dataCreatorForAllCategories", dbgmsg);
			}			
			this.szTblCategory = tblCategory.getTableSize();
			
			String dbgmsg = "CategoryTableSize = " + this.szTblCategory; 
			err.writeDebug(this.getClass().getName(), "dataCreatorForAllCategories", dbgmsg);
			
			tblCategory.debugAll(AppDefs.DEBUG_LEVEL_DEBUG);
			tblCategory.saveData();
			
			bResult = true;
		}
		catch(Exception e) {
			AppMain.getApp().getErr().writeError(this.getClass().getName(), "dataCreatorForAllCategories", e.getMessage());
			e.printStackTrace();
		}

		AppMain.getApp().getErr().writeLog(this.getClass().getName(), "dataCreatorForAllCategories", "Data creator for all Categories finished!");
		return bResult;
	}

	/* EXECUTE */
	
	@Override
	public boolean executeDataCreator() {
		if( !this.dataCreatorForAllCategories() )
			return false;
		return true;
	}
	
}
