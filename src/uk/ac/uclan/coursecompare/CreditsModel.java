package uk.ac.uclan.coursecompare;

public class CreditsModel {

	//the xcri credits details parameters
	private String creditsscheme = "No Data Provided";
	private String creditslevel = "No Data Provided";
	private String creditsval = "No Data Provided";
	
	
	public CreditsModel(String creditsscheme, String creditslevel,
			String creditsval) {
		super();
		this.creditsscheme = creditsscheme;
		this.creditslevel = creditslevel;
		this.creditsval = creditsval;
	}

	public CreditsModel() {
	}

	@Override
	public String toString() {
		return "CreditsModel [creditsscheme=" + creditsscheme
				+ ", creditslevel=" + creditslevel + ", creditsval="
				+ creditsval + "]";
	}
	
	public String getCreditsscheme() {
		return creditsscheme;
	}
	public void setCreditsscheme(String creditsscheme) {
		this.creditsscheme = creditsscheme;
	}
	public String getCreditslevel() {
		return creditslevel;
	}
	public void setCreditslevel(String creditslevel) {
		this.creditslevel = creditslevel;
	}
	public String getCreditsval() {
		return creditsval;
	}
	public void setCreditsval(String creditsval) {
		this.creditsval = creditsval;
	}

}
