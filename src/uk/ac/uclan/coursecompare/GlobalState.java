package uk.ac.uclan.coursecompare;

import java.util.ArrayList;

import android.app.Application;

public class GlobalState extends Application {

	//global items for use throughout the app
	private ArrayList<SearchModel> globalCourseList;
	private ArrayList<ArrayList<PresentationsModel>> globalPresentations;
	private ArrayList<ArrayList<CreditsModel>> globalCredits;
	
	private int searchCourse;
	private String totalHits;
	private String urlSearch;

	public String getUrlSearch() {
		return urlSearch;
	}

	public void setUrlSearch(String urlSearch) {
		this.urlSearch = urlSearch;
	}

	public String getTotalHits() {
		return totalHits;
	}

	public void setTotalHits(String totalHits) {
		this.totalHits = totalHits;
	}

	public int getSearchCourse() {
		return searchCourse;
	}

	public void setSearchCourse(int searchCourse) {
		this.searchCourse = searchCourse;
	}

	public ArrayList<SearchModel> getGlobalCourseList() {
		return globalCourseList;
	}

	public void setGlobalCourseList(ArrayList<SearchModel> globalCourseList) {
		this.globalCourseList = globalCourseList;
	}

	public ArrayList<ArrayList<PresentationsModel>> getGlobalPresentations() {
		return globalPresentations;
	}

	public void setGlobalPresentations(
			ArrayList<ArrayList<PresentationsModel>> globalPresentations) {
		this.globalPresentations = globalPresentations;
	}

	public ArrayList<ArrayList<CreditsModel>> getGlobalCredits() {
		return globalCredits;
	}

	public void setGlobalCredits(ArrayList<ArrayList<CreditsModel>> globalCredits) {
		this.globalCredits = globalCredits;
	}	
}
