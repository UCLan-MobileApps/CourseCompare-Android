package uk.ac.uclan.coursecompare;

//import android.app.Activity;
import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.ExpandableListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.TextView;


public class CourseDetails extends ExpandableListActivity {
	
	//used to display the details for an individual course
    ArrayList<SearchModel> courseList;
    ArrayList<ArrayList<PresentationsModel>> presentations;
    ArrayList<ArrayList<CreditsModel>> credits;
    
    ArrayList<String> presentationsList = new ArrayList<String>();
    ArrayList<String> creditsList = new ArrayList<String>();
    
    String presentation;
    String credit;
    
		    static final String arrGroupelements[] = //expanded as needed
		    {
		    	"Course Description",
		    	"Course Abstract",
		    	"Course Provider",
		    	"Course Subjects",
		    	"Course URL", 
		    	"Course Qualifications",
		    	"Course Prerequisites",
		    	"Course Career Outcome", 
		    	"Course Aim",
		    	"Course Credits",
		    	"Course Presentations",
		    	"Course Assessment Strategy",
		    	"Course Learning Outcome",
		    	"Course Indicative Resource",
		    	"Course Syllabus",
		    	"Course Leads To"
		 };

		    String[][] arrChildelements;
		    ArrayList<String> courseTitles = new ArrayList<String>();
		    int courseItem;

		 DisplayMetrics metrics;
		 int width;
		 ExpandableListView courseDetails;
		  
		    @Override
		    public void onCreate(Bundle savedInstanceState)
		    {
		    	
		    	//hide the name
		        requestWindowFeature(Window.FEATURE_NO_TITLE);
		        
		        //set to portrait only
		        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		        
		        super.onCreate(savedInstanceState);
		        setContentView(R.layout.coursedetails);
		        
		        GlobalState gs = (GlobalState) getApplication(); 
		        courseList = gs.getGlobalCourseList();
		        courseItem = gs.getSearchCourse();
		        presentations = gs.getGlobalPresentations();
		        credits = gs.getGlobalCredits();

		        TextView courseTitle = (TextView)findViewById(R.id.title);
		        courseTitle.setText(courseList.get(courseItem).getTitle());
		        
		        String[] subject = {courseList.get(courseItem).getSubject()};
		        String[] qualifications = {courseList.get(courseItem).getQualtitle() + "\n" + courseList.get(courseItem).getQualdescription() + "\n" + "Level: " + courseList.get(courseItem).getQuallevel() + "\n" + "Type: " + courseList.get(courseItem).getQualtype() + "\n" + "Awarded By: " + courseList.get(courseItem).getQualawardedBy() + "\n" + "Accredited By: " + courseList.get(courseItem).getQualaccreditedBy() + "\n" + "Level Controlled Term: " + courseList.get(courseItem).getQuallevelControlledTerm()};
		        String[] prerequisites = {courseList.get(courseItem).getPrerequisites()};
		        String[] cabstract = {courseList.get(courseItem).getCabstract()};
		        String[] description = {courseList.get(courseItem).getDescription()};
		        String[] careeroutcome = {courseList.get(courseItem).getCareerOutcome()};
		        String[] url = {courseList.get(courseItem).getUrl()};
		        String[] provider = {courseList.get(courseItem).getProvtitle()};
		        String[] aim = {courseList.get(courseItem).getAim()};
		        
		        int pres = presentations.get(courseItem).size();
		        
		        for (int x = 0; x < pres; x++) {
		        	String tempString = ("Presentation " + "\n" + "\n" + presentations.get(courseItem).get(x).getDescription() + "\n" + "Study Mode: " + presentations.get(courseItem).get(x).getStudyMode() + "\n" + "Start: " + presentations.get(courseItem).get(x).getStart() + "\n" + "End: " + presentations.get(courseItem).get(x).getEnd() + "\n" + "Cost: " + presentations.get(courseItem).get(x).getCost() + "\n" + "Duration: " + presentations.get(courseItem).get(x).getDuration() + "\n" + "Apply To: " + presentations.get(courseItem).get(x).getApplyTo() + "\n" + "Attendance Mode: " + presentations.get(courseItem).get(x).getAttendanceMode() + "\n" + "Attendance Pattern: " + presentations.get(courseItem).get(x).getAttendancePattern() + "\n" + "Enquire To: " + presentations.get(courseItem).get(x).getEnquireTo() + "\n" + "Entry Requirements: " + presentations.get(courseItem).get(x).getEntryRequirements() + "\n" + "Applications Open: " + presentations.get(courseItem).get(x).getApplicationsOpen() + "\n" + "Applications Close: " + presentations.get(courseItem).get(x).getApplicationsClose() + "\n" + "Language of Assessment: " + presentations.get(courseItem).get(x).getLanguageOfAssessment() + "\n" +"Language of Instruction: " + presentations.get(courseItem).get(x).getLanguageOfInstruction() + "\n" + "\n" + "Venue" + "\n" +"\n" + presentations.get(courseItem).get(x).getVenuename() + "\n" + presentations.get(courseItem).get(x).getVenuestreet() + "\n" + presentations.get(courseItem).get(x).getVenuetown() + "\n" + presentations.get(courseItem).get(x).getVenuepostcode() + "\n");
		        	presentationsList.add(tempString);
		        	//Log.d("pres =", "" + tempString);
		       }
		        
		        int cre = credits.get(courseItem).size();
		        
		        for (int x = 0; x < cre; x++) {
		        	String tempString = ("Credits " + "\n" + "\n" + "Level: " + credits.get(courseItem).get(x).getCreditslevel() + "\n" + "Scheme: " + credits.get(courseItem).get(x).getCreditsscheme() + "\n" + "Value: " + credits.get(courseItem).get(x).getCreditsval() +"\n");
		        	creditsList.add(tempString);
		        }
		        
		        String[] credits = creditsList.toArray(new String[0]);
		        String[] presentation = presentationsList.toArray(new String[0]);
		        String[] assessmentstrategy = {courseList.get(courseItem).getAssessmentStrategy()};
		        String[] learningoutcome = {courseList.get(courseItem).getLearningOutcome()};
		        String[] indicativeresource = {courseList.get(courseItem).getIndicativeResource()};
		        String[] syllabus = {courseList.get(courseItem).getSyllabus()};
		        String[] leadsto = {courseList.get(courseItem).getLeadsTo()};
		        
		        String[][] tempStringArray = {description, cabstract, provider, subject, url, qualifications, prerequisites, careeroutcome, aim, credits, presentation, assessmentstrategy, learningoutcome, indicativeresource, syllabus, leadsto};//expanded as needed
		        
		        arrChildelements = tempStringArray;
		         
		        courseDetails = getExpandableListView();
		        metrics = new DisplayMetrics();
		        getWindowManager().getDefaultDisplay().getMetrics(metrics);
		        width = metrics.widthPixels;
		        courseDetails.setIndicatorBounds(width - GetDipsFromPixel(50), width - GetDipsFromPixel(10));
		        courseDetails.setAdapter(new ExpAdapter(this));
		   
		        courseDetails.setOnGroupExpandListener(new OnGroupExpandListener()
		  {
		   @Override
		   public void onGroupExpand(int groupPosition) 
		   {
		   }
		  });
		  
		        courseDetails.setOnGroupClickListener(new OnGroupClickListener() {

            public boolean onGroupClick(ExpandableListView parent, View v,
                    int groupPosition, long id) {
                return false;
            }
		  });
		  }

