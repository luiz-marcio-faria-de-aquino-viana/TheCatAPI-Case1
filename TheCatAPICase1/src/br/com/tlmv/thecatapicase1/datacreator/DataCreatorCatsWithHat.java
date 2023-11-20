/*
 * Copyright(C) TLMV Consultoria e Sistemas Ltda. Todos os direitos reservados.
 *
 * DataCreatorImage.java
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
import br.com.tlmv.thecatapicase1.nosql.CatsWithHatTable;
import br.com.tlmv.thecatapicase1.nosql.ImageTable;
import br.com.tlmv.thecatapicase1.services.BreedService;
import br.com.tlmv.thecatapicase1.services.ImageService;

public class DataCreatorCatsWithHat extends DataCreatorBase
{
//Private
	private int szTbltblCatsWithHat = -1;
		
//Public
	public static final String DATA_CREATOR_NAME = "DATA_CREATOR_CATS_WITH_HAT";
		
	public DataCreatorCatsWithHat() {
		super(DataCreatorCatsWithHat.DATA_CREATOR_NAME);
	}

	/* Methodes */
	
	public boolean dataCreatorForCatsWithHat() {
		boolean bResult = false;
		
		try {
			AppMain.getApp().getErr().writeLog(this.getClass().getName(), "dataCreatorForCatsWithHat", "Starting data creator for all CatsWithHat...");
			
			AppError err = AppMain.getApp().getErr();
			AppDataManager dataMan = AppMain.getApp().getDataMan(); 
			
			ImageService service = new ImageService();

			CategoryTable tblCategory = dataMan.getTblCategory();
			CatsWithHatTable tblCatsWithHat = dataMan.getTblCatsWithHat();
			
			Hashtable<String,BaseObject> tblCategoryData = tblCategory.getTableData();
			BaseObject obj = tblCategory.getObj(Integer.toString(AppDefs.DEF_CATEGORY_HATS));
			if(obj != null) {
				Category oCategory = (Category)obj;
				
				String dbgmsg = String.format("Category: %s...", oCategory.getName());
				err.writeDebug(this.getClass().getName(), "dataCreatorForCatsWithHat", dbgmsg);

				JSONArray aJSON = service.requestImagesByCategoryIdsLimit100JSON(AppDefs.WS_MAX_SEARCH_LIMIT, oCategory.getId());
				for(int i = 0; i < aJSON.length(); i++) {
					JSONObject jsonObj = (JSONObject)aJSON.get(i);
					
					Image oImage = new Image(jsonObj);
					if( !tblCatsWithHat.existObj(oImage.getId()) )
						tblCatsWithHat.putObj(oImage);
				}
			}
						
			this.szTbltblCatsWithHat = tblCatsWithHat.getTableSize();
			
			String dbgmsg = "CatsWithHatTableSize = " + this.szTbltblCatsWithHat; 
			err.writeDebug(this.getClass().getName(), "dataCreatorForCatsWithHat", dbgmsg);
			
			tblCatsWithHat.debugAll(AppDefs.DEBUG_LEVEL_DEBUG);
			tblCatsWithHat.saveData();
			
			bResult = true;
		}
		catch(Exception e) {
			AppMain.getApp().getErr().writeError(this.getClass().getName(), "dataCreatorForCatsWithHat", e.getMessage());
			e.printStackTrace();
		}

		AppMain.getApp().getErr().writeLog(this.getClass().getName(), "dataCreatorForCatsWithHat", "Data creator for all CatsWithHat finished!");
		return bResult;
	}
	
	public boolean dataCreatorForDownloadCatsWithHatFromURL() {
		boolean bResult = false;
		
		try {
			AppMain.getApp().getErr().writeLog(this.getClass().getName(), "dataCreatorForDownloadCatsWithHatFromURL", "Starting download CatsWithHat images...");
			
			AppContext ctx = AppMain.getApp().getContext();
			AppDataManager dataMan = AppMain.getApp().getDataMan(); 
			
			ImageService service = new ImageService();

			CatsWithHatTable tblCatsWithHat = dataMan.getTblCatsWithHat();
			
			Hashtable<String,BaseObject> tblCatsWithHatData = tblCatsWithHat.getTableData();
			Collection<BaseObject> lsCatsWithHatData = tblCatsWithHatData.values();
			for(BaseObject obj : lsCatsWithHatData) {
				boolean bResultImage = false;

				Image oImage = (Image)obj;
				oImage.debug(AppDefs.DEBUG_LEVEL_DEBUG);
				
				try {
					java.awt.Image oImageJPG = service.requestDownloadImageFromURL(oImage.getUrl(), ctx.getDataCatsWithHatDir(), oImage.getLocalFile());
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
						byte[] oImageJPG = service.requestDownloadImageFromEx(oImage.getUrl(), ctx.getDataCatsWithHatDir(), oImage.getLocalFile());
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
			AppMain.getApp().getErr().writeError(this.getClass().getName(), "dataCreatorForDownloadCatsWithHatFromURL", e.getMessage());
			e.printStackTrace();
		}

		AppMain.getApp().getErr().writeLog(this.getClass().getName(), "dataCreatorForDownloadCatsWithHatFromURL", "Download CatsWithHat images finished!");
		return bResult;
	}

	/* EXECUTE */
	
	@Override
	public boolean executeDataCreator() {
		if( !this.dataCreatorForCatsWithHat() )
			return false;
		
		if( !this.dataCreatorForDownloadCatsWithHatFromURL() )
			return false;

		return true;
	}
	
}
