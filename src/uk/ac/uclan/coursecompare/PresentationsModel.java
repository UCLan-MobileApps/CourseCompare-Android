package uk.ac.uclan.coursecompare;

public class PresentationsModel {

	//scri presentations details paramaters
	private String description = "No Data Provided";
	private String start = "No Data Provided";
	private String end = "No Data Provided";
	private String duration = "No Data Provided";
	private String cost = "No Data Provided";
	private String applicationsOpen = "No Data Provided";
	private String applicationsClose = "No Data Provided";
	private String applyTo = "No Data Provided";
	private String enquireTo = "No Data Provided";
	private String studyMode = "No Data Provided";
	private String attendanceMode = "No Data Provided";
	private String attendancePattern = "No Data Provided";
	private String languageOfAssessment = "No Data Provided";
	private String languageOfInstruction = "No Data Provided";
	private String venuename = "On Campus";
	private String venuestreet = "";
	private String venuetown = "";
	private String venuepostcode = "";
	private String entryRequirements = "No Data Provided";
	
	
	
	public PresentationsModel(String description, String start, String end,
			String duration, String cost, String applicationsOpen,
			String applicationsClose, String applyTo, String enquireTo,
			String studyMode, String attendanceMode, String attendancePattern,
			String languageOfAssessment, String languageOfInstruction,
			String venuename, String venuestreet, String venuetown,
			String venuepostcode, String entryRequirements) {
		super();
		this.description = description;
		this.start = start;
		this.end = end;
		this.duration = duration;
		this.cost = cost;
		this.applicationsOpen = applicationsOpen;
		this.applicationsClose = applicationsClose;
		this.applyTo = applyTo;
		this.enquireTo = enquireTo;
		this.studyMode = studyMode;
		this.attendanceMode = attendanceMode;
		this.attendancePattern = attendancePattern;
		this.languageOfAssessment = languageOfAssessment;
		this.languageOfInstruction = languageOfInstruction;
		this.venuename = venuename;
		this.venuestreet = venuestreet;
		this.venuetown = venuetown;
		this.venuepostcode = venuepostcode;
		this.entryRequirements = entryRequirements;
	}
	public PresentationsModel() {
	
	}
	@Override
	public String toString() {
		return "PresentationsModel [description=" + description + ", start="
				+ start + ", end=" + end + ", duration=" + duration + ", cost="
				+ cost + ", applicationsOpen=" + applicationsOpen
				+ ", applicationsClose=" + applicationsClose + ", applyTo="
				+ applyTo + ", enquireTo=" + enquireTo + ", studyMode="
				+ studyMode + ", attendanceMode=" + attendanceMode
				+ ", attendancePattern=" + attendancePattern
				+ ", languageOfAssessment=" + languageOfAssessment
				+ ", languageOfInstruction=" + languageOfInstruction
				+ ", venuename=" + venuename + ", venuestreet=" + venuestreet
				+ ", venuetown=" + venuetown + ", venuepostcode="
				+ venuepostcode + ", entryRequirements=" + entryRequirements
				+ "]";
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getApplicationsOpen() {
		return applicationsOpen;
	}
	public void setApplicationsOpen(String applicationsOpen) {
		this.applicationsOpen = applicationsOpen;
	}
	public String getApplicationsClose() {
		return applicationsClose;
	}
	public void setApplicationsClose(String applicationsClose) {
		this.applicationsClose = applicationsClose;
	}
	public String getApplyTo() {
		return applyTo;
	}
	public void setApplyTo(String applyTo) {
		this.applyTo = applyTo;
	}
	public String getEnquireTo() {
		return enquireTo;
	}
	public void setEnquireTo(String enquireTo) {
		this.enquireTo = enquireTo;
	}
	public String getStudyMode() {
		return studyMode;
	}
	public void setStudyMode(String studyMode) {
		this.studyMode = studyMode;
	}
	public String getAttendanceMode() {
		return attendanceMode;
	}
	public void setAttendanceMode(String attendanceMode) {
		this.attendanceMode = attendanceMode;
	}
	public String getAttendancePattern() {
		return attendancePattern;
	}
	public void setAttendancePattern(String attendancePattern) {
		this.attendancePattern = attendancePattern;
	}
	public String getLanguageOfAssessment() {
		return languageOfAssessment;
	}
	public void setLanguageOfAssessment(String languageOfAssessment) {
		this.languageOfAssessment = languageOfAssessment;
	}
	public String getLanguageOfInstruction() {
		return languageOfInstruction;
	}
	public void setLanguageOfInstruction(String languageOfInstruction) {
		this.languageOfInstruction = languageOfInstruction;
	}
	public String getVenuename() {
		return venuename;
	}
	public void setVenuename(String venuename) {
		this.venuename = venuename;
	}
	public String getVenuestreet() {
		return venuestreet;
	}
	public void setVenuestreet(String venuestreet) {
		this.venuestreet = venuestreet;
	}
	public String getVenuetown() {
		return venuetown;
	}
	public void setVenuetown(String venuetown) {
		this.venuetown = venuetown;
	}
	public String getVenuepostcode() {
		return venuepostcode;
	}
	public void setVenuepostcode(String venuepostcode) {
		this.venuepostcode = venuepostcode;
	}
	public String getEntryRequirements() {
		return entryRequirements;
	}
	public void setEntryRequirements(String entryRequirements) {
		this.entryRequirements = entryRequirements;
	}
}
