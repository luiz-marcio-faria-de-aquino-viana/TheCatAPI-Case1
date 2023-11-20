/*
 * Copyright(C) TLMV Consultoria e Sistemas Ltda. Todos os direitos reservados.
 *
 * Breed.java
 * Autor: Luiz Marcio Faria de Aquino Viana, 24/10/2022
 * revisoes: ...
 *
 */

package br.com.tlmv.thecatapicase1.data;

import org.json.JSONObject;

import br.com.tlmv.thecatapicase1.AppDefs;
import br.com.tlmv.thecatapicase1.AppError;
import br.com.tlmv.thecatapicase1.AppMain;
import br.com.tlmv.thecatapicase1.utils.JSONUtil;

// {
//   "weight":{
//     "imperial":"7  -  10",
//	   "metric":"3 - 5"
//   },
//   "id":"abys",
//   "name":"Abyssinian",
//   "cfa_url":"http://cfa.org/Breeds/BreedsAB/Abyssinian.aspx",
//   "vetstreet_url":"http://www.vetstreet.com/cats/abyssinian",
//   "vcahospitals_url":"https://vcahospitals.com/know-your-pet/cat-breeds/abyssinian",
//   "temperament":"Active, Energetic, Independent, Intelligent, Gentle",
//   "origin":"Egypt",
//   "country_codes":"EG",
//   "country_code":"EG",
//   "description":"The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.",
//   "life_span":"14 - 15",
//   "indoor":0,
//   "lap":1,
//   "alt_names":"",
//   "adaptability":5,
//   "affection_level":5,
//   "child_friendly":3,
//   "dog_friendly":4,
//   "energy_level":5,
//   "grooming":1,
//   "health_issues":2,
//   "intelligence":5,
//   "shedding_level":2,
//   "social_needs":5,
//   "stranger_friendly":5,
//   "vocalisation":1,
//   "experimental":0,
//   "hairless":0,
//   "natural":1,
//   "rare":0,
//   "rex":0,
//   "suppressed_tail":0,
//   "short_legs":0,
//   "wikipedia_url":"https://en.wikipedia.org/wiki/Abyssinian_(cat)",
//   "hypoallergenic":0,
//   "reference_image_id":"0XYvRd7oD",
//   "image":{
//     "id":"0XYvRd7oD",
//     "width":1204,
//     "height":1445,
//     "url":"https://cdn2.thecatapi.com/images/0XYvRd7oD.jpg" 
//   }
// }
public class Breed extends BaseObject
{
//Private
	private BreedWeight weight;
	private String id;
	private String name;
	private String cfaUrl;
	private String vetstreetUrl;
	private String vcahospitalsUrl;
	private String temperament;
	private String origin;
	private String countryCodes;
	private String countryCode;
	private String description;
	private String lifeSpan;
	private Integer indoor;
	private Integer lap;
	private String altNames;
	private Integer adaptability;
	private Integer affectionLevel;
	private Integer childFriendly;
	private Integer dogFriendly;
	private Integer energyLevel;
	private Integer grooming;
	private Integer healthIssues;
	private Integer intelligence;
	private Integer sheddingLevel;
	private Integer socialNeeds;
	private Integer strangerFriendly;
	private Integer vocalisation;
	private Integer experimental;
	private Integer hairless;
	private Integer natural;
	private Integer rare;
	private Integer rex;
	private Integer suppressedTail;
	private Integer shortLegs;
	private String wikipediaUrl;
	private Integer hypoallergenic;
	private String referenceImageId;
	private BreedImage image;

//Public
	
	public Breed() {
		super(AppDefs.BASE_OBJTYPE_BREED, "");
				
		this.weight = null;
		this.id = "-1";
		this.name = "";
		this.cfaUrl = "";
		this.vetstreetUrl = "";
		this.vcahospitalsUrl = "";
		this.temperament = "";
		this.origin = "";
		this.countryCodes = "";
		this.countryCode = "";
		this.description = "";
		this.lifeSpan = "";
		this.indoor = 0;
		this.lap = 0;
		this.altNames = "";
		this.adaptability = 0;
		this.affectionLevel = 0;
		this.childFriendly = 0;
		this.dogFriendly = 0;
		this.energyLevel = 0;
		this.grooming = 0;
		this.healthIssues = 0;
		this.intelligence = 0;
		this.sheddingLevel = 0;
		this.socialNeeds = 0;
		this.strangerFriendly = 0;
		this.vocalisation = 0;
		this.experimental = 0;
		this.hairless = 0;
		this.natural = 0;
		this.rare = 0;
		this.rex = 0;
		this.suppressedTail = 0;
		this.shortLegs = 0;
		this.wikipediaUrl = "";
		this.hypoallergenic = 0;
		this.referenceImageId = "";
		this.image = null;
	}

	public Breed(
		BreedWeight weight,
		String id,
		String name,
		String cfaUrl,
		String vetstreetUrl,
		String vcahospitalsUrl,
		String temperament,
		String origin,
		String countryCodes,
		String countryCode,
		String description,
		String lifeSpan,
		Integer indoor,
		Integer lap,
		String altNames,
		Integer adaptability,
		Integer affectionLevel,
		Integer childFriendly,
		Integer dogFriendly,
		Integer energyLevel,
		Integer grooming,
		Integer healthIssues,
		Integer intelligence,
		Integer sheddingLevel,
		Integer socialNeeds,
		Integer strangerFriendly,
		Integer vocalisation,
		Integer experimental,
		Integer hairless,
		Integer natural,
		Integer rare,
		Integer rex,
		Integer suppressedTail,
		Integer shortLegs,
		String wikipediaUrl,
		Integer hypoallergenic,
		String referenceImageId,
		BreedImage image) {
		super();
		
		this.weight = weight;
		this.id = id;
		this.name = name;
		this.cfaUrl = cfaUrl;
		this.vetstreetUrl = vetstreetUrl;
		this.vcahospitalsUrl = vcahospitalsUrl;
		this.temperament = temperament;
		this.origin = origin;
		this.countryCodes = countryCodes;
		this.countryCode = countryCode;
		this.description = description;
		this.lifeSpan = lifeSpan;
		this.indoor = indoor;
		this.lap = lap;
		this.altNames = altNames;
		this.adaptability = adaptability;
		this.affectionLevel = affectionLevel;
		this.childFriendly = childFriendly;
		this.dogFriendly = dogFriendly;
		this.energyLevel = energyLevel;
		this.grooming = grooming;
		this.healthIssues = healthIssues;
		this.intelligence = intelligence;
		this.sheddingLevel = sheddingLevel;
		this.socialNeeds = socialNeeds;
		this.strangerFriendly = strangerFriendly;
		this.vocalisation = vocalisation;
		this.experimental = experimental;
		this.hairless = hairless;
		this.natural = natural;
		this.rare = rare;
		this.rex = rex;
		this.suppressedTail = suppressedTail;
		this.shortLegs = shortLegs;
		this.wikipediaUrl = wikipediaUrl;
		this.hypoallergenic = hypoallergenic;
		this.referenceImageId = referenceImageId;
		this.image = image;

		this.baseInit(AppDefs.BASE_OBJTYPE_BREED, name);
	}

	public Breed(String strJSON)
		throws Exception
	{
		JSONObject jsonObj = new JSONObject(strJSON);
		this.fromJSONObject(jsonObj);
	}

	public Breed(JSONObject jsonObj)
		throws Exception
	{
		this.fromJSONObject(jsonObj);
	}
	
	/* Methodes */
	
	@Override
	public void init(JSONObject jsonObj) {
		try {
			this.fromJSONObject(jsonObj);
		}
		catch(Exception e) {
			AppMain.getApp().getErr().writeError(this.getClass().getName(), "init", e.getMessage());
			e.printStackTrace();
		}
	}
	
	public String toStrJSON() {
		String strJSON = "{ " +
			"\"weight\":{ " + this.weight.toStrJSON() + " }," +
			"\"id\":\"" + this.id + "\"," +
			"\"name\":\"" + this.name + "\"," +
			"\"cfa_url\":\"" + this.name + "\"," +
			"\"vetstreet_url\":\"" + this.vetstreetUrl + "\"," +
			"\"vcahospitals_url\":\"" + this.vcahospitalsUrl + "\"," +
			"\"temperament\":\"" + this.temperament + "\"," +
			"\"origin\":\"" + this.origin + "\"," +
			"\"country_codes\":\"" + this.countryCodes + "\"," +
			"\"country_code\":\"" + this.countryCode + "\"," +
			"\"description\":\"" + this.description + "\"," +
			"\"life_span\":\"" + this.lifeSpan + "\"," +
			"\"indoor\":" + Integer.toString(this.indoor) + "," +
			"\"lap\":\"" + Integer.toString(this.indoor) + "," +
			"\"alt_names\":\"" + this.altNames + "\"," +
			"\"adaptability\":" + Integer.toString(this.adaptability) + "," +
			"\"affection_level\":" + Integer.toString(this.affectionLevel) + "," +
			"\"child_friendly\":" + Integer.toString(this.childFriendly) + "," +
			"\"dog_friendly\":" + Integer.toString(this.dogFriendly) + "," +
			"\"energy_level\":" + Integer.toString(this.energyLevel) + "," +
			"\"grooming\":" + Integer.toString(this.grooming) + "," +
			"\"health_issues\":" + Integer.toString(this.healthIssues) + "," +
			"\"intelligence\":" + Integer.toString(this.intelligence) + "," +
			"\"shedding_level\":" + Integer.toString(this.sheddingLevel) + "," +
			"\"social_needs\":" + Integer.toString(this.socialNeeds) + "," +
			"\"stranger_friendly\":" + Integer.toString(this.strangerFriendly) + "," +
			"\"vocalisation\":" + Integer.toString(this.vocalisation) + "," +
			"\"experimental\":" + Integer.toString(this.experimental) + "," +
			"\"hairless\":" + Integer.toString(this.hairless) + "," +
			"\"natural\":" + Integer.toString(this.natural) + "," +
			"\"rare\":" + Integer.toString(this.rare) + "," +
			"\"rex\":" + Integer.toString(this.rex) + "," +
			"\"suppressed_tail\":" + Integer.toString(this.suppressedTail) + "," +
			"\"short_legs\":" + Integer.toString(this.shortLegs) + "," +
			"\"wikipedia_url\":\"" + this.wikipediaUrl + "\"," +
			"\"hypoallergenic\":" + Integer.toString(this.hypoallergenic) + "," +
			"\"reference_image_id\":\"" + this.referenceImageId + "\"," +
			"\"image\":{ " + image.toStrJSON() + " } }";
		return strJSON;
	}

	public void fromStrJSON(String strJSON)
		throws Exception
	{
		JSONObject jsonObj = new JSONObject(strJSON);
		this.fromJSONObject(jsonObj);
	}
	
	public JSONObject toJSON() 
		throws Exception
	{
		String strJSON = this.toStrJSON();
		JSONObject jsonObj = new JSONObject(strJSON);
		return jsonObj;
	}

	public void fromJSONObject(JSONObject jsonObj)
		throws Exception
	{
		this.weight = (BreedWeight)JSONUtil.safeObjFromJSON(jsonObj, "weight", new BreedWeight());		
		this.id = JSONUtil.safeStrFromJSON(jsonObj, "id", "-1");
		this.name = JSONUtil.safeStrFromJSON(jsonObj, "name", "");
		this.cfaUrl = JSONUtil.safeStrFromJSON(jsonObj, "cfa_url", "");
		this.vetstreetUrl = JSONUtil.safeStrFromJSON(jsonObj, "vetstreet_url", "");
		this.vcahospitalsUrl = JSONUtil.safeStrFromJSON(jsonObj, "vcahospitals_url", "");
		this.temperament = JSONUtil.safeStrFromJSON(jsonObj, "temperament", "");
		this.origin = JSONUtil.safeStrFromJSON(jsonObj, "origin", "");
		this.countryCodes = JSONUtil.safeStrFromJSON(jsonObj, "country_codes", "");
		this.countryCode = JSONUtil.safeStrFromJSON(jsonObj, "country_code", "");
		this.description = JSONUtil.safeStrFromJSON(jsonObj, "description", "");
		this.lifeSpan = JSONUtil.safeStrFromJSON(jsonObj, "life_span", "");
		this.indoor = JSONUtil.safeIntFromJSON(jsonObj, "indoor", 0);
		this.lap = JSONUtil.safeIntFromJSON(jsonObj, "lap", 0);
		this.altNames = JSONUtil.safeStrFromJSON(jsonObj, "alt_names", "");
		this.adaptability = JSONUtil.safeIntFromJSON(jsonObj, "adaptability", 0);
		this.affectionLevel = JSONUtil.safeIntFromJSON(jsonObj, "affection_level", 0);
		this.childFriendly = JSONUtil.safeIntFromJSON(jsonObj, "child_friendly", 0);
		this.dogFriendly = JSONUtil.safeIntFromJSON(jsonObj, "dog_friendly", 0);
		this.energyLevel = JSONUtil.safeIntFromJSON(jsonObj, "energy_level", 0);
		this.grooming = JSONUtil.safeIntFromJSON(jsonObj, "grooming", 0);
		this.healthIssues = JSONUtil.safeIntFromJSON(jsonObj, "health_issues", 0);
		this.intelligence = JSONUtil.safeIntFromJSON(jsonObj, "intelligence", 0);
		this.sheddingLevel = JSONUtil.safeIntFromJSON(jsonObj, "shedding_level", 0);
		this.socialNeeds = JSONUtil.safeIntFromJSON(jsonObj, "social_needs", 0);
		this.strangerFriendly = JSONUtil.safeIntFromJSON(jsonObj, "stranger_friendly", 0);
		this.vocalisation = JSONUtil.safeIntFromJSON(jsonObj, "vocalisation", 0);
		this.experimental = JSONUtil.safeIntFromJSON(jsonObj, "experimental", 0);
		this.hairless = JSONUtil.safeIntFromJSON(jsonObj, "hairless", 0);
		this.natural = JSONUtil.safeIntFromJSON(jsonObj, "natural", 0);
		this.rare = JSONUtil.safeIntFromJSON(jsonObj, "rare", 0);
		this.rex = JSONUtil.safeIntFromJSON(jsonObj, "rex", 0);
		this.suppressedTail = JSONUtil.safeIntFromJSON(jsonObj, "suppressed_tail", 0);
		this.shortLegs = JSONUtil.safeIntFromJSON(jsonObj, "short_legs", 0);
		this.wikipediaUrl = JSONUtil.safeStrFromJSON(jsonObj, "wikipedia_url", "");
		this.hypoallergenic = JSONUtil.safeIntFromJSON(jsonObj, "hypoallergenic", 0);
		this.referenceImageId = JSONUtil.safeStrFromJSON(jsonObj, "reference_image_id", "");
		this.image = (BreedImage)JSONUtil.safeObjFromJSON(jsonObj, "image", new BreedImage());
		
		this.setBaseDescription(name);
	}

	@Override
	public void debug(int debugLevel) {
		if( !AppMain.getApp().getErr().checkDebugLevel(debugLevel) ) return;
		
		String strJSON = this.toStrJSON(); 
		AppMain.getApp().getErr().writeDebug(this.getClass().getName(), "debug", strJSON);
	}

	/* Getters/Setters */

	public BreedWeight getWeight() {
		return weight;
	}

