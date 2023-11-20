/*
 * Copyright(C) TLMV Consultoria e Sistemas Ltda. Todos os direitos reservados.
 *
 * RESTfullServer.java
 * Autor: Luiz Marcio Faria de Aquino Viana, 26/10/2022
 * revisoes: ...
 *
 */

package br.com.tlmv.thecatapicase1.rest;

import java.net.InetSocketAddress;
import java.util.ArrayList;

import com.sun.net.httpserver.HttpServer;

import br.com.tlmv.thecatapicase1.AppDefs;
import sun.net.www.http.HttpCapture;
import sun.net.www.protocol.http.Handler;

public class RESTfullServer
{
//Private
	private static RESTfullServer gServer = null;
	
	private HttpServer httpServer = null;
	
//Public
	
	public RESTfullServer() {
		RESTfullServer.gServer = this;
	}

	/* Methodes */
	
	public void init() {
		//TODO:
	}
	
	public void start() 
		throws Exception
	{
		InetSocketAddress addr = new InetSocketAddress(AppDefs.DEF_REST_SERVER_ADDR, AppDefs.DEF_REST_SERVER_PORT);		
		httpServer = HttpServer.create(addr, AppDefs.DEF_REST_REQUEST_QUEUE_SIZE);
		httpServer.createContext(AppDefs.DEF_REST_APPLICATION_CONTEXT, new RESTfullCommandHandler());
		httpServer.setExecutor(null);
		httpServer.start();
	}
	
	public void stop() {
		if(httpServer != null) {
			this.httpServer.stop(AppDefs.DEF_REST_SERVER_STOP_DELAY);
		}
	}
	
}
