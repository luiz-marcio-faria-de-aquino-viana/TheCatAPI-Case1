/*
 * Copyright(C) TLMV Consultoria e Sistemas Ltda. Todos os direitos reservados.
 *
 * TestBase.java
 * Autor: Luiz Marcio Faria de Aquino Viana, 25/10/2022
 * revisoes: ...
 *
 */

package br.com.tlmv.thecatapicase1.tests;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.tlmv.thecatapicase1.AppDefs;
import br.com.tlmv.thecatapicase1.AppMain;

public class TestBase 
{
//Private
	private String testName = "UNITTEST";
	
//Public
	
	public TestBase(String testName) {
		this.testName = testName;
	}

	/* Methodes */
	
	public boolean executeUnitTest() {
		String warnmsg = String.format("Test %s not implemented.", this.testName);
		AppMain.getApp().getErr().writeUnitTest(this.getClass().getName(), "executeUnitTest", warnmsg);
		return false;
	}

	/* Getters/Setters */
	
	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

}
