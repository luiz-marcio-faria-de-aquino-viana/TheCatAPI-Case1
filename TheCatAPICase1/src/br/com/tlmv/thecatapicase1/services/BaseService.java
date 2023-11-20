/*
 * Copyright(C) TLMV Consultoria e Sistemas Ltda. Todos os direitos reservados.
 *
 * BaseService.java
 * Autor: Luiz Marcio Faria de Aquino Viana, 24/10/2022
 * revisoes: ...
 *
 */

package br.com.tlmv.thecatapicase1.services;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.URL;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.util.ArrayList;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.net.ssl.SSLSocketFactory;

import com.sun.net.ssl.HttpsURLConnection;
import com.sun.net.ssl.KeyManager;
import com.sun.net.ssl.KeyManagerFactory;
import com.sun.net.ssl.SSLContext;
import com.sun.net.ssl.TrustManager;

import br.com.tlmv.thecatapicase1.AppDefs;
import br.com.tlmv.thecatapicase1.AppError;
import br.com.tlmv.thecatapicase1.AppMain;
import sun.net.www.protocol.https.HttpsURLConnectionImpl;

public class BaseService 
{
//Public
	
	public BaseService() {
		/* nothing todo! */
	}
	
	/* Methodes */
	
	public String requestData(String strUrl, String contentType) {
		StringBuilder sb = new StringBuilder();
		
		try {
			boolean retry = false;
			int retryNum = 0;
			do {
				retry = false;
				
			    URL url = new URL(strUrl);
	
			    HttpsURLConnectionImpl con = (HttpsURLConnectionImpl)url.openConnection();
				con.setRequestMethod(AppDefs.DEF_REQUEST_METHOD_GET);
				con.setDoInput(true);
				con.setDoOutput(true);
				con.setUseCaches(false);
				con.setConnectTimeout(AppDefs.DEF_CONNECTION_TIMEOUT);
				con.setReadTimeout(AppDefs.DEF_READ_TIMEOUT);
				con.setRequestProperty("Content-Type", contentType);
				con.setRequestProperty("x-api-key", AppDefs.DEF_API_KEY);
				con.setRequestProperty("user-agent", AppDefs.DEF_USER_AGENT);
		      
				int responseCode = con.getResponseCode();
				try {
					InputStreamReader is = new InputStreamReader(con.getInputStream());		
					BufferedReader bis = new BufferedReader(is);

					if( (responseCode >= AppDefs.ERR_HTTP_RESPCODE_SUCCESSFUL) && (responseCode < AppDefs.ERR_HTTP_RESPCODE_REDIRECTION) ) {
						String sbuf = null;
						while ((sbuf = bis.readLine()) != null) {
							sb.append(sbuf);
						}
					}
				}
				catch(Exception e) {
					if(responseCode == AppDefs.ERR_HTTP_RESPCODE_CLIENT_ERROR_TOO_MANY_RETRY) {
						String errmsg = String.format(AppError.ERR_TOO_MANY_REQUESTS, AppDefs.THREAD_FAILURE_SLEEP_TIME);
						AppMain.getApp().getErr().writeError(this.getClass().getName(), "requestData", errmsg);
						
						if(retryNum < AppDefs.DEF_REQUEST_MAXRETRY) {
							retry = true;
							retryNum += 1;
		
							try {
								Thread.sleep(AppDefs.THREAD_FAILURE_SLEEP_TIME);
							}
							catch(Exception e1) { }
						}
					}
					else {
						String errmsg = String.format(AppError.ERR_HTTP_RESPONSE_ERROR, responseCode);
						AppMain.getApp().getErr().writeError(this.getClass().getName(), "requestData", errmsg);
					}
				}
			} while( retry );
		}
		catch(Exception e) {
			AppMain.getApp().getErr().writeError(this.getClass().getName(), "requestData", e.getMessage());
			e.printStackTrace();
		}
		
		return sb.toString();
	}
	
	public String requestData(String strUrl) {
		String strHtml = new String();
		try {			
			strHtml = this.requestData(strUrl, AppDefs.MIMETYPE_XML);
		}
		catch(Exception e) {
			AppMain.getApp().getErr().writeError(this.getClass().getName(), "requestData", e.getMessage());
			e.printStackTrace();
		}		
		return strHtml;
	}
	
	public BufferedImage requestImageData(String strUrl, String mimeType) {
		BufferedImage image = null;
		
		try {
			boolean retry = false;
			int retryNum = 0;
			do {
				retry = false;

				URL url = new URL(strUrl);
	
			    //SSLSocketFactory sockFact = getDefaultSocketFactoryJAVA();
			     
			    HttpsURLConnectionImpl con = (HttpsURLConnectionImpl)url.openConnection();
			    //con.setSSLSocketFactory(sockFact);
				con.setRequestMethod(AppDefs.DEF_REQUEST_METHOD_GET);
				con.setDoInput(true);
				con.setDoOutput(true);
				con.setUseCaches(false);
				con.setConnectTimeout(AppDefs.DEF_CONNECTION_TIMEOUT);
				con.setReadTimeout(AppDefs.DEF_READ_TIMEOUT);
				con.setRequestProperty("Content-Type", mimeType);
				con.setRequestProperty("x-api-key", AppDefs.DEF_API_KEY);
				con.setRequestProperty("user-agent", AppDefs.DEF_USER_AGENT);
				
				int responseCode = con.getResponseCode();
				if( (responseCode >= AppDefs.ERR_HTTP_RESPCODE_SUCCESSFUL) && (responseCode < AppDefs.ERR_HTTP_RESPCODE_REDIRECTION) ) {
					try {
						image = ImageIO.read(con.getInputStream());
					}
					catch(Exception e) {
						String errmsg = String.format(AppError.ERR_UNSUPORTED_IMAGE_FORMAT, strUrl);
						AppMain.getApp().getErr().writeError(this.getClass().getName(), "requestData", errmsg);

						if(retryNum < AppDefs.DEF_REQUEST_MAXRETRY) {
							retry = true;
							retryNum += 1;
		
							try {
								Thread.sleep(AppDefs.THREAD_FAILURE_SLEEP_TIME);
							}
							catch(Exception e1) { }
						}
					}
				}
				else if(responseCode == AppDefs.ERR_HTTP_RESPCODE_CLIENT_ERROR_TOO_MANY_RETRY) {
					String errmsg = String.format(AppError.ERR_TOO_MANY_REQUESTS, AppDefs.THREAD_FAILURE_SLEEP_TIME);
					AppMain.getApp().getErr().writeError(this.getClass().getName(), "requestData", errmsg);
					
					if(retryNum < AppDefs.DEF_REQUEST_MAXRETRY) {
						retry = true;
						retryNum += 1;
	
						try {
							Thread.sleep(AppDefs.THREAD_FAILURE_SLEEP_TIME);
						}
						catch(Exception e1) { }
					}
				}
				else {
					String errmsg = String.format(AppError.ERR_HTTP_RESPONSE_ERROR, responseCode);
					AppMain.getApp().getErr().writeError(this.getClass().getName(), "requestData", errmsg);
				}
				
				
			} while( retry );
				
		}
		catch(Exception e) {
			AppMain.getApp().getErr().writeError(this.getClass().getName(), "requestImageData", e.getMessage());
			e.printStackTrace();
		}
		
		return image;
	}
	
	public byte[] requestImageDataEx(String strUrl, String mimeType) {
		ArrayList<Byte> lsImage = new ArrayList<Byte>();

		int bufsz = AppDefs.DEF_FILE_READ_BUFFER_SIZE;
		byte[] buf = new byte[bufsz];
		
		byte[] bImage = null;
		
		try {
			boolean retry = false;
			int retryNum = 0;
			do {
				retry = false;

				URL url = new URL(strUrl);
	
			    //SSLSocketFactory sockFact = getDefaultSocketFactoryJAVA();
			     
			    HttpsURLConnectionImpl con = (HttpsURLConnectionImpl)url.openConnection();
			    //con.setSSLSocketFactory(sockFact);
				con.setRequestMethod(AppDefs.DEF_REQUEST_METHOD_GET);
				con.setDoInput(true);
				con.setDoOutput(true);
				con.setUseCaches(false);
				con.setConnectTimeout(AppDefs.DEF_CONNECTION_TIMEOUT);
				con.setReadTimeout(AppDefs.DEF_READ_TIMEOUT);
				con.setRequestProperty("Content-Type", mimeType);
				con.setRequestProperty("x-api-key", AppDefs.DEF_API_KEY);
				con.setRequestProperty("user-agent", AppDefs.DEF_USER_AGENT);
				
				int responseCode = con.getResponseCode();
				if( (responseCode >= AppDefs.ERR_HTTP_RESPCODE_SUCCESSFUL) && (responseCode < AppDefs.ERR_HTTP_RESPCODE_REDIRECTION) ) {
					try {
						InputStream is = con.getInputStream();
						int numread = -1;
						while((numread = is.read(buf, 0, bufsz)) > 0) {
							for(int i = 0; i < numread; i++) {
								byte b = buf[i];
								lsImage.add(b);								
							}
						}
						
						bImage = new byte[lsImage.size()];
						for(int i = 0; i < lsImage.size(); i++) {
							bImage[i] = (byte)lsImage.get(i);							
						}
					}
					catch(Exception e) {
						AppMain.getApp().getErr().writeError(this.getClass().getName(), "requestData", e.getMessage());
						e.printStackTrace();
					}
				}
				else if(responseCode == AppDefs.ERR_HTTP_RESPCODE_CLIENT_ERROR_TOO_MANY_RETRY) {
					String errmsg = String.format(AppError.ERR_TOO_MANY_REQUESTS, AppDefs.THREAD_FAILURE_SLEEP_TIME);
					AppMain.getApp().getErr().writeError(this.getClass().getName(), "requestData", errmsg);
					
					if(retryNum < AppDefs.DEF_REQUEST_MAXRETRY) {
						retry = true;
						retryNum += 1;
	
						try {
							Thread.sleep(AppDefs.THREAD_FAILURE_SLEEP_TIME);
						}
						catch(Exception e1) { }
					}
				}
				else {
					String errmsg = String.format(AppError.ERR_HTTP_RESPONSE_ERROR, responseCode);
					AppMain.getApp().getErr().writeError(this.getClass().getName(), "requestData", errmsg);
				}
				
			} while( retry );
				
		}
		catch(Exception e) {
			AppMain.getApp().getErr().writeError(this.getClass().getName(), "requestImageData", e.getMessage());
			e.printStackTrace();
		}
		
		return bImage;
	}
	    
}
