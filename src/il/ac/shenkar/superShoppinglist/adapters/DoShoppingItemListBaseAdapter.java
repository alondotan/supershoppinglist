package il.ac.shenkar.superShoppinglist.adapters;

import il.ac.shenkar.superShoppinglist.R;
import il.ac.shenkar.superShoppinglist.beans.Product;
import il.ac.shenkar.superShoppinglist.utils.ShoppingListRepository;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

public class DoShoppingItemListBaseAdapter extends BaseAdapter {
	private ShoppingListRepository shoppingList;
		
	private LayoutInflater l_Inflater;
	private int categoryPos;
	
	public DoShoppingItemListBaseAdapter(Context context,int categoryPos) {
		l_Inflater = LayoutInflater.from(context);
		this.shoppingList = ShoppingListRepository.getInstance(context);
		this.categoryPos = categoryPos;
	}

	public int getCount() {
		return shoppingList.getCategory(categoryPos).getProductsList().size();
	}

	public Object getItem(int position) {
		return shoppingList.getCategory(categoryPos).getProductsList().get(position);		
	}

	public long getItemId(int position) {
		return position;
	}
	
	private final OnClickListener gotItButtonOnClickListener = new OnClickListener(){		
		
		public void onClick(View view) {
			int	position = (Integer)view.getTag();
			Product p = shoppingList.getCategory(categoryPos).getProductsList().get(position);
			p.setGotIt(!p.isGotIt());
			shoppingList.updateProduct(p);
			notifyDataSetChanged();
		}
	};
	

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewDoShoppingProductHolder holder;
		View view;
		if (convertView == null) {
			holder = new ViewDoShoppingProductHolder();
			ViewGroup viewGroup = (ViewGroup) l_Inflater.inflate(R.layout.do_shopping_details_view, null);
			holder.txt_itemName = (TextView) viewGroup.findViewById(R.id.doShoppingProductName);
			holder.txt_itemDesc = (TextView) viewGroup.findViewById(R.id.doShoppingProductDesc);
			
			holder.gotItButton = (ImageButton) viewGroup.findViewById(R.id.gotItButton);
			holder.gotItButton.setOnClickListener(gotItButtonOnClickListener);

			
			viewGroup.setTag(holder);
			view = viewGroup;
		} else {
			holder = (ViewDoShoppingProductHolder) convertView.getTag();
			view = convertView;
		}
	
		Product p = shoppingList.getCategory(categoryPos).getProductsList().get(position);
		holder.txt_itemName.setText(p.getName());
		holder.gotItButton.setTag(position);
		
		if (p.getDescription().isEmpty()){
			holder.txt_itemDesc.setVisibility(View.GONE);
		}
		else{
			holder.txt_itemDesc.setVisibility(View.VISIBLE);
			holder.txt_itemDesc.setText(p.getDescription());
		}
		if (p.isGotIt()){
			holder.txt_itemName.setPaintFlags(holder.txt_itemName.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
		}
		else
			holder.txt_itemName.setPaintFlags( holder.txt_itemName.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));

		return view;			
		
	}
	
	static class ViewDoShoppingProductHolder {
		TextView txt_itemName;
		TextView txt_itemDesc;
		ImageButton gotItButton;
	}
	
}
