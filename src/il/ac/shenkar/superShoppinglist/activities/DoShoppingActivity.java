package il.ac.shenkar.superShoppinglist.activities;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.Tracker;

import il.ac.shenkar.superShoppinglist.R;
import il.ac.shenkar.superShoppinglist.adapters.CategoriesPagerAdapter;
import il.ac.shenkar.superShoppinglist.utils.ShoppingListRepository;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.TextView;


public class DoShoppingActivity extends Activity
{

	private ShoppingListRepository shoppingList;
	
    private ViewPager categoriesPager;
    private Context context;
    private CategoriesPagerAdapter categoriesAdapter;
    private OnPageChangeListener mPageChangeListener;
    private boolean startedFromReminder;
    private int currPage;
	private Tracker tracker;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_shopping);
        
        shoppingList = ShoppingListRepository.getInstance(this);
        context = this;
        startedFromReminder = false;       
        currPage = 1;
        
        // ga tracker
        EasyTracker.getInstance().setContext(getApplicationContext());
        tracker = EasyTracker.getTracker();
        
        // checking if the origin was from location reminder
        Intent intent = getIntent();
        String message = intent.getStringExtra(ShoppingListMainActivity.EXTRA_LIST_NAME);

        if (!message.isEmpty()){
        	// was from reminder
    		tracker.trackEvent("Shopping List Do shopping", "open_from_location", "",null);
        	startedFromReminder = true;        	
        	if (shoppingList.replaceCurrentList(message)){
            	shoppingList.updateLocationToCurrentList("");
        	}
        	else {
        		// the list was deleted
        		tracker.trackEvent("Shopping List Do shopping", "open_deleted_list_from_location", "",null);
        		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        		TextView errorText = new TextView(this);
        		errorText.setText("הרשימה " + message + " כבר נמחקה, חבל.");
        		alertDialogBuilder.setView(errorText)
               .setNegativeButton("אשר", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   backToEdit(new View(context));
                       dialog.cancel();
                   }
               });      
        		AlertDialog alertDialog = alertDialogBuilder.create();
        		alertDialog.show();
        	}
        }

        
        TextView name = (TextView) findViewById(R.id.pageNumberText);
        name.setText(1 + "/" +  shoppingList.getCategoriesNumber());
        
        TextView listName = (TextView) findViewById(R.id.currentListNameText);
        listName.setText(shoppingList.getCurrentShoppingListName());
        
        categoriesAdapter = new CategoriesPagerAdapter(this);
        categoriesPager = (ViewPager) findViewById(R.id.categoriespager);
        categoriesPager.setAdapter(categoriesAdapter);
        
        mPageChangeListener = new OnPageChangeListener() {

            public void onPageScrollStateChanged(int arg0) {
            }

            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            public void onPageSelected(int pos) {

              TextView name = (TextView) findViewById(R.id.pageNumberText);
              name.setText((pos+1) + "/" +  shoppingList.getCategoriesNumber());
              currPage = pos+1;
            }

        };
        categoriesPager.setOnPageChangeListener(mPageChangeListener);
    }
    
    @Override
	public void onResume() {
        super.onResume();
        shoppingList = ShoppingListRepository.getInstance(this);
    }
    
    public void goNext(View view) {
    	if (currPage<shoppingList.getCategoriesNumber())
    		categoriesPager.setCurrentItem(currPage,true);
    }	
    
    public void goPrev(View view) {
    	if (currPage>1)
    		categoriesPager.setCurrentItem( currPage-2,true);
    }	
    public void backToEdit(View view) {
    	if (!startedFromReminder)
    		finish();
    	else
    	{
        	Intent intent = new Intent(this, ShoppingListMainActivity.class);    	
        	startActivity(intent);
       		finish();
    	}
    }	
    @Override
    public void onStart() {
      super.onStart();
      EasyTracker.getInstance().activityStart(this); // Add this method.
    }

    @Override
    public void onStop() {
      super.onStop();
      EasyTracker.getInstance().activityStop(this); // Add this method.
    }

}