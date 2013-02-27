package il.ac.shenkar.superShoppinglist.beans;

import java.util.ArrayList;
import java.util.Date;

public class ShoppingList {
	private String name;
	private int id;
	private Date date;
	private	ArrayList<Category> categoriesList;
	private String location;
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public ArrayList<Category> getCategoriesList() {
		return categoriesList;
	}

	public void setCategoriesList(ArrayList<Category> categoriesList) {
		this.categoriesList = categoriesList;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Category getCategory(int pos){
		return categoriesList.get(pos);
	}
	
	public Category getCategoryByName(String categoryName){
		for (Category c :categoriesList) {
			if (c.getName()==categoryName)
				return c;
		}
		return null;
	}

	public void addCategory (Category c){
		categoriesList.add(c);
	}
	
	public boolean isCategoryInList(String categoryName){
		Category c = new Category();
		c.setName(categoryName);
		
		return (categoriesList.indexOf(c) != -1);
	}
	
	public Object indexToProduct(int index){
		int i=0;
		for (Category c :categoriesList) {
			if(i==index)
				return c;
			i++;
			for (Product p:c.getProductsList()){
				if(i==index)
					return p;
				i++;
			}
		}
		return null;
	}

	public int getCategoriesNumber(){
		return categoriesList.size();
	}

	public int getSize(){
		int i = 0;
		for (Category c :categoriesList) {
			i++;
			i += c.getProductsList().size();
		}
		return i;
	}
	
	public boolean isCategory(int index){
		int i=0;
		for (Category c :categoriesList) {
			if (i == index)
				return true;
			i++;
			for (Product p:c.getProductsList()){
				if (i == index)
					return false;				
				i++;
			}
		}	
		return false;
	}
	
	public void removeProduct(Product p){
		Category c = new Category ();
		c.setName(p.getCategory());
		int catIndex = categoriesList.indexOf(c);
		categoriesList.get(catIndex).removeProduct(p);
		if (categoriesList.get(catIndex).getProductsList().isEmpty())
			categoriesList.remove(catIndex);
	}
		
	public String toHtml(){
		String val = "";
		val += "<h2>" + "רשימת קניות: " + name + "</h2>";
		for (Category c : categoriesList) {
			val += c.toHtml();
		}
		
		return val;
	}
}
