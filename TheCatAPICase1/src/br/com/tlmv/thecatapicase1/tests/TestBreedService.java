/*
 * Copyright(C) TLMV Consultoria e Sistemas Ltda. Todos os direitos reservados.
 *
 * TestBreedService.java
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
import br.com.tlmv.thecatapicase1.services.BreedService;

public class TestBreedService extends TestBase
{
//Public
	public static final String TEST_NAME = "TEST_BREED_SERVICE";
		
	public TestBreedService() {
		super(TestBreedService.TEST_NAME);
	}

	/* Methodes */
	
	public boolean testRequestAllBreedsJSON() {
		boolean bResult = false;
		
		try {
			AppMain.getApp().getErr().writeUnitTest(this.getClass().getName(), "testRequestAllBreedsJSON", "Starting unit test for all Breeds...");

			BreedService service = new BreedService();
			JSONArray aJSON = service.requestAllBreedsJSON();
	
			ArrayList<Breed> lsBreed = new ArrayList<Breed>();
			for(int i = 0; i < aJSON.length(); i++) {
				JSONObject jsonObj = (JSONObject)aJSON.get(i);
				
				Breed oBreed = new Breed(jsonObj);
				lsBreed.add(oBreed);
			}
			
			for(Breed o : lsBreed) {
				o.debug(AppDefs.DEBUG_LEVEL_DEBUG);
			}
			
			bResult = true;
		}
		catch(Exception e) {
			AppMain.getApp().getErr().writeDebug(this.getClass().getName(), "testRequestAllBreedsJSON", e.getMessage());
			e.printStackTrace();
		}

		AppMain.getApp().getErr().writeUnitTestResult(this.getClass().getName(), "testRequestAllBreedsJSON", "Unit test for all Breeds finished!", bResult);			
		return bResult;
	}

	/* EXECUTE */
	
	@Override
	public boolean executeUnitTest() {
		if( !this.testRequestAllBreedsJSON() )
			return false;
		return true;
	}
	
}
