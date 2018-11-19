package com.virtualfittingroom;


import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ProductList extends Activity {
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
    setContentView(R.layout.productlist);    


    GridView view = (GridView)findViewById(R.id.productsGrid);
	MeasureAdjuster.setMeasures(PreferencesHelper.getSavedMeasures());	
    ProductItem[] items=ImagesLoader.getArray();

    ArrayList<ProductItem> fitems = new ArrayList<ProductItem>();
    if(!PreferencesHelper.getSavedBrand().equals("null")){
	    for (int i=0; i<items.length; i++){	    
	    		if(items[i].bodyPart.equals("T") && items[i].size.equals(MeasureAdjuster.upperSize) &&  (items[i]).gender.equals(PreferencesHelper.getSavedGender()) && (items[i]).brand.equals(PreferencesHelper.getSavedBrand()))
	    		fitems.add(items[i]);
	    		if(items[i].bodyPart.equals("B") && items[i].size.equals(MeasureAdjuster.lowerSize) &&  (items[i]).gender.equals(PreferencesHelper.getSavedGender()) && (items[i]).brand.equals(PreferencesHelper.getSavedBrand()))
	    		fitems.add(items[i]);
	    }
    }
    else{
    	for (int i=0; i<items.length; i++){	    	
	    		if(items[i].bodyPart.equals("T") && items[i].size.equals(MeasureAdjuster.upperSize) &&  (items[i]).gender.equals(PreferencesHelper.getSavedGender()) && (items[i]).fashion.equals(PreferencesHelper.getSavedFashion()))
	    		fitems.add(items[i]);
	    		if(items[i].bodyPart.equals("B") && items[i].size.equals(MeasureAdjuster.lowerSize) &&  (items[i]).gender.equals(PreferencesHelper.getSavedGender()) && (items[i]).fashion.equals(PreferencesHelper.getSavedFashion()))
	    		fitems.add(items[i]);
	    }
    	
    }
    
    view.setAdapter(new IconAdapter(getApplicationContext(),fitems));
      
    
	
	}


public class IconAdapter extends BaseAdapter {
	
	private ArrayList<ProductItem> items;
	private Context context;
	
	IconAdapter(Context context,ArrayList<ProductItem> items) {
		this.items = items;
		   this.context = context;

		}
	
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	public Object getItem(int index) {
		// TODO Auto-generated method stub
		return items.get(index);
	}

	public long getItemId(int index) {
		// TODO Auto-generated method stub
		return index;
	}

	 public View getView(int index, View view, ViewGroup parent) {
	        ImageView imageView;
	        if (view == null) {  
	            imageView = new ImageView(this.context);
	        } else {
	            imageView = (ImageView) view;
	        }
	        ProductItem item = items.get(index);
	        imageView.setImageResource(item.image);
	        imageView.setPadding(0,8,0,8);
	        return imageView;
	    
	 }
}
}
