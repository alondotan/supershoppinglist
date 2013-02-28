package il.ac.shenkar.superShoppinglist.utils;

import il.ac.shenkar.superShoppinglist.beans.Category;
import il.ac.shenkar.superShoppinglist.beans.Product;
import il.ac.shenkar.superShoppinglist.beans.ShoppingList;
import il.ac.shenkar.superShoppinglist.db.DatabaseHandler;

//import java.util.ArrayList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;

public class ShoppingListRepository {	
	private	static ShoppingListRepository instance = null;	
	private	Context	context;

	private static final String PROX_ALERT_INTENT = "il.ac.shenkar.superShoppinglist.utils.reminder_broadcast";

	public static final String EXTRA_LIST_NAME = "il.ac.shenkar.superShoppingList.LIST_NAME";

	private	ShoppingList currentShoppingList;

	private	DatabaseHandler db;
	private LocationManager locationManager;

	private	ShoppingListRepository(Context context)	{
		this.context = context;
		db = new DatabaseHandler(context);
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		
        // check for last updated list
		SharedPreferences pref = context.getSharedPreferences("ssl Session Data", 0);
		String lastSavedListName = pref.getString("last saved list", "");
		if (lastSavedListName.isEmpty())
			lastSavedListName = db.getLastShoppingListName();		
		currentShoppingList = db.getShoppingListbyName(lastSavedListName);
	}
		
	public static ShoppingListRepository getInstance(Context context)	{	
		if(instance	== null) {	
			instance = new ShoppingListRepository(context);	
		}
		return instance;
	}
			
	public Context getContext(){
		return context;
	}
		
	//---------------------------------------------------------------
	// lists handlers
	//---------------------------------------------------------------

	public ArrayList<String> getAllShoppingListNames(){
		return db.getAllShoppingListNames();
	}

	public void newList(String listName){
		currentShoppingList.setId(db.addShoppingList(listName));
		currentShoppingList.setName(listName);
		currentShoppingList.setCategoriesList(new ArrayList<Category>());
		savedLastSavedListName(listName);
	}
	
	//---------------------------------------------------------------
	// products handlers
	//---------------------------------------------------------------
	public void addProduct(Product p){

		p.setCategory(db.getProductCategory(p));
		db.addProduct(p,currentShoppingList.getId());
		refreshList();
	}
	
	public void removeProduct(int i){
		Product p = (Product) currentShoppingList.indexToProduct(i);
		db.deleteProduct(p.getName(),p.getCategory(),currentShoppingList.getId());
		refreshList();
	}
	
	public void updateProduct(Product p){
		db.updateProduct(currentShoppingList.getId(), p);
		refreshList();
	}
	
	public Category getCategory(int i){
		return currentShoppingList.getCategory(i);
	}
	
	// for the mixed (category and products) list
	public Object getObject(int i){
		return currentShoppingList.indexToProduct(i);
	}
	
	public int getCategoriesNumber(){
		return currentShoppingList.getCategoriesNumber();
	}
	
	public int getSize(){
		return currentShoppingList.getSize();
	}
	
	public boolean isCategory(int pos){
		return (currentShoppingList.isCategory(pos));
	}

	//---------------------------------------------------------------
	// predefined Data handlers
	//---------------------------------------------------------------

	public String[] getAllPredefinedProductsNames(){
		return db.getAllPredefinedProductsNames();
	}
	

	
	//---------------------------------------------------------------
	// current list data
	//---------------------------------------------------------------
	public String currentShoppingListToHtml(){
		return currentShoppingList.toHtml();
	}
	
	public String getCurrentShoppingListName(){
		return currentShoppingList.getName();
	}
	
	public String getCurrentShoppingListLocation(){
		return currentShoppingList.getLocation();
	}

	public void updateCurrentListName(String newName){
		currentShoppingList.setName(newName);
		db.updateShoppingListName(currentShoppingList.getId(), newName);
		savedLastSavedListName(newName);
	}
	
	public boolean replaceCurrentList(String newListName){
		currentShoppingList = db.getShoppingListbyName(newListName);
		if (currentShoppingList!=null){
			savedLastSavedListName(newListName);
			return true;
		}
		else{
			currentShoppingList = db.getShoppingListbyName(db.getLastShoppingListName());
			savedLastSavedListName(currentShoppingList.getName());
			return false;
		}
	}
	
	public void saveAsCurrentList(String newListName){
		currentShoppingList.setId(db.saveAsShoppingList(currentShoppingList.getId(),newListName));
		currentShoppingList.setName(newListName);
		savedLastSavedListName(currentShoppingList.getName());
	}

	public void clearCurrentShoppingList(){
		db.clearShoppingList(currentShoppingList.getId());
		refreshList();
	}
	
	public void removeCurrentList(){
		updateLocationToCurrentList("");
		db.removeShoppingList(currentShoppingList.getId());
		currentShoppingList = db.getShoppingListbyName(db.getLastShoppingListName());
		savedLastSavedListName(currentShoppingList.getName());
	}
	
	public void refreshList(){
		currentShoppingList = db.getShoppingListbyName(currentShoppingList.getName());
		savedLastSavedListName(currentShoppingList.getName());
	}
		
	public void updateLocationToCurrentList(String locationName){
		
 	   Intent intent = new Intent(PROX_ALERT_INTENT);
 	   intent.putExtra(EXTRA_LIST_NAME,currentShoppingList.getName());
 	   PendingIntent proximityIntent = PendingIntent.getBroadcast( context, currentShoppingList.getId(), intent, PendingIntent.FLAG_UPDATE_CURRENT);

 	   currentShoppingList.setLocation(locationName);
 	   db.updateShoppingListLocation(currentShoppingList.getId(), locationName);

		if (!locationName.isEmpty()){
     	   Geocoder gc = new Geocoder(context);
     	   try {
	     		   List<Address> list = gc.getFromLocationName(locationName, 1);
		    		Address a = list.get(0);
		    		locationManager.addProximityAlert(a.getLatitude(),a.getLongitude(),50,-1,proximityIntent);
				} catch (IOException e) {
					e.printStackTrace();
				}
 	   }
 	   else{
    		locationManager.removeProximityAlert(proximityIntent);        		   
 	   }
		
	}

	void savedLastSavedListName(String listName){
		SharedPreferences pref = context.getSharedPreferences("ssl Session Data", 0);
		SharedPreferences.Editor edit = pref.edit();
		edit.putString("last saved list", listName);
		edit.commit();
	}
}