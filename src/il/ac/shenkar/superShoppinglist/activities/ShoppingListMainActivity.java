package il.ac.shenkar.superShoppinglist.activities;

import il.ac.shenkar.superShoppinglist.R;
import il.ac.shenkar.superShoppinglist.adapters.ItemListBaseAdapter;
import il.ac.shenkar.superShoppinglist.beans.Product;
import il.ac.shenkar.superShoppinglist.utils.ShoppingListRepository;

import java.util.ArrayList;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.Tracker;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

public class ShoppingListMainActivity extends Activity {

	public static final String EXTRA_LIST_NAME = "il.ac.shenkar.superShoppingList.LIST_NAME";
	
	private ShoppingListRepository shoppingList;
	private Tracker tracker;
		
	private ListView lv1;
    private TextView currentNameTextView;
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_shopping_list);

        // ga tracker
        EasyTracker.getInstance().setContext(getApplicationContext());
        tracker = EasyTracker.getTracker();
                
        shoppingList = ShoppingListRepository.getInstance(this);

        // add current list name to the display
        currentNameTextView = (TextView) findViewById(R.id.currentListNameText);
        currentNameTextView.setText(shoppingList.getCurrentShoppingListName());

        // get predefined products list to the drop down menu
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,shoppingList.getAllPredefinedProductsNames());
        
        AutoCompleteTextView textView = (AutoCompleteTextView)
                findViewById(R.id.editTextName);
        textView.setAdapter(adapter);

        // set the enter key to save to product 
        textView.setOnEditorActionListener(
                new AutoCompleteTextView.OnEditorActionListener() {
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                                actionId == EditorInfo.IME_ACTION_DONE ||
                                event.getAction() == KeyEvent.ACTION_DOWN &&
                                event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                        	addNewProduct(v);                     	
                            return true;
                        }
                        return false;
                    }
                });

        // handle the product list
        lv1 = (ListView) findViewById(R.id.listV_main);
        lv1.setAdapter(new ItemListBaseAdapter(this));
        
     }

    @Override
	public void onResume() {
        super.onResume();
        shoppingList = ShoppingListRepository.getInstance(this);

        // reset the display
        final ListView lv1 = (ListView) findViewById(R.id.listV_main);
    	EditText nameText = (EditText) findViewById(R.id.editTextName);
    	nameText.setText("");
    	
    	currentNameTextView = (TextView) findViewById(R.id.currentListNameText);
        currentNameTextView.setText(shoppingList.getCurrentShoppingListName());
        
        lv1.setAdapter(new ItemListBaseAdapter(this));
    }
    

    // handle adding new product
    public void addNewProduct(View view) {
    	Product p = new Product();
    	EditText nameText = (EditText) findViewById(R.id.editTextName);
    	if (!nameText.getText().toString().isEmpty()){
	    	p.setName(nameText.getText().toString());
	    	shoppingList.addProduct(p);
	    	// reset the product name field
	    	nameText.setText("");
	    	
	        final ListView lv1 = (ListView) findViewById(R.id.listV_main);    	
	        lv1.setAdapter(new ItemListBaseAdapter(this));
			tracker.trackEvent("Shopping List Home page", "new_product_was_added", "",null);
    	}
    	
    }
    
    // share the list
    public void share(View view) {
		tracker.trackEvent("Shopping List Home page", "product_was_shared", "",null);
		Intent share = new Intent(Intent.ACTION_SEND);
		share.setType("text/plain");
		share.putExtra(Intent.EXTRA_TEXT, //shoppingList.currentShoppingListToString());
				Html.fromHtml(new StringBuilder()
                .append(shoppingList.currentShoppingListToHtml())
                .toString()));
				
		startActivity(Intent.createChooser(share, "איך תרצה לשתף את רשימת: " + shoppingList.getCurrentShoppingListName()));
    }
    
    // move the the do shopping activity
    public void goShopping(View view) {
		tracker.trackEvent("Shopping List Home page", "went_to_shopping", "",null);
    	Intent intent = new Intent(this, DoShoppingActivity.class);    	
    	// pass blank parameter, to indicate that we are from inside the aplication and not a reminder
    	intent.putExtra(ShoppingListMainActivity.EXTRA_LIST_NAME, "");
    	startActivity(intent);
    }
    
    public void addLocation (View view) {
		tracker.trackEvent("Shopping List Home page", "location_was_added", "",null);

		// build dialog for new location
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		LayoutInflater l_Inflater = LayoutInflater.from(this);
		View newNameView = l_Inflater.inflate(R.layout.enter_shopping_location, null);
		EditText newNameText = (EditText) newNameView.findViewById(R.id.newName);
		newNameText.setText(shoppingList.getCurrentShoppingListLocation());

		alertDialogBuilder.setView(newNameView)
		.setPositiveButton("שמור", new DialogInterface.OnClickListener() {
           public void onClick(DialogInterface dialog, int id) {
        	   Dialog d = (Dialog) dialog;

         	   EditText newNameText = (EditText) d.findViewById(R.id.newName);
         	   shoppingList.updateLocationToCurrentList(newNameText.getText().toString());
           }
        })
        .setNegativeButton("בטל התרעה", new DialogInterface.OnClickListener() {
           public void onClick(DialogInterface dialog, int id) {
               dialog.cancel();
         	   shoppingList.updateLocationToCurrentList("");
           }
        });      
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();		
						
    }

    @Override
    public void onStart() {
      super.onStart();
      EasyTracker.getInstance().activityStart(this); 
    }

    @Override
    public void onStop() {
      super.onStop();
      EasyTracker.getInstance().activityStop(this);
    }
    
    //Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.activity_main_shopping_list, menu);
	    return true;
	}
    
    // Handle item selection in the menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	AlertDialog.Builder alertDialogBuilder;
    	LayoutInflater l_Inflater;
    	View newNameView;
    	EditText newNameText;
    	AlertDialog alertDialog;

		tracker.trackEvent("Shopping List Home page", "menu_was_opened", "",null);
    	
    	switch (item.getItemId()) {
    		// adding new list
            case R.id.menu_newList: 
    			alertDialogBuilder = new AlertDialog.Builder(this);
    			l_Inflater = LayoutInflater.from(this);
    			newNameView = l_Inflater.inflate(R.layout.change_list_name, null);
    			alertDialogBuilder.setView(newNameView)
    			.setPositiveButton("שמור", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   Dialog d = (Dialog) dialog;
                	   EditText newNameText = (EditText) d.findViewById(R.id.newName);
                	   shoppingList.newList(newNameText.getText().toString());
                	   tracker.trackEvent("Shopping List Home page", "new_list_was_added", "",null);
                	   refreshDisplay();
                   }
               })
               .setNegativeButton("בטל", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       dialog.cancel();
                   }
               });      
    			alertDialog = alertDialogBuilder.create();
    			alertDialog.show();
                return true;
                
        	// rename the list
            case R.id.menu_renameList:
    			alertDialogBuilder = new AlertDialog.Builder(this);
    			l_Inflater = LayoutInflater.from(this);
    			newNameView = l_Inflater.inflate(R.layout.change_list_name, null);
    			newNameText = (EditText) newNameView.findViewById(R.id.newName);
    			newNameText.setText(shoppingList.getCurrentShoppingListName());
    			alertDialogBuilder.setView(newNameView)
    			.setPositiveButton("שמור", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   Dialog d = (Dialog) dialog;
                	   EditText newNameText = (EditText) d.findViewById(R.id.newName);
                	   shoppingList.updateCurrentListName(newNameText.getText().toString());
                	   tracker.trackEvent("Shopping List Home page", "list_renamed", "",null);
                	   refreshDisplay();
                   }
               })
               .setNegativeButton("בטל", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       dialog.cancel();
                   }
               });      
    			alertDialog = alertDialogBuilder.create();
    			alertDialog.show();
                return true;
                
            // choose other list
            case R.id.menu_chooseList:
            	ArrayList<String> list = shoppingList.getAllShoppingListNames();
            	final CharSequence[] items = new CharSequence[list.size()];
            	for (int i =0; i<  list.size();i++) {
					System.out.println(list.get(i));
					items[i] = list.get(i);
				}
            	alertDialogBuilder = new AlertDialog.Builder(this);
            	alertDialogBuilder.setTitle("בחר רשימה");
            	alertDialogBuilder.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                    	shoppingList.replaceCurrentList(items[item].toString());
                		tracker.trackEvent("Shopping List Home page", "change_current_list", "",null);
                    	refreshDisplay();
                    }
                });
            	alertDialog = alertDialogBuilder.create();
            	alertDialog.show();
                return true;    
                
            // create new list base on the current one
            case R.id.menu_saveAsList:
    			alertDialogBuilder = new AlertDialog.Builder(this);
    			l_Inflater = LayoutInflater.from(this);
    			newNameView = l_Inflater.inflate(R.layout.change_list_name, null);
    			alertDialogBuilder.setView(newNameView)
    			.setPositiveButton("שמור", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   Dialog d = (Dialog) dialog;
                	   EditText newNameText = (EditText) d.findViewById(R.id.newName);
                	   shoppingList.saveAsCurrentList(newNameText.getText().toString());
                	   tracker.trackEvent("Shopping List Home page", "list_saved_as", "",null);
                	   refreshDisplay();
                   }
               })
               .setNegativeButton("בטל", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       dialog.cancel();
                   }
               });      
    			alertDialog = alertDialogBuilder.create();
    			alertDialog.show();
                return true;
                
            // remove from the list all the products mark as done
            case R.id.menu_clearList:
            	shoppingList.clearCurrentShoppingList();
    	        lv1 = (ListView) findViewById(R.id.listV_main);    	
    	        lv1.setAdapter(new ItemListBaseAdapter(this));
    			tracker.trackEvent("Shopping List Home page", "list_was_cleared", "",null);    	        
                return true;          
                
            // removeing the list
            case R.id.menu_removeList:
            	shoppingList.removeCurrentList();
                currentNameTextView = (TextView) findViewById(R.id.currentListNameText);
                currentNameTextView.setText(shoppingList.getCurrentShoppingListName());
    	        lv1 = (ListView) findViewById(R.id.listV_main);    	
    	        lv1.setAdapter(new ItemListBaseAdapter(this));
    			tracker.trackEvent("Shopping List Home page", "list_was_removed", "",null);
                return true;          
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    private void refreshDisplay(){
        currentNameTextView = (TextView) findViewById(R.id.currentListNameText);
        currentNameTextView.setText(shoppingList.getCurrentShoppingListName());
        lv1 = (ListView) findViewById(R.id.listV_main);    	
        lv1.setAdapter(new ItemListBaseAdapter(ShoppingListMainActivity.this));
    }
}
