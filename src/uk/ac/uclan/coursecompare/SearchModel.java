package uk.ac.uclan.coursecompare;

public class SearchModel {
	
	//xcri standard parameters
	//create a string for every course attribute
	private String cabstract = "No Data Provided";
	private String learningOutcome = "No Data Provided";
	private String assessmentStrategy = "No Data Provided";
	private String indicativeResource = "No Data Provided";
	private String lastModified = "No Data Provided";// not used yet
	private String description = "No Data Provided";
	private String provid = "No Data Provided";// not used yet
	private String provtitle = "No Data Provided";
	private String provuri = "No Data Provided";// not used yet
	private String provloclat = "No Data Provided";// not used yet
	private String provloclon = "No Data Provided";// not used yet
	private String title = "No Data Provided";
	private String imageuri = "No Data Provided";// not used yet
	private String qualtype = "No Data Provided";
	private String qualtitle = "No Data Provided";
	private String quallevel = "No Data Provided";
	private String qualawardedBy = "No Data Provided";
	private String qualdescription = "No Data provided";
	private String qualaccreditedBy = "No Data Provided";
	private String quallevelControlledTerm = "No Data Provided";
	private String aim = "No Data Provided";
	private String syllabus = "No Data Provided";
	private String careerOutcome = "No Data Provided";
	private String prerequisites = "No Data Provided";
	private String leadsTo = "No Data Provided";
	private String url = "No Data Provided";
	private String subject = "No Data Provided";
	private String subjectKeywords = "No Data Provided";// not used yet
	private String recstatus = "No Data Provided";// not used yet

	public SearchModel(String cabstract, String learningOutcome,
			String assessmentStrategy, String indicativeResource,
			String lastModified, String description, String provid,
			String provtitle, String provuri, String provloclat,
			String provloclon, String title, String imageuri, String qualtype,
			String qualtitle, String quallevel, String qualawardedBy,
			String qualdescription, String qualaccreditedBy,
			String quallevelControlledTerm, String aim, String syllabus,
			String careerOutcome, String prerequisites, String leadsTo,
			String url, String subject, String subjectKeywords, String recstatus) {
		super();
		this.cabstract = cabstract;
		this.learningOutcome = learningOutcome;
		this.assessmentStrategy = assessmentStrategy;
		this.indicativeResource = indicativeResource;
		this.lastModified = lastModified;
		this.description = description;
		this.provid = provid;
		this.provtitle = provtitle;
		this.provuri = provuri;
		this.provloclat = provloclat;
		this.provloclon = provloclon;
		this.title = title;
		this.imageuri = imageuri;
		this.qualtype = qualtype;
		this.qualtitle = qualtitle;
		this.quallevel = quallevel;
		this.qualawardedBy = qualawardedBy;
		this.qualdescription = qualdescription;
		this.qualaccreditedBy = qualaccreditedBy;
		this.quallevelControlledTerm = quallevelControlledTerm;
		this.aim = aim;
		this.syllabus = syllabus;
		this.careerOutcome = careerOutcome;
		this.prerequisites = prerequisites;
		this.leadsTo = leadsTo;
		this.url = url;
		this.subject = subject;
		this.subjectKeywords = subjectKeywords;
		this.recstatus = recstatus;
	}

	@Override
	public String toString() {
		return "SearchModel [cabstract=" + cabstract + ", learningOutcome="
				+ learningOutcome + ", assessmentStrategy="
				+ assessmentStrategy + ", indicativeResource="
				+ indicativeResource + ", lastModified=" + lastModified
				+ ", description=" + description + ", provid=" + provid
				+ ", provtitle=" + provtitle + ", provuri=" + provuri
				+ ", provloclat=" + provloclat + ", provloclon=" + provloclon
				+ ", title=" + title + ", imageuri=" + imageuri + ", qualtype="
				+ qualtype + ", qualtitle=" + qualtitle + ", quallevel="
				+ quallevel + ", qualawardedBy=" + qualawardedBy
				+ ", qualdescription=" + qualdescription
				+ ", qualaccreditedBy=" + qualaccreditedBy
				+ ", quallevelControlledTerm=" + quallevelControlledTerm
				+ ", aim=" + aim + ", syllabus=" + syllabus
				+ ", careerOutcome=" + careerOutcome + ", prerequisites="
				+ prerequisites + ", leadsTo=" + leadsTo + ", url=" + url
				+ ", subject=" + subject + ", subjectKeywords="
				+ subjectKeywords + ", recstatus=" + recstatus + "]";
	}

