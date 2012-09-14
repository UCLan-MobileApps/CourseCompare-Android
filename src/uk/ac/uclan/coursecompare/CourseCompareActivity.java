package uk.ac.uclan.coursecompare;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.ActivityInfo;
//import android.util.Log;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;

@SuppressLint("ParserError")
public class CourseCompareActivity extends Activity {
	
	//loads the main basic menu
	XMLSearch myXMLSearch = new XMLSearch();
	
	EditText keywordText;
	
    String keyword;
	
	 /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //hide the name
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        //set to portrait only
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        
        setContentView(R.layout.main);
        
        //setup buttons
        Button advanced, search, information;
        advanced = (Button) findViewById(R.id.advanced);
        search = (Button) findViewById(R.id.search);
        information = (Button) findViewById(R.id.info);
        
        //button actions
        advanced.setOnClickListener(new OnClickListener() 
        {
			//@Override
			public void onClick(View arg0) {
				
			startActivity(new Intent("uk.ac.uclan.ADVANCEDSEARCH"));
			}
		});
        
        search.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View arg0) {
        		//to be done
        		searchCourses(); //basic keyword search
        	}
        		
        });
        
        information.setOnClickListener(new OnClickListener()
        {
			public void onClick(View arg0) {
				
			startActivity(new Intent("uk.ac.uclan.INFORMATION"));
			}
        });
        
        
    } 
    
    public void searchCourses() {
    	
  		keywordText = (EditText) findViewById(R.id.keyword);
  		String newString = keywordText.getText().toString();
  		keyword = newString.replaceAll("\\s", "");
  		//Log.d("input text", keyword);
  		
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
  			
  		String searchURL = ("http://coursedata.k-int.com/discover/?adv=true&q=" + keyword + "&provider=&qualification=*&studyMode=*&distance=25&dunit=miles&order=distance&location=&max=100&format=xml");
  		GlobalState gs = (GlobalState) getApplication();
  		gs.setUrlSearch(searchURL);
  		
  		//submit the search
      	URL url2;
  		try {
  			url2 = new URL("http://coursedata.k-int.com/discover/?adv=true&q=" + keyword + "&provider=&qualification=*&studyMode=*&distance=25&dunit=miles&order=distance&location=&max=100&offset=0&format=xml");
  			new SearchXMLTask(this).execute(url2);
  		} catch (MalformedURLException e) {
  			Log.d("catch","catch 1" + e.toString());
  		}
  		finally {
  		}
  		
  			}
      }
    
    //setup xml structure
   	private class SearchXMLTask extends AsyncTask<URL, Integer, String> {
   		
   		
   		private ProgressDialog status;
   		private CourseCompareActivity activity;
   		
   		public SearchXMLTask(CourseCompareActivity activity) {
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
   			
   			
   			//do the search with encoding
   			xmlR2.setContentHandler(myXMLSearch);
   			InputSource is = new InputSource(urls[0].openStream());
   			is.setEncoding("UTF-8");
   			xmlR2.parse(is);
   			
   			}
   			
   			catch (Exception e) {

   				//Log.d("catch", "catch 2 " + e.toString());
   				
   				this.status.cancel();//cancel the loading icon
   				
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
   	
   	public void displayAlert() {
			
			AlertDialog.Builder alertdialog = new AlertDialog.Builder(this);
			alertdialog.setTitle("Network Error");
			alertdialog.setMessage("Error connecting to the server, please try again or reload the App");
			alertdialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					
				}
			});
			//alertdialog.setIcon(R.drawable.icon);
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
			//alertdialog.setIcon(R.drawable.icon);
			alertdialog.show();
   	}

}