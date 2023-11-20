/*
 * Copyright(C) TLMV Consultoria e Sistemas Ltda. Todos os direitos reservados.
 *
 * CategoryTable.java
 * Autor: Luiz Marcio Faria de Aquino Viana, 25/10/2022
 * revisoes: ...
 *
 */

package br.com.tlmv.thecatapicase1.nosql;

import br.com.tlmv.thecatapicase1.AppDefs;

public class CategoryTable extends BaseTable
{
//Public
	
	public CategoryTable() {
		super(AppDefs.BASE_OBJTYPE_CATEGORY, AppDefs.BASE_TBLNAME_CATEGORY);
	}

}
