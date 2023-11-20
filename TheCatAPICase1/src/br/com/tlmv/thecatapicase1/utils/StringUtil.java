/*
 * Copyright(C) 2019 TLMV Consultoria e Sistemas EIRELI
 * 
 * StringUtil.java
 * emissao: Luiz Marcio Faria de Aquino Viana, 27/10/2022
 * revisoes: ...
 */

package br.com.tlmv.thecatapicase1.utils;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;

public class StringUtil 
{
//Public
	
	// SAFE_CONVERTION_FUNCTIONS
	
	public static String safeStr(String strIn) {
		if(strIn != null) {
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < strIn.length(); i++) {
				char ch = strIn.charAt(i);
				if(ch > 255) 
					ch += ' ';
				sb.append(ch);
			}
			return sb.toString();
		}
		return "";
	}
	
	public static Date safeDate(DateFormat df, String strIn) {
		Date result = new Date(1900, 0, 1);
		
		try {
			result = df.parse(strIn);
		}
		catch(Exception e) { }
		
		return result;
	}
	
	public static double safeDbl(NumberFormat nf, String strIn) {
		double result = 0.0;
		
		try {
			result = nf.parse(strIn).doubleValue();
		}
		catch(Exception e) { }
		
		return result;
	}
	
	public static int safeInt(String strIn) {
		int result = 0;
		
		try {
			result = Integer.parseInt(strIn);
		}
		catch(Exception e) { }
		
		return result;
	}
	
	public static long safeLng(String strIn) {
		long result = 0;
		
		try {
			result = Long.parseLong(strIn);
		}
		catch(Exception e) { }
		
		return result;
	}

}
