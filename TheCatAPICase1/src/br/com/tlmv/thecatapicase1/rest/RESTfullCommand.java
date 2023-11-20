/*
 * Copyright(C) TLMV Consultoria e Sistemas Ltda. Todos os direitos reservados.
 *
 * RESTfullCommand.java
 * Autor: Luiz Marcio Faria de Aquino Viana, 26/10/2022
 * revisoes: ...
 *
 */

package br.com.tlmv.thecatapicase1.rest;

import br.com.tlmv.thecatapicase1.AppDefs;
import br.com.tlmv.thecatapicase1.utils.UuidUtil;

public class RESTfullCommand 
{
//Private
	private Long cmdRequestId = -1L;
	private String cmdRequestStr = AppDefs.REST_CMD_DEFAULT_STR;
	private Integer cmdRequestVal = AppDefs.REST_CMD_DEFAULT_VAL;
	public String cmdParam = "";
	
//Public

	public RESTfullCommand(String cmdRequestStr) {
		this.cmdRequestId = UuidUtil.generateRequestID();
		this.cmdRequestStr = cmdRequestStr;
		if( !this.parseCmdRequest() ) {
			this.defaultCmdRequest();
		}
	}
	
	/* Methodes */

	public void defaultCmdRequest() {
		this.cmdRequestStr = AppDefs.REST_CMD_DEFAULT_STR;
		this.cmdRequestVal = AppDefs.REST_CMD_DEFAULT_VAL;
		this.cmdParam = "";
	}

	public boolean parseCmdRequest() {
		RESTfullCommandParser parser = new RESTfullCommandParser(this);

		boolean bResult = parser.executeParser();
		return bResult;
	}

	/* Getters/Setters */
		
	public Long getCmdRequestId() {
		return cmdRequestId;
	}

	public void setCmdRequestId(Long cmdRequestId) {
		this.cmdRequestId = cmdRequestId;
	}

	public String getCmdRequestStr() {
		return cmdRequestStr;
	}

	public void setCmdRequestStr(String cmdRequestStr) {
		this.cmdRequestStr = cmdRequestStr;
	}

	public Integer getCmdRequestVal() {
		return cmdRequestVal;
	}

	public void setCmdRequestVal(Integer cmdRequestVal) {
		this.cmdRequestVal = cmdRequestVal;
	}

	public String getCmdParam() {
		return cmdParam;
	}

	public void setCmdParam(String cmdParam) {
		this.cmdParam = cmdParam;
	}
		
}
