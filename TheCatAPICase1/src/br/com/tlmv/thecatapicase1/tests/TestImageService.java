/*
 * Copyright(C) TLMV Consultoria e Sistemas Ltda. Todos os direitos reservados.
 *
 * TestImageService.java
 * Autor: Luiz Marcio Faria de Aquino Viana, 25/10/2022
 * revisoes: ...
 *
 */

package br.com.tlmv.thecatapicase1.tests;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.tlmv.thecatapicase1.AppContext;
import br.com.tlmv.thecatapicase1.AppDefs;
import br.com.tlmv.thecatapicase1.AppMain;
import br.com.tlmv.thecatapicase1.data.Breed;
import br.com.tlmv.thecatapicase1.data.Image;
import br.com.tlmv.thecatapicase1.services.BreedService;
import br.com.tlmv.thecatapicase1.services.ImageService;

public class TestImageService extends TestBase
{
//Public
	public static final String TEST_NAME = "TEST_IMAGE_SERVICE";
		
	public TestImageService() {
		super(TestImageService.TEST_NAME);
	}

	/* Methodes */
	
	public boolean testRequestAnyImageJSON() {
		boolean bResult = false;
		
		try {
			AppMain.getApp().getErr().writeUnitTest(this.getClass().getName(), "testRequestAnyImageJSON", "Starting unit test to request any Image...");

			ImageService service = new ImageService();
			JSONObject jsonObj = service.requestAnyImageJSON();
	
			Image oImage = new Image(jsonObj);
			oImage.debug(AppDefs.DEBUG_LEVEL_DEBUG);
			
			bResult = true;
		}
		catch(Exception e) {
			AppMain.getApp().getErr().writeError(this.getClass().getName(), "testRequestAnyImageJSON", e.getMessage());
			e.printStackTrace();
		}

		AppMain.getApp().getErr().writeUnitTestResult(this.getClass().getName(), "testRequestAnyImageJSON", "Unit test to request any Image finished!", bResult);
		return bResult;
	}
	
	public boolean testRequestDownloadImageFromURL() {
		boolean bResult = false;
		
		try {
			AppMain.getApp().getErr().writeUnitTest(this.getClass().getName(), "testRequestDownloadImageFromURL", "Starting unit test to download any Image...");

			AppContext ctx = AppMain.getApp().getContext();
			
			ImageService service = new ImageService();
			JSONObject jsonObj = service.requestAnyImageJSON();
	
			Image oImage = new Image(jsonObj);
			oImage.debug(AppDefs.DEBUG_LEVEL_DEBUG);
			
			java.awt.Image oImageJPG = service.requestDownloadImageFromURL(oImage.getUrl(), ctx.getDataImagesDir(), oImage.getLocalFile());
			if(oImageJPG != null)
				bResult = true;
		}
		catch(Exception e) {
			AppMain.getApp().getErr().writeError(this.getClass().getName(), "testRequestDownloadImageFromURL", e.getMessage());
			e.printStackTrace();
		}

		AppMain.getApp().getErr().writeUnitTestResult(this.getClass().getName(), "testRequestDownloadImageFromURL", "Unit test to download any Image finished!", bResult);
		return bResult;
	}
	
	public boolean testRequestDownloadHtmlFromURL_UnsupportedImageFormat() {
		boolean bResult = false;
		
		try {
			AppMain.getApp().getErr().writeUnitTest(this.getClass().getName(), "testRequestDownloadHtmlFromURL_UnsupportedImageFormat", "Starting unit test to download any Image throw UnsupportedImageFormat exception...");

			AppContext ctx = AppMain.getApp().getContext();

			ImageService service = new ImageService();

			String url = "https://cdn2.thecatapi.com/images/p2U4ZXgKL.jpg";		// UnsupportedImageFormat
			String localFile = "p2U4ZXgKL.jpg";
			
			try {
				java.awt.Image oImageJPG = service.requestDownloadImageFromURL(url, ctx.getDataImagesDir(), localFile);
				if(oImageJPG != null)
					bResult = true;
			}
			catch(Exception e) {
				AppMain.getApp().getErr().writeError(this.getClass().getName(), "dataCreatorForDownloadImageFromURL", e.getMessage());
				e.printStackTrace();					
			}
			
			if( !bResult ) {
				try {
					byte[] oImageJPG = service.requestDownloadImageFromEx(url, ctx.getDataImagesDir(), localFile);
					if(oImageJPG != null)
						bResult = true;
				}
				catch(Exception e) {
					AppMain.getApp().getErr().writeError(this.getClass().getName(), "dataCreatorForDownloadImageFromURL", e.getMessage());
					e.printStackTrace();					
				}					
			}
		}
		catch(Exception e) {
			AppMain.getApp().getErr().writeError(this.getClass().getName(), "testRequestDownloadHtmlFromURL_UnsupportedImageFormat", e.getMessage());
			e.printStackTrace();
		}

		AppMain.getApp().getErr().writeUnitTestResult(this.getClass().getName(), "testRequestDownloadHtmlFromURL_UnsupportedImageFormat", "Unit test to download any Image throw UnsupportedImageFormat exception finished!", bResult);
		return bResult;
	}

	public boolean testRequestDownloadHtmlFromURL() {
		boolean bResult = false;
		
		try {
			AppMain.getApp().getErr().writeUnitTest(this.getClass().getName(), "testRequestDownloadHtmlFromURL", "Starting unit test to download any Image and the respective HTML format...");

			ImageService service = new ImageService();
			JSONObject jsonObj = service.requestAnyImageJSON();
	
			Image oImage = new Image(jsonObj);
			oImage.debug(AppDefs.DEBUG_LEVEL_DEBUG);
			
			String strHtml = service.requestDownloadHtmlFromURL(oImage.getUrl(), oImage.getLocalFile());
			if( (strHtml != null) && ( !"".equals(strHtml) ) )
				bResult = true;
		}
		catch(Exception e) {
			AppMain.getApp().getErr().writeError(this.getClass().getName(), "testRequestDownloadHtmlFromURL", e.getMessage());
			e.printStackTrace();
		}

		AppMain.getApp().getErr().writeUnitTestResult(this.getClass().getName(), "testRequestDownloadHtmlFromURL", "Unit test to download any Image and the respective HTML format finished!", bResult);
		return bResult;
	}

	/* EXECUTE */
	
	@Override
	public boolean executeUnitTest() {
		if( !this.testRequestAnyImageJSON() )
			return false;
		
		if( !this.testRequestDownloadImageFromURL() )
			return false;
		
		if( !this.testRequestDownloadHtmlFromURL_UnsupportedImageFormat() )
			return false;
		
		return true;
	}
	
}
