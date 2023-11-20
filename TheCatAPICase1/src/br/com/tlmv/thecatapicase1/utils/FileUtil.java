/*
 * Copyright(C) TLMV Consultoria e Sistemas Ltda. Todos os direitos reservados.
 *
 * FileUtil.java
 * Autor: Luiz Marcio Faria de Aquino Viana, 24/10/2022
 * revisoes: ...
 *
 */

package br.com.tlmv.thecatapicase1.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;

import br.com.tlmv.thecatapicase1.AppDefs;
import br.com.tlmv.thecatapicase1.AppMain;

public class FileUtil 
{
//Public
	
	public static String getFileExt(String fullFileName) {
		String[] strFileNameParts = fullFileName.split("."); 
		if(strFileNameParts.length > 0) {
			String fileExt = strFileNameParts[strFileNameParts.length - 1];
			return fileExt;
		}
		return "";
	}

	public static String getMimeType(String fullFileName) {
		String strResult = AppDefs.MIMETYPE_BLOB;
		
		String fileExt = FileUtil.getFileExt(fullFileName);
		if( AppDefs.EXT_JPG.equals(fileExt) )
			strResult = AppDefs.MIMETYPE_JPG;
		
		if( AppDefs.EXT_JPEG.equals(fileExt) )
			strResult = AppDefs.MIMETYPE_JPEG;
		
		if( AppDefs.EXT_PNG.equals(fileExt) )
			strResult = AppDefs.MIMETYPE_PNG;
		
		return strResult;
	}
	
	public static boolean saveFile(String fileName, byte[] data)
		throws Exception
	{
		try {
			FileOutputStream f = new FileOutputStream(fileName);
			f.write(data);
			f.close();
			return true;
		} 
		catch (Exception e) {
			AppMain.getApp().getErr().writeError(FileUtil.class.getName(), "saveFile", e.getMessage());
			e.printStackTrace();
		}
		return false;
	}
	
	public static String readData(String f) {
		StringBuilder sb = new StringBuilder();
		
		BufferedReader fin = null;		
		try {
			fin = new BufferedReader(new FileReader(f));
			String sbuf = null;
			while((sbuf = fin.readLine()) != null)
				sb.append(sbuf);
		}
		catch(Exception e) {
			AppMain.getApp().getErr().writeError(FileUtil.class.getName(), "readData", e.getMessage());
			e.printStackTrace();
		}
		finally {
			try {
				if(fin != null) fin.close();				
			}
			catch(Exception e1) { }
		}
		
		return sb.toString();
	}

	public static byte[] readBinaryData(String fileName) {
		byte[] arrByte = null;
		try {
			InputStream fin = new FileInputStream(fileName);
			arrByte = new byte[fin.available()];
			fin.read(arrByte);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return arrByte;
	}
	
}
