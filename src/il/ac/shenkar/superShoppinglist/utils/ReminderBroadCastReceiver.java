package il.ac.shenkar.superShoppinglist.utils;

import il.ac.shenkar.superShoppinglist.R;
import il.ac.shenkar.superShoppinglist.activities.DoShoppingActivity;
import il.ac.shenkar.superShoppinglist.activities.ShoppingListMainActivity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ReminderBroadCastReceiver extends BroadcastReceiver {
	public void onReceive(Context context,Intent intent){

        String shoppingListName = intent.getStringExtra(ShoppingListRepository.EXTRA_LIST_NAME);
        
		Intent shoppingListIntent	= new Intent(context,DoShoppingActivity.class);
		shoppingListIntent.putExtra(ShoppingListMainActivity.EXTRA_LIST_NAME, shoppingListName);
		PendingIntent pendingIntent	= PendingIntent.getActivity(context,0,shoppingListIntent,PendingIntent.FLAG_UPDATE_CURRENT);	
		NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);	
											
        
		Notification notification = new Notification (R.drawable.super_launcher,"הגיע הזמן לקנות",System.currentTimeMillis());	
		notification.setLatestEventInfo(context,"הגיע הזמן לקנות!!","רשימת הקניות "+shoppingListName+" מחכה לך!", pendingIntent);	
		notificationManager.notify(0,notification);	//0	is	id	
	}	
}	