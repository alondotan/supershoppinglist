package il.ac.shenkar.superShoppinglist.utils;

import il.ac.shenkar.superShoppinglist.R;
import il.ac.shenkar.superShoppinglist.beans.Category;
import il.ac.shenkar.superShoppinglist.beans.Product;


import java.util.ArrayList;

public class PredefinedDataHandler {
	

	private ArrayList<Category> predifiedCategoryList;

	public PredefinedDataHandler(){


		predifiedCategoryList = new ArrayList<Category>();
		predifiedCategoryList.add(new Category("שתיה",R.drawable.drinks));
		predifiedCategoryList.add(new Category("ממתקים",R.drawable.candy));
		predifiedCategoryList.add(new Category("טיפוח",R.drawable.beauty));
		predifiedCategoryList.add(new Category("ניקוי",R.drawable.cleaning));
		predifiedCategoryList.add(new Category("תינוקות",R.drawable.baby));
		predifiedCategoryList.add(new Category("פירות וירקות",R.drawable.frouts));
		predifiedCategoryList.add(new Category("מאפייה",R.drawable.bread));
		predifiedCategoryList.add(new Category("מוצרי חלב",R.drawable.milk));
		predifiedCategoryList.add(new Category("קפואים",R.drawable.frozen));
		predifiedCategoryList.add(new Category("מוצרי יסוד",R.drawable.basic));
		predifiedCategoryList.add(new Category("אחר",R.drawable.other));
				
	}
	
	public ArrayList<Product> getPredefinedProductsList(){
		ArrayList<Product> predifiedProductsList;

		predifiedProductsList = new ArrayList<Product>();
		predifiedProductsList.add(new Product("מרכך כביסה",0,"","ניקוי"));		
		predifiedProductsList.add(new Product("אבקת כביסה",0,"","ניקוי"));		
		predifiedProductsList.add(new Product("מנקה רצפות",0,"","ניקוי"));		
		predifiedProductsList.add(new Product("מנקה שמנים",0,"","ניקוי"));		
		predifiedProductsList.add(new Product("מנקה חלונות",0,"","ניקוי"));		
		predifiedProductsList.add(new Product("דאודורנט",0,"","טיפוח"));
		predifiedProductsList.add(new Product("סבון גוף",0,"","טיפוח"));
		predifiedProductsList.add(new Product("שמפו",0,"","טיפוח"));
		predifiedProductsList.add(new Product("מרכך",0,"","טיפוח"));
		predifiedProductsList.add(new Product("סבון ידים",0,"","טיפוח"));
		predifiedProductsList.add(new Product("קולה",0,"","שתיה"));
		predifiedProductsList.add(new Product("בירה",0,"","שתיה"));
		predifiedProductsList.add(new Product("יין",0,"","שתיה"));
		predifiedProductsList.add(new Product("ויסקי",0,"","שתיה"));
		predifiedProductsList.add(new Product("מיץ",0,"","שתיה"));
		predifiedProductsList.add(new Product("ביסלי",0,"","ממתקים"));
		predifiedProductsList.add(new Product("במבה",0,"","ממתקים"));
		predifiedProductsList.add(new Product("ביגלה",0,"","ממתקים"));
		predifiedProductsList.add(new Product("סוכריות",0,"","ממתקים"));
		predifiedProductsList.add(new Product("וופלים",0,"","ממתקים"));
		predifiedProductsList.add(new Product("מסטיקים",0,"","ממתקים"));
		predifiedProductsList.add(new Product("מרשמלו",0,"","ממתקים"));
		predifiedProductsList.add(new Product("שוקולד",0,"","ממתקים"));
		predifiedProductsList.add(new Product("חיתולים",0,"","תינוקות"));
		predifiedProductsList.add(new Product("מוצצים",0,"","תינוקות"));
		predifiedProductsList.add(new Product("משחה",0,"","תינוקות"));
		predifiedProductsList.add(new Product("בצל",0,"","פירות וירקות"));
		predifiedProductsList.add(new Product("בצל סגול",0,"","פירות וירקות"));
		predifiedProductsList.add(new Product("עגבניות",0,"","פירות וירקות"));
		predifiedProductsList.add(new Product("מלפפונים",0,"","פירות וירקות"));
		predifiedProductsList.add(new Product("פלפל אדום",0,"","פירות וירקות"));
		predifiedProductsList.add(new Product("פלפל ירוק",0,"","פירות וירקות"));
		predifiedProductsList.add(new Product("פלפל כתום",0,"","פירות וירקות"));
		predifiedProductsList.add(new Product("פלפל צהוב",0,"","פירות וירקות"));
		predifiedProductsList.add(new Product("פלפל חריף",0,"","פירות וירקות"));
		predifiedProductsList.add(new Product("כרוב לבן",0,"","פירות וירקות"));
		predifiedProductsList.add(new Product("כרוב אדום",0,"","פירות וירקות"));
		predifiedProductsList.add(new Product("כרובית",0,"","פירות וירקות"));
		predifiedProductsList.add(new Product("קישואים",0,"","פירות וירקות"));
		predifiedProductsList.add(new Product("גזר",0,"","פירות וירקות"));
		predifiedProductsList.add(new Product("חסה",0,"","פירות וירקות"));
		predifiedProductsList.add(new Product("תפוחים",0,"","פירות וירקות"));
		predifiedProductsList.add(new Product("בננות",0,"","פירות וירקות"));
		predifiedProductsList.add(new Product("תפוזים",0,"","פירות וירקות"));
		predifiedProductsList.add(new Product("קלמנטינה",0,"","פירות וירקות"));
		predifiedProductsList.add(new Product("אפרסמון",0,"","פירות וירקות"));
		predifiedProductsList.add(new Product("תותים",0,"","פירות וירקות"));
		predifiedProductsList.add(new Product("אשכולית",0,"","פירות וירקות"));
		predifiedProductsList.add(new Product("אשכולית אדומה",0,"","פירות וירקות"));
		predifiedProductsList.add(new Product("פומלה",0,"","פירות וירקות"));
		predifiedProductsList.add(new Product("פומלית",0,"","פירות וירקות"));
		predifiedProductsList.add(new Product("בטטה",0,"","פירות וירקות"));
		predifiedProductsList.add(new Product("תפוח אדמה",0,"","פירות וירקות"));
		predifiedProductsList.add(new Product("שום",0,"","פירות וירקות"));
		predifiedProductsList.add(new Product("אפרסקים",0,"","פירות וירקות"));
		predifiedProductsList.add(new Product("שזיפים",0,"","פירות וירקות"));
		predifiedProductsList.add(new Product("סלק",0,"","פירות וירקות"));
		predifiedProductsList.add(new Product("צנון",0,"","פירות וירקות"));
		predifiedProductsList.add(new Product("צנונית",0,"","פירות וירקות"));
		predifiedProductsList.add(new Product("דלעת",0,"","פירות וירקות"));		
		predifiedProductsList.add(new Product("לחם לבן",0,"","מאפייה"));
		predifiedProductsList.add(new Product("לחם קל",0,"","מאפייה"));
		predifiedProductsList.add(new Product("לחמניה",0,"","מאפייה"));
		predifiedProductsList.add(new Product("פיתות",0,"","מאפייה"));
		predifiedProductsList.add(new Product("בגט",0,"","מאפייה"));
		predifiedProductsList.add(new Product("בורקסים",0,"","מאפייה"));
		predifiedProductsList.add(new Product("רוגלך",0,"","מאפייה"));
		predifiedProductsList.add(new Product("עוגיות",0,"","מאפייה"));
		predifiedProductsList.add(new Product("חלב",0,"","מוצרי חלב"));
		predifiedProductsList.add(new Product("גבינה לבנה",0,"","מוצרי חלב"));
		predifiedProductsList.add(new Product("קוטג",0,"","מוצרי חלב"));
		predifiedProductsList.add(new Product("גבינה צהובה",0,"","מוצרי חלב"));
		predifiedProductsList.add(new Product("חלב סויה",0,"","מוצרי חלב"));
		predifiedProductsList.add(new Product("מעדן חלב",0,"","מוצרי חלב"));
		predifiedProductsList.add(new Product("מעדן סויה",0,"","מוצרי חלב"));
		predifiedProductsList.add(new Product("יוגורט",0,"","מוצרי חלב"));
		predifiedProductsList.add(new Product("בולגרית",0,"","מוצרי חלב"));
		predifiedProductsList.add(new Product("צפתית",0,"","מוצרי חלב"));
		predifiedProductsList.add(new Product("לבנה",0,"","מוצרי חלב"));
		predifiedProductsList.add(new Product("גבינת עזים",0,"","מוצרי חלב"));
		predifiedProductsList.add(new Product("שמנת מתוקה",0,"","מוצרי חלב"));
		predifiedProductsList.add(new Product("שמנת לבישול",0,"","מוצרי חלב"));
		predifiedProductsList.add(new Product("חמאה",0,"","מוצרי חלב"));
		predifiedProductsList.add(new Product("מרגרינה",0,"","מוצרי חלב"));
		predifiedProductsList.add(new Product("שוקו",0,"","מוצרי חלב"));
		predifiedProductsList.add(new Product("גבינה צהובה מגורדת",0,"","מוצרי חלב"));
		predifiedProductsList.add(new Product("ברוקולי קפוא",0,"","קפואים"));
		predifiedProductsList.add(new Product("אפונה קפואה",0,"","קפואים"));
		predifiedProductsList.add(new Product("כרובית קפואה",0,"","קפואים"));
		predifiedProductsList.add(new Product("לקט ירקות קפוא",0,"","קפואים"));
		predifiedProductsList.add(new Product("שום קפוא",0,"","קפואים"));
		predifiedProductsList.add(new Product("פולי סויה קפואים",0,"","קפואים"));
		predifiedProductsList.add(new Product("מלח שולחן",0,"","מוצרי יסוד"));
		predifiedProductsList.add(new Product("סוכר",0,"","מוצרי יסוד"));
		predifiedProductsList.add(new Product("קמח",0,"","מוצרי יסוד"));
		predifiedProductsList.add(new Product("קמח תופח",0,"","מוצרי יסוד"));
		predifiedProductsList.add(new Product("אורז",0,"","מוצרי יסוד"));
		predifiedProductsList.add(new Product("פסטה",0,"","מוצרי יסוד"));
		predifiedProductsList.add(new Product("פתיתים",0,"","מוצרי יסוד"));
		
		return predifiedProductsList;
	}
	
	public ArrayList<Category> getPredefinedCategoryList(){
		return predifiedCategoryList;
	}
}
