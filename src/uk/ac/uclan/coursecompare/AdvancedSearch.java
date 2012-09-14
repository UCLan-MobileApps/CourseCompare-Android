package uk.ac.uclan.coursecompare;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class AdvancedSearch extends Activity {

    /** Called when the activity is first created. */
	
	//loads the advanced menu options
	XMLHandler myXMLHandler = new XMLHandler();
	XMLSearch myXMLSearch = new XMLSearch();
	
	EditText keywordText;
	EditText postcodeText;
    
    //spinner list strings and arrays
    String[] studyModeItems;
    ArrayList<String> studyModeStrings = new ArrayList<String>();
    String choosenStudyMode;
    
    String[] institutionsItems;
    ArrayList<String> institutionsStrings = new ArrayList<String>();
    String choosenInstitution;
    
    String[] qualificationsItems;
    ArrayList<String> qualificationsStrings = new ArrayList<String>();
    String choosenQualification;
    
    String[] distanceItems;
    ArrayList<String> distanceStrings = new ArrayList<String>();
    String choosenDistance;
    
    String[] dunitItems;
    ArrayList<String> dunitStrings = new ArrayList<String>();
    String choosenUnits;
    
    String[] orderItems;
    ArrayList<String> orderStrings = new ArrayList<String>();
    String choosenOrder;
    
    String postcode;
    String keyword;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        
      //hide the name
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        setContentView(R.layout.advancedsearch);
        
        //set to portrait only
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        
        URL url1;
		try {
			url1 = new URL("http://coursedata.k-int.com/discover/?adv=true&q=blank&provider=&qualification=*&studyMode=*&distance=25&dunit=miles&order=distance&location=&format=xml");
			new ReadXMLTask(this).execute(url1);
		} catch (MalformedURLException e) {
			Log.d("catch","catch 1" + e.toString());
		}
	
        //load the search button
        Button search;
        search = (Button) findViewById(R.id.search);

        search.setOnClickListener(new OnClickListener () {
        	public void onClick(View arg0) {
       
        		searchCourses();
        	}
        });
    }

    public void searchCourses() {
    	
		keywordText = (EditText) findViewById(R.id.keyword);
		String newString = keywordText.getText().toString();
		keyword = newString.replaceAll("\\s", "");
		//Log.d("input text", keyword);
		
		postcodeText = (EditText) findViewById(R.id.postcode);
		String newString2 = postcodeText.getText().toString();
		postcode = newString2.replaceAll("\\s", "");
		//Log.d("input text", postcode);
		
  		if (keyword.length() == 0) {
  			
  			AlertDialog.Builder alertdialog = new AlertDialog.Builder(this);
  			alertdialog.setTitle("Alert");
  			alertdialog.setMessage("You need to type a keyword");
  			alertdialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
  				
  				public void onClick(DialogInterface dialog, int which) {
  					
  				}
  			});
  			alertdialog.show();
  		}
  		
  		else {
		
  		String searchURL = ("http://coursedata.k-int.com/discover/?adv=true&q=" + keyword + "&provider=" + choosenInstitution + "&qualification=" + choosenQualification + "&studyMode=" + choosenStudyMode + "&distance=" + choosenDistance + "&dunit=" + choosenUnits + "&order=" + choosenOrder + "&location=" + postcode + "&max=100&format=xml");
  		GlobalState gs = (GlobalState) getApplication();
  		gs.setUrlSearch(searchURL);
  		
		//submit the search
    	URL url2;
		try {
			url2 = new URL("http://coursedata.k-int.com/discover/?adv=true&q=" + keyword + "&provider=" + choosenInstitution + "&qualification=" + choosenQualification + "&studyMode=" + choosenStudyMode + "&distance=" + choosenDistance + "&dunit=" + choosenUnits + "&order=" + choosenOrder + "&location=" + postcode + "&max=100&offset=0&format=xml");
			new SearchXMLTask(this).execute(url2);
		} catch (MalformedURLException e) {
			Log.d("catch","catch 1" + e.toString());
		}
		finally {
		}
  			}
    }
    
   	public void displayAlert() {
		
			AlertDialog.Builder alertdialog = new AlertDialog.Builder(this);
			alertdialog.setTitle("Network Error");
			alertdialog.setMessage("Error connecting to the server, please try again or reload the App");
			alertdialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					
				}
			});
			alertdialog.show();
   	}
   	
   	public void displayAlert2() {
		
			AlertDialog.Builder alertdialog = new AlertDialog.Builder(this);
			alertdialog.setTitle("Alert");
			alertdialog.setMessage("There are no results for this search, try a broader search or keyword");
			alertdialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					
				}
			});
			alertdialog.show();
   	}
    
    //setup menus xml structure
	private class ReadXMLTask extends AsyncTask<URL, Integer, String> {
		
		private ProgressDialog status;
		private AdvancedSearch activity;
		
		public ReadXMLTask(AdvancedSearch activity) {
			this.activity = activity;
			status = new ProgressDialog(activity);
		}
	
		@Override
		protected void onPreExecute() {
			
			this.status.setCancelable(true);
			this.status.setMessage("Loading...");
			this.status.show();
			
			//Log.d("start", "start");
		}
		
		@Override
		protected String doInBackground(URL... urls) {
			
			try {
			/**
			 * Create a new instance of the SAX parser
			 **/
			SAXParserFactory saxPF = SAXParserFactory.newInstance();
			SAXParser saxP = saxPF.newSAXParser();
			XMLReader xmlR = saxP.getXMLReader();
			
			/** 
			 * Create the Handler to handle each of the XML tags. 
			 **/
			
			xmlR.setContentHandler(myXMLHandler);
			InputSource is = new InputSource(urls[0].openStream());
			is.setEncoding("utf-8");
			xmlR.parse(is);
			}
			catch (Exception e) {

				Log.d("catch", "catch 2 " + e.toString());
				this.status.cancel();
				
				publishProgress();
			}
			
			return null;
		}
		
		protected void onProgressUpdate(Integer... progress) {
			//Log.d("middle", "middle");
			displayAlert();//display error alert
		}

		@Override
		protected void onPostExecute(String result) {
			
			this.status.cancel();
			//Log.d("end", "end");		
			
			//update display here
			createTheDisplay();
		}
	}
	
    //setup xml structure
	private class SearchXMLTask extends AsyncTask<URL, Integer, String> {
		
		private ProgressDialog status;
		private AdvancedSearch activity;
		
		public SearchXMLTask(AdvancedSearch activity) {
			this.activity = activity;
			status = new ProgressDialog(activity);
		}
	
		@Override
		protected void onPreExecute() {
			
			this.status.setCancelable(true);
			this.status.setMessage("Searching...");
			this.status.show();
			
			//Log.d("start", "start");
		}
		
		@Override
		protected String doInBackground(URL... urls) {
			
			try {
			/**
			 * Create a new instance of the SAX parser
			 **/
			SAXParserFactory saxPF2 = SAXParserFactory.newInstance();
			SAXParser saxP2 = saxPF2.newSAXParser();
			XMLReader xmlR2 = saxP2.getXMLReader();
			
			/** 
			 * Create the Handler to handle each of the XML tags. 
			 **/
			
			//Log.d("xml","" + urls[0]);
			
			
			
			xmlR2.setContentHandler(myXMLSearch);
			InputSource is = new InputSource(urls[0].openStream());
			is.setEncoding("UTF-8");
			xmlR2.parse(is);
			}
			catch (Exception e) {

				Log.d("catch", "catch " + e.toString());
				this.status.cancel();
				
				publishProgress();
			}
			
			return null;
		}
		
		protected void onProgressUpdate(Integer... progress) {
			Log.d("middle", "middle");
			displayAlert();//display error alert
		}

		@Override
		protected void onPostExecute(String result) {
			
			this.status.cancel();
			//Log.d("end", "end");
			
   			//set the list global to use later
   			GlobalState gs = (GlobalState) getApplication();
   			gs.setGlobalCourseList(myXMLSearch.getCourseList());
   			gs.setGlobalPresentations(myXMLSearch.getPresentations());
   			gs.setGlobalCredits(myXMLSearch.getCredits());
   			gs.setTotalHits(myXMLSearch.getTotalHits());
   			
   			if(myXMLSearch.getTotalHits().equalsIgnoreCase("0")) {
   				Log.d("results", "no results");
   				displayAlert2();
   			}
   			//Log.d("end", "end");
			else {
   			startActivity(new Intent("uk.ac.uclan.SEARCHLIST"));//load the list
				}
		}
	}
	
		//setup the display with the menu choices
		public void createTheDisplay() {
			
		//Log.d("updating display", "updated");
		
		//studymodes
		final ArrayList<ListModel> studyModelist =  myXMLHandler.getStudyModeList();
			
        Spinner studyModeSpinner = (Spinner) this.findViewById(R.id.studymodes);
        
        for (int i = 0; i < studyModelist.size() ; i++) {
    	   
    	   String testString = studyModelist.get(i).getListName();
    	   
    	   //Log.d("test data 4", testString);

    	   studyModeStrings.add(testString);
        }
       
       studyModeItems = studyModeStrings.toArray(new String[0]);

       ArrayAdapter<String> studyModeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, studyModeItems);
       studyModeSpinner.setAdapter(studyModeAdapter);	
       studyModeSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

    	  public void onNothingSelected(AdapterView<?> parentView) {
    		   choosenStudyMode = "*";
    	   }
    	  @Override
    	  public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			//Log.d("item selected", studyModelist.get(arg2).getListCode());
			//Log.d("item selected", studyModelist.get(arg2).getListName());
			String newString = studyModelist.get(arg2).getListCode();
			choosenStudyMode = newString.replaceAll("\\s", "");
			//Log.d("new", choosenStudyMode);
    	  }   
       });
       
       //institutions
		final ArrayList<ListModel> institutionslist =  myXMLHandler.getInstitutionsList();
		
        Spinner institutionsSpinner = (Spinner) this.findViewById(R.id.institutions);
        
        for (int i = 0; i < institutionslist.size() ; i++) {
    	   
    	   String testString = institutionslist.get(i).getListName();
    	   
    	   //Log.d("test data 4", testString);

    	   institutionsStrings.add(testString);
        }
       
       institutionsItems = institutionsStrings.toArray(new String[0]);

       ArrayAdapter<String> institutionsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, institutionsItems);
       institutionsSpinner.setAdapter(institutionsAdapter);
       institutionsSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

     	  public void onNothingSelected(AdapterView<?> parentView) {
     		   choosenInstitution = "";
     	   }
     	  @Override
     	  public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
 				long arg3) {
 			//Log.d("item selected", institutionslist.get(arg2).getListCode());
 			//Log.d("item selected", institutionslist.get(arg2).getListName());
 			choosenInstitution = institutionslist.get(arg2).getListCode();
     	  }   
        });
       
       //qualifications
		final ArrayList<ListModel> qualificationslist =  myXMLHandler.getQualificationsList();
		
        Spinner qualificationsSpinner = (Spinner) this.findViewById(R.id.qualifications);
        
        for (int i = 0; i < qualificationslist.size() ; i++) {
    	   
    	   String testString = qualificationslist.get(i).getListName();
    	   
    	   //Log.d("test data 4", testString);

    	   qualificationsStrings.add(testString);
        }
       
       qualificationsItems = qualificationsStrings.toArray(new String[0]);

       ArrayAdapter<String> qualificationsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, qualificationsItems);
       qualificationsSpinner.setAdapter(qualificationsAdapter);
       qualificationsSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

     	  public void onNothingSelected(AdapterView<?> parentView) {
     		   choosenQualification = "*";
     	   }
     	  @Override
     	  public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
 				long arg3) {
 			//Log.d("item selected", qualificationslist.get(arg2).getListCode());
 			//Log.d("item selected", qualificationslist.get(arg2).getListName());
 			choosenQualification = qualificationslist.get(arg2).getListCode();
     	  }   
        });
       
       //distance
		final ArrayList<ListModel> distancelist =  myXMLHandler.getDistanceList();
		
        Spinner distanceSpinner = (Spinner) this.findViewById(R.id.distance);
        
        for (int i = 0; i < distancelist.size() ; i++) {
    	   
    	   String testString = distancelist.get(i).getListName();
    	   
    	   //Log.d("test data 4", testString);

    	   distanceStrings.add(testString);
        }
       
       distanceItems = distanceStrings.toArray(new String[0]);

       ArrayAdapter<String> distanceAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, distanceItems);
       distanceSpinner.setAdapter(distanceAdapter);
       distanceSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

     	  public void onNothingSelected(AdapterView<?> parentView) {
     		   choosenDistance = "25";
     	   }
     	  @Override
     	  public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
 				long arg3) {
 			//Log.d("item selected", distancelist.get(arg2).getListCode());
 			//Log.d("item selected", distancelist.get(arg2).getListName());
 			choosenDistance = distancelist.get(arg2).getListCode();
     	  }   
        });
       
       //distance units
		final ArrayList<ListModel> dunitlist =  myXMLHandler.getDunitsList();
		
        Spinner dunitSpinner = (Spinner) this.findViewById(R.id.units);
        
        for (int i = 0; i < dunitlist.size() ; i++) {
    	   
    	   String testString = dunitlist.get(i).getListName();
    	   
    	   //Log.d("test data 4", testString);

    	   dunitStrings.add(testString);
        }
       
       dunitItems = dunitStrings.toArray(new String[0]);

       ArrayAdapter<String> dunitAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, dunitItems);
       dunitSpinner.setAdapter(dunitAdapter);
       dunitSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

     	  public void onNothingSelected(AdapterView<?> parentView) {
     		   choosenUnits = "miles";
     	   }
     	  @Override
     	  public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
 				long arg3) {
 			//Log.d("item selected", dunitlist.get(arg2).getListCode());
 			//Log.d("item selected", dunitlist.get(arg2).getListName());
 			choosenUnits = dunitlist.get(arg2).getListCode();
     	  }   
        });
       
       //order
		final ArrayList<ListModel> orderlist =  myXMLHandler.getOrderList();
		
        Spinner orderSpinner = (Spinner) this.findViewById(R.id.displayorder);
        
        for (int i = 0; i < orderlist.size() ; i++) {
    	   
    	   String testString = orderlist.get(i).getListName();
    	   
    	   //Log.d("test data 4", testString);

    	   orderStrings.add(testString);
        }
       
       orderItems = orderStrings.toArray(new String[0]);

       ArrayAdapter<String> orderAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, orderItems);
       orderSpinner.setAdapter(orderAdapter);
       orderSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
    	  
      	  public void onNothingSelected(AdapterView<?> parentView) {
      		   choosenOrder = "distance";
      	   }
      	  @Override
      	  public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
  				long arg3) {
  			//Log.d("item selected", orderlist.get(arg2).getListCode());
  			//Log.d("item selected", orderlist.get(arg2).getListName());
  			choosenOrder = orderlist.get(arg2).getListCode();
      	  }   
         });
		}	
}
