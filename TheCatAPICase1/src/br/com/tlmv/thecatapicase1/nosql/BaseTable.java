/*
 * Copyright(C) TLMV Consultoria e Sistemas Ltda. Todos os direitos reservados.
 *
 * BaseTable.java
 * Autor: Luiz Marcio Faria de Aquino Viana, 24/10/2022
 * revisoes: ...
 *
 */

package br.com.tlmv.thecatapicase1.nosql;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Hashtable;

import br.com.tlmv.thecatapicase1.AppContext;
import br.com.tlmv.thecatapicase1.AppDefs;
import br.com.tlmv.thecatapicase1.AppMain;
import br.com.tlmv.thecatapicase1.data.BaseObject;
import br.com.tlmv.thecatapicase1.data.Breed;
import br.com.tlmv.thecatapicase1.data.Category;
import br.com.tlmv.thecatapicase1.data.Image;
import br.com.tlmv.thecatapicase1.utils.UuidUtil;

public class BaseTable
{
//Private
	private Integer tableObjectType = AppDefs.BASE_OBJTYPE_NONE;

	private String tableName = "";
	private String tableFileName = "";
	private Hashtable<String,BaseObject> tableData = new Hashtable<String,BaseObject>();
	
//Public
	
	public BaseTable(Integer tableObjectType, String tableName) {
		AppMain app = AppMain.getApp();
		AppContext ctx = app.getContext();
		
		this.tableObjectType = tableObjectType;
		this.tableName = tableName;
		this.tableFileName = ctx.getDataDir() + "/" + tableName + AppDefs.EXT_DAT;
		this.tableData = new Hashtable<String,BaseObject>();
	}
	
	/* Methodes - SAVE/LOAD */
	
	public synchronized void createDataBackup() {
		AppContext ctx = AppMain.getApp().getContext();
		
		String uuid = UuidUtil.generateUUID();
		
		File fsrc = new File(this.tableFileName);
		if( fsrc.exists() ) {
			String newTableFileName = ctx.getDataDir() + "/" + tableName + uuid + AppDefs.EXT_DAT;
			
			File fdst = new File(newTableFileName); 
			if( !fdst.exists() )
				fsrc.renameTo(fdst);
		}		
	}
	
	public synchronized void saveData() 
		throws Exception
	{
		Collection<BaseObject> lsObj = this.tableData.values();
		
		FileOutputStream fos = null;
		ObjectOutputStream os = null;
		try {
			this.createDataBackup();
				
			fos = new FileOutputStream(this.tableFileName);
			os = new ObjectOutputStream(fos);
			
			for(BaseObject obj : lsObj) {
				os.writeObject(obj);
			}
		}
		catch(Exception e) {
			AppMain.getApp().getErr().writeError(this.getClass().getName(), "saveData", e.getMessage());
			e.printStackTrace();
		}
		finally {
			if(os != null) os.close();
			if(fos != null)	fos.close();
		}
	}

	public synchronized void clear() {
		this.tableData = new Hashtable<String,BaseObject>();
	}

	public synchronized void loadData() 
		throws Exception
	{
		this.tableData = new Hashtable<String,BaseObject>();
		
		FileInputStream fis = null;
		ObjectInputStream is = null;
		try {
			File f = new File(this.tableFileName);
			if( f.exists() ) {
				fis = new FileInputStream(f);
				is = new ObjectInputStream(fis);
				
				BaseObject obj = null;
				boolean bEof = false;
				while( !bEof ) {
					try {
						obj = (BaseObject)is.readObject();
						this.putObj(obj);
					}
					catch(Exception e) {
						obj = null;
						bEof = true;
					}
				}
			}
		}
		catch(Exception e) {
			AppMain.getApp().getErr().writeError(this.getClass().getName(), "loadData", e.getMessage());
			e.printStackTrace();
		}
		finally {
			if(is != null) is.close();
			if(fis != null)	fis.close();
		}
	}
	
	/* Methodes - EXIST/PUT/GET */
	
	public boolean existObj(String key) {
		if( this.tableData.containsKey(key) )
			return true;
		return false;
	}

	public BaseObject getObj(String key) {
		BaseObject oResult = null;
		if( this.existObj(key) ) {
			if(this.tableObjectType == AppDefs.BASE_OBJTYPE_CATEGORY) {
				oResult = (Category)this.tableData.get(key);
			}
			else if(this.tableObjectType == AppDefs.BASE_OBJTYPE_BREED) {
				oResult = (Breed)this.tableData.get(key);
			}
			else if(this.tableObjectType == AppDefs.BASE_OBJTYPE_IMAGE) {
				oResult = (Image)this.tableData.get(key);
			}
			else {
				oResult = this.tableData.get(key);
			}					
		}
		return oResult;
	}

	public void putObj(BaseObject obj) {
		if(this.tableObjectType == AppDefs.BASE_OBJTYPE_CATEGORY) {
			this.tableData.put(((Category)obj).getId(), (Category)obj);
		}
		else if(this.tableObjectType == AppDefs.BASE_OBJTYPE_BREED) {
			this.tableData.put(((Breed)obj).getId(), (Breed)obj);
		}
		else if(this.tableObjectType == AppDefs.BASE_OBJTYPE_IMAGE) {
			this.tableData.put(((Image)obj).getId(), (Image)obj);
		}
		else {
			this.tableData.put(obj.getBaseObjectId(), obj);
		}		
	}
	
	/* DEBUG */
	
	public void debugAll(int debugLevel) {
		if( !AppMain.getApp().getErr().checkDebugLevel(debugLevel) ) return;
		
		Collection<BaseObject> lsObj = this.tableData.values();
		for(BaseObject obj : lsObj) {
			if(this.tableObjectType == AppDefs.BASE_OBJTYPE_CATEGORY) {
				((Category)obj).debug(debugLevel);
			}
			else if(this.tableObjectType == AppDefs.BASE_OBJTYPE_BREED) {
				((Breed)obj).debug(debugLevel);
			}
			else if(this.tableObjectType == AppDefs.BASE_OBJTYPE_IMAGE) {
				((Image)obj).debug(debugLevel);
			}
		}
	}
	
	/* Getters/Setters */
	
	public Integer getTableObjectType() {
		return tableObjectType;
	}

	public void setTableObjectType(Integer tableObjectType) {
		this.tableObjectType = tableObjectType;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableFileName() {
		return tableFileName;
	}

	public void setTableFileName(String tableFileName) {
		this.tableFileName = tableFileName;
	}

	public Hashtable<String, BaseObject> getTableData() {
		return tableData;
	}

	public void setTableData(Hashtable<String, BaseObject> tableData) {
		this.tableData = tableData;
	}
	
	public int getTableSize() {
		return this.tableData.size();
	}	
	
}