		    public int GetDipsFromPixel(float pixels)

		    {
		     // Get the screen's density scale
		     final float scale = getResources().getDisplayMetrics().density;
		     // Convert the dps to pixels, based on density scale
		     return (int) (pixels * scale + 0.5f);
		    }
		    
		    /**
		     * This is adapter for expandable list-view for constructing the group and child elements.
		     */
		    public class ExpAdapter extends BaseExpandableListAdapter {

		    	private Context myContext;
		    	public ExpAdapter(Context context) {
		    		myContext = context;
		    	}
		    	@Override
		    	public Object getChild(int groupPosition, int childPosition) {
		    		return null;
		    	}

		    	@Override
		    	public long getChildId(int groupPosition, int childPosition) {
		    		return 0;
		    	}

		    	@Override
		    	public View getChildView(int groupPosition, int childPosition,
		    			boolean isLastChild, View convertView, ViewGroup parent) {

		    		if (convertView == null) {
		    			LayoutInflater inflater =  (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		    			convertView = inflater.inflate(R.layout.child_row, null);
		    		}

		    		TextView details = (TextView) convertView.findViewById(R.id.details);
		    		//if(arrChildelements[groupPosition][childPosition].equalsIgnoreCase("No Data Provided")) {
		    			//details.setText("");
		    			//Log.d("details", "here");//
		    		//} else {
		    		details.setText(arrChildelements[groupPosition][childPosition]);
		    		//}
		    		//Log.d("details",arrChildelements[groupPosition][childPosition]);
		    		return convertView;
		    	}

		    	@Override
		    	public int getChildrenCount(int groupPosition) {
		    		return arrChildelements[groupPosition].length;
		    	}

		    	@Override
		    	public Object getGroup(int groupPosition) {
		    		return null;
		    	}

		    	@Override
		    	public int getGroupCount() {
		    		return arrGroupelements.length;
		    	}

		    	@Override
		    	public long getGroupId(int groupPosition) {
		    		return 0;
		    	}

		    	@Override
		    	public View getGroupView(int groupPosition, boolean isExpanded,
		    			View convertView, ViewGroup parent) {

		    		if (convertView == null) {
		    			LayoutInflater inflater =  (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		    			convertView = inflater.inflate(R.layout.group_row, null);
		    		}

		    		TextView header = (TextView) convertView.findViewById(R.id.header);
		    		header.setText(arrGroupelements[groupPosition]);

		    		return convertView;
		    	}

		    	@Override
		    	public boolean hasStableIds() {
		    		return false;
		    	}

		    	@Override
		    	public boolean isChildSelectable(int groupPosition, int childPosition) {
		    		return true;
		    	}
		    }
}




