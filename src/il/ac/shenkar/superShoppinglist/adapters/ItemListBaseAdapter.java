package il.ac.shenkar.superShoppinglist.adapters;

import il.ac.shenkar.superShoppinglist.R;
import il.ac.shenkar.superShoppinglist.beans.Category;
import il.ac.shenkar.superShoppinglist.beans.Product;
import il.ac.shenkar.superShoppinglist.utils.DropDownAnimation;
import il.ac.shenkar.superShoppinglist.utils.ShoppingListRepository;

import java.util.ArrayList;
import java.util.Collections;



import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ItemListBaseAdapter extends BaseAdapter {
	private Context context;
	private ShoppingListRepository shoppingList;
		
	private ArrayList<Boolean> areOpened; 
	private LayoutInflater l_Inflater;

	public ItemListBaseAdapter(Context context) {
		l_Inflater = LayoutInflater.from(context);
		this.context = context;
		this.shoppingList = ShoppingListRepository.getInstance(context);
		areOpened = new ArrayList<Boolean> (Collections.nCopies(shoppingList.getSize(), false));
	}

	public int getCount() {
		return shoppingList.getSize();
	}

	public Object getItem(int position) {
		return shoppingList.getObject(position);
	}

	public long getItemId(int position) {
		return position;
	}
	
	private final OnClickListener doneButtonOnClickListener = new OnClickListener(){		
		
		public void onClick(View view) {
			int	position = (Integer)view.getTag();
			ShoppingListRepository.getInstance(context).removeProduct(position);
			areOpened.remove(position);
			notifyDataSetChanged();	
		}
	};
	
	private final OnClickListener expandButtonOnClickListener = new OnClickListener(){		
		
		public void onClick(View view) {
			View parentView = (View)view.getParent().getParent().getParent();
			int	position = (Integer)view.getTag();
			System.out.println(view.toString());
			LinearLayout txt_itemDesc  = (LinearLayout) parentView.findViewById(R.id.extraDetails);
			if (txt_itemDesc.getVisibility() == View.VISIBLE){
				txt_itemDesc.setVisibility(View.GONE);
				areOpened.set(position, false);
			}
			else{
				DropDownAnimation a = new DropDownAnimation(txt_itemDesc,100,true);
			    a.reset();
			    txt_itemDesc.clearAnimation();
			    txt_itemDesc.startAnimation(a);
				txt_itemDesc.setVisibility(View.VISIBLE);
				areOpened.set(position, true);
			}
			
		}
	};
	
	private final OnFocusChangeListener descChangeListener = new OnFocusChangeListener(){		
		
		public void onFocusChange(View previouslyFocused, boolean gainFocus) {
		    if (!gainFocus) {		        
				int	position = (Integer)previouslyFocused.getTag();
				Product p = (Product) shoppingList.getObject(position);
				TextView t = (TextView)previouslyFocused;
				p.setDescription(t.getText().toString());
				shoppingList.updateProduct(p);
		      }
		}
	};

	public View getView(int position, View convertView, ViewGroup parent) {
		if (!shoppingList.isCategory(position)){
			ViewProductHolder holder;
			View view;
			if (convertView == null) {
				holder = new ViewProductHolder();
				ViewGroup viewGroup = (ViewGroup)l_Inflater.inflate(R.layout.product_details_view, null);
				holder.txt_itemName = (TextView) viewGroup.findViewById(R.id.name);
				holder.txt_itemDesc = (TextView) viewGroup.findViewById(R.id.desc);
				holder.txt_itemDesc.setOnFocusChangeListener(descChangeListener);
	
				holder.doneButton = (Button) viewGroup.findViewById(R.id.doneButton);
				holder.doneButton.setOnClickListener(doneButtonOnClickListener);

				holder.expandButton = (Button) viewGroup.findViewById(R.id.expandButton);
				holder.expandButton.setOnClickListener(expandButtonOnClickListener);
				
				holder.layout_itemDesc = (LinearLayout) viewGroup.findViewById(R.id.extraDetails);
				viewGroup.setTag(holder);
				view = viewGroup;
			} else {
				holder = (ViewProductHolder) convertView.getTag();
				view = convertView;

			}
			
			
			holder.doneButton.setTag(position);	
			holder.expandButton.setTag(position);
			Product p = (Product) shoppingList.getObject(position);
			holder.txt_itemName.setText(p.getName());
			holder.txt_itemDesc.setText(p.getDescription());
			holder.txt_itemDesc.setTag(position);
			if (areOpened.get(position)){
				holder.layout_itemDesc.setVisibility(View.VISIBLE);				
			}
			else{
				holder.layout_itemDesc.setVisibility(View.GONE);
			}

			if (p.isGotIt()){
				holder.txt_itemName.setPaintFlags(holder.txt_itemName.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
			}
			else
				holder.txt_itemName.setPaintFlags( holder.txt_itemName.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));

			return view;
		}			
		else{
			ViewCategoryHolder holder;
			View view;
			if (convertView == null) {
				holder = new ViewCategoryHolder();
				ViewGroup viewGroup = (ViewGroup) l_Inflater.inflate(R.layout.category_details_view, null);
				holder.txt_itemName = (TextView) viewGroup.findViewById(R.id.categoryName);
				holder.imageView = (ImageView) viewGroup.findViewById(R.id.categoryImg);
				viewGroup.setTag(holder);
				view = viewGroup;
			} else {
				holder = (ViewCategoryHolder) convertView.getTag();
				view = convertView;
			}

			Category c = (Category) shoppingList.getObject(position);
			holder.txt_itemName.setText(c.getName());
			holder.imageView.setImageResource(c.getIconName());
			return view;			
		}
		
	}
	
	static class ViewProductHolder {
		TextView txt_itemName;
		TextView txt_itemDesc;
		Button doneButton;
		Button expandButton;
		LinearLayout layout_itemDesc;
	}
	
	static class ViewCategoryHolder {
		TextView txt_itemName;
		ImageView imageView;
	}
	
	public int getViewTypeCount() {
	    return 2;
	}
	
	public int getItemViewType(int position) {
	    if (!shoppingList.isCategory(position)) return 0;
	    else return 1;
	}
}
