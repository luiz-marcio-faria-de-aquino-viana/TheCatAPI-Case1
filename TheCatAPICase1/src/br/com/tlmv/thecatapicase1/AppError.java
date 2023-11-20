/*
 * Copyright(C) TLMV Consultoria e Sistemas Ltda. Todos os direitos reservados.
 *
 * BaseLog.java
 * Autor: Luiz Marcio Faria de Aquino Viana, 27/10/2022
 * revisoes: ...
 *
 */

package br.com.tlmv.thecatapicase1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.tlmv.thecatapicase1.utils.FileUtil;
import br.com.tlmv.thecatapicase1.utils.UuidUtil;

public class AppError 
{
//Private
	private String appName = AppDefs.APP_NAME;
	private Integer appDebugLevel = AppDefs.DEBUG_LEVEL_ERROR;
	private Integer appDebugOutputMode = AppDefs.DEBUG_OUTPUT_MODE_LOG_FILE;
	private PrintStream appOut = null;
	
	private synchronized boolean openLogFile() {
		boolean bResult = false;
		
		try {
			AppContext ctx = AppMain.getApp().getContext();
	
			String fileName = ctx.getLogFileName();		
			File fsrc = new File(fileName);
			if( fsrc.exists() ) {
				String uuid = UuidUtil.generateUUID();
				
				String newFileName = ctx.getLogFileName() + "-" + uuid + AppDefs.EXT_LOG;
				File fdst = new File(newFileName);
				if( !fdst.exists() )
					fsrc.renameTo(fdst);
			}
			
			this.appOut = new PrintStream(fsrc);
			bResult = true;
		}
		catch(Exception e) {
			if(this.appDebugOutputMode == AppDefs.DEBUG_OUTPUT_MODE_STDOUT) {
				System.out.println(e.getMessage());
			}
			else {
				System.err.println(e.getMessage());
			}
			e.printStackTrace();
		}
		return bResult;
	}	

	private synchronized void closeLogFile() {
		try {
			if(this.appOut != null) {
				this.appOut.flush();
				this.appOut.close();
			}
		}
		catch(Exception e) {
			if(this.appDebugOutputMode == AppDefs.DEBUG_OUTPUT_MODE_STDOUT) {
				System.out.println(e.getMessage());
			}
			else {
				System.err.println(e.getMessage());
			}
			e.printStackTrace();
		}
	}
	
	private synchronized void writeMessageToFile(String message) {
		try {
			this.appOut.println(message);
		}
		catch(Exception e) {
			if(this.appDebugOutputMode == AppDefs.DEBUG_OUTPUT_MODE_STDOUT) {
				System.out.println(message);
				System.out.println(e.getMessage());
			}
			else {
				System.err.println(message);
				System.err.println(e.getMessage());
			}
			e.printStackTrace();
		}
	}	
	
//Public

	/* MESSAGES */
	public static final String ERR_INVALID_COMMAND_LINE_SWITCHES = "Invalid command line switches.";
	public static final String ERR_CANT_OPEN_FILE = "Can't open file (%s).";
	public static final String ERR_CONFIGURATION_FILE_READ_FAILURE = "Configuration file read failure (%s).";
	public static final String ERR_UNIT_TEST_EXECUTION_FAILURE = "Unit test execution failure.";
	public static final String ERR_DATA_CREATOR_EXECUTION_FAILURE = "Data creator execution failure.";
	public static final String ERR_DATA_CREATOR_NOT_IMPLEMENTED = "Data creator not implemented (%s).";
	public static final String ERR_TOO_MANY_REQUESTS = "Too many requestes (RETRY_AFTER = %s).";
	public static final String ERR_UNSUPORTED_IMAGE_FORMAT = "Unsuported image format (REQUEST_URL=%s).";
	public static final String ERR_HTTP_INFORMATIONAL_RESPONSE = "HTTP Informational response (RETRY_AFTER = %s).";
	public static final String ERR_HTTP_SUCCESSFUL_RESPONSE = "HTTP Successful response.";
	public static final String ERR_HTTP_REDIRECTION_RESPONSE = "HTTP Redirection response.";
	public static final String ERR_HTTP_CLIENT_ERROR_RESPONSE = "HTTP Client error responses.";
	public static final String ERR_HTTP_SERVER_ERROR_RESPONSE = "HTTP Server error response.";
	public static final String ERR_HTTP_RESPONSE_ERROR = "HTTP response erro (RESPONSE_CODE=%s).";
	
	
	/* Constructors */
	
	public AppError() {
		this.init(AppDefs.APP_NAME, AppDefs.DEBUG_LEVEL_ERROR, AppDefs.DEBUG_OUTPUT_MODE_STDOUT);
	}
	
	public AppError(String appName, int appDebugLevel, int appDebugOutputMode) {
		this.init(appName, appDebugLevel, appDebugOutputMode);
	}
	
