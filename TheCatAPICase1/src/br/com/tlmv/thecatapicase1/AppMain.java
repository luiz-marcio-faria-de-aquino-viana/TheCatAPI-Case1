/*
 * Copyright(C) TLMV Consultoria e Sistemas Ltda. Todos os direitos reservados.
 *
 * AppMain.java
 * Autor: Luiz Marcio Faria de Aquino Viana, 24/10/2022
 * revisoes: ...
 *
 */

package br.com.tlmv.thecatapicase1;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.tlmv.thecatapicase1.data.Breed;
import br.com.tlmv.thecatapicase1.datacreator.ExecDataCreator;
import br.com.tlmv.thecatapicase1.rest.RESTfullServer;
import br.com.tlmv.thecatapicase1.services.BreedService;
import br.com.tlmv.thecatapicase1.tests.UnitTest;

public class AppMain 
{
//Private
	private static AppMain gApp = null;
	
	private AppContext ctx = null;
	
	private AppError err = null;
	
	private AppConfig config = null;
	
	private AppDataManager dataMan = null;

	//START_SWITCHES
	private boolean bUnitTest = false;
	private boolean bDataCreator = false;
	private boolean bRestServer = false;
	
//Public
	
	public AppMain() {
		AppMain.gApp = this;
		
		this.copyright();
		
		this.ctx = new AppContext();
		this.ctx.show();
		
		this.err = new AppError();				// start error log with default values

		this.config = new AppConfig(this.ctx.getConfigFileName());
		this.config.show();
		
		this.err.init(							// re-initialize error log with configuration values
			AppDefs.APP_NAME,
			config.getDebugLevelVal(),
			config.getDebugOutputModeVal());
		
	}
	
	/* Methodes */

	public void copyright()
	{
		String str = String.format("%s %s\n\n%s\n", 
			AppDefs.APP_NAME, 
			AppDefs.APP_VERSAO,
			AppDefs.APP_COPYRIGHT);
		System.out.print(str); 
	}
	
	public void showUsageInfo()
	{
		String str = String.format("%s\n", 
			AppDefs.HLP_USAGE_INFO);
		System.out.print(str); 	
	}
	
	public boolean loadParms(String[] args) {
		for(int i = 0; i < args.length; i++) {
			String strCmd = args[i];
			if( AppDefs.CMD_HELP_STR.equals(strCmd) ) {
				this.showUsageInfo();
				System.exit(0);
			}
			else if( AppDefs.CMD_TEST_STR.equals(strCmd) ) {
				bUnitTest = true;
			}
			else if( AppDefs.CMD_DATACREATOR_STR.equals(strCmd) ) {
				bDataCreator = true;
			}
			else if( AppDefs.CMD_RESTSERVER_STR.equals(strCmd) ) {
				bRestServer = true;
			}
		}
		
		if( !bUnitTest && !bDataCreator && !bRestServer ) {
			this.err.writeError(this.getClass().getName(), "loadParms", AppError.ERR_INVALID_COMMAND_LINE_SWITCHES);
			this.showUsageInfo();
			System.exit(1);
		}
		return true;
	}

	public void execute(String[] args)
		throws Exception
	{
		if( this.loadParms(args) ) 
		{
			this.dataMan = new AppDataManager();
			
			if( this.bDataCreator ) {
				this.doDataCreator();					
			}
			else if( this.bRestServer ) {
				this.doRestServer();										
			}
			else if( this.bUnitTest ) {
				this.doUnitTest();															
			}
		}
	}
	
	public void doDataCreator()
		throws Exception
	{
		AppMain.getApp().getErr().writeLog(this.getClass().getName(), "doDataCreator", "Starting Data Creator...");
		System.out.println("Starting Data Creator...");
		
		ExecDataCreator exec = new ExecDataCreator();
		if( !exec.executeDataCreator() ) {
			this.err.writeError(this.getClass().getName(), "doDataCreator", AppError.ERR_DATA_CREATOR_EXECUTION_FAILURE);
			System.exit(1);	
		}
		
		AppMain.getApp().getErr().writeLog(this.getClass().getName(), "doDataCreator", "Data Creator terminated!");
		System.out.println("Data Creator Terminated!");
	}
	
	public void doRestServer()
		throws Exception
	{
		AppMain.getApp().getErr().writeLog(this.getClass().getName(), "doRestServer", "Loading data tables...");
		System.out.println("Loading data tables...");

		this.dataMan.loadAll();
		
		AppMain.getApp().getErr().writeLog(this.getClass().getName(), "doRestServer", "Starting RESTfull Server...");
		System.out.println("Starting RESTfull Server...");

		RESTfullServer server = new RESTfullServer();
		server.start();

		AppMain.getApp().getErr().writeLog(this.getClass().getName(), "doRestServer", "RESTfull Server started!");
		System.out.println("RESTfull Server started! Press ANY KEY to stop.");
		
		int ch = -1;
		while(ch == -1) {
			try {
				ch = System.in.read();
			}
			catch(Exception e) { }
		}
		
		server.stop();
		
		AppMain.getApp().getErr().writeLog(this.getClass().getName(), "doRestServer", "RESTfull Server terminated!");
		System.out.println("RESTfull Server Terminated!");
	}

	public void doUnitTest()
		throws Exception
	{
		AppMain.getApp().getErr().writeLog(this.getClass().getName(), "doUnitTest", "Starting Unit Test...");
		System.out.println("Starting Unit Test...");
		
		UnitTest uTest = new UnitTest();
		if( !uTest.executeUnitTest() ) {
			this.err.writeError(this.getClass().getName(), "doUnitTest", AppError.ERR_UNIT_TEST_EXECUTION_FAILURE);
			System.exit(1);	
		}

		AppMain.getApp().getErr().writeLog(this.getClass().getName(), "doUnitTest", "Unit Test terminated!");
		System.out.println("Unit Test terminated!");
	}
	
	/* Getters/Setters */
	
	public static AppMain getApp() {
		return AppMain.gApp;
	}

	public AppContext getContext() {
		return this.ctx;
	}
	
	public AppError getErr() {
		return err;
	}

	public AppDataManager getDataMan() {
		return this.dataMan;
	}
	
	/* MAIN */
	
	public static void main(String[] args)
	{
		try {
			AppMain.gApp = new AppMain();
			AppMain.gApp.execute(args);
		}
		catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
}
