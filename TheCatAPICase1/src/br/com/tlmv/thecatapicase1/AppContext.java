/*
 * Copyright(C) TLMV Consultoria e Sistemas Ltda. Todos os direitos reservados.
 *
 * AppContext.java
 * Autor: Luiz Marcio Faria de Aquino Viana, 25/10/2022
 * revisoes: ...
 *
 */

package br.com.tlmv.thecatapicase1;

public class AppContext 
{
//Private
	private String homeDir = "";

	private String binDir = "";
	private String configDir = "";
	private String dataDir = "";
	private String dataImagesDir = "";
	private String dataCatsWithHatDir = "";
	private String dataCatsWithGlassesDir = "";
	private String logsDir = "";
	
	private String configFileName = "";
	private String logFileName = "";

//Public
	
	public AppContext() {
		this.homeDir = System.getenv(AppDefs.APP_HOME);
		if(homeDir == null)
			this.homeDir = AppDefs.CTX_DEFAULT_HOME_DIR;
		
		this.binDir = this.homeDir + AppDefs.CTX_HOME_BIN;
		this.configDir = this.homeDir + AppDefs.CTX_HOME_CONFIG;
		this.dataDir = this.homeDir + AppDefs.CTX_HOME_DATA;
		this.dataImagesDir = this.homeDir + AppDefs.CTX_HOME_DATA_IMAGES;
		this.dataCatsWithHatDir = this.homeDir + AppDefs.CTX_HOME_DATA_CATSWITHHAT;
		this.dataCatsWithGlassesDir = this.homeDir + AppDefs.CTX_HOME_DATA_CATSWITHGLASSES;
		this.logsDir = this.homeDir + AppDefs.CTX_HOME_LOGS;
		
		this.configFileName = this.configDir + "/" + AppDefs.CTX_CONFIG_FILENAME;
		this.logFileName = this.logsDir + "/" + AppDefs.CTX_LOG_FILENAME;
		
	}
	
	/* Methodes */
	
	public void show() {
		System.out.println("CONTEXTO:");
		System.out.println("  HomeDir: " + this.homeDir);
		System.out.println("");
		System.out.println("  BinDir: " + this.binDir);
		System.out.println("  ConfigDir: " + this.configDir);
		System.out.println("  DataDir: " + this.dataDir);
		System.out.println("  DataImagesDir: " + this.dataImagesDir);
		System.out.println("");		
		System.out.println("  DataCatsWithHatDir: " + this.dataCatsWithHatDir);
		System.out.println("  DataCatsWithGlassesDir: " + this.dataCatsWithGlassesDir);
		System.out.println("");		
		System.out.println("  LogDir: " + this.logsDir);
		System.out.println("");		
		System.out.println("  ConfigFileName: " + this.configFileName);
		System.out.println("  LogFileName: " + this.logFileName);
		
	}
	
	/* Getters/Settters */

	public String getHomeDir() {
		return homeDir;
	}

	public void setHomeDir(String homeDir) {
		this.homeDir = homeDir;
	}

	public String getBinDir() {
		return binDir;
	}

	public void setBinDir(String binDir) {
		this.binDir = binDir;
	}

	public String getConfigDir() {
		return configDir;
	}

	public void setConfigDir(String configDir) {
		this.configDir = configDir;
	}

	public String getDataDir() {
		return dataDir;
	}

	public void setDataDir(String dataDir) {
		this.dataDir = dataDir;
	}

	public String getDataImagesDir() {
		return dataImagesDir;
	}

	public void setDataImagesDir(String dataImagesDir) {
		this.dataImagesDir = dataImagesDir;
	}

	public String getDataCatsWithHatDir() {
		return dataCatsWithHatDir;
	}

	public void setDataCatsWithHatDir(String dataCatsWithHatDir) {
		this.dataCatsWithHatDir = dataCatsWithHatDir;
	}

	public String getDataCatsWithGlassesDir() {
		return dataCatsWithGlassesDir;
	}

	public void setDataCatsWithGlassesDir(String dataCatsWithGlassesDir) {
		this.dataCatsWithGlassesDir = dataCatsWithGlassesDir;
	}

	public String getConfigFileName() {
		return configFileName;
	}

	public void setConfigFileName(String configFileName) {
		this.configFileName = configFileName;
	}

	public String getLogsDir() {
		return logsDir;
	}

	public void setLogsDir(String logsDir) {
		this.logsDir = logsDir;
	}

	public String getLogFileName() {
		return logFileName;
	}

	public void setLogFileName(String logFileName) {
		this.logFileName = logFileName;
	}

}