	/* Methodes */

	public void init(String appName, int appDebugLevel, int appDebugOutputMode) {
		this.appName = appName;
		this.appDebugLevel = appDebugLevel;
		this.appDebugOutputMode = appDebugOutputMode;
		
		if(this.appOut != null)
			this.closeLogFile();

		if(this.appDebugOutputMode == AppDefs.DEBUG_OUTPUT_MODE_LOG_FILE)
			this.openLogFile();
	}
	
	// WRITE_TO_LOG_FILE
	
	public boolean checkDebugLevel(int debugLevel) {
		if(debugLevel <= this.appDebugLevel)
			return true;
		return false;
	}
	
	public void writeToLogFile(int debugLevel, String className, String methodName, String logMessage) {
		DateFormat df = new SimpleDateFormat(AppDefs.FMT_DATETIME_INV_MASC);
	
		String debugLevelStr = AppDefs.DEBUG_LEVEL_ARR[debugLevel];
		
		Date dataAtual = new Date();		
		String outMsg = String.format("\n[%s] %s - %s@%s@%s: %s", debugLevelStr, df.format(dataAtual), this.appName, className, methodName, logMessage);

		if(this.appOut != null) {
			this.writeMessageToFile(outMsg);
		}
		else {
			if(appDebugOutputMode == AppDefs.DEBUG_OUTPUT_MODE_STDOUT) {
				System.out.println(outMsg);
			}
			else {
				System.err.println(outMsg);
			}
		}
	}
	
	public void writeLog(String className, String methodName, String logMessage) {
		if( !checkDebugLevel(AppDefs.DEBUG_LEVEL_INFO) ) return;
		this.writeToLogFile(AppDefs.DEBUG_LEVEL_INFO, className, methodName, logMessage);
	}
	
	public void writeDebug(String className, String methodName, String message) {
		if( !checkDebugLevel(AppDefs.DEBUG_LEVEL_DEBUG) ) return;
		this.writeToLogFile(AppDefs.DEBUG_LEVEL_DEBUG, className, methodName, message);
	}
	
	public void writeWarn(String className, String methodName, String warnMessage) {
		if( !checkDebugLevel(AppDefs.DEBUG_LEVEL_WARN) ) return;
		this.writeToLogFile(AppDefs.DEBUG_LEVEL_WARN, className, methodName, warnMessage);
	}
	
	public void writeError(String className, String methodName, String errMessage) {
		if( !checkDebugLevel(AppDefs.DEBUG_LEVEL_ERROR) ) return;
		this.writeToLogFile(AppDefs.DEBUG_LEVEL_ERROR, className, methodName, errMessage);
	}
	
	// WRITE_UNIT_TEST
	
	public void writeUnitTest(String className, String methodName, String message) {
		DateFormat df = new SimpleDateFormat(AppDefs.FMT_DATETIME_INV_MASC);
		
		String debugLevelStr = "TEST";

		Date dataAtual = new Date();		
		String outMsg = String.format("\n[%s] %s - %s@%s@%s: %s", debugLevelStr, df.format(dataAtual), this.appName, className, methodName, message);

		if(this.appOut != null) {
			this.writeMessageToFile(outMsg);
		}
		else {
			if(this.appDebugOutputMode == AppDefs.DEBUG_OUTPUT_MODE_STDOUT) {
				System.out.println(outMsg);
			}
			else {
				System.err.println(outMsg);
			}
		}
	}

	public void writeUnitTestResult(String className, String methodName, String message, boolean bResult) {
		DateFormat df = new SimpleDateFormat(AppDefs.FMT_DATETIME_INV_MASC);

		String debugLevelStr = "TEST::SUCCESS";
		if( !bResult )
			debugLevelStr = "TEST::FAIL";
		
		Date dataAtual = new Date();		
		String outMsg = String.format("\n[%s] %s - %s@%s@%s: %s", debugLevelStr, df.format(dataAtual), this.appName, className, methodName, message);

		if(this.appOut != null) {
			this.writeMessageToFile(outMsg);
		}
		else {
			if(this.appDebugOutputMode == AppDefs.DEBUG_OUTPUT_MODE_STDOUT) {
				System.out.println(outMsg);
			}
			else {
				System.err.println(outMsg);
			}
		}
	}
	
	/* Getters/Setters */
	
	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public int getAppDebugLevel() {
		return appDebugLevel;
	}

	public void setAppDebugLevel(int appDebugLevel) {
		this.appDebugLevel = appDebugLevel;
	}

	public PrintStream getAppOut() {
		return appOut;
	}

	public void setAppOut(PrintStream appOut) {
		this.appOut = appOut;
	}

	public void setAppDebugLevel(Integer appDebugLevel) {
		this.appDebugLevel = appDebugLevel;
	}

}