	public SearchModel() {
		
	}
	
	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getLearningOutcome() {
		return learningOutcome;
	}

	public void setLearningOutcome(String learningOutcome) {
		this.learningOutcome = learningOutcome;
	}

	public String getCabstract() {
		return cabstract;
	}

	public void setCabstract(String cabstract) {
		this.cabstract = cabstract;
	}

	public String getAssessmentStrategy() {
		return assessmentStrategy;
	}

	public void setAssessmentStrategy(String assessmentStrategy) {
		this.assessmentStrategy = assessmentStrategy;
	}

	public String getIndicativeResource() {
		return indicativeResource;
	}

	public void setIndicativeResource(String indicativeResource) {
		this.indicativeResource = indicativeResource;
	}

	public String getLastModified() {
		return lastModified;
	}

	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}

	public String getProvid() {
		return provid;
	}

	public void setProvid(String provid) {
		this.provid = provid;
	}

	public String getProvtitle() {
		return provtitle;
	}

	public void setProvtitle(String provtitle) {
		this.provtitle = provtitle;
	}

	public String getProvuri() {
		return provuri;
	}

	public void setProvuri(String provuri) {
		this.provuri = provuri;
	}

	public String getProvloclat() {
		return provloclat;
	}

	public void setProvloclat(String provloclat) {
		this.provloclat = provloclat;
	}

	public String getProvloclon() {
		return provloclon;
	}

	public void setProvloclon(String provloclon) {
		this.provloclon = provloclon;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImageuri() {
		return imageuri;
	}

	public void setImageuri(String imageuri) {
		this.imageuri = imageuri;
	}

	public String getQualtype() {
		return qualtype;
	}

	public void setQualtype(String qualtype) {
		this.qualtype = qualtype;
	}

	public String getQualtitle() {
		return qualtitle;
	}

	public void setQualtitle(String qualtitle) {
		this.qualtitle = qualtitle;
	}

	public String getQuallevel() {
		return quallevel;
	}

	public void setQuallevel(String quallevel) {
		this.quallevel = quallevel;
	}

	public String getQualawardedBy() {
		return qualawardedBy;
	}

	public void setQualawardedBy(String qualawardedBy) {
		this.qualawardedBy = qualawardedBy;
	}

	public String getQualaccreditedBy() {
		return qualaccreditedBy;
	}

	public void setQualaccreditedBy(String qualaccreditedBy) {
		this.qualaccreditedBy = qualaccreditedBy;
	}

	public String getQuallevelControlledTerm() {
		return quallevelControlledTerm;
	}

	public void setQuallevelControlledTerm(String quallevelControlledTerm) {
		this.quallevelControlledTerm = quallevelControlledTerm;
	}

	public String getAim() {
		return aim;
	}

	public void setAim(String aim) {
		this.aim = aim;
	}

	public String getSyllabus() {
		return syllabus;
	}

	public void setSyllabus(String syllabus) {
		this.syllabus = syllabus;
	}

	public String getCareerOutcome() {
		return careerOutcome;
	}

	public void setCareerOutcome(String careerOutcome) {
		this.careerOutcome = careerOutcome;
	}

	public String getPrerequisites() {
		return prerequisites;
	}

	public void setPrerequisites(String prerequisites) {
		this.prerequisites = prerequisites;
	}

	public String getLeadsTo() {
		return leadsTo;
	}

	public void setLeadsTo(String leadsTo) {
		this.leadsTo = leadsTo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSubjectKeywords() {
		return subjectKeywords;
	}

	public void setSubjectKeywords(String subjectKeywords) {
		this.subjectKeywords = subjectKeywords;
	}

	public String getRecstatus() {
		return recstatus;
	}

	public void setRecstatus(String recstatus) {
		this.recstatus = recstatus;
	}

	public String getQualdescription() {
		return qualdescription;
	}

	public void setQualdescription(String qualdescription) {
		this.qualdescription = qualdescription;
	}
}

