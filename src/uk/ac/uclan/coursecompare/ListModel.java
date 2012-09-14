package uk.ac.uclan.coursecompare;

public class ListModel {

	private String listName;
	private String listCode;

	ListModel(String lName, String lCode) {
    setListName(lName);
    setListCode(lCode);
	}
	
	public ListModel() {
		
	}

	public void setListName(String name) {
    this.listName = name;
	}
	
	public void setListCode(String name) {
    this.listCode = name;
	}
	
	public String getListCode() {
    return this.listCode;
	}
	
	public String getListName() {
    return this.listName;
	}

	public String toString() {
    return "Name: " + this.listName + ", Code:" + this.listCode;
	}
	
}
