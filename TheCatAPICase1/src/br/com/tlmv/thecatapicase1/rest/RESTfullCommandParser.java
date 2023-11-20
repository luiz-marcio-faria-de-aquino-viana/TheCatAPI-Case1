/*
 * Copyright(C) TLMV Consultoria e Sistemas Ltda. Todos os direitos reservados.
 *
 * RESTfullCommandParser.java
 * Autor: Luiz Marcio Faria de Aquino Viana, 26/10/2022
 * revisoes: ...
 *
 */

package br.com.tlmv.thecatapicase1.rest;

import br.com.tlmv.thecatapicase1.AppDefs;

public class RESTfullCommandParser 
{
//Private
	private RESTfullCommand cmd = null;
	
//Public
	
	public RESTfullCommandParser(RESTfullCommand cmd) {
		this.cmd = cmd;
	}
	
	/* Methodes */
	
	public boolean executeParser() {
		boolean bResult = false;
		
		if( parseCommand(AppDefs.REST_CMD_COPYRIGHT_STR) ) {
			this.cmd.setCmdRequestVal(AppDefs.REST_CMD_COPYRIGHT_VAL);
			bResult = true;
		}
		else if( parseCommand(AppDefs.REST_CMD_LISTALL_BREEDS_STR) ) {
			this.cmd.setCmdRequestVal(AppDefs.REST_CMD_LISTALL_BREEDS_VAL);
			bResult = true;
		}
		else if( parseCommand(AppDefs.REST_CMD_LIST_BREED_STR) ) {
			this.cmd.setCmdRequestVal(AppDefs.REST_CMD_LIST_BREED_VAL);

			String strParam = parseCommandParam(AppDefs.REST_CMD_LIST_BREED_STR); 
			if( !"?".equals(strParam) ) {
				this.cmd.setCmdParam(strParam);
				bResult = true;
			}
			else
				this.cmd.setCmdRequestVal(AppDefs.REST_CMD_DEFAULT_VAL);
		}
		else if( parseCommand(AppDefs.REST_CMD_LIST_BREEDS_BY_TEMPERAMENT_STR) ) {
			this.cmd.setCmdRequestVal(AppDefs.REST_CMD_LIST_BREEDS_BY_TEMPERAMENT_VAL);

			String strParam = parseCommandParam(AppDefs.REST_CMD_LIST_BREEDS_BY_TEMPERAMENT_STR); 
			if( !"?".equals(strParam) ) {
				this.cmd.setCmdParam(strParam);
				bResult = true;
			}
			else
				this.cmd.setCmdRequestVal(AppDefs.REST_CMD_DEFAULT_VAL);
		}
		else if( parseCommand(AppDefs.REST_CMD_LIST_BREEDS_BY_ORIGIN_STR) ) {
			this.cmd.setCmdRequestVal(AppDefs.REST_CMD_LIST_BREEDS_BY_ORIGIN_VAL);

			String strParam = parseCommandParam(AppDefs.REST_CMD_LIST_BREEDS_BY_ORIGIN_STR); 
			if( !"?".equals(strParam) ) {
				this.cmd.setCmdParam(strParam);
				bResult = true;
			}
			else
				this.cmd.setCmdRequestVal(AppDefs.REST_CMD_DEFAULT_VAL);
		}
		else if( parseCommand(AppDefs.REST_CMD_LISTALL_IMAGES_STR) ) {
			this.cmd.setCmdRequestVal(AppDefs.REST_CMD_LISTALL_IMAGES_VAL);
			bResult = true;
		}
		else if( parseCommand(AppDefs.REST_CMD_LIST_IMAGE_STR) ) {
			this.cmd.setCmdRequestVal(AppDefs.REST_CMD_LIST_IMAGE_VAL);

			String strParam = parseCommandParam(AppDefs.REST_CMD_LIST_IMAGE_STR); 
			if( !"?".equals(strParam) ) {
				this.cmd.setCmdParam(strParam);
				bResult = true;
			}
			else
				this.cmd.setCmdRequestVal(AppDefs.REST_CMD_DEFAULT_VAL);
		}
		else if( parseCommand(AppDefs.REST_CMD_LIST_IMAGES_BY_CATWITHGLASSES_STR) ) {
			this.cmd.setCmdRequestVal(AppDefs.REST_CMD_LIST_IMAGES_BY_CATWITHGLASSES_VAL);
			bResult = true;
		}
		else if( parseCommand(AppDefs.REST_CMD_LIST_IMAGES_BY_CATWITHHAT_STR) ) {
			this.cmd.setCmdRequestVal(AppDefs.REST_CMD_LIST_IMAGES_BY_CATWITHHAT_VAL);
			bResult = true;
		}		
		else if( parseCommand(AppDefs.REST_CMD_DOWNLOAD_IMAGE_STR) ) {
			this.cmd.setCmdRequestVal(AppDefs.REST_CMD_DOWNLOAD_IMAGE_VAL);

			String strParam = parseCommandParam(AppDefs.REST_CMD_DOWNLOAD_IMAGE_STR); 
			if( !"?".equals(strParam) ) {
				this.cmd.setCmdParam(strParam);
				bResult = true;
			}
			else
				this.cmd.setCmdRequestVal(AppDefs.REST_CMD_DEFAULT_VAL);
		}
		else {
			this.cmd.setCmdRequestVal(AppDefs.REST_CMD_DEFAULT_VAL);
		}
		
		return bResult;
	}	
	
	public boolean parseCommand(String restCmd) {
		String[] restCmdPart = restCmd.split("/");
		int restCmdPartSz = restCmdPart.length;
		
		String[] cmdPart = this.cmd.getCmdRequestStr().split("/");
		int cmdPartSz = cmdPart.length;
		
		if(restCmdPartSz != cmdPartSz)
			return false;
		
		for(int i = 0; i < restCmdPartSz; i++) {
			String restCmdToken = restCmdPart[i];
			String cmdToken = cmdPart[i];
			
			if( !"?".equals(restCmdToken) ) {
				if( !cmdToken.equals(restCmdToken) )
					return false;
			}
		}
		
		return true;
	}
	
	public String parseCommandParam(String restCmd) {
		String[] restCmdPart = restCmd.split("/");
		int restCmdPartSz = restCmdPart.length;
		
		String[] cmdPart = this.cmd.getCmdRequestStr().split("/");
		int cmdPartSz = cmdPart.length;
		
		if(restCmdPartSz != cmdPartSz)
			return "";
		
		for(int i = 0; i < restCmdPartSz; i++) {
			String restCmdToken = restCmdPart[i];
			String cmdToken = cmdPart[i];
			
			if( "?".equals(restCmdToken) )
				return cmdToken;
		}
		
		return "";
	}
	
}
