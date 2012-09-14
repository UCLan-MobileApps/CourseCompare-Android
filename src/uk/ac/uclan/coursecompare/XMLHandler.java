package uk.ac.uclan.coursecompare;

import java.util.ArrayList;
//import java.util.Iterator;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

//import android.util.Log;

import uk.ac.uclan.coursecompare.ListModel;

public class XMLHandler extends DefaultHandler {

	//used to populate the menu options
	
	String elementValue = null;
	Boolean elementOn = false;
	String elementCode = null;
	
	Boolean menuOn = false;
	
	//list booleans for each menu
	Boolean studyModeValue = false;
	Boolean qualificationsValue = false;
	Boolean orderValue = false;
	Boolean distanceValue = false;
	Boolean dunitValue = false;
	Boolean providerValue = false;
	
	//create array lists for each menu
	ArrayList<ListModel> studyModeList = new ArrayList<ListModel>();
	ArrayList<ListModel> qualificationsList = new ArrayList<ListModel>();
	ArrayList<ListModel> orderList = new ArrayList<ListModel>();
	ArrayList<ListModel> distanceList = new ArrayList<ListModel>();
	ArrayList<ListModel> dunitList = new ArrayList<ListModel>();
	ArrayList<ListModel> providerList = new ArrayList<ListModel>();
	
	/**
	 * read when the document is first loaded, here we setup the arrays
	 */
	@Override
	public void startDocument() {
		
		//pre populate array lists with first line
		studyModeList.add(new ListModel("Study Modes", "*"));
		//Log.d("studymodes", "first line added");
		
		qualificationsList.add(new ListModel("Qualifications", "*"));
		//Log.d("qualifications", "first line added");
		
		orderList.add(new ListModel("Order By", "distance"));
		//Log.d("order", "first line added");
		
		distanceList.add(new ListModel("Search Distance", "25"));
		//Log.d("distance", "first line added");
		
		dunitList.add(new ListModel("Search Units", "miles"));
		//Log.d("dunit", "first line added");
		
		providerList.add(new ListModel("Institutions", ""));
		//Log.d("provider", "first line added");
	}
	
	/** 
	 * This will be called when the tags of the XML starts.
	 **/
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		elementOn = true;
		
		String attributeValue = attributes.getValue("key");
		
		if (!(attributeValue == null)) {
			
			elementCode = attributeValue;
			
			//Log.d("elementCode","" + attributeValue);
			
		if (!attributeValue.isEmpty() && attributeValue.equalsIgnoreCase("search_config")) {
			//Log.d("start", "yes");
			menuOn = true;
		}
		if (!attributeValue.isEmpty() && attributeValue.equalsIgnoreCase("studyMode")) {
			//Log.d("studymodes", "yes");
			studyModeValue = true;
		}
		if (!attributeValue.isEmpty() && attributeValue.equalsIgnoreCase("qualification")) {
			//Log.d("qualification", "yes");
			studyModeValue = false;
			qualificationsValue = true;
		}
		if (!attributeValue.isEmpty() && attributeValue.equalsIgnoreCase("order")) {
			//Log.d("order", "yes");
			qualificationsValue = false;
			orderValue = true;	
		}
		if (!attributeValue.isEmpty() && attributeValue.equalsIgnoreCase("distance")) {
			//Log.d("distance", "yes");
			distanceValue = true;
			orderValue = false;	
		}
		if (!attributeValue.isEmpty() && attributeValue.equalsIgnoreCase("dunit")) {
			//Log.d("dunit", "yes");
			dunitValue = true;
			distanceValue = false;	
		}
		if (!attributeValue.isEmpty() && attributeValue.equalsIgnoreCase("provider")) {
			//Log.d("provider", "yes");
			providerValue = true;
			dunitValue = false;	
		}
		if (!attributeValue.isEmpty() && attributeValue.equalsIgnoreCase("hits")) {
			//Log.d("provider", "yes");
			providerValue = false;
			menuOn = false;
		}
		
		}

	}

	/** 
	 * This will be called when the tags of the XML end.
	 **/
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		elementOn = false;
		
		if (studyModeValue == true) {
			studyModeList.add(new ListModel(elementCode, elementValue));
		}
		else if (qualificationsValue == true) {
			qualificationsList.add(new ListModel(elementCode, elementValue));
		}
		else if (orderValue == true) {
			orderList.add(new ListModel(elementCode, elementValue));
		}
		else if (distanceValue == true) {
			distanceList.add(new ListModel(elementCode, elementValue));
		}
		else if (dunitValue == true) {
			dunitList.add(new ListModel(elementCode, elementValue));
		}
		else if (providerValue == true && menuOn == true) {
			providerList.add(new ListModel(elementCode, elementValue));
		}
	}

	/** 
	 * This is called to get the tags value
	 **/
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {

		if (elementOn) {
			elementValue = new String(ch, start, length);
			
			//Log.d("elementValue", elementValue);

			elementOn = false;
		}

	}
	
	/**
	 * This will remove the last duplicate element in each list
	 */
	@Override
	public void endDocument() {
		
		int a = studyModeList.size();
		int b = qualificationsList.size();
		int c = orderList.size();
		int d = distanceList.size();
		int e = dunitList.size();
		
		//clean up list
		studyModeList.remove(a-1);
		qualificationsList.remove(b-1);
		orderList.remove(c-1);
		distanceList.remove(d-1);
		dunitList.remove(e-1);

		providerList.set(1, new ListModel("All" ,""));
		
		int f = providerList.size();
		
		providerList.remove(f-1);
		providerList.remove(f-2);
		
		//logging
		/*
		Log.d("study", "" + studyModeList.size());
		Log.d("qual", "" + qualificationsList.size());
		Log.d("order", "" + orderList.size());
		Log.d("distance", "" + distanceList.size());
		Log.d("dunit", "" + dunitList.size());
		Log.d("prov", "" + providerList.size());
		
		Iterator<ListModel> itr = studyModeList.iterator();
		
		while(itr.hasNext()) {
			Log.d("study contents" , "" + itr.next());
		}
		
		Iterator<ListModel> itr2 = providerList.iterator();
		
		while(itr2.hasNext()) {
			Log.d("prov contents" , "" + itr2.next());
		}
		
		Iterator<ListModel> itr3 = qualificationsList.iterator();
		
		while(itr3.hasNext()) {
			Log.d(" qual contents" , "" + itr3.next());
		}
		
		Iterator<ListModel> itr4 = orderList.iterator();
		
		while(itr4.hasNext()) {
			Log.d("oder contents" , "" + itr4.next());
		}
		
		Iterator<ListModel> itr5 = distanceList.iterator();
		
		while(itr5.hasNext()) {
			Log.d("dist contents" , "" + itr5.next());
		}
		
		Iterator<ListModel> itr6 = dunitList.iterator();
		
		while(itr6.hasNext()) {
			Log.d("dunit contents" , "" + itr6.next());
		}
		//end debugging logging
		 */
	}

	public ArrayList<ListModel> getStudyModeList() {
			return studyModeList;
	}
	
	public ArrayList<ListModel> getInstitutionsList() {
		return providerList;
}
	
	public ArrayList<ListModel> getQualificationsList() {
		return qualificationsList;
}
	
	public ArrayList<ListModel> getDistanceList() {
		return distanceList;
}
	
	public ArrayList<ListModel> getDunitsList() {
		return dunitList;
}
	
	public ArrayList<ListModel> getOrderList() {
		return orderList;
}
	
}
