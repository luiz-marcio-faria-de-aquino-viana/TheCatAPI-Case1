/*
 * Copyright(C) TLMV Consultoria e Sistemas Ltda. Todos os direitos reservados.
 *
 * DataCreatorCatsWithGlasses.java
 * Autor: Luiz Marcio Faria de Aquino Viana, 25/10/2022
 * revisoes: ...
 *
 */

package br.com.tlmv.thecatapicase1.datacreator;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.tlmv.thecatapicase1.AppContext;
import br.com.tlmv.thecatapicase1.AppDataManager;
import br.com.tlmv.thecatapicase1.AppDefs;
import br.com.tlmv.thecatapicase1.AppError;
import br.com.tlmv.thecatapicase1.AppMain;
import br.com.tlmv.thecatapicase1.data.BaseObject;
import br.com.tlmv.thecatapicase1.data.Breed;
import br.com.tlmv.thecatapicase1.data.Category;
import br.com.tlmv.thecatapicase1.data.Image;
import br.com.tlmv.thecatapicase1.nosql.BreedTable;
import br.com.tlmv.thecatapicase1.nosql.CategoryTable;
import br.com.tlmv.thecatapicase1.nosql.CatsWithGlassesTable;
import br.com.tlmv.thecatapicase1.nosql.CatsWithHatTable;
import br.com.tlmv.thecatapicase1.nosql.ImageTable;
import br.com.tlmv.thecatapicase1.services.BreedService;
import br.com.tlmv.thecatapicase1.services.ImageService;

public class DataCreatorCatsWithGlasses extends DataCreatorBase
{
//Private
	private int szTblCatsWithGlasses = -1;
		
//Public
	public static final String DATA_CREATOR_NAME = "DATA_CREATOR_CATS_WITH_GLASSES";
		
	public DataCreatorCatsWithGlasses() {
		super(DataCreatorCatsWithGlasses.DATA_CREATOR_NAME);
	}

	/* Methodes */
	
	public boolean dataCreatorForCatsWithGlasses() {
		boolean bResult = false;
		
		try {
			AppMain.getApp().getErr().writeLog(this.getClass().getName(), "dataCreatorForCatsWithGlasses", "Starting data creator for all CatsWithGlasses...");
			
			AppError err = AppMain.getApp().getErr();
			AppDataManager dataMan = AppMain.getApp().getDataMan(); 
			
			ImageService service = new ImageService();

			CategoryTable tblCategory = dataMan.getTblCategory();
			CatsWithGlassesTable tblCatsWithGlasses = dataMan.getTblCatsWithGlasses();
			
			BaseObject obj = tblCategory.getObj(Integer.toString(AppDefs.DEF_CATEGORY_SUNGLASSES));
			if(obj != null) {
				Category oCategory = (Category)obj;

				String dbgmsg = String.format("Category: %s...", oCategory.getName());
				err.writeDebug(this.getClass().getName(), "dataCreatorForCatsWithGlasses", dbgmsg);
				
				JSONArray aJSON = service.requestImagesByCategoryIdsLimit100JSON(AppDefs.WS_MAX_SEARCH_LIMIT, oCategory.getId());
				for(int i = 0; i < aJSON.length(); i++) {
					JSONObject jsonObj = (JSONObject)aJSON.get(i);
					
					Image oImage = new Image(jsonObj);
					if( !tblCatsWithGlasses.existObj(oImage.getId()) )
						tblCatsWithGlasses.putObj(oImage);
				}
			}
						
			this.szTblCatsWithGlasses = tblCatsWithGlasses.getTableSize();
			
			String dbgmsg = "CatsWithGlassesTableSize = " + this.szTblCatsWithGlasses; 
			err.writeDebug(this.getClass().getName(), "dataCreatorForCatsWithGlasses", dbgmsg);
			
			tblCatsWithGlasses.debugAll(AppDefs.DEBUG_LEVEL_DEBUG);
			tblCatsWithGlasses.saveData();
			
			bResult = true;
		}
		catch(Exception e) {
			AppMain.getApp().getErr().writeError(this.getClass().getName(), "dataCreatorForCatsWithGlasses", e.getMessage());
			e.printStackTrace();
		}

		AppMain.getApp().getErr().writeLog(this.getClass().getName(), "dataCreatorForCatsWithGlasses", "Data creator for all CatsWithGlasses finished!");
		return bResult;
	}
	
	public boolean dataCreatorForDownloadCatsWithGlassesFromURL() {
		boolean bResult = false;
		
		try {
			AppMain.getApp().getErr().writeLog(this.getClass().getName(), "dataCreatorForDownloadCatsWithGlassesFromURL", "Starting download CatsWithGlasses images...");
			
			AppContext ctx = AppMain.getApp().getContext();
			AppDataManager dataMan = AppMain.getApp().getDataMan(); 

			ImageService service = new ImageService();

			CatsWithGlassesTable tblCatsWithGlasses = dataMan.getTblCatsWithGlasses();
			
			Hashtable<String,BaseObject> tblCatsWithGlassesData = tblCatsWithGlasses.getTableData();
			Collection<BaseObject> lsCatsWithGlassesData = tblCatsWithGlassesData.values();
			for(BaseObject obj : lsCatsWithGlassesData) {
				boolean bResultImage = false;
				
				Image oImage = (Image)obj;
				oImage.debug(AppDefs.DEBUG_LEVEL_DEBUG);
				
				try {
					java.awt.Image oImageJPG = service.requestDownloadImageFromURL(oImage.getUrl(), ctx.getDataCatsWithGlassesDir(), oImage.getLocalFile());
					if(oImageJPG != null) {
						bResultImage = true;
					}
				}
				catch(Exception e) {
					AppMain.getApp().getErr().writeError(this.getClass().getName(), "dataCreatorForDownloadImageFromURL", e.getMessage());
					e.printStackTrace();					
				}
				
				if( !bResultImage ) {
					try {
						byte[] oImageJPG = service.requestDownloadImageFromEx(oImage.getUrl(), ctx.getDataCatsWithGlassesDir(), oImage.getLocalFile());
						if(oImageJPG != null) {
							bResultImage = true;
						}
						else {
							AppMain.getApp().getErr().writeError(this.getClass().getName(), "dataCreatorForDownloadImageFromURL", "Can't download image: " + oImage.getUrl());
						}
					}
					catch(Exception e) {
						AppMain.getApp().getErr().writeError(this.getClass().getName(), "dataCreatorForDownloadImageFromURL", e.getMessage());
						e.printStackTrace();					
					}					
				}
			}
			
			bResult = true;
		}
		catch(Exception e) {
			AppMain.getApp().getErr().writeError(this.getClass().getName(), "dataCreatorForDownloadCatsWithGlassesFromURL", e.getMessage());
			e.printStackTrace();
		}

		AppMain.getApp().getErr().writeLog(this.getClass().getName(), "dataCreatorForDownloadCatsWithGlassesFromURL", "Download CatsWithGlasses images finished!");
		return bResult;
	}

	/* EXECUTE */
	
	@Override
	public boolean executeDataCreator() {
		if( !this.dataCreatorForCatsWithGlasses() )
			return false;
		
		if( !this.dataCreatorForDownloadCatsWithGlassesFromURL() )
			return false;

		return true;
	}
	
}
