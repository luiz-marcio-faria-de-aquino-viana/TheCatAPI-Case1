/*
 * Copyright(C) TLMV Consultoria e Sistemas Ltda. Todos os direitos reservados.
 *
 * TestCategoryTable.java
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
import br.com.tlmv.thecatapicase1.nosql.BreedTable;
import br.com.tlmv.thecatapicase1.nosql.CategoryTable;
import br.com.tlmv.thecatapicase1.services.BreedService;
import br.com.tlmv.thecatapicase1.services.CategoryService;

public class TestCategoryTable extends TestBase
{
//Private
	private int szTblCategory = -1;
	
//Public
	public static final String TEST_NAME = "TEST_CATEGORY_TABLE";
	
	public TestCategoryTable() {
		super(TestCategoryService.TEST_NAME);
	}
	
	/* Methodes */
	
	public boolean testCategoryTableAddElements() {
		boolean bResult = false;
		
		try {
			AppMain.getApp().getErr().writeUnitTest(this.getClass().getName(), "testCategoryTableAddElements", "Starting unit test for Category data table...");

			AppDataManager dataMan = AppMain.getApp().getDataMan(); 
			
			CategoryService service = new CategoryService();
			JSONArray aJSON = service.requestAllCategoriesJSON();
	
			CategoryTable tblCategory = dataMan.getTblCategory();
			for(int i = 0; i < aJSON.length(); i++) {
				JSONObject jsonObj = (JSONObject)aJSON.get(i);
				
				Category oCategory = new Category(jsonObj);
				tblCategory.putObj(oCategory);
			}
			
			this.szTblCategory = tblCategory.getTableSize();
			
			String warnmsg = "CategoryTableSize = " + this.szTblCategory; 
			AppMain.getApp().getErr().writeUnitTest(this.getClass().getName(), "testCategoryTableAddElements", warnmsg);
			
			tblCategory.debugAll(AppDefs.DEBUG_LEVEL_DEBUG);
			
			bResult = true;
		}
		catch(Exception e) {
			AppMain.getApp().getErr().writeError(this.getClass().getName(), "testCategoryTableAddElements", e.getMessage());
			e.printStackTrace();
		}

		AppMain.getApp().getErr().writeUnitTestResult(this.getClass().getName(), "testCategoryTableAddElements", "Unit test for Category data table finished!", bResult);
		return bResult;
	}

	public boolean testCategoryTableSaveData() {
		boolean bResult = false;
		
		try {
			AppMain.getApp().getErr().writeUnitTest(this.getClass().getName(), "testCategoryTableSaveData", "Starting unit test for save Category data table...");

			AppDataManager dataMan = AppMain.getApp().getDataMan(); 
			
			CategoryTable tblCategory = dataMan.getTblCategory();
			tblCategory.saveData();
			
			bResult = true;
		}
		catch(Exception e) {
			AppMain.getApp().getErr().writeError(this.getClass().getName(), "testCategoryTableSaveData", e.getMessage());
			e.printStackTrace();
		}

		AppMain.getApp().getErr().writeUnitTestResult(this.getClass().getName(), "testCategoryTableSaveData", "Unit test for save Category data table finished!", bResult);
		return bResult;
	}

	public boolean testCategoryTableLoadData() {
		boolean bResult = false;
		
		try {
			AppMain.getApp().getErr().writeUnitTest(this.getClass().getName(), "testCategoryTableLoadData", "Starting unit test for load Category data table...");

			AppDataManager dataMan = AppMain.getApp().getDataMan(); 
			
			CategoryTable tblCategory = dataMan.getTblCategory();
			tblCategory.clear();
			tblCategory.loadData();
			tblCategory.debugAll(AppDefs.DEBUG_LEVEL_DEBUG);
			
			bResult = true;
		}
		catch(Exception e) {
			AppMain.getApp().getErr().writeError(this.getClass().getName(), "testCategoryTableLoadData", e.getMessage());
			e.printStackTrace();
		}

		AppMain.getApp().getErr().writeUnitTestResult(this.getClass().getName(), "testCategoryTableLoadData", "Unit test for load Category data table finished!", bResult);
		return bResult;
	}
	
	public boolean testCategoryTableSizeAfterLoadData() {
		boolean bResult = false;
		
		try {
			AppMain.getApp().getErr().writeUnitTest(this.getClass().getName(), "testCategoryTableSizeAfterLoadData", "Starting unit test to check Category data table size...");

			AppDataManager dataMan = AppMain.getApp().getDataMan(); 
			
			CategoryTable tblCategory = dataMan.getTblCategory();	
			int szTblCategory = tblCategory.getTableSize();
			
			String dbgmsg = "OldCategoryTableSize = " + szTblCategory + "; NewCategoryTableSize = " + this.szTblCategory; 
			AppMain.getApp().getErr().writeUnitTest(this.getClass().getName(), "testCategoryTableSizeAfterLoadData", dbgmsg);

			if(szTblCategory == this.szTblCategory)
				bResult = true;
		}
		catch(Exception e) {
			AppMain.getApp().getErr().writeError(this.getClass().getName(), "testCategoryTableSizeAfterLoadData", e.getMessage());
			e.printStackTrace();
		}

		AppMain.getApp().getErr().writeUnitTestResult(this.getClass().getName(), "testCategoryTableSizeAfterLoadData", "Unit test to check Category data table size finished!", bResult);			
		return bResult;
	}
	
	@Override
	public boolean executeUnitTest() {
		if( !this.testCategoryTableAddElements() )
			return false;
		
		if( !this.testCategoryTableSaveData() )
			return false;
		
		if( !this.testCategoryTableLoadData() )
			return false;
		
		if( !this.testCategoryTableSizeAfterLoadData() )
			return false;
		
		return true;
	}
		
}
