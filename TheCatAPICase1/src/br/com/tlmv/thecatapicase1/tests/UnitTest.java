/*
 * Copyright(C) TLMV Consultoria e Sistemas Ltda. Todos os direitos reservados.
 *
 * UnitTest.java
 * Autor: Luiz Marcio Faria de Aquino Viana, 25/10/2022
 * revisoes: ...
 *
 */

package br.com.tlmv.thecatapicase1.tests;

public class UnitTest 
{
//Public
	
	public boolean executeUnitTest() {
		TestBase tbs = new TestBreedService();
		if( !((TestBreedService)tbs).executeUnitTest() )
			return false;
		
		TestBase tbt = new TestBreedTable();
		if( !((TestBreedTable)tbt).executeUnitTest() )
			return false;
		
		TestBase tis = new TestImageService();
		if( !((TestImageService)tis).executeUnitTest() )
			return false;
		
		TestBase tit = new TestImageTable();
		if( !((TestImageTable)tit).executeUnitTest() )
			return false;
		
		TestBase tcs = new TestCategoryService();
		if( !((TestCategoryService)tcs).executeUnitTest() )
			return false;		
		
		TestBase tct = new TestCategoryTable();
		if( !((TestCategoryTable)tct).executeUnitTest() )
			return false;
		
		return true;
	}
	
}
