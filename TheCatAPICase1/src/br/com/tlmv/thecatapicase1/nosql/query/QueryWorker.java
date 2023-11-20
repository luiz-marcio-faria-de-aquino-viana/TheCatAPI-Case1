/*
 * Copyright(C) TLMV Consultoria e Sistemas Ltda. Todos os direitos reservados.
 *
 * BreedQueryData.java
 * Autor: Luiz Marcio Faria de Aquino Viana, 29/10/2022
 * revisoes: ...
 *
 */

package br.com.tlmv.thecatapicase1.nosql.query;

import java.util.Collection;
import java.util.Hashtable;

import br.com.tlmv.thecatapicase1.AppDataManager;
import br.com.tlmv.thecatapicase1.AppDefs;
import br.com.tlmv.thecatapicase1.AppMain;
import br.com.tlmv.thecatapicase1.data.BaseObject;
import br.com.tlmv.thecatapicase1.data.Breed;
import br.com.tlmv.thecatapicase1.data.Image;
import br.com.tlmv.thecatapicase1.nosql.BreedTable;
import br.com.tlmv.thecatapicase1.rest.RESTfullCommand;

public class QueryWorker implements Runnable
{
//Private
	private Integer threadId = -1;
	private RESTfullCommand cmdRequest = null;
	private BaseObject[] arrObj = null;
	private Integer startPos = -1;
	private Integer endPos = -1;
	private StringBuilder output = null;
	private Thread threadObj = null;
	private boolean isRunning = false;
	
//Public
	
	public QueryWorker(Integer threadId, RESTfullCommand cmdRequest, BaseObject[] arrObj, Integer startPos, Integer endPos) {
		this.threadId = threadId;
		this.cmdRequest = cmdRequest;
		this.arrObj = arrObj;
		this.startPos = startPos;
		this.endPos = endPos;
		this.output = new StringBuilder();
		this.threadObj = null;
		this.isRunning = false;
	}
	
	/* Methodes */
	
	public void startQuery() {
		this.isRunning = true;

		this.threadObj = new Thread(this);
		this.threadObj.start();
	}

	public synchronized void waitForComplete() {
		while( this.isRunning ) {
			try {
				this.threadObj.wait();
			}
			catch(Exception e) { };
		}
		this.threadObj = null;
		this.notifyAll();
	}

	//FIND
	
	public void findAllBreedsPart() {
		for(int i = this.startPos; i < this.endPos; i++) {
			Breed oBreed = (Breed)arrObj[i];
			if(this.output.length() == 0) {
				this.output.append(oBreed.toStrJSON());
			}
			else {
				this.output.append(",");
				this.output.append(oBreed.toStrJSON());
			}
		}
	}
	
	public void findBreedsByTemperamentPart() {
		String temperament = cmdRequest.getCmdParam();
		for(int i = this.startPos; i < this.endPos; i++) {
			Breed oBreed = (Breed)arrObj[i];
			if( (oBreed.getTemperament() != null) && 
				(oBreed.getTemperament().contains(temperament)) ) {
				if(this.output.length() == 0) {
					this.output.append(oBreed.toStrJSON());
				}
				else {
					this.output.append(",");
					this.output.append(oBreed.toStrJSON());
				}
			}
		}
	}
	
	public void findBreedsByOriginPart() {
		String origin = cmdRequest.getCmdParam();
		for(int i = this.startPos; i < this.endPos; i++) {
			Breed oBreed = (Breed)arrObj[i];
			if( (oBreed.getOrigin() != null) && 
				(oBreed.getOrigin().equals(origin)) ) {
				if(this.output.length() == 0) {
					this.output.append(oBreed.toStrJSON());
				}
				else {
					this.output.append(",");
					this.output.append(oBreed.toStrJSON());
				}
			}
		}
	}
	
	public void findAllImagesPart() {
		for(int i = this.startPos; i < this.endPos; i++) {
			Image oImage = (Image)arrObj[i];
			if(this.output.length() == 0) {
				this.output.append(oImage.toStrJSON());
			}
			else {
				this.output.append(",");
				this.output.append(oImage.toStrJSON());
			}
		}
	}
	
	public void findImagesByCatWithGlassesPart() {
		for(int i = this.startPos; i < this.endPos; i++) {
			Image oImage = (Image)arrObj[i];
			if(this.output.length() == 0) {
				this.output.append(oImage.toStrJSON());
			}
			else {
				this.output.append(",");
				this.output.append(oImage.toStrJSON());
			}
		}		
	}

	public void findImagesByCatWithHatPart() {
		for(int i = this.startPos; i < this.endPos; i++) {
			Image oImage = (Image)arrObj[i];
			if(this.output.length() == 0) {
				this.output.append(oImage.toStrJSON());
			}
			else {
				this.output.append(",");
				this.output.append(oImage.toStrJSON());
			}
		}		
	}
	
	public void findImagesByBreedPart() {	
		/* Not Implemented */
	}
	
	public void findImagesByTemperamentPart() {
		/* Not Implemented */		
	}
	
	public void findImagesByOriginPart() {
		/* Not Implemented */		
	}
	
	/* Thread */
	
	@Override
	public void run() {
		if( AppDefs.REST_CMD_LISTALL_BREEDS_VAL.equals(this.cmdRequest.getCmdRequestVal()) ) {
			this.findAllBreedsPart();			
		}
		else if( AppDefs.REST_CMD_LIST_BREEDS_BY_TEMPERAMENT_VAL.equals(this.cmdRequest.getCmdRequestVal()) ) {
			this.findBreedsByTemperamentPart();									
		}
		else if( AppDefs.REST_CMD_LIST_BREEDS_BY_ORIGIN_VAL.equals(this.cmdRequest.getCmdRequestVal()) ) {
			this.findBreedsByOriginPart();									
		}
		else if( AppDefs.REST_CMD_LISTALL_IMAGES_VAL.equals(this.cmdRequest.getCmdRequestVal()) ) {
			this.findAllImagesPart();												
		}
		else if( AppDefs.REST_CMD_LIST_IMAGES_BY_CATWITHGLASSES_VAL.equals(this.cmdRequest.getCmdRequestVal()) ) {
			this.findImagesByCatWithGlassesPart();												
		}
		else if( AppDefs.REST_CMD_LIST_IMAGES_BY_CATWITHHAT_VAL.equals(this.cmdRequest.getCmdRequestVal()) ) {
			this.findImagesByCatWithHatPart();												
		}
		else if( AppDefs.REST_CMD_LIST_IMAGES_BY_BREED_VAL.equals(this.cmdRequest.getCmdRequestVal()) ) {
			this.findImagesByBreedPart();												
		}
		else if( AppDefs.REST_CMD_LIST_IMAGES_BY_TEMPERAMENT_VAL.equals(this.cmdRequest.getCmdRequestVal()) ) {
			this.findImagesByTemperamentPart();												
		}
		else if( AppDefs.REST_CMD_LIST_IMAGES_BY_ORIGIN_VAL.equals(this.cmdRequest.getCmdRequestVal()) ) {
			this.findImagesByOriginPart();												
		}
		this.isRunning = false;
	}

	/* Getters/Setters */

	public BaseObject[] getArrBaseObject() {
		return arrObj;
	}

	public void setArrBaseObject(BaseObject[] arrObj) {
		this.arrObj = arrObj;
	}

	public Integer getStartPos() {
		return startPos;
	}

	public void setStartPos(Integer startPos) {
		this.startPos = startPos;
	}

	public Integer getEndPos() {
		return endPos;
	}

	public void setEndPos(Integer endPos) {
		this.endPos = endPos;
	}

	public StringBuilder getOutput() {
		return output;
	}

	public void setOutput(StringBuilder output) {
		this.output = output;
	}
	
}
