package il.ac.shenkar.superShoppinglist.adapters;

import il.ac.shenkar.superShoppinglist.R;
import il.ac.shenkar.superShoppinglist.utils.ShoppingListRepository;
import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class CategoriesPagerAdapter extends PagerAdapter{

	private LayoutInflater l_Inflater;
	private Context context;
	private ShoppingListRepository shoppingList;

	public CategoriesPagerAdapter(Context context){
		this.context = context;
        shoppingList = ShoppingListRepository.getInstance(context);
	}
	
    @Override
    public int getCount() {
       return shoppingList.getCategoriesNumber();
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
    	l_Inflater = LayoutInflater.from(context);
    	
		View viewGroup = l_Inflater.inflate(R.layout.do_shopping_category, null);
		
		TextView cat = (TextView) viewGroup.findViewById(R.id.categoryName);

		cat.setText(shoppingList.getCategory(position).getName());
        ImageView img = (ImageView)  viewGroup.findViewById(R.id.categoryIcon);
        img.setImageResource(shoppingList.getCategory(position).getIconName());
        
        ListView lv1 = (ListView) viewGroup.findViewById(R.id.listView1);
        lv1.setAdapter(new DoShoppingItemListBaseAdapter(context,position));
            
        collection.addView(viewGroup,0);                
        return viewGroup;
  	}

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
            collection.removeView((LinearLayout) view);
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
            return (view==object);
    }

        
    @Override
    public void finishUpdate(ViewGroup arg0) {}
    

    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1) {}

    @Override
    public Parcelable saveState() {
            return null;
    }

    @Override
    public void startUpdate(ViewGroup arg0) {}
    
}