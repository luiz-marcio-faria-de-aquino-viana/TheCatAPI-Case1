/*
 * Copyright(C) TLMV Consultoria e Sistemas Ltda. Todos os direitos reservados.
 *
 * ExecDataCreator.java
 * Autor: Luiz Marcio Faria de Aquino Viana, 25/10/2022
 * revisoes: ...
 *
 */

package br.com.tlmv.thecatapicase1.datacreator;

public class ExecDataCreator 
{
//Public
	
	public boolean executeDataCreator() {
		DataCreatorBase dcc = new DataCreatorCategory();
		if( !((DataCreatorCategory)dcc).executeDataCreator() )
			return false;		
		
		DataCreatorBase dcb = new DataCreatorBreed();
		if( !((DataCreatorBreed)dcb).executeDataCreator() )
			return false;
		
		DataCreatorBase dci = new DataCreatorImage();
		if( !((DataCreatorImage)dci).executeDataCreator() )
			return false;
		
		DataCreatorBase dcch = new DataCreatorCatsWithHat();
		if( !((DataCreatorCatsWithHat)dcch).executeDataCreator() )
			return false;
		
		DataCreatorBase dccg = new DataCreatorCatsWithGlasses();
		if( !((DataCreatorCatsWithGlasses)dccg).executeDataCreator() )
			return false;
		
		return true;
	}
	
}
