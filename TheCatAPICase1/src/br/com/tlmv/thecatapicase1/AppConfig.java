/*
 * Copyright(C) TLMV Consultoria e Sistemas Ltda. Todos os direitos reservados.
 *
 * AppContext.java
 * Autor: Luiz Marcio Faria de Aquino Viana, 28/10/2022
 * revisoes: ...
 *
 */

package br.com.tlmv.thecatapicase1;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import br.com.tlmv.thecatapicase1.utils.FileUtil;
import br.com.tlmv.thecatapicase1.utils.XmlUtil;

public class AppConfig 
{
//Private
	private String debugLevelStr = AppDefs.DEBUG_LEVEL_ARR[AppDefs.DEBUG_LEVEL_ERROR];
	private String debugOutputModeStr = AppDefs.DEBUG_OUTPUT_MODE_ARR[AppDefs.DEBUG_OUTPUT_MODE_STDOUT];
	//
	private Integer debugLevelVal = AppDefs.DEBUG_LEVEL_ERROR;
	private Integer debugOutputModeVal = AppDefs.DEBUG_OUTPUT_MODE_STDOUT;
		
//Public
	
	public AppConfig(String fileName) {
		loadDataFromFile(fileName);
	}
	
	/* Methodes */
		
	public void init(String fileName) {
		loadDataFromFile(fileName);
	}
	
	public void loadDataFromFile(String fileName) {
		try {
			String xmlData = FileUtil.readData(fileName);
			loadData(xmlData);
		}
		catch(Exception e) {
			String errmsg = String.format(AppError.ERR_CONFIGURATION_FILE_READ_FAILURE, fileName);
			AppMain.getApp().getErr().writeError(this.getClass().getName(), "doDataCreator", errmsg);
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public void loadData(String xmlData)
		throws Exception
	{
		AppContext ctx = AppMain.getApp().getContext();
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		
		InputSource is = new InputSource(new StringReader(xmlData));
	    Document doc = db.parse(is);

	    Node nConfig = doc.getFirstChild();		    

		this.debugLevelStr = XmlUtil.getChildNodeValueByName(nConfig, AppDefs.CFG_DEBUG_LEVEL, null);
		this.debugOutputModeStr = XmlUtil.getChildNodeValueByName(nConfig, AppDefs.CFG_DEBUG_OUTPUT_MODE, null);
		
		this.debugLevelVal = this.findDebugLevelByName(this.debugLevelStr);
		this.debugOutputModeVal = this.findDebugOutputModeByName(this.debugOutputModeStr);
		
	}

	//FIND
	
	public Integer findDebugLevelByName(String debugLevelStr) {
		for(int i = 0; i < AppDefs.DEBUG_LEVEL_ARRSZ; i++) {
			String itemStr = AppDefs.DEBUG_LEVEL_ARR[i];
			if( itemStr.equalsIgnoreCase(debugLevelStr) )
				return(i);
		}
		return(-1);
	}
	
	public Integer findDebugOutputModeByName(String debugOutputModeStr) {
		for(int i = 0; i < AppDefs.DEBUG_OUTPUT_MODE_ARRSZ; i++) {
			String itemStr = AppDefs.DEBUG_OUTPUT_MODE_ARR[i];
			if( itemStr.equalsIgnoreCase(debugOutputModeStr) )
				return(i);
		}
		return(-1);
	}
	
	/* Methodes */
	
	public void show() {
		System.out.println("\nCONFIGURACOES:");
		System.out.println("  DebugLevel: " + Integer.toString(this.debugLevelVal) + " - " + this.debugLevelStr);
		System.out.println("  DebugOutputMode: " + Integer.toString(this.debugOutputModeVal) + " - " + this.debugOutputModeStr);
		System.out.println("");
	}
		
	/* Getters/Setters */

	public Integer getDebugLevelVal() {
		return debugLevelVal;
	}

	public void setDebugLevelVal(Integer debugLevelVal) {
		this.debugLevelVal = debugLevelVal;
	}

	public String getDebugLevelStr() {
		return debugLevelStr;
	}

	public void setDebugLevelStr(String debugLevelStr) {
		this.debugLevelStr = debugLevelStr;
	}

	public Integer getDebugOutputModeVal() {
		return debugOutputModeVal;
	}

	public void setDebugOutputModeVal(Integer debugOutputModeVal) {
		this.debugOutputModeVal = debugOutputModeVal;
	}

	public String getDebugOutputModeStr() {
		return debugOutputModeStr;
	}

	public void setDebugOutputModeStr(String debugOutputModeStr) {
		this.debugOutputModeStr = debugOutputModeStr;
	}
	
}
