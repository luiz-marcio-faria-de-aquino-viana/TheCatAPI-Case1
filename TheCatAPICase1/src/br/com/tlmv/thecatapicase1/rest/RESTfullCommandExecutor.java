/*
 * Copyright(C) TLMV Consultoria e Sistemas Ltda. Todos os direitos reservados.
 *
 * RESTfullCommandExecutor.java
 * Autor: Luiz Marcio Faria de Aquino Viana, 26/10/2022
 * revisoes: ...
 *
 */

package br.com.tlmv.thecatapicase1.rest;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Collection;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import br.com.tlmv.thecatapicase1.AppContext;
import br.com.tlmv.thecatapicase1.AppDataManager;
import br.com.tlmv.thecatapicase1.AppDefs;
import br.com.tlmv.thecatapicase1.AppMain;
import br.com.tlmv.thecatapicase1.data.BaseObject;
import br.com.tlmv.thecatapicase1.data.Breed;
import br.com.tlmv.thecatapicase1.data.Image;
import br.com.tlmv.thecatapicase1.nosql.BreedTable;
import br.com.tlmv.thecatapicase1.nosql.CatsWithGlassesTable;
import br.com.tlmv.thecatapicase1.nosql.CatsWithHatTable;
import br.com.tlmv.thecatapicase1.nosql.ImageTable;
import br.com.tlmv.thecatapicase1.nosql.query.QueryExecutor;
import br.com.tlmv.thecatapicase1.utils.FileUtil;

public class RESTfullCommandExecutor 
{
//Private
	private RESTfullCommand cmdRequest = null;
	
//Public
	
	public RESTfullCommandExecutor(RESTfullCommand cmdRequest) {
		this.cmdRequest = cmdRequest;
	}
	
	/* Methodes */

//	public String executeCmdCopyright() {
//		AppMain.getApp().getErr().writeLog(this.getClass().getName(), "executeCmdCopyright", "Executing command to show application copyright...");
//		
//		String str = String.format("%s %s\n\n%s\n", 
//			AppDefs.APP_NAME, 
//			AppDefs.APP_VERSAO,
//			AppDefs.APP_COPYRIGHT);
//		return str;
//	}
//	
//	public String executeCmdListAllBreeds() {
//		AppMain.getApp().getErr().writeLog(this.getClass().getName(), "executeCmdListAllBreeds", "Executing command to list all Breeds...");
//
//		AppDataManager dataMan = AppMain.getApp().getDataMan();
//		
//		StringBuilder sb = new StringBuilder();
//		
//		BreedTable tblBreed = dataMan.getTblBreed();
//		Hashtable<String,BaseObject> tblBreedData = tblBreed.getTableData();
//		Collection<BaseObject> lsBreedData = tblBreedData.values();
//		
//		sb.append("[ ");
//		int n = 0;
//		for(BaseObject obj : lsBreedData) {
//			Breed oBreed = (Breed)obj;
//			if(n == 0)
//				sb.append(oBreed.toStrJSON());
//			else
//				sb.append("," + oBreed.toStrJSON());
//			n++;
//		}
//		sb.append(" ]");
//		
//		return sb.toString();
//	}
//
//	public String executeCmdListBreed() {
//		AppMain.getApp().getErr().writeLog(this.getClass().getName(), "executeCmdListBreed", "Executing command to list one Breed...");
//
//		AppDataManager dataMan = AppMain.getApp().getDataMan();
//		
//		StringBuilder sb = new StringBuilder();
//		
//		String key = cmdRequest.getCmdParam();
//		AppMain.getApp().getErr().writeDebug(this.getClass().getName(), "executeCmdListBreedsByTemperament", "KEY: " + key);
//		
//		BreedTable tblBreed = dataMan.getTblBreed();
//		BaseObject obj = tblBreed.getObj(key);
//		if(obj != null) {
//			Breed oBreed = (Breed)obj;
//
//			sb.append("[ ");
//			sb.append(oBreed.toStrJSON());
//			sb.append(" ]");
//		}
//		else {
//			return new String("{ \"error\":\"Invalid breed identifyer.\" }");
//		}
//		
//		return sb.toString();
//	}
//
//	public String executeCmdListBreedsByOrigin() {
//		AppMain.getApp().getErr().writeLog(this.getClass().getName(), "executeCmdListBreedsByOrigin", "Executing command to list Breeds by origin...");
//
//		AppDataManager dataMan = AppMain.getApp().getDataMan();
//		
//		StringBuilder sb = new StringBuilder();
//
//		String origin = cmdRequest.getCmdParam();
//		AppMain.getApp().getErr().writeDebug(this.getClass().getName(), "executeCmdListBreedsByTemperament", "ORIGIN: " + origin);
//
//		if(origin != null) {
//			BreedTable tblBreed = dataMan.getTblBreed();
//			Hashtable<String,BaseObject> tblBreedData = tblBreed.getTableData();
//			Collection<BaseObject> lsBreedData = tblBreedData.values();
//			
//			sb.append("[ ");
//			int n = 0;
//			for(BaseObject obj : lsBreedData) {
//				Breed oBreed = (Breed)obj;
//				if( (oBreed.getOrigin() != null) && 
//					(oBreed.getOrigin().equals(origin)) ) {
//					if(n == 0)
//						sb.append(oBreed.toStrJSON());
//					else
//						sb.append("," + oBreed.toStrJSON());
//					n++;
//				}
//			}
//			sb.append(" ]");
//		}
//		else {
//			return new String("{ \"error\":\"Invalid breed origin.\" }");
//		}
//		
//		return sb.toString();
//	}
//
//	public String executeCmdListBreedsByTemperament() {
//		AppMain.getApp().getErr().writeLog(this.getClass().getName(), "executeCmdListBreedsByTemperament", "Executing command to list Breeds by temperament...");
//		
//		AppDataManager dataMan = AppMain.getApp().getDataMan();
//		
//		StringBuilder sb = new StringBuilder();
//		
//		String temperament = cmdRequest.getCmdParam();
//		AppMain.getApp().getErr().writeDebug(this.getClass().getName(), "executeCmdListBreedsByTemperament", "TEMPERAMENT: " + temperament);
//
//		if(temperament != null) {
//			BreedTable tblBreed = dataMan.getTblBreed();
//			Hashtable<String,BaseObject> tblBreedData = tblBreed.getTableData();
//			Collection<BaseObject> lsBreedData = tblBreedData.values();
//			
//			sb.append("[ ");
//			int n = 0;
//			for(BaseObject obj : lsBreedData) {
//				Breed oBreed = (Breed)obj;
//				if( (oBreed.getTemperament() != null) && 
//					(oBreed.getTemperament().contains(temperament)) ) {
//					if(n == 0)
//						sb.append(oBreed.toStrJSON());
//					else
//						sb.append("," + oBreed.toStrJSON());
//					n++;
//				}
//			}
//			sb.append(" ]");
//		}
//		else {
//			return new String("{ \"error\":\"Invalid breed temperament.\" }");
//		}
//		
//		return sb.toString();
//	}
//	
//	public String executeCmdListAllImages() {
//		AppMain.getApp().getErr().writeLog(this.getClass().getName(), "executeCmdListAllImages", "Executing command to list all Images...");
//		
//		AppDataManager dataMan = AppMain.getApp().getDataMan();
//		
//		StringBuilder sb = new StringBuilder();
//		
//		ImageTable tblImage = dataMan.getTblImage();
//		Hashtable<String,BaseObject> tblImageData = tblImage.getTableData();
//		Collection<BaseObject> lsImageData = tblImageData.values();
//
//		CatsWithGlassesTable tblCatsWithGlasses = dataMan.getTblCatsWithGlasses();
//		Hashtable<String,BaseObject> tblCatsWithGlassesData = tblCatsWithGlasses.getTableData();
//		Collection<BaseObject> lsCatsWithGlassesData = tblCatsWithGlassesData.values();
//		
//		CatsWithHatTable tblCatsWithHat = dataMan.getTblCatsWithHat();
//		Hashtable<String,BaseObject> tblCatsWithHatData = tblCatsWithHat.getTableData();
//		Collection<BaseObject> lsCatsWithHatData = tblCatsWithHatData.values();
//		
//		int n = 0;
//		
//		sb.append("[ ");
//
//		for(BaseObject obj : lsImageData) {
//			Image oImage = (Image)obj;
//			if(n == 0)
//				sb.append(oImage.toStrJSON());
//			else
//				sb.append("," + oImage.toStrJSON());
//			n++;
//		}
//
//		for(BaseObject obj : lsCatsWithGlassesData) {
//			Image oImage = (Image)obj;
//			if(n == 0)
//				sb.append(oImage.toStrJSON());
//			else
//				sb.append("," + oImage.toStrJSON());
//			n++;
//		}
//
//		for(BaseObject obj : lsCatsWithHatData) {
//			Image oImage = (Image)obj;
//			if(n == 0)
//				sb.append(oImage.toStrJSON());
//			else
//				sb.append("," + oImage.toStrJSON());
//			n++;
//		}
//
//		sb.append(" ]");
//		
//		return sb.toString();		
//	}
//	
//	public String executeCmdListImage() {
//		AppMain.getApp().getErr().writeLog(this.getClass().getName(), "executeCmdListImage", "Executing command to list one Image...");
//		
//		AppDataManager dataMan = AppMain.getApp().getDataMan();
//		
//		StringBuilder sb = new StringBuilder();
//		
//		String key = cmdRequest.getCmdParam();
//		AppMain.getApp().getErr().writeDebug(this.getClass().getName(), "executeCmdListImage", "KEY: " + key);
//		
//		ImageTable tblImage = dataMan.getTblImage();
//		CatsWithGlassesTable tblCatsWithGlasses = dataMan.getTblCatsWithGlasses();
//		CatsWithHatTable tblCatsWithHat = dataMan.getTblCatsWithHat();
//
//		BaseObject obj = tblImage.getObj(key);
//		if(obj == null) {
//			obj = tblCatsWithGlasses.getObj(key);
//			if(obj == null) {
//				obj = tblCatsWithHat.getObj(key);
//			}
//		}
//			
//		if(obj != null) {
//			Image oImage = (Image)obj;
//
//			sb.append("[ ");
//			sb.append(oImage.toStrJSON());
//			sb.append(" ]");
//		}
//		else {
//			return new String("{ \"error\":\"Invalid image identifyer.\" }");
//		}
//		
//		return sb.toString();
//	}
//
//	public String executeCmdListImagesByCatWithGlasses() {
//		AppMain.getApp().getErr().writeLog(this.getClass().getName(), "executeCmdListAllImages", "Executing command to list all CatsWithGlasses images...");
//		
//		AppDataManager dataMan = AppMain.getApp().getDataMan();
//		
//		StringBuilder sb = new StringBuilder();
//		
//		CatsWithGlassesTable tblCatsWithGlasses = dataMan.getTblCatsWithGlasses();
//		Hashtable<String,BaseObject> tblCatsWithGlassesData = tblCatsWithGlasses.getTableData();
//		Collection<BaseObject> lsCatsWithGlassesData = tblCatsWithGlassesData.values();
//		
//		int n = 0;
//		
//		sb.append("[ ");
//
//		for(BaseObject obj : lsCatsWithGlassesData) {
//			Image oImage = (Image)obj;
//			if(n == 0)
//				sb.append(oImage.toStrJSON());
//			else
//				sb.append("," + oImage.toStrJSON());
//			n++;
//		}
//
//		sb.append(" ]");
//		
//		return sb.toString();		
//	}
//
//	public String executeCmdListImagesByCatWithHat() {												
//		AppMain.getApp().getErr().writeLog(this.getClass().getName(), "executeCmdListAllImages", "Executing command to list all CatsWithHat images...");
//		
//		AppDataManager dataMan = AppMain.getApp().getDataMan();
//		
//		StringBuilder sb = new StringBuilder();
//		
//		CatsWithHatTable tblCatsWithHat = dataMan.getTblCatsWithHat();
//		Hashtable<String,BaseObject> tblCatsWithHatData = tblCatsWithHat.getTableData();
//		Collection<BaseObject> lsCatsWithHatData = tblCatsWithHatData.values();
//		
//		int n = 0;
//		
//		sb.append("[ ");
//
//		for(BaseObject obj : lsCatsWithHatData) {
//			Image oImage = (Image)obj;
//			if(n == 0)
//				sb.append(oImage.toStrJSON());
//			else
//				sb.append("," + oImage.toStrJSON());
//			n++;
//		}
//
//		sb.append(" ]");
//		
//		return sb.toString();		
//	}
//
//	public byte[] executeCmdDownloadImage() {
//		AppMain.getApp().getErr().writeLog(this.getClass().getName(), "executeCmdDownloadImage", "Executing command to download an Image...");
//		
//		byte[] imgResult = null;
//		
//		AppContext ctx = AppMain.getApp().getContext();
//		AppDataManager dataMan = AppMain.getApp().getDataMan();
//		
//		StringBuilder sb = new StringBuilder();
//		
//		String key = cmdRequest.getCmdParam();
//		AppMain.getApp().getErr().writeDebug(this.getClass().getName(), "executeCmdDownloadImage", "KEY: " + key);
//		
//		ImageTable tblImage = dataMan.getTblImage();
//		CatsWithGlassesTable tblCatsWithGlasses = dataMan.getTblCatsWithGlasses();
//		CatsWithHatTable tblCatsWithHat = dataMan.getTblCatsWithHat();
//
//		BaseObject obj = tblImage.getObj(key);
//		String pathName = null;
//		if(obj == null) {
//			obj = tblCatsWithGlasses.getObj(key);
//			if(obj == null) {
//				obj = tblCatsWithHat.getObj(key);
//				if(obj != null)
//					pathName = ctx.getDataCatsWithHatDir();									
//			}
//			else {
//				pathName = ctx.getDataCatsWithGlassesDir();				
//			}
//		}
//		else {
//			pathName = ctx.getDataImagesDir();
//		}
//			
//		if(obj != null) {
//			Image oImage = (Image)obj;
//
//			String fileName = pathName + "/" + oImage.getLocalFile();
//			File f = new File(fileName);
//			if( f.exists() ) {
//				imgResult = FileUtil.readBinaryData(fileName);
//			}
//		}
//		
//		return imgResult;
//	}
//	
//	public String executeCmdListImagesByBreed() {	
//		return "Not implemented!";
//	}
//
//	public String executeCmdListImagesByTemperament() {												
//		return "Not implemented!";
//	}
//	
//	public String executeCmdListImagesByOrigin() {
//		return "Not implemented!";		
//	}
//
//	public String executeStr() {
//		String strResult = new String();
//		
//		if( AppDefs.REST_CMD_LISTALL_BREEDS_VAL.equals(this.cmdRequest.getCmdRequestVal()) ) {
//			strResult = this.executeCmdListAllBreeds();			
//		}
//		else if( AppDefs.REST_CMD_LIST_BREED_VAL.equals(this.cmdRequest.getCmdRequestVal()) ) {
//			strResult = this.executeCmdListBreed();						
//		}
//		else if( AppDefs.REST_CMD_LIST_BREEDS_BY_TEMPERAMENT_VAL.equals(this.cmdRequest.getCmdRequestVal()) ) {
//			strResult = this.executeCmdListBreedsByTemperament();									
//		}
//		else if( AppDefs.REST_CMD_LIST_BREEDS_BY_ORIGIN_VAL.equals(this.cmdRequest.getCmdRequestVal()) ) {
//			strResult = this.executeCmdListBreedsByOrigin();									
//		}
//		else if( AppDefs.REST_CMD_LISTALL_IMAGES_VAL.equals(this.cmdRequest.getCmdRequestVal()) ) {
//			strResult = this.executeCmdListAllImages();												
//		}
//		else if( AppDefs.REST_CMD_LIST_IMAGE_VAL.equals(this.cmdRequest.getCmdRequestVal()) ) {
//			strResult = this.executeCmdListImage();												
//		}
//		else if( AppDefs.REST_CMD_LIST_IMAGES_BY_CATWITHGLASSES_VAL.equals(this.cmdRequest.getCmdRequestVal()) ) {
//			strResult = this.executeCmdListImagesByCatWithGlasses();												
//		}
//		else if( AppDefs.REST_CMD_LIST_IMAGES_BY_CATWITHHAT_VAL.equals(this.cmdRequest.getCmdRequestVal()) ) {
//			strResult = this.executeCmdListImagesByCatWithHat();												
//		}
//		else if( AppDefs.REST_CMD_LIST_IMAGES_BY_BREED_VAL.equals(this.cmdRequest.getCmdRequestVal()) ) {
//			strResult = this.executeCmdListImagesByBreed();												
//		}
//		else if( AppDefs.REST_CMD_LIST_IMAGES_BY_TEMPERAMENT_VAL.equals(this.cmdRequest.getCmdRequestVal()) ) {
//			strResult = this.executeCmdListImagesByTemperament();												
//		}
//		else if( AppDefs.REST_CMD_LIST_IMAGES_BY_ORIGIN_VAL.equals(this.cmdRequest.getCmdRequestVal()) ) {
//			strResult = this.executeCmdListImagesByOrigin();												
//		}
//		else {
//			strResult = this.executeCmdCopyright();
//		}
//		
//		return strResult;
//	}

	public byte[] execute() {
		byte[] arrResult = null;
		
		try {
			QueryExecutor query = new QueryExecutor(cmdRequest);
			arrResult = query.execute();
		}
		catch(Exception e) { 
			AppMain.getApp().getErr().writeError(this.getClass().getName(), "execute", e.getMessage());
			e.printStackTrace();
		}
		
		return arrResult;
	}

}
