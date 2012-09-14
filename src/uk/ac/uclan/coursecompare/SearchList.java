package uk.ac.uclan.coursecompare;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;

public class SearchList extends ListActivity {
   
	//display the results
    String[] courseItems;
    String totalHits;
    String searchURL;
    int offset;
    int results;
    
    Boolean loadingExtra;
    
    Button loadMore;
    Button loadPrevious;
    
    ArrayList<String> courseTitles = new ArrayList<String>();
    ArrayList<SearchModel> courseList;
    
    ListView listView;
    ArrayAdapter<String> adapter;
    
	XMLSearch myXMLSearch = new XMLSearch();
    
	 /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        
        //hide the name
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        //set to portrait only
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        
        setContentView(R.layout.searchlist);
        
        loadingExtra = false;
        
        GlobalState gs = (GlobalState) getApplication(); 
        courseList = gs.getGlobalCourseList();
        totalHits = gs.getTotalHits();
        searchURL = gs.getUrlSearch();
        
        offset = 0;
        results = Integer.parseInt(totalHits.toString());
        Log.d("results" , "" + results);
        
		AlertDialog.Builder alertdialog = new AlertDialog.Builder(this);
		alertdialog.setTitle("Total Hits");
		alertdialog.setMessage("Total Serach Results " + totalHits);
		alertdialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				
			}
		});
		alertdialog.show();
        
        for(int i = 0 ; i < courseList.size(); i++) {
        	String course = courseList.get(i).getQualtitle();
        	courseTitles.add(course);
        }
   
        courseItems = courseTitles.toArray(new String[0]);
        
        //setup headers and footers
        View header = getLayoutInflater().inflate(R.layout.header, null);
        View footer = getLayoutInflater().inflate(R.layout.footer, null);
        listView = getListView();
        listView.addHeaderView(header);
        listView.addFooterView(footer);
	
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, courseItems);      
        setListAdapter(adapter);
        
        //load the search button
        loadMore = (Button) footer.findViewById(R.id.search);
        loadPrevious = (Button) footer.findViewById(R.id.back);

        loadMore.setOnClickListener(new OnClickListener () {
        	public void onClick(View arg0) {
       
        		if (results > offset+100) {
        			loadingExtra = true;
               	 	loadPrevious.setText("Previous");
        			setListAdapter(null); 
        			offset = offset+100;
        			loadMoreCourses();
        		}
        		else {
        		}
        	}
        }); 
        
        loadPrevious.setOnClickListener(new OnClickListener () {
        	public void onClick(View arg0) {
       
        		if (loadingExtra == true) {
        			setListAdapter(null); 
        			offset = offset-100;
        			loadMoreCourses();
        			
        		}
        		else {
        			Log.d("loading", "at start");
        		}
        	}
        });
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	
    	GlobalState gs = (GlobalState) getApplication(); 
    	gs.setSearchCourse(position-1);
    	
    	startActivity(new Intent("uk.ac.uclan.COURSEDETAILS"));//load the course with details
    	
    }

    public void loadMoreCourses() {
    	
    	Log.d("offset", "" + offset);
    	
  		GlobalState gs = (GlobalState) getApplication();
  		String searchURL = gs.getUrlSearch();
		//submit the search
    	URL url2;
		try {
			url2 = new URL(searchURL + "&offset=" + offset);
			new SearchXMLTask(this).execute(url2);
		} catch (MalformedURLException e) {
			Log.d("catch","catch 1" + e.toString());
		}
		finally {
		}
    }
    
    //setup xml structure
  	private class SearchXMLTask extends AsyncTask<URL, Integer, String> {
  		
  		private ProgressDialog status;
  		private SearchList activity;
  		
  		public SearchXMLTask(SearchList activity) {
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
  			
  			Log.d("xml","" + urls[0]);

  			xmlR2.setContentHandler(myXMLSearch);
  			InputSource is = new InputSource(urls[0].openStream());
  			is.setEncoding("UTF-8");
  			xmlR2.parse(is);
  			}
  			catch (Exception e) {

  				Log.d("catch", "catch " + e.toString());
  				this.status.cancel();
  			}
  			
  			return null;
  		}
  		
  		protected void onProgressUpdate(Integer... progress) {
  			//Log.d("middle", "middle");
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

     			updateList();
  		}
  	}
  	
  	public void updateList() {
  		
  		ArrayList<String> courseTitles = new ArrayList<String>();
        
  		 GlobalState gs = (GlobalState) getApplication(); 
         courseList = gs.getGlobalCourseList();
         
         for(int i = 0 ; i < courseList.size(); i++) {
         	String course = courseList.get(i).getTitle();
         	courseTitles.add(course);
         	//Log.d("title = " , course);
         }
    
         courseItems = courseTitles.toArray(new String[0]);

         adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, courseItems);
   
         setListAdapter(adapter);
         
         if (offset == 0) {
        	 loadingExtra = false;
        	 loadPrevious.setText("Start");
        	 Log.d("loading", "start");
         }
         
         if (results > offset+100) {
        	 loadMore.setText("Load More");
        	 Log.d("loading", "more");
     		}
     		else { 
     			loadMore.setText("End of Results");
     			Log.d("loading", "end");
     		}		
  	}
  	
}
