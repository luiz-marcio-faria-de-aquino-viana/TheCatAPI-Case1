/*
 * Copyright(C) TLMV Consultoria e Sistemas Ltda. Todos os direitos reservados.
 *
 * QueryExecutor.java
 * Autor: Luiz Marcio Faria de Aquino Viana, 26/10/2022
 * revisoes: ...
 *
 */

package br.com.tlmv.thecatapicase1.nosql.query;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
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
import br.com.tlmv.thecatapicase1.rest.RESTfullCommand;
import br.com.tlmv.thecatapicase1.utils.FileUtil;

public class QueryExecutor
{
//Private
	private RESTfullCommand cmdRequest = null;
	private QueryWorker[] arrWorker = null;
	private StringBuilder output = null;
	
	private StringBuilder dispatchQueryWorker(Collection<BaseObject> lsBaseObjectData) {
		AppMain.getApp().getErr().writeDebug(this.getClass().getName(), "dispatchQueryWorker", "Dispatch query workers...");

		StringBuilder sb = new StringBuilder();
		
		//DISPATCH_THREADS

		BaseObject[] arrObj = lsBaseObjectData.toArray(new BaseObject[lsBaseObjectData.size()]);
		Integer szArrObj = arrObj.length;
		
		Integer szArrObjPart = szArrObj / AppDefs.THREAD_MAX_WORKERS;
		Integer startPos = 0;
		Integer endPos = szArrObjPart;
		for(int i = 0; i < AppDefs.THREAD_MAX_WORKERS - 1; i++) {
			QueryWorker worker = new QueryWorker(
				i + 1, 
				this.cmdRequest, 
				arrObj, 
				startPos, 
				endPos);
			arrWorker[i] = worker; 
			worker.startQuery();
			
			startPos = endPos;
			endPos = endPos + szArrObjPart;
		}

		endPos = szArrObj;
		QueryWorker lastWorker = new QueryWorker(
				AppDefs.THREAD_MAX_WORKERS, 
				this.cmdRequest, 
				arrObj, 
				startPos, 
				endPos);
			arrWorker[AppDefs.THREAD_MAX_WORKERS - 1] = lastWorker; 
			lastWorker.startQuery();

		//BARRIER - WAINTING_FOR_RESULTS
			
		for(int i = 0; i < AppDefs.THREAD_MAX_WORKERS; i++) {
			QueryWorker worker = arrWorker[i];
			worker.waitForComplete();
			if(worker.getOutput().length() != 0) {
				if(sb.length() == 0) {
					sb.append(worker.getOutput());
				}
				else {
					sb.append(",");
					sb.append(worker.getOutput());
				}
			}
		}
		
		AppMain.getApp().getErr().writeDebug(this.getClass().getName(), "dispatchQueryWorker", "Query workers finished!");

		return sb;
	}
	
//Public
	
	public QueryExecutor(RESTfullCommand cmdRequest) {
		this.cmdRequest = cmdRequest;
		this.arrWorker = new QueryWorker[AppDefs.THREAD_MAX_WORKERS];
		this.output = new StringBuilder();
	}
	
	/* Methodes */

	public void copyright() {
		AppMain.getApp().getErr().writeDebug(this.getClass().getName(), "copyright", "Show application copyright...");
		
		String str = String.format("%s %s\n\n%s\n", 
			AppDefs.APP_NAME, 
			AppDefs.APP_VERSAO,
			AppDefs.APP_COPYRIGHT);
		this.output.append(str);
	}
	
	public void findAllBreeds() {
		AppMain.getApp().getErr().writeDebug(this.getClass().getName(), "findAllBreeds", "Find all Breeds...");

		AppDataManager dataMan = AppMain.getApp().getDataMan();
		
		BreedTable tblBreed = dataMan.getTblBreed();
		Hashtable<String,BaseObject> tblBreedData = tblBreed.getTableData();
		Collection<BaseObject> lsBreedData = tblBreedData.values();
		
		this.output.append("[ ");
		this.output.append(this.dispatchQueryWorker(lsBreedData));				
		this.output.append(" ]");
	}
	
	public void findBreed() {
		AppMain.getApp().getErr().writeDebug(this.getClass().getName(), "findBreed", "Find one Breed...");

		AppDataManager dataMan = AppMain.getApp().getDataMan();
		
		String key = cmdRequest.getCmdParam();
		AppMain.getApp().getErr().writeDebug(this.getClass().getName(), "findBreed", "KEY: " + key);
		
		BreedTable tblBreed = dataMan.getTblBreed();
		BaseObject obj = tblBreed.getObj(key);
		if(obj != null) {
			Breed oBreed = (Breed)obj;

			this.output.append("[ ");
			this.output.append(oBreed.toStrJSON());
			this.output.append(" ]");
		}
		else {
			this.output.append("{ \"error\":\"Invalid breed identifyer.\" }");
		}
	}
	
	public void findBreedsByOrigin() {
		AppMain.getApp().getErr().writeDebug(this.getClass().getName(), "findBreedsByOrigin", "Find all Breeds...");

		AppDataManager dataMan = AppMain.getApp().getDataMan();
		
		String origin = cmdRequest.getCmdParam();
		AppMain.getApp().getErr().writeDebug(this.getClass().getName(), "findBreedsByOrigin", "ORIGIN: " + origin);
		
		if(origin == null) {
			this.output.append("{ \"error\":\"Invalid breed origin.\" }");
			return;
		}
		
		BreedTable tblBreed = dataMan.getTblBreed();
		Hashtable<String,BaseObject> tblBreedData = tblBreed.getTableData();
		Collection<BaseObject> lsBreedData = tblBreedData.values();
		
		this.output.append("[ ");
		this.output.append(this.dispatchQueryWorker(lsBreedData));				
		this.output.append(" ]");
	}

	public void findBreedsByTemperament() {
		AppMain.getApp().getErr().writeDebug(this.getClass().getName(), "findBreedsByTemperament", "Find all Breeds...");

		AppDataManager dataMan = AppMain.getApp().getDataMan();
		
		String origin = cmdRequest.getCmdParam();
		AppMain.getApp().getErr().writeDebug(this.getClass().getName(), "findBreedsByTemperament", "ORIGIN: " + origin);
		
		if(origin == null) {
			this.output.append("{ \"error\":\"Invalid breed temperament.\" }");
			return;
		}
		
		BreedTable tblBreed = dataMan.getTblBreed();
		Hashtable<String,BaseObject> tblBreedData = tblBreed.getTableData();
		Collection<BaseObject> lsBreedData = tblBreedData.values();
		
		this.output.append("[ ");
		this.output.append(this.dispatchQueryWorker(lsBreedData));				
		this.output.append(" ]");
	}
	
	public void findAllImages() {
		AppMain.getApp().getErr().writeDebug(this.getClass().getName(), "findAllImages", "Find all Images...");
		
		AppDataManager dataMan = AppMain.getApp().getDataMan();
		
		ImageTable tblImage = dataMan.getTblImage();
		Hashtable<String,BaseObject> tblImageData = tblImage.getTableData();
		Collection<BaseObject> lsImageData = tblImageData.values();

		CatsWithGlassesTable tblCatsWithGlasses = dataMan.getTblCatsWithGlasses();
		Hashtable<String,BaseObject> tblCatsWithGlassesData = tblCatsWithGlasses.getTableData();
		Collection<BaseObject> lsCatsWithGlassesData = tblCatsWithGlassesData.values();
		
		CatsWithHatTable tblCatsWithHat = dataMan.getTblCatsWithHat();
		Hashtable<String,BaseObject> tblCatsWithHatData = tblCatsWithHat.getTableData();
		Collection<BaseObject> lsCatsWithHatData = tblCatsWithHatData.values();
		
		ArrayList<BaseObject> lsObj = new ArrayList<BaseObject>();
		lsObj.addAll(lsImageData);
		lsObj.addAll(lsCatsWithGlassesData);
		lsObj.addAll(lsCatsWithHatData);
		
		this.output.append("[ ");
		this.output.append(this.dispatchQueryWorker(lsObj));				
		this.output.append(" ]");
	}
		
