package il.ac.shenkar.superShoppinglist.utils;

import il.ac.shenkar.superShoppinglist.R;

import java.util.Random;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

public class ShoppingListWidgetProvider extends AppWidgetProvider {

  private static final String ACTION_CLICK = "ACTION_CLICK";

  private ShoppingListRepository shoppingList;
  
  
  @Override
  public void onUpdate(Context context, AppWidgetManager appWidgetManager,
      int[] appWidgetIds) {

	  shoppingList = ShoppingListRepository.getInstance(context);

    // Get all ids
    ComponentName thisWidget = new ComponentName(context,
    		ShoppingListWidgetProvider.class);
    int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
    for (int widgetId : allWidgetIds) {
      // Create some random data
      int number = (new Random().nextInt(100));
      RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
          R.layout.widget_layout);
      Log.w("WidgetExample", String.valueOf(number));
      // Set the text
      remoteViews.setTextViewText(R.id.widget_update, String.valueOf(number));

      // Register an onClickListener
      Intent intent = new Intent(context, ShoppingListWidgetProvider.class);

      intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
      intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

      PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
          0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
      remoteViews.setOnClickPendingIntent(R.id.widget_update, pendingIntent);
      appWidgetManager.updateAppWidget(widgetId, remoteViews);
    }
  }
}