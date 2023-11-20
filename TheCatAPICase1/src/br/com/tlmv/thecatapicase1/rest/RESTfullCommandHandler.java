/*
 * Copyright(C) TLMV Consultoria e Sistemas Ltda. Todos os direitos reservados.
 *
 * RESTfullCommandHandler.java
 * Autor: Luiz Marcio Faria de Aquino Viana, 26/10/2022
 * revisoes: ...
 *
 */

package br.com.tlmv.thecatapicase1.rest;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import br.com.tlmv.thecatapicase1.AppDefs;
import br.com.tlmv.thecatapicase1.AppMain;

public class RESTfullCommandHandler implements HttpHandler 
{
//Public
	
	@Override
	public void handle(HttpExchange httpEx) 
		throws IOException 
	{
		try {
			URI cmdRequestURI = httpEx.getRequestURI();
			String cmdRequestStr = cmdRequestURI.getRawPath();

			AppMain.getApp().getErr().writeDebug(this.getClass().getName(), "handle", "CmdRequestStr: " + cmdRequestStr);
	
			RESTfullCommand cmd = new RESTfullCommand(cmdRequestStr);
			RESTfullCommandExecutor exec = new RESTfullCommandExecutor(cmd);
			//String resp = exec.execute();

			String contentType = AppDefs.MIMETYPE_XML;
			if(cmd.getCmdRequestVal() == AppDefs.REST_CMD_DOWNLOAD_IMAGE_VAL)
				contentType = AppDefs.MIMETYPE_JPG;
			
			//byte[] arrResp = resp.getBytes("utf-8");
			byte[] arrResp = exec.execute();
			if(arrResp == null) {
				contentType = AppDefs.MIMETYPE_XML;
				arrResp = "{ \"error\":\"Invalid image identifyer.\" }".getBytes("utf-8");
			}
			int respSz = arrResp.length;
						
			httpEx.sendResponseHeaders(200, respSz);
			httpEx.setAttribute("content-type", contentType);
	        OutputStream os = httpEx.getResponseBody();
	        os.write(arrResp);
	        os.close();
		}
		catch(Exception e) {
			AppMain.getApp().getErr().writeError(this.getClass().getName(), "handle", e.getMessage());
			e.printStackTrace();
		}
	}

}
