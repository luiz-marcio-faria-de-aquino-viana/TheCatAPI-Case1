/*
 * Copyright(C) TLMV Consultoria e Sistemas Ltda. Todos os direitos reservados.
 *
 * ImageTable.java
 * Autor: Luiz Marcio Faria de Aquino Viana, 25/10/2022
 * revisoes: ...
 *
 */

package br.com.tlmv.thecatapicase1.nosql;

import br.com.tlmv.thecatapicase1.AppDefs;

public class CatsWithHatTable extends BaseTable
{
//Public
	
	public CatsWithHatTable() {
		super(AppDefs.BASE_OBJTYPE_IMAGE, AppDefs.BASE_TBLNAME_CATSWITHHAT);
	}

}