	public void setWeight(BreedWeight weight) {
		this.weight = weight;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCfaUrl() {
		return cfaUrl;
	}

	public void setCfaUrl(String cfaUrl) {
		this.cfaUrl = cfaUrl;
	}

	public String getVetstreetUrl() {
		return vetstreetUrl;
	}

	public void setVetstreetUrl(String vetstreetUrl) {
		this.vetstreetUrl = vetstreetUrl;
	}

	public String getVcahospitalsUrl() {
		return vcahospitalsUrl;
	}

	public void setVcahospitalsUrl(String vcahospitalsUrl) {
		this.vcahospitalsUrl = vcahospitalsUrl;
	}

	public String getTemperament() {
		return temperament;
	}

	public void setTemperament(String temperament) {
		this.temperament = temperament;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getCountryCodes() {
		return countryCodes;
	}

	public void setCountryCodes(String countryCodes) {
		this.countryCodes = countryCodes;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLifeSpan() {
		return lifeSpan;
	}

	public void setLifeSpan(String lifeSpan) {
		this.lifeSpan = lifeSpan;
	}

	public Integer getIndoor() {
		return indoor;
	}

	public void setIndoor(Integer indoor) {
		this.indoor = indoor;
	}

	public Integer getLap() {
		return lap;
	}

	public void setLap(Integer lap) {
		this.lap = lap;
	}

	public String getAltNames() {
		return altNames;
	}

	public void setAltNames(String altNames) {
		this.altNames = altNames;
	}

	public Integer getAdaptability() {
		return adaptability;
	}

	public void setAdaptability(Integer adaptability) {
		this.adaptability = adaptability;
	}

	public Integer getAffectionLevel() {
		return affectionLevel;
	}

	public void setAffectionLevel(Integer affectionLevel) {
		this.affectionLevel = affectionLevel;
	}

	public Integer getChildFriendly() {
		return childFriendly;
	}

	public void setChildFriendly(Integer childFriendly) {
		this.childFriendly = childFriendly;
	}

	public Integer getDogFriendly() {
		return dogFriendly;
	}

	public void setDogFriendly(Integer dogFriendly) {
		this.dogFriendly = dogFriendly;
	}

	public Integer getEnergyLevel() {
		return energyLevel;
	}

	public void setEnergyLevel(Integer energyLevel) {
		this.energyLevel = energyLevel;
	}

	public Integer getGrooming() {
		return grooming;
	}

	public void setGrooming(Integer grooming) {
		this.grooming = grooming;
	}

	public Integer getHealthIssues() {
		return healthIssues;
	}

	public void setHealthIssues(Integer healthIssues) {
		this.healthIssues = healthIssues;
	}

	public Integer getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(Integer intelligence) {
		this.intelligence = intelligence;
	}

	public Integer getSheddingLevel() {
		return sheddingLevel;
	}

	public void setSheddingLevel(Integer sheddingLevel) {
		this.sheddingLevel = sheddingLevel;
	}

	public Integer getSocialNeeds() {
		return socialNeeds;
	}

	public void setSocialNeeds(Integer socialNeeds) {
		this.socialNeeds = socialNeeds;
	}

	public Integer getStrangerFriendly() {
		return strangerFriendly;
	}

	public void setStrangerFriendly(Integer strangerFriendly) {
		this.strangerFriendly = strangerFriendly;
	}

	public Integer getVocalisation() {
		return vocalisation;
	}

	public void setVocalisation(Integer vocalisation) {
		this.vocalisation = vocalisation;
	}

	public Integer getExperimental() {
		return experimental;
	}

	public void setExperimental(Integer experimental) {
		this.experimental = experimental;
	}

	public Integer getHairless() {
		return hairless;
	}

	public void setHairless(Integer hairless) {
		this.hairless = hairless;
	}

	public Integer getNatural() {
		return natural;
	}

	public void setNatural(Integer natural) {
		this.natural = natural;
	}

	public Integer getRare() {
		return rare;
	}

	public void setRare(Integer rare) {
		this.rare = rare;
	}

	public Integer getRex() {
		return rex;
	}

	public void setRex(Integer rex) {
		this.rex = rex;
	}

	public Integer getSuppressedTail() {
		return suppressedTail;
	}

	public void setSuppressedTail(Integer suppressedTail) {
		this.suppressedTail = suppressedTail;
	}

	public Integer getShortLegs() {
		return shortLegs;
	}

	public void setShortLegs(Integer shortLegs) {
		this.shortLegs = shortLegs;
	}

	public String getWikipediaUrl() {
		return wikipediaUrl;
	}

	public void setWikipediaUrl(String wikipediaUrl) {
		this.wikipediaUrl = wikipediaUrl;
	}

	public Integer getHypoallergenic() {
		return hypoallergenic;
	}

	public void setHypoallergenic(Integer hypoallergenic) {
		this.hypoallergenic = hypoallergenic;
	}

	public String getReferenceImageId() {
		return referenceImageId;
	}

	public void setReferenceImageId(String referenceImageId) {
		this.referenceImageId = referenceImageId;
	}

	public BreedImage getImage() {
		return image;
	}

	public void setImage(BreedImage image) {
		this.image = image;
	}
			
}