	public void findImage() {
		AppMain.getApp().getErr().writeDebug(this.getClass().getName(), "findImage", "Find one Image...");
		
		AppDataManager dataMan = AppMain.getApp().getDataMan();
		
		String key = cmdRequest.getCmdParam();
		AppMain.getApp().getErr().writeDebug(this.getClass().getName(), "findImage", "KEY: " + key);
		
		ImageTable tblImage = dataMan.getTblImage();
		CatsWithGlassesTable tblCatsWithGlasses = dataMan.getTblCatsWithGlasses();
		CatsWithHatTable tblCatsWithHat = dataMan.getTblCatsWithHat();

		BaseObject obj = tblImage.getObj(key);
		if(obj == null) {
			obj = tblCatsWithGlasses.getObj(key);
			if(obj == null) {
				obj = tblCatsWithHat.getObj(key);
			}
		}
			
		if(obj != null) {
			Image oImage = (Image)obj;

			this.output.append("[ ");
			this.output.append(oImage.toStrJSON());
			this.output.append(" ]");
		}
		else {
			this.output.append("{ \"error\":\"Invalid image identifyer.\" }");
		}
	}
	
	public void findImagesByCatWithGlasses() {
		AppMain.getApp().getErr().writeDebug(this.getClass().getName(), "findImagesByCatWithGlasses", "Find all CatsWithGlasses images...");
		
		AppDataManager dataMan = AppMain.getApp().getDataMan();
		
		CatsWithGlassesTable tblCatsWithGlasses = dataMan.getTblCatsWithGlasses();
		Hashtable<String,BaseObject> tblCatsWithGlassesData = tblCatsWithGlasses.getTableData();
		Collection<BaseObject> lsCatsWithGlassesData = tblCatsWithGlassesData.values();
				
		this.output.append("[ ");
		this.output.append(this.dispatchQueryWorker(lsCatsWithGlassesData));				
		this.output.append(" ]");
	}

	public void findImagesByCatWithHat() {												
		AppMain.getApp().getErr().writeDebug(this.getClass().getName(), "findImagesByCatWithHat", "Find all CatsWithHat images...");
		
		AppDataManager dataMan = AppMain.getApp().getDataMan();
		
		CatsWithHatTable tblCatsWithHat = dataMan.getTblCatsWithHat();
		Hashtable<String,BaseObject> tblCatsWithHatData = tblCatsWithHat.getTableData();
		Collection<BaseObject> lsCatsWithHatData = tblCatsWithHatData.values();
		
		this.output.append("[ ");
		this.output.append(this.dispatchQueryWorker(lsCatsWithHatData));				
		this.output.append(" ]");
	}
	
	public byte[] downloadImage() {
		AppMain.getApp().getErr().writeDebug(this.getClass().getName(), "downloadImage", "Download an Image...");
		
		byte[] imgResult = null;
		
		AppContext ctx = AppMain.getApp().getContext();
		AppDataManager dataMan = AppMain.getApp().getDataMan();
		
		String key = cmdRequest.getCmdParam();
		AppMain.getApp().getErr().writeDebug(this.getClass().getName(), "downloadImage", "KEY: " + key);
		
		ImageTable tblImage = dataMan.getTblImage();
		CatsWithGlassesTable tblCatsWithGlasses = dataMan.getTblCatsWithGlasses();
		CatsWithHatTable tblCatsWithHat = dataMan.getTblCatsWithHat();

		BaseObject obj = tblImage.getObj(key);
		String pathName = null;
		if(obj == null) {
			obj = tblCatsWithGlasses.getObj(key);
			if(obj == null) {
				obj = tblCatsWithHat.getObj(key);
				if(obj != null)
					pathName = ctx.getDataCatsWithHatDir();									
			}
			else {
				pathName = ctx.getDataCatsWithGlassesDir();				
			}
		}
		else {
			pathName = ctx.getDataImagesDir();
		}
			
		if(obj != null) {
			Image oImage = (Image)obj;

			String fileName = pathName + "/" + oImage.getLocalFile();
			File f = new File(fileName);
			if( f.exists() ) {
				imgResult = FileUtil.readBinaryData(fileName);
			}
		}
		
		return imgResult;
	}

