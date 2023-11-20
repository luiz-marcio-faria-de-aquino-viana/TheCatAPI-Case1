/*
 * Copyright(C) TLMV Consultoria e Sistemas Ltda. Todos os direitos reservados.
 *
 * TestCategoryService.java
 * Autor: Luiz Marcio Faria de Aquino Viana, 24/10/2022
 * revisoes: ...
 *
 */

package br.com.tlmv.thecatapicase1.tests;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.tlmv.thecatapicase1.AppDefs;
import br.com.tlmv.thecatapicase1.AppMain;
import br.com.tlmv.thecatapicase1.data.Breed;
import br.com.tlmv.thecatapicase1.data.Category;
import br.com.tlmv.thecatapicase1.services.BreedService;
import br.com.tlmv.thecatapicase1.services.CategoryService;

public class TestCategoryService extends TestBase
{
//Public
	public static final String TEST_NAME = "TEST_CATEGORY_SERVICE";
		
	public TestCategoryService() {
		super(TestCategoryService.TEST_NAME);
	}

	/* Methodes */
	
	public boolean testRequestAllCategoriesJSON() {
		boolean bResult = false;
		
		try {
			AppMain.getApp().getErr().writeUnitTest(this.getClass().getName(), "testRequestAllCategoriesJSON", "Starting unit test for all Categories...");

			CategoryService service = new CategoryService();
			JSONArray aJSON = service.requestAllCategoriesJSON();
	
			ArrayList<Category> lsCategory = new ArrayList<Category>();
			for(int i = 0; i < aJSON.length(); i++) {
				JSONObject jsonObj = (JSONObject)aJSON.get(i);
				
				Category oCategory = new Category(jsonObj);
				lsCategory.add(oCategory);
			}
			
			for(Category o : lsCategory) {
				o.debug(AppDefs.DEBUG_LEVEL_DEBUG);
			}
			
			bResult = true;
		}
		catch(Exception e) {
			AppMain.getApp().getErr().writeError(this.getClass().getName(), "testRequestAllCategoriesJSON", e.getMessage());
			e.printStackTrace();
		}

		AppMain.getApp().getErr().writeUnitTestResult(this.getClass().getName(), "testRequestAllCategoriesJSON", "Unit test for all Categories finished!", bResult);
		return bResult;
	}

	/* EXECUTE */
	
	@Override
	public boolean executeUnitTest() {
		if( !this.testRequestAllCategoriesJSON() )
			return false;
		return true;
	}
	
}
