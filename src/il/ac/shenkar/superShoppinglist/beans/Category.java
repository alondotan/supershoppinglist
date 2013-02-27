package il.ac.shenkar.superShoppinglist.beans;

import java.util.ArrayList;

public class Category {
	private ArrayList<Product> productsList;
	private String name;
	private int iconName;

	public Category (){
		productsList = new ArrayList<Product>();
	}

	public Category (String name,int iconName){
		this.name = name;
		this.iconName = iconName;
		productsList = new ArrayList<Product>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Product> getProductsList(){
		return productsList;
	}
	
	public void setProductsList(ArrayList<Product> productsList){
		this.productsList = productsList;
	}
	
	public void addProduct(Product p){
		productsList.add(p);
	}
	
	public void removeProduct(Product p){
		productsList.remove(p);
	}
	
	public void removeProduct(int i){
		productsList.remove(i);
	}
	
	public int getIconName() {
		return iconName;
	}

	public void setIconName(int iconName) {
		this.iconName = iconName;
	}

	public boolean equals(Object obj){
		Category c = (Category) obj;
		return (this.name.equals(c.name));
	}
	
	public String toHtml(){
		String val = "<h4>" +name+"</h4>";
		for (Product p:productsList){
			val += p.toString() + "<br>";
		}
		return val;
	}
}
