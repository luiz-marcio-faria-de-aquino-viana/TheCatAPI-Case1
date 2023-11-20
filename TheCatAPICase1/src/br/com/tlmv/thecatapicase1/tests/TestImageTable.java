/*
 * Copyright(C) TLMV Consultoria e Sistemas Ltda. Todos os direitos reservados.
 *
 * TestImageTable.java
 * Autor: Luiz Marcio Faria de Aquino Viana, 25/10/2022
 * revisoes: ...
 *
 */

package br.com.tlmv.thecatapicase1.tests;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.tlmv.thecatapicase1.AppContext;
import br.com.tlmv.thecatapicase1.AppDataManager;
import br.com.tlmv.thecatapicase1.AppDefs;
import br.com.tlmv.thecatapicase1.AppMain;
import br.com.tlmv.thecatapicase1.data.Breed;
import br.com.tlmv.thecatapicase1.data.Category;
import br.com.tlmv.thecatapicase1.data.Image;
import br.com.tlmv.thecatapicase1.nosql.BreedTable;
import br.com.tlmv.thecatapicase1.nosql.CategoryTable;
import br.com.tlmv.thecatapicase1.nosql.ImageTable;
import br.com.tlmv.thecatapicase1.services.BreedService;
import br.com.tlmv.thecatapicase1.services.CategoryService;
import br.com.tlmv.thecatapicase1.services.ImageService;

public class TestImageTable extends TestBase
{
//Private
	private int szTblImage = -1;
	
//Public
	public static final String TEST_NAME = "TEST_IMAGE_TABLE";
	
	public TestImageTable() {
		super(TestImageService.TEST_NAME);
	}
	
	/* Methodes */
	
	public boolean testImageTableAddElements() {
		boolean bResult = false;
		
		try {
			AppMain.getApp().getErr().writeUnitTest(this.getClass().getName(), "testImageTableAddElements", "Starting unit test to check Image data table...");
			
			AppDataManager dataMan = AppMain.getApp().getDataMan(); 
			
			ImageService service = new ImageService();
			JSONArray aJSON = service.requestAnyImagesLimit10JSON();
	
			ImageTable tblImage = dataMan.getTblImage();
			for(int i = 0; i < aJSON.length(); i++) {
				JSONObject jsonObj = (JSONObject)aJSON.get(i);
				
				Image oImage = new Image(jsonObj);
				tblImage.putObj(oImage);
			}
			
			this.szTblImage = tblImage.getTableSize();
			
			String warnmsg = "ImageTableSize = " + this.szTblImage; 
			AppMain.getApp().getErr().writeUnitTest(this.getClass().getName(), "testImageTableAddElements", warnmsg);
			
			tblImage.debugAll(AppDefs.DEBUG_LEVEL_DEBUG);
			
			bResult = true;
		}
		catch(Exception e) {
			AppMain.getApp().getErr().writeError(this.getClass().getName(), "testCategoryTableAddElements", e.getMessage());
			e.printStackTrace();
		}

		AppMain.getApp().getErr().writeUnitTestResult(this.getClass().getName(), "testCategoryTableAddElements", "Unit test to check Image data table finished!", bResult);
		return bResult;
	}

	public boolean testImageTableSaveData() {
		boolean bResult = false;
		
		try {
			AppMain.getApp().getErr().writeUnitTest(this.getClass().getName(), "testImageTableSaveData", "Starting unit test for save Image data table...");

			AppMain app = AppMain.getApp();
			AppContext ctx = app.getContext();
			AppDataManager dataMan = app.getDataMan(); 
			
			ImageTable tblImage = dataMan.getTblImage();
			tblImage.saveData();
			
			bResult = true;
		}
		catch(Exception e) {
			AppMain.getApp().getErr().writeError(this.getClass().getName(), "testImageTableSaveData", e.getMessage());
			e.printStackTrace();
		}

		AppMain.getApp().getErr().writeUnitTestResult(this.getClass().getName(), "testImageTableSaveData", "Unit test for save Image data table finished!", bResult);
		return bResult;
	}

	public boolean testImageTableLoadData() {
		boolean bResult = false;
		
		try {
			AppMain.getApp().getErr().writeUnitTest(this.getClass().getName(), "testImageTableLoadData", "Starting unit test for load Image data table...");

			AppMain app = AppMain.getApp();
			AppContext ctx = app.getContext();
			AppDataManager dataMan = app.getDataMan(); 
			
			ImageTable tblImage = dataMan.getTblImage();
			tblImage.clear();
			tblImage.loadData();
			tblImage.debugAll(AppDefs.DEBUG_LEVEL_DEBUG);
			
			bResult = true;
		}
		catch(Exception e) {
			AppMain.getApp().getErr().writeError(this.getClass().getName(), "testImageTableLoadData", e.getMessage());
			e.printStackTrace();
		}

		AppMain.getApp().getErr().writeUnitTestResult(this.getClass().getName(), "testImageTableLoadData", "Unit test for load Image data table finished!", bResult);
		return bResult;
	}
	
	public boolean testImageTableSizeAfterLoadData() {
		boolean bResult = false;
		
		try {
			AppMain.getApp().getErr().writeUnitTest(this.getClass().getName(), "testImageTableSizeAfterLoadData", "Starting unit test to check Image data table size...");

			AppDataManager dataMan = AppMain.getApp().getDataMan(); 
			
			ImageTable tblImage = dataMan.getTblImage();	
			int szTblImage = tblImage.getTableSize();
			
			String warnmsg = "OldImageTableSize = " + szTblImage + "; NewImageTableSize = " + this.szTblImage; 
			AppMain.getApp().getErr().writeUnitTest(this.getClass().getName(), "testImageTableSizeAfterLoadData", warnmsg);

			if(szTblImage == this.szTblImage)
				bResult = true;
		}
		catch(Exception e) {
			AppMain.getApp().getErr().writeError(this.getClass().getName(), "testImageTableSizeAfterLoadData", e.getMessage());
			e.printStackTrace();
		}

		AppMain.getApp().getErr().writeUnitTestResult(this.getClass().getName(), "testImageTableSizeAfterLoadData", "Unit test to check Image data table size finished!", bResult);
		return bResult;
	}
	
	@Override
	public boolean executeUnitTest() {
		if( !this.testImageTableAddElements() )
			return false;
		
		if( !this.testImageTableSaveData() )
			return false;
		
		if( !this.testImageTableLoadData() )
			return false;
		
		if( !this.testImageTableSizeAfterLoadData() )
			return false;
		
		return true;
	}
		
}