	public String findImagesByBreed() {	
		return "Not implemented!";
	}

	public String findImagesByTemperament() {												
		return "Not implemented!";
	}
	
	public String findImagesByOrigin() {
		return "Not implemented!";		
	}

	public byte[] execute() {
		String strResult = null;
		byte[] arrResult = null;

		try {
			if( AppDefs.REST_CMD_LISTALL_BREEDS_VAL.equals(this.cmdRequest.getCmdRequestVal()) ) {
				this.findAllBreeds();	
			}
			else if( AppDefs.REST_CMD_LIST_BREED_VAL.equals(this.cmdRequest.getCmdRequestVal()) ) {
				this.findBreed();						
			}
			else if( AppDefs.REST_CMD_LIST_BREEDS_BY_TEMPERAMENT_VAL.equals(this.cmdRequest.getCmdRequestVal()) ) {
				this.findBreedsByTemperament();									
			}
			else if( AppDefs.REST_CMD_LIST_BREEDS_BY_ORIGIN_VAL.equals(this.cmdRequest.getCmdRequestVal()) ) {
				this.findBreedsByOrigin();									
			}
			else if( AppDefs.REST_CMD_LISTALL_IMAGES_VAL.equals(this.cmdRequest.getCmdRequestVal()) ) {
				this.findAllImages();												
			}
			else if( AppDefs.REST_CMD_LIST_IMAGE_VAL.equals(this.cmdRequest.getCmdRequestVal()) ) {
				this.findImage();												
			}
			else if( AppDefs.REST_CMD_LIST_IMAGES_BY_CATWITHGLASSES_VAL.equals(this.cmdRequest.getCmdRequestVal()) ) {
				this.findImagesByCatWithGlasses();												
			}
			else if( AppDefs.REST_CMD_LIST_IMAGES_BY_CATWITHHAT_VAL.equals(this.cmdRequest.getCmdRequestVal()) ) {
				this.findImagesByCatWithHat();												
			}
			else if( AppDefs.REST_CMD_LIST_IMAGES_BY_BREED_VAL.equals(this.cmdRequest.getCmdRequestVal()) ) {
				this.findImagesByBreed();												
			}
			else if( AppDefs.REST_CMD_LIST_IMAGES_BY_TEMPERAMENT_VAL.equals(this.cmdRequest.getCmdRequestVal()) ) {
				this.findImagesByTemperament();												
			}
			else if( AppDefs.REST_CMD_LIST_IMAGES_BY_ORIGIN_VAL.equals(this.cmdRequest.getCmdRequestVal()) ) {
				this.findImagesByOrigin();												
			}
			else if( AppDefs.REST_CMD_DOWNLOAD_IMAGE_VAL.equals(this.cmdRequest.getCmdRequestVal()) ) {
				arrResult = this.downloadImage();
				return arrResult;
			}
			else {
				this.copyright();
			}

			strResult = this.output.toString();
			arrResult = strResult.getBytes("utf-8");	
		}
		catch(Exception e) { 
			AppMain.getApp().getErr().writeError(this.getClass().getName(), "execute", e.getMessage());
			e.printStackTrace();
		}
		
		return arrResult;
	}

}
