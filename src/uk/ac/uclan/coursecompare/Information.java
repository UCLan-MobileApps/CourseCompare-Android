package uk.ac.uclan.coursecompare;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Window;
import android.widget.TextView;

public class Information extends Activity {
	
	TextView about;
	
	 public void onCreate(Bundle savedInstanceState) {
	    	
	        super.onCreate(savedInstanceState);
	        
	      //hide the name
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        
	        setContentView(R.layout.information);
	        
	        //set to portrait only
	        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	        
	        about = (TextView) findViewById(R.id.textView1);
	        
	      loadText();
	 }
	 
	    private void loadText(){

	        String s = "Description " + "\n" + "\n" +
	        		"This app lets you search a wide range of UK university courses and browse general information to help find the right one for you.  It provides information only; additional enquiries should be made directly to the relevant universities. " + "\n" + "\n" +
	        		"Features" + "\n" + "\n" +
	        		"Search courses by keyword, institution, qualification, study mode, location (miles or kilometres from postcode at various distances, i.e. 25x)" + "\n" + "\n" +
	        		"Disclaimer " + "\n" + "\n" +
	        		"UCLan accepts no responsibility for the availability or non-availability or accuracy of course data relating to institutions, other than its own. Availability of course data is reliant upon institutions registering their XCRI-CAP data with the JISC aggregator, at their sole discretion. The accuracy of course data is the responsibility of individual institutions.";

	        about.setMovementMethod(new ScrollingMovementMethod());

	        about.setText(s);
	    }
}
