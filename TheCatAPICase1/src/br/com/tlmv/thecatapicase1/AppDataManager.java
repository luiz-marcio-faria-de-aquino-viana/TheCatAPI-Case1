/*
 * Copyright(C) TLMV Consultoria e Sistemas Ltda. Todos os direitos reservados.
 *
 * AppDataManager.java
 * Autor: Luiz Marcio Faria de Aquino Viana, 25/10/2022
 * revisoes: ...
 *
 */

package br.com.tlmv.thecatapicase1;

import java.util.Hashtable;

import br.com.tlmv.thecatapicase1.data.Breed;
import br.com.tlmv.thecatapicase1.nosql.BreedTable;
import br.com.tlmv.thecatapicase1.nosql.CategoryTable;
import br.com.tlmv.thecatapicase1.nosql.CatsWithGlassesTable;
import br.com.tlmv.thecatapicase1.nosql.CatsWithHatTable;
import br.com.tlmv.thecatapicase1.nosql.ImageTable;

public class AppDataManager 
{
//Public
	private CategoryTable tblCategory = null; 
	private BreedTable tblBreed = null; 
	private ImageTable tblImage = null; 
	private CatsWithGlassesTable tblCatsWithGlasses = null; 
	private CatsWithHatTable tblCatsWithHat = null; 
	
//Public
	
	public AppDataManager() {
		tblCategory = new CategoryTable();
		tblBreed = new BreedTable();
		tblImage = new ImageTable();
		tblCatsWithGlasses = new CatsWithGlassesTable();
		tblCatsWithHat = new CatsWithHatTable();
	}
	
	/* Methodes */
	
	public void saveAll() 
		throws Exception
	{
		tblCategory.saveData();
		tblBreed.saveData();
		tblImage.saveData();
		tblCatsWithGlasses.saveData();
		tblCatsWithHat.saveData();
	}
	
	public void loadAll() 
		throws Exception
	{
		tblCategory.loadData();
		tblBreed.loadData();
		tblImage.loadData();
		tblCatsWithGlasses.loadData();
		tblCatsWithHat.loadData();
	}

	/* Getters/Setters */

	public BreedTable getTblBreed() {
		return tblBreed;
	}

	public void setTblBreed(BreedTable tblBreed) {
		this.tblBreed = tblBreed;
	}

	public CategoryTable getTblCategory() {
		return tblCategory;
	}

	public void setTblCategory(CategoryTable tblCategory) {
		this.tblCategory = tblCategory;
	}

	public ImageTable getTblImage() {
		return tblImage;
	}

	public void setTblImage(ImageTable tblImage) {
		this.tblImage = tblImage;
	}

	public CatsWithGlassesTable getTblCatsWithGlasses() {
		return tblCatsWithGlasses;
	}

	public void setTblCatsWithGlasses(CatsWithGlassesTable tblCatsWithGlasses) {
		this.tblCatsWithGlasses = tblCatsWithGlasses;
	}

	public CatsWithHatTable getTblCatsWithHat() {
		return tblCatsWithHat;
	}

	public void setTblCatsWithHat(CatsWithHatTable tblCatsWithHat) {
		this.tblCatsWithHat = tblCatsWithHat;
	}
	
}
