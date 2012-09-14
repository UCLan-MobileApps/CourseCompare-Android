package uk.ac.uclan.coursecompare;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

public class XMLSearch extends DefaultHandler {
	
	//used to search for a course
	String elementValue = null;
	String elementCode = null;
	
	String totalHits;

	Boolean elementOn = false;
	
	//search change booleans
	Boolean qualifications = false; //title and descriptions
	Boolean isString = false;
	Boolean pres = false;
	Boolean cred = false;
	
	//create course array lists	and objects
	SearchModel courseEntry = new SearchModel();	
	ArrayList<SearchModel> courseList = new ArrayList<SearchModel>();
	
	//presentations
	PresentationsModel presentationEntry = new PresentationsModel();
	ArrayList<PresentationsModel> presentationsList = new ArrayList<PresentationsModel>();
	ArrayList<ArrayList<PresentationsModel>> presentations = new ArrayList<ArrayList<PresentationsModel>>();
	
	//credits
	CreditsModel creditEntry = new CreditsModel();
	ArrayList<CreditsModel> creditsList = new ArrayList<CreditsModel>();
	ArrayList<ArrayList<CreditsModel>> credits = new ArrayList<ArrayList<CreditsModel>>();
	/**
	 * read when the document is first loaded, here we setup the arrays
	 */
	@Override
	public void startDocument() {
		courseList = new ArrayList<SearchModel>();
		presentations = new ArrayList<ArrayList<PresentationsModel>>();
		pres = false;
		cred = false;
	}
	
	/** 
	 * This will be called when the tags of the XML starts.
	 **/
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		elementOn = true;
		
		String attributeValue = attributes.getValue("key");
		
		if (localName.equalsIgnoreCase("source")) {
			//Log.d("course", "start");
			courseEntry = new SearchModel(); //create a new list array
			presentationsList = new ArrayList<PresentationsModel>(); //create a new list
			creditsList = new ArrayList<CreditsModel>(); // create a new list
			//Log.d("pres", "create new list");
			pres = false;
			cred = false;
			}
		
		if (localName.equalsIgnoreCase("map")) {
			if (pres == true) {
				presentationEntry = new PresentationsModel();//create a new entry
				//Log.d("pres", "create new entry");
			}
			if (cred == true) {
				creditEntry = new CreditsModel();//create a new entry
			}
		}
		
		if(localName.equalsIgnoreCase("entry")) {
			elementValue = new String("");
			}
		
		if(localName.equalsIgnoreCase("string")) {
			isString = true;
		}
		
