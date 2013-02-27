package il.ac.shenkar.superShoppinglist.beans;

import android.text.format.Time;

public class Product {
	
	private int id;
	private Time creationTime;
	private String description;
	private String category;
	private String name;
	private boolean gotIt;
		

	public Product(){
		super();	
		this.gotIt = false;
		description = "";
	}

	public Product(String name,int id,String description,String category){
		super();
		this.name = name;
		this.id = id;
		this.creationTime = new Time();
		this.description = description;
		this.category = category;
		this.gotIt = false;
	}
	
	public boolean isGotIt() {
		return gotIt;
	}

	public void setGotIt(boolean gotIt) {
		this.gotIt = gotIt;
	}

	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Time getTime(){
		return creationTime;
	}
	
	public void setTime(Time time){
		this.creationTime = time;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String toString(){
		String val = name;
		if (!description.isEmpty()) val += " : " +description; 
		return val;
	}
}
