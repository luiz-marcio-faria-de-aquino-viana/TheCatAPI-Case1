/*
 * Copyright(C) TLMV Consultoria e Sistemas Ltda. Todos os direitos reservados.
 *
 * TestBreedTable.java
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
import br.com.tlmv.thecatapicase1.nosql.BreedTable;
import br.com.tlmv.thecatapicase1.services.BreedService;

public class TestBreedTable extends TestBase
{
//Private
	private int szTblBreed = -1;
	
//Public
	public static final String TEST_NAME = "TEST_BREED_TABLE";
	
	public TestBreedTable() {
		super(TestBreedService.TEST_NAME);
	}
	
	/* Methodes */
	
	public boolean testBreedTableAddElements() {
		boolean bResult = false;
		
		try {
			AppMain.getApp().getErr().writeUnitTest(this.getClass().getName(), "testBreedTableAddElements", "Starting unit test for Breed data table...");
			
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
			
			String warnmsg = "BreedTableSize = " + this.szTblBreed; 
			AppMain.getApp().getErr().writeUnitTest(this.getClass().getName(), "testBreedTableAddElements", warnmsg);
			
			tblBreed.debugAll(AppDefs.DEBUG_LEVEL_DEBUG);
			
			bResult = true;
		}
		catch(Exception e) {
			AppMain.getApp().getErr().writeError(this.getClass().getName(), "testBreedTableAddElements", e.getMessage());
			e.printStackTrace();
		}

		AppMain.getApp().getErr().writeUnitTestResult(this.getClass().getName(), "testBreedTableAddElements", "Unit test for Breed data table finished!", bResult);			
		return bResult;
	}

	public boolean testBreedTableSaveData() {
		boolean bResult = false;
		
		try {
			AppMain.getApp().getErr().writeUnitTest(this.getClass().getName(), "testBreedTableSaveData", "Starting unit test for save Breed data table...");

			AppDataManager dataMan = AppMain.getApp().getDataMan(); 
			
			BreedTable tblBreed = dataMan.getTblBreed();
			tblBreed.saveData();
			
			bResult = true;
		}
		catch(Exception e) {
			AppMain.getApp().getErr().writeError(this.getClass().getName(), "testBreedTableSaveData", e.getMessage());
			e.printStackTrace();
		}

		AppMain.getApp().getErr().writeUnitTestResult(this.getClass().getName(), "testBreedTableSaveData", "Unit test for save Breed data table finished!", bResult);			
		return bResult;
	}

	public boolean testBreedTableLoadData() {
		boolean bResult = false;
		
		try {
			AppMain.getApp().getErr().writeUnitTest(this.getClass().getName(), "testBreedTableLoadData", "Starting unit test for load Breed data table...");

			AppDataManager dataMan = AppMain.getApp().getDataMan(); 
			
			BreedTable tblBreed = dataMan.getTblBreed();
			tblBreed.clear();
			tblBreed.loadData();
			tblBreed.debugAll(AppDefs.DEBUG_LEVEL_DEBUG);
			
			bResult = true;
		}
		catch(Exception e) {
			AppMain.getApp().getErr().writeError(this.getClass().getName(), "testBreedTableLoadData", e.getMessage());
			e.printStackTrace();
		}

		AppMain.getApp().getErr().writeUnitTestResult(this.getClass().getName(), "testBreedTableLoadData", "Unit test for load Breed data table finished!", bResult);			
		return bResult;
	}
	
	public boolean testBreedTableSizeAfterLoadData() {
		boolean bResult = false;
		
		try {
			AppMain.getApp().getErr().writeUnitTest(this.getClass().getName(), "testBreedTableSizeAfterLoadData", "Starting unit test to check Breed data table size...");

			AppDataManager dataMan = AppMain.getApp().getDataMan(); 
			
			BreedTable tblBreed = dataMan.getTblBreed();	
			int szTblBreed = tblBreed.getTableSize();
			
			String warnmsg = "OldBreadTableSize = " + szTblBreed + "; NewBreedTableSize = " + this.szTblBreed; 
			AppMain.getApp().getErr().writeUnitTest(this.getClass().getName(), "testBreedTableSizeAfterLoadData", warnmsg);

			if(szTblBreed == this.szTblBreed)
				bResult = true;
		}
		catch(Exception e) {
			AppMain.getApp().getErr().writeError(this.getClass().getName(), "testBreedTableSizeAfterLoadData", e.getMessage());
			e.printStackTrace();
		}

		AppMain.getApp().getErr().writeUnitTestResult(this.getClass().getName(), "testBreedTableSizeAfterLoadData", "Unit test to check Breed data table finished!", bResult);			
		return bResult;
	}
	
	@Override
	public boolean executeUnitTest() {
		if( !this.testBreedTableAddElements() )
			return false;
		
		if( !this.testBreedTableSaveData() )
			return false;
		
		if( !this.testBreedTableLoadData() )
			return false;
		
		if( !this.testBreedTableSizeAfterLoadData() )
			return false;
		
		return true;
	}
		
}