		if (!(attributeValue == null)) {
			
			elementCode = attributeValue;
			
			//Log.d("elementCode","" + attributeValue);
			
		if (!attributeValue.isEmpty() && attributeValue.equalsIgnoreCase("qual")) {
			//Log.d("setting", "turn on qual");
			qualifications = true;
			}
		
		if(!attributeValue.isEmpty() && attributeValue.equalsIgnoreCase("identifier")) {
			
			qualifications = false; // set it back	
			}
		
		if (!attributeValue.isEmpty() && attributeValue.equalsIgnoreCase("presentations")) {
			//Log.d("setting", "turn on pres");
			pres = true;
			cred = false;
			}
		
		if (!attributeValue.isEmpty() && attributeValue.equalsIgnoreCase("credits")) {
			//Log.d("setting", "turn on qual");
			cred = true;
			pres = false;
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
		
		//check to add extra strings to the name
		if(qualifications == true) {
			elementCode = "qual" + elementCode;
			//Log.d("setting", "adding qual");
		}
		
		if(localName.equalsIgnoreCase("source")) {
			//course = false; //end course list
			courseList.add(courseEntry); // add to main list
			presentations.add(presentationsList);
			credits.add(creditsList);
			//Log.d("setting", "adding presentation");
		}
		
		if(localName.equalsIgnoreCase("string")) {
			isString = false;
		}
		
		if(localName.equalsIgnoreCase("map")) {
			if (pres == true) {
				presentationsList.add(presentationEntry);
				//Log.d("settings", "adding pres entry");
			}
			if (cred == true) {
				creditsList.add(creditEntry);
			}
		}
		
		//long if checks for attributes and set values
		if(localName.equalsIgnoreCase("entry")) {
			//Log.d("entry", "is entry");
			//Log.d("entry", "code is =" + elementCode);
			
			if(elementCode.equalsIgnoreCase("title")) {
				courseEntry.setTitle(elementValue);
				//Log.d("add", "adding title");
			}
			else if(elementCode.equalsIgnoreCase("start")) {
				presentationEntry.setStart(elementValue);
			}
			else if(elementCode.equalsIgnoreCase("studyMode")) {
				presentationEntry.setStudyMode(elementValue);
			}
			else if(elementCode.equalsIgnoreCase("street")) {
				presentationEntry.setVenuestreet(elementValue);
			}
			else if(elementCode.equalsIgnoreCase("name")) {
				presentationEntry.setVenuename(elementValue);
			}
			else if(elementCode.equalsIgnoreCase("town")) {
				presentationEntry.setVenuetown(elementValue);
			}
			else if(elementCode.equalsIgnoreCase("postcode")) {
				presentationEntry.setVenuepostcode(elementValue);
			}
			else if(elementCode.equalsIgnoreCase("cost")) {
				presentationEntry.setCost(elementValue);
			}
			else if(elementCode.equalsIgnoreCase("end")) {
				presentationEntry.setEnd(elementValue);
			}
			else if(elementCode.equalsIgnoreCase("subject")) {
				courseEntry.setSubject(elementValue);
			}
			else if(elementCode.equalsIgnoreCase("leadsTo")) {
				courseEntry.setLeadsTo(elementValue);
			}
			else if(elementCode.equalsIgnoreCase("careerOutcome")) {
				courseEntry.setCareerOutcome(elementValue);
			}
			else if(elementCode.equalsIgnoreCase("provtitle")) {
				courseEntry.setProvtitle(elementValue);
			}
			else if(elementCode.equalsIgnoreCase("url")) {
				courseEntry.setUrl(elementValue);
			}
			else if(elementCode.equalsIgnoreCase("prerequisites")) {
				courseEntry.setPrerequisites(elementValue);
			}
			else if(elementCode.equalsIgnoreCase("aim")) {
				courseEntry.setAim(elementValue);
			}
			else if(elementCode.equalsIgnoreCase("description")) {
				if (pres == true) {
					presentationEntry.setDescription(elementValue);
				}
				else {
				courseEntry.setDescription(elementValue);
				}
			}
			else if(elementCode.equalsIgnoreCase("syllabus")) {
				courseEntry.setSyllabus(elementValue);
			}
			else if(elementCode.equalsIgnoreCase("qualawardedBy")) {
				courseEntry.setQualawardedBy(elementValue);
			}
			else if(elementCode.equalsIgnoreCase("qualtitle")) {
				courseEntry.setQualtitle(elementValue);
			}
			else if(elementCode.equalsIgnoreCase("quallevel")) {
				courseEntry.setQuallevel(elementValue);
			}
			else if(elementCode.equalsIgnoreCase("qualdescription")) {
				courseEntry.setQualdescription(elementValue);//needs doing
			}
			else if(elementCode.equalsIgnoreCase("qualtype")) {
				courseEntry.setQualtype(elementValue);
			}
			else if(elementCode.equalsIgnoreCase("qualaccreditedBy")) {
				courseEntry.setQualaccreditedBy(elementValue);
			}
			else if(elementCode.equalsIgnoreCase("imageuri")) {
				courseEntry.setImageuri(elementValue);
			}		
			else if(elementCode.equalsIgnoreCase("entryRequirements")) {
				presentationEntry.setEntryRequirements(elementValue);	
			}
			else if(elementCode.equalsIgnoreCase("enquireTo")) {
				presentationEntry.setEnquireTo(elementValue);
			}
			else if(elementCode.equalsIgnoreCase("attendanceMode")) {
				presentationEntry.setAttendanceMode(elementValue);
			}
			else if(elementCode.equalsIgnoreCase("languageOfAssessment")) {
				presentationEntry.setLanguageOfAssessment(elementValue);
			}
			else if(elementCode.equalsIgnoreCase("languageOfInstruction")) {
				presentationEntry.setLanguageOfInstruction(elementValue);
			}
			else if(elementCode.equalsIgnoreCase("attendancePattern")) {
				presentationEntry.setAttendancePattern(elementValue);
			}
			else if(elementCode.equalsIgnoreCase("applyTo")) {
				presentationEntry.setApplyTo(elementValue);
			}
			else if(elementCode.equalsIgnoreCase("applicationsClose")) {
				presentationEntry.setApplicationsClose(elementValue);
			}
			else if(elementCode.equalsIgnoreCase("applicationsOpen")) {
				presentationEntry.setApplicationsOpen(elementValue);
			}
			else if(elementCode.equalsIgnoreCase("duration")) {
				presentationEntry.setDuration(elementValue);
			}
			else if(elementCode.equalsIgnoreCase("abstract")) {
				courseEntry.setCabstract(elementValue);
			}
			else if(elementCode.equalsIgnoreCase("assessmentStrategy")) {
				courseEntry.setAssessmentStrategy(elementValue);
			}
			else if(elementCode.equalsIgnoreCase("indicativeResource")) {
				courseEntry.setIndicativeResource(elementValue);
			}
			else if(elementCode.equalsIgnoreCase("learningOutcome")) {
				courseEntry.setLearningOutcome(elementValue);
			}
			else if(elementCode.equalsIgnoreCase("resultsTotal")) {
				totalHits = elementValue;	
			}
			else if(elementCode.equalsIgnoreCase("val")) {
				creditEntry.setCreditsval(elementValue);
			}
			else if(elementCode.equalsIgnoreCase("level")) {
				creditEntry.setCreditslevel(elementValue);
			}
			else if(elementCode.equalsIgnoreCase("scheme")) {
				creditEntry.setCreditsscheme(elementCode);
			}
			
		}
	}

	/** 
	 * This is called to get the tags value
	 **/
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {

		if (elementOn) {
			
			if(isString == false) {
			elementValue = new String(ch, start, length);
			
			//Log.d("elementValue", elementValue);

			elementOn = false; }
			else {
				elementValue = elementValue + " " + new String(ch, start, length);
			}
		}
	}
	
	/**
	 * This will remove the last duplicate element in each list
	 */
	@Override
	public void endDocument() {
		/*
		int a = courseList.size();
		Log.d("array size", "" + a);
		
		Iterator<SearchModel> courseIterator = courseList.iterator();
		
		while(courseIterator.hasNext()) {
			
			//Log.d("course entry" , "" + courseIterator.next());
		}
		
		int b = presentations.size();
		Log.d("presentation size", "" + b);
		
		for (int i = 0; i < b-1 ; i++) {
		
		Log.d("presentations", "" + presentations.get(i).toString());
		}
		
		int c = credits.size();
		Log.d("credits size", "" + c);
		
		for (int i = 0; i < c-1 ; i++) {
			
		Log.d("credits", "" + credits.get(i).toString());
		}
		*/
	}

	public ArrayList<SearchModel> getCourseList() {
		return courseList;
	}
	
	public ArrayList<ArrayList<PresentationsModel>> getPresentations() {
		return presentations;
	}
	
	public ArrayList<ArrayList<CreditsModel>> getCredits() {
		return credits;
	}
	
	public String getTotalHits() {
		return totalHits;
	}
}
