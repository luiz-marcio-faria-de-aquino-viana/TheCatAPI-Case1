/*
 * Copyright(C) TLMV Consultoria e Sistemas Ltda. Todos os direitos reservados.
 *
 * ImageService.java
 * Autor: Luiz Marcio Faria de Aquino Viana, 24/10/2022
 * revisoes: ...
 *
 */

package br.com.tlmv.thecatapicase1.services;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.tlmv.thecatapicase1.AppContext;
import br.com.tlmv.thecatapicase1.AppDefs;
import br.com.tlmv.thecatapicase1.AppMain;
import br.com.tlmv.thecatapicase1.utils.FileUtil;

public class ImageService extends BaseService 
{
//Public
	
	/* Methodes */
	
	public String requestAnyImage() {
		String wsEndPoint = AppDefs.WS_ENDPOINT_IMAGES_SEARCH;
		
		String strJSON = this.requestData(wsEndPoint);
		return strJSON;
	}
	
	public JSONObject requestAnyImageJSON()
		throws Exception
	{
		String strJSON = this.requestAnyImage();
		JSONArray aJSON = new JSONArray(strJSON);

		JSONObject objJSON = null;
		if(aJSON.length() > 0)
			objJSON = aJSON.getJSONObject(0);
		return objJSON;
	}
	
	public JSONArray requestAnyImagesLimit10JSON()
		throws Exception
	{
		String wsEndPoint = AppDefs.WS_ENDPOINT_IMAGES_SEARCH10;
		
		String strJSON = this.requestData(wsEndPoint);
		JSONArray aJSON = new JSONArray(strJSON);
		return aJSON;
	}
	
	public JSONArray requestImagesByCategoryIdsLimit100JSON(String strLimit, String strCategoryIds)
		throws Exception
	{
		String wsEndPoint = String.format(AppDefs.WS_ENDPOINT_IMAGES_BY_CATEGORY_SEARCH100, strLimit, strCategoryIds, AppDefs.DEF_API_KEY);
		
		String strJSON = this.requestData(wsEndPoint);
		JSONArray aJSON = new JSONArray(strJSON);
		return aJSON;
	}
	
	public JSONArray requestImagesByBreedIdsLimit100JSON(String strLimit, String strBreedIds)
		throws Exception
	{
		String wsEndPoint = String.format(AppDefs.WS_ENDPOINT_IMAGES_BY_BREED_SEARCH100, strLimit, strBreedIds, AppDefs.DEF_API_KEY);
		
		String strJSON = this.requestData(wsEndPoint);
		JSONArray aJSON = new JSONArray(strJSON);
		return aJSON;
	}

	public Image requestDownloadImageFromURL(String strBaseUrl, String pathName, String fileName)
		throws Exception		
	{
		AppContext ctx = AppMain.getApp().getContext();
		
		BufferedImage image = null;
		
		String outputFileName = pathName + "/" + fileName;
		File f = new File(outputFileName);
		if( !f.exists() ) {
			String strMimeType = FileUtil.getMimeType(strBaseUrl);
			
			//String strUrl = strBaseUrl + "?api_key=" + AppDefs.DEF_API_KEY;
			String strUrl = strBaseUrl;
			image = this.requestImageData(strUrl, strMimeType);
			if(image != null) {
				File fileout = new File(outputFileName);
		        ImageIO.write(image, "jpg", fileout);
			}
		}
		else {
			String warnmsg = String.format("File [%s] already exist in image repository.", fileName);
			AppMain.getApp().getErr().writeWarn(this.getClass().getName(), "requestImageData", warnmsg);
		}
		return image;
	}

	public byte[] requestDownloadImageFromEx(String strBaseUrl, String pathName, String fileName)
		throws Exception		
	{
		AppContext ctx = AppMain.getApp().getContext();
		
		byte[] bImage = null;
		
		String outputFileName = pathName + "/" + fileName;
		File f = new File(outputFileName);
		if( !f.exists() ) {
			String strMimeType = FileUtil.getMimeType(strBaseUrl);
			
			//String strUrl = strBaseUrl + "?api_key=" + AppDefs.DEF_API_KEY;
			String strUrl = strBaseUrl;
			bImage = this.requestImageDataEx(strUrl, strMimeType);
			if(bImage != null) {
		        FileUtil.saveFile(outputFileName, bImage);
			}
		}
		else {
			String warnmsg = String.format("File [%s] already exist in image repository.", fileName);
			AppMain.getApp().getErr().writeWarn(this.getClass().getName(), "requestImageData", warnmsg);
		}
		return bImage;
	}

	public String requestDownloadHtmlFromURL(String strUrl, String fileName)
		throws Exception		
	{
		AppMain app = AppMain.getApp();
		AppContext ctx = app.getContext();

		String strHtml = new String();
		
		String outputFileName = ctx.getDataImagesDir() + "/" + fileName;
		File f = new File(outputFileName);
		if( !f.exists() ) {		
			strHtml = this.requestData(strUrl, AppDefs.MIMETYPE_HTML);
			if(strHtml != null) {
				FileUtil.saveFile(outputFileName, strHtml.getBytes("utf-8"));
			}
		}
		else {
			String warnmsg = String.format("File [%s] already exist in image repository.", fileName);
			AppMain.getApp().getErr().writeWarn(this.getClass().getName(), "requestDownloadHtmlFromURL", warnmsg);
		}
		return strHtml;
	}

}
