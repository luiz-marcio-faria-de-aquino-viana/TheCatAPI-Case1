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
import br.com.tlmv.thecatapicase1.nosql.ImageTable;
import br.com.tlmv.thecatapicase1.services.BreedService;
import br.com.tlmv.thecatapicase1.services.ImageService;

public class DataCreatorImage extends DataCreatorBase
{
//Private
	private int szTblImage = -1;
		
//Public
	public static final String DATA_CREATOR_NAME = "DATA_CREATOR_IMAGE";
		
	public DataCreatorImage() {
		super(DataCreatorImage.DATA_CREATOR_NAME);
	}

	/* Methodes */
	
	public boolean dataCreatorForAllImagesByBreed() {
		boolean bResult = false;
		
		try {
			AppMain.getApp().getErr().writeLog(this.getClass().getName(), "dataCreatorForAllImagesByBreed", "Starting data creator for all Images by breed...");

			AppError err = AppMain.getApp().getErr();
			AppDataManager dataMan = AppMain.getApp().getDataMan(); 
			
			ImageService service = new ImageService();

			BreedTable tblBreed = dataMan.getTblBreed();
			ImageTable tblImage = dataMan.getTblImage();
			
			Hashtable<String,BaseObject> tblBreedData = tblBreed.getTableData();
			Collection<BaseObject> lsBreedData = tblBreedData.values();
			for(BaseObject obj : lsBreedData) {
				Breed oBreed = (Breed)obj;

				String dbgmsg = String.format("Breed: %s...", oBreed.getName());
				err.writeDebug(this.getClass().getName(), "dataCreatorForAllImagesByBreed", dbgmsg);
				
				JSONArray aJSON = service.requestImagesByBreedIdsLimit100JSON(AppDefs.WS_MAX_SEARCH_LIMIT, oBreed.getId());
				for(int i = 0; i < aJSON.length(); i++) {
					JSONObject jsonObj = (JSONObject)aJSON.get(i);
					
					Image oImage = new Image(jsonObj);
					tblImage.putObj(oImage);
				}
			}
						
			this.szTblImage = tblImage.getTableSize();
			
			String dbgmsg = "ImageTableSize = " + this.szTblImage; 
			err.writeDebug(this.getClass().getName(), "dataCreatorForAllImagesByBreed", dbgmsg);
			
			tblImage.debugAll(AppDefs.DEBUG_LEVEL_DEBUG);
			tblImage.saveData();
			
			bResult = true;
		}
		catch(Exception e) {
			AppMain.getApp().getErr().writeError(this.getClass().getName(), "dataCreatorForAllImagesByBreed", e.getMessage());
			e.printStackTrace();
		}

		AppMain.getApp().getErr().writeLog(this.getClass().getName(), "dataCreatorForAllImagesByBreed", "Data creator for all Images by breed finished!");
		return bResult;
	}
	
	public boolean dataCreatorForAllImagesByCategory() {
		boolean bResult = false;
		
		try {
			AppMain.getApp().getErr().writeLog(this.getClass().getName(), "dataCreatorForAllImagesByCategory", "Starting data creator for all Images by category...");
			
			AppContext ctx = AppMain.getApp().getContext();
			AppError err = AppMain.getApp().getErr();
			AppDataManager dataMan = AppMain.getApp().getDataMan(); 

			ImageService service = new ImageService();

			CategoryTable tblCategory = dataMan.getTblCategory();
			ImageTable tblImage = dataMan.getTblImage();
			
			Hashtable<String,BaseObject> tblCategoryData = tblCategory.getTableData();
			Collection<BaseObject> lsCategoryData = tblCategoryData.values();
			for(BaseObject obj : lsCategoryData) {
				Category oCategory = (Category)obj;

				String dbgmsg = String.format("Category: %s...", oCategory.getName());
				err.writeDebug(this.getClass().getName(), "dataCreatorForAllImagesByCategory", dbgmsg);
				
				JSONArray aJSON = service.requestImagesByCategoryIdsLimit100JSON(AppDefs.WS_MAX_SEARCH_LIMIT, oCategory.getId());
				for(int i = 0; i < aJSON.length(); i++) {
					JSONObject jsonObj = (JSONObject)aJSON.get(i);
					
					Image oImage = new Image(jsonObj);
					if( !tblImage.existObj(oImage.getId()) )
						tblImage.putObj(oImage);
				}
			}
						
			this.szTblImage = tblImage.getTableSize();
			
			String dbgmsg = "ImageTableSize = " + this.szTblImage; 
			err.writeDebug(this.getClass().getName(), "dataCreatorForAllImagesByCategory", dbgmsg);
			
			tblImage.debugAll(AppDefs.DEBUG_LEVEL_DEBUG);
			tblImage.saveData();
			
			bResult = true;
		}
		catch(Exception e) {
			AppMain.getApp().getErr().writeError(this.getClass().getName(), "dataCreatorForAllImagesByCategory", e.getMessage());
			e.printStackTrace();
		}

		AppMain.getApp().getErr().writeLog(this.getClass().getName(), "dataCreatorForAllImagesByCategory", "Download Images by category finished!");
		return bResult;
	}
	
	public boolean dataCreatorForDownloadImageFromURL() {
		boolean bResult = false;
		
		try {
			AppMain.getApp().getErr().writeLog(this.getClass().getName(), "dataCreatorForDownloadImageFromURL", "Starting download all Images...");

			AppContext ctx = AppMain.getApp().getContext();
			AppDataManager dataMan = AppMain.getApp().getDataMan(); 

			ImageService service = new ImageService();

			ImageTable tblImage = dataMan.getTblImage();
			Hashtable<String,BaseObject> tblImageData = tblImage.getTableData();
			Collection<BaseObject> lsImageData = tblImageData.values();
			for(BaseObject obj : lsImageData) {
				boolean bResultImage = false;
				
				Image oImage = (Image)obj;
				oImage.debug(AppDefs.DEBUG_LEVEL_DEBUG);
			
				if( "p2U4ZXgKL".equals(oImage.getId()) ) {
					AppMain.getApp().getErr().writeDebug(this.getClass().getName(), "dataCreatorForDownloadImageFromURL", "Downloading image with UnsupportedImageFormat exception: " + oImage.getUrl());
				}
				
				try {
					java.awt.Image oImageJPG = service.requestDownloadImageFromURL(oImage.getUrl(), ctx.getDataImagesDir(), oImage.getLocalFile());
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
						byte[] oImageJPG = service.requestDownloadImageFromEx(oImage.getUrl(), ctx.getDataImagesDir(), oImage.getLocalFile());
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
			AppMain.getApp().getErr().writeError(this.getClass().getName(), "dataCreatorForDownloadImageFromURL", e.getMessage());
			e.printStackTrace();
		}

		AppMain.getApp().getErr().writeLog(this.getClass().getName(), "dataCreatorForDownloadImageFromURL", "Download all Images finished!");
		return bResult;
	}

	/* EXECUTE */
	
	@Override
	public boolean executeDataCreator() {
		if( !this.dataCreatorForAllImagesByBreed() )
			return false;

		//if( !this.dataCreatorForAllImagesByCategory() )
		//	return false;
		
		if( !this.dataCreatorForDownloadImageFromURL() )
			return false;
		
		return true;
	}
	
}
