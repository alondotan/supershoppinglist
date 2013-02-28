package il.ac.shenkar.superShoppinglist.db;

import il.ac.shenkar.superShoppinglist.beans.Category;
import il.ac.shenkar.superShoppinglist.beans.Product;
import il.ac.shenkar.superShoppinglist.beans.ShoppingList;
import il.ac.shenkar.superShoppinglist.utils.PredefinedDataHandler;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
	 
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 2;
 
    // Database Name
    private static final String DATABASE_NAME = "shoppingListManager";
 
    // tables name
    private static final String TABLE_PRODUCTS = "products";
    private static final String TABLE_LISTS = "lists";
    private static final String TABLE_CATEGORIES = "categories";
    private static final String TABLE_PREDEFINED_PRODUCTS = "predefiend_products";
 
    // products Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESC = "description";
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_GOT_IT = "gotit";
    
    // categories Table Columns names
    private static final String KEY_CATEGORY_C = "category";
    private static final String KEY_CATEGORY_ICON_C = "icon";

    // lists Table Columns names
    private static final String LIST_KEY_ID = "listId";
    private static final String LIST_KEY_NAME = "name";
    private static final String LIST_LOCATION = "location";
      

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_PRODUCTS + "("
                + KEY_ID       + " INTEGER PRIMARY KEY," 
        		+ KEY_NAME     + " TEXT,"
                + KEY_CATEGORY + " TEXT," 
        		+ KEY_DESC     + " TEXT,"
                + LIST_KEY_ID  + " INTEGER,"
        		+ KEY_GOT_IT   + " TEXT" + ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);
        
        String CREATE_CATEGORIES_TABLE = "CREATE TABLE " + TABLE_CATEGORIES + "("
                + KEY_CATEGORY_C + " TEXT,"
                + KEY_CATEGORY_ICON_C + " INTEGER" + ")";
        db.execSQL(CREATE_CATEGORIES_TABLE);
        
        String CREATE_PREDEFINED_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_PREDEFINED_PRODUCTS + "("
                + KEY_ID       + " INTEGER PRIMARY KEY," 
        		+ KEY_NAME     + " TEXT,"
                + KEY_CATEGORY + " TEXT" +")";
        db.execSQL(CREATE_PREDEFINED_PRODUCTS_TABLE);
        
        String CREATE_LISTS_TABLE = "CREATE TABLE " + TABLE_LISTS + "("
                + LIST_KEY_ID      + " INTEGER PRIMARY KEY," 
        		+ LIST_KEY_NAME    + " TEXT,"
                + LIST_LOCATION    + " TEXT" +")";
        db.execSQL(CREATE_LISTS_TABLE);
        
        // init the DB
        PredefinedDataHandler predefinedData = new PredefinedDataHandler();
        initPredefindCategories(db,predefinedData.getPredefinedCategoryList());
        initPredefindProduct(db,predefinedData.getPredefinedProductsList());
        initPredefindList(db);
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PREDEFINED_PRODUCTS); 
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LISTS); 
        // Create tables again
        onCreate(db);
    }
    

    

    //-------------------------------------------------------------------
    // Products handlers
    //-------------------------------------------------------------------    
    public void addProduct(Product product,int listId) {
        SQLiteDatabase db = this.getWritableDatabase();
     
        ContentValues  values = new ContentValues ();
        values.put(KEY_NAME, product.getName()); // product Name
        values.put(KEY_CATEGORY, product.getCategory()); // product category
        values.put(KEY_DESC, product.getDescription()); // product description
        values.put(LIST_KEY_ID, listId); // product description
        values.put(KEY_GOT_IT,Boolean.toString(product.isGotIt())); // got this product

        // Inserting Row
        db.insert(TABLE_PRODUCTS, null, values);
        db.close(); // Closing database connection
    }

    public void updateProduct(int listId,Product p){
        SQLiteDatabase db = this.getWritableDatabase();

    	ContentValues args = new ContentValues();
        args.put(KEY_CATEGORY, p.getCategory());
        args.put(KEY_DESC, p.getDescription());
        args.put(KEY_NAME, p.getName());
    	args.put(KEY_GOT_IT, Boolean.toString(p.isGotIt()));

        db.update(TABLE_PRODUCTS, args, LIST_KEY_ID + "=" + listId + " and " + KEY_NAME + "='" + p.getName() +"'" , null);        
    }
    
    public void deleteProduct(String name, String category,int listId) {
        
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PRODUCTS, KEY_NAME + " = ? and " + KEY_CATEGORY + " = ? and " + LIST_KEY_ID + " =? ",
                new String[] { String.valueOf(name),String.valueOf(category),String.valueOf(listId)});
        db.close();
    }

    public ArrayList<Product> getCategoryProducts(int listId, String categoryName,SQLiteDatabase db){
    	ArrayList<Product> productsList = new ArrayList<Product>();
    	
        String selectQuery = "SELECT  * FROM " + TABLE_PRODUCTS + 
        		" WHERE " + KEY_CATEGORY + " = '" + categoryName + "' and "
        				  + LIST_KEY_ID + " = " + listId;
     
        Cursor cursor = db.rawQuery(selectQuery, null);
     
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	Product product = new Product();
            	product.setId(Integer.parseInt(cursor.getString(0)));
            	product.setName(cursor.getString(1));
            	product.setCategory(cursor.getString(2));
            	product.setDescription(cursor.getString(3));
            	product.setGotIt (Boolean.parseBoolean(cursor.getString(5)));
                // Adding product to list
            	productsList.add(product);
            } while (cursor.moveToNext());
        }

    	return productsList;    	
    }
    
    public ArrayList<Category> getListCategories(int listId,SQLiteDatabase db) {
    	ArrayList<Category> categoriesList = new ArrayList<Category>();
        // Select All Query
        String selectQuery = "SELECT " + KEY_CATEGORY + "," + KEY_CATEGORY_ICON_C + 
        					" FROM " + TABLE_CATEGORIES  + 
        					" WHERE " + KEY_CATEGORY + " IN ("+
        						"SELECT DISTINCT " + KEY_CATEGORY + 
        						" FROM " + TABLE_PRODUCTS + 
        						" WHERE " + LIST_KEY_ID + " = " + listId +")";
     
        Cursor cursor = db.rawQuery(selectQuery, null);
     
       	Category tempCategory;
           // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                // Adding category to list
            	tempCategory = new Category();
            	tempCategory.setName(cursor.getString(0));
            	tempCategory.setIconName(Integer.parseInt(cursor.getString(1)));
            	categoriesList.add(tempCategory);
            } while (cursor.moveToNext());
        }
        
        for (Category category : categoriesList) {
        	category.setProductsList(getCategoryProducts(listId,category.getName(),db));
		}
        return categoriesList;
    }
    
    public ShoppingList getShoppingListbyName(String listName){
    	ShoppingList shoppingList = new ShoppingList();
        String selectQuery = "SELECT  * FROM " + TABLE_LISTS + " WHERE " + LIST_KEY_NAME + " = '" + listName +"'";              
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);     
        
        if (cursor.moveToFirst()) {
        	int listId = Integer.parseInt(cursor.getString(0));
        	shoppingList.setId(listId);
        	shoppingList.setName(cursor.getString(1));
        	shoppingList.setLocation(cursor.getString(2));
        	
    		ArrayList<Category> categoryList = getListCategories(listId,db);
    		shoppingList.setCategoriesList(categoryList);
        }
        else
        	return null;
    	
    	return shoppingList;
    }

    public String getProductCategory(Product p){
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PREDEFINED_PRODUCTS + " WHERE " + KEY_NAME + " = '" + p.getName() + "'";
     
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
     
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
        	return cursor.getString(2);
        }
     
        // return products list
        return "אחר";    	
    }

    
    //----------------------------------------------------------------------------
    // handle shopping lists
    //----------------------------------------------------------------------------
    public ArrayList<String> getAllShoppingListNames() {
    	ArrayList<String> shoppingLists = new ArrayList<String>();
        // Select All Query
        String selectQuery = "SELECT  " + LIST_KEY_NAME + " FROM " + TABLE_LISTS;
     
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
     
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	String name = cursor.getString(0);
            	shoppingLists.add(name);
            } while (cursor.moveToNext());
        }
     
        return shoppingLists;
    }
    
    public String getLastShoppingListName() {
        // Select All Query
        String selectQuery = "SELECT  " + LIST_KEY_NAME + " FROM " + TABLE_LISTS;
     
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
        	return (cursor.getString(0));
        }
        else  
        	return "";
    }
    
    
    public void updateShoppingListName(int listId,String newName){
        SQLiteDatabase db = this.getWritableDatabase();

    	ContentValues args = new ContentValues();
        args.put(LIST_KEY_NAME, newName);
        db.update(TABLE_LISTS, args, LIST_KEY_ID + "=" + listId, null);        

    }
    
    public void updateShoppingListLocation(int listId,String newLocation){
        SQLiteDatabase db = this.getWritableDatabase();

    	ContentValues args = new ContentValues();
        args.put(LIST_LOCATION, newLocation);
        db.update(TABLE_LISTS, args, LIST_KEY_ID + "=" + listId, null);        

    }

    
    public int addShoppingList(String listName) {
        SQLiteDatabase db = this.getWritableDatabase();
        
        ContentValues  values = new ContentValues ();
	    values.put(LIST_KEY_NAME, listName); // list Name
	    db.insert(TABLE_LISTS, null, values);
	    
	    String selectQuery = "SELECT  * FROM " + TABLE_LISTS + " WHERE " + LIST_KEY_NAME + " = '" + listName +"'";              
        Cursor cursor = db.rawQuery(selectQuery, null);     
        
        if (cursor.moveToFirst()) 
        	return Integer.parseInt(cursor.getString(0));
    	else
    		return -1;
    }
    
    public void removeShoppingList(int listId){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_LISTS, LIST_KEY_ID + " =? ",
                new String[] {String.valueOf(listId)});

        db.delete(TABLE_PRODUCTS, LIST_KEY_ID + " =? ",
                new String[] {String.valueOf(listId)});

        String selectQuery = "SELECT  * FROM " + TABLE_LISTS ;              
        Cursor cursor = db.rawQuery(selectQuery, null);     
        
        if (!cursor.moveToFirst()) {
        	System.out.println("in here");
        	initPredefindList(db);
        }
        
        db.close();   
    }
    
    public void clearShoppingList(int listId){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_PRODUCTS, LIST_KEY_ID + " =? AND "+ KEY_GOT_IT + " =?",
                new String[] {String.valueOf(listId),Boolean.toString(true)});

        String selectQuery = "SELECT  * FROM " + TABLE_LISTS ;              
        Cursor cursor = db.rawQuery(selectQuery, null);     
        
        if (!cursor.moveToFirst()) {
        	System.out.println("in here");
        	initPredefindList(db);
        }
        
        db.close();   
    }
    
    public int saveAsShoppingList(int originListId,String newListName){
        int newListId = addShoppingList(newListName);
        
        String selectQuery = "SELECT  * FROM " + TABLE_PRODUCTS + " WHERE " + LIST_KEY_ID + " = " + originListId ;              
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);     
        ArrayList<Product> productsList = new ArrayList<Product>();
        if (cursor.moveToFirst()) {
            do {
            	Product product = new Product();
            	product.setId(Integer.parseInt(cursor.getString(0)));
            	product.setName(cursor.getString(1));
            	product.setCategory(cursor.getString(2));
            	product.setDescription(cursor.getString(3));
                // Adding product to list
            	productsList.add(product);
            } while (cursor.moveToNext());
        }

        for (Product p: productsList){
        	ContentValues  values = new ContentValues ();
	        values.put(KEY_NAME, p.getName()); // product Name
	        values.put(KEY_CATEGORY, p.getCategory()); // product category
	        values.put(KEY_DESC, p.getDescription()); // product description
	        values.put(LIST_KEY_ID, newListId); // product description
	        
	        // Inserting Row
	        db.insert(TABLE_PRODUCTS, null, values);
        }
        db.close(); // Closing database connection
        
        return newListId;
    }
    
    
    public void initPredefindList(SQLiteDatabase db) {
        ContentValues  values = new ContentValues ();
	    values.put(LIST_KEY_NAME, "רשימה חדשה שלי"); // list Name
	    values.put(LIST_LOCATION, ""); // list Name
	    db.insert(TABLE_LISTS, null, values);       	
    }

    public void initPredefindProduct(SQLiteDatabase db,ArrayList<Product> predifiedProductsList) {
        ContentValues  values = new ContentValues ();
        for (Product p: predifiedProductsList){
            values.put(KEY_NAME, p.getName()); // product Name
            values.put(KEY_CATEGORY, p.getCategory()); // product category
            db.insert(TABLE_PREDEFINED_PRODUCTS, null, values);
        	
        }
    }
    
    public void initPredefindCategories(SQLiteDatabase db,ArrayList<Category> predifiedCategoriesList) {
        ContentValues  values = new ContentValues ();
        for (Category c: predifiedCategoriesList){
            values.put(KEY_CATEGORY_C, c.getName()); // category Name
            values.put(KEY_CATEGORY_ICON_C, c.getIconName()); // category icon name
            db.insert(TABLE_CATEGORIES, null, values);
        	
        }
    }
    
    public String[] getAllPredefinedProductsNames() {
    	
        SQLiteDatabase db = this.getReadableDatabase();
                
        // Select All Query
        String selectQuery = "SELECT " + KEY_NAME + " FROM  " + TABLE_PREDEFINED_PRODUCTS;
     
        Cursor cursor = db.rawQuery(selectQuery, null);
     
        int numberOfProducts = cursor.getCount();
        String[] productsList = new String[numberOfProducts];

        // looping through all rows and adding to list
        int i = 0;
        if (cursor.moveToFirst()) {
            do {
            	
                // Adding product to list
            	productsList[i] = cursor.getString(0);
            	i++;
            } while (cursor.moveToNext());
        }
     
        // return products list
        return productsList;
    }

    public Category getCategoryByName(String name) {

    	Category category;
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CATEGORIES + " where " + KEY_CATEGORY_C + " = '" + name + "'";
     
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
     
        if (cursor.moveToFirst()) {

        	category = new Category();
        	category.setName(cursor.getString(0));
        	category.setIconName(Integer.parseInt(cursor.getString(1)));
        	return category;
        }
        else
        	return null;
    }
}