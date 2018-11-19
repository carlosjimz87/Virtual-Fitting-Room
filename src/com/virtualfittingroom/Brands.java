package com.virtualfittingroom;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class Brands extends Activity {
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
    setContentView(R.layout.brands);
    
    GridView view = (GridView)findViewById(R.id.brandsGrid);
    
    BrandsItem[] items = new BrandsItem[9];        
    
    items[0]=new BrandsItem("DolceGabbana",R.drawable.dolcegabbana_brands,R.drawable.dolcegabbana_brands_p);
    items[1]=new BrandsItem("Zara",R.drawable.zara_brands,R.drawable.zara_brands_p);
    items[2]=new BrandsItem("Mango",R.drawable.mango_brands,R.drawable.mango_brands_p);
    items[3]=new BrandsItem("CalvinKlein",R.drawable.calvinklein_brand,R.drawable.calvinklein_brand_p);
    items[4]=new BrandsItem("TommyHilfigher",R.drawable.tommy_brands,R.drawable.tommy_brands_p);    
    items[5]=new BrandsItem("Levis",R.drawable.levis_brands,R.drawable.levis_brands_p);
    items[6]=new BrandsItem("OscarDeLaRenta",R.drawable.oscardelarenta_brand,R.drawable.oscardelarenta_brand_p);
    items[7]=new BrandsItem("Gucci",R.drawable.gucci_brands,R.drawable.gucci_brands);
    items[8]=new BrandsItem("GreenCoast",R.drawable.greencoast_brands,R.drawable.greencoast_brands_p);
	
    view.setAdapter(new IconAdapter(getApplicationContext(),items));
    
}

class BrandsItem {
	final String name;
	final int image;
	final int pimage;
	BrandsItem(String name, int image, int pimage) {
	this.name = name;
	this.image = image;
	this.pimage = pimage;
	}
	}

public class IconAdapter extends BaseAdapter {
	
	private BrandsItem[] items;
	private Context context;
	
	IconAdapter(Context context,BrandsItem... items) {
		this.items = items;
		   this.context = context;

		}
	
	public int getCount() {
		// TODO Auto-generated method stub
		return items.length;
	}

	public Object getItem(int index) {
		// TODO Auto-generated method stub
		return items[index];
	}

	public long getItemId(int index) {
		// TODO Auto-generated method stub
		return index;
	}

	 public View getView(int index, View view, ViewGroup parent) {
	        final ImageView imageView;
	        if (view == null) { 
	            imageView = new ImageView(this.context);
	        } else {
	            imageView = (ImageView) view;
	        }
	        final BrandsItem item = items[index];
	        imageView.setImageResource(item.image);
	        imageView.setPadding(0,10, 0,10);
	        imageView.setOnTouchListener(new View.OnTouchListener() {
	            public boolean onTouch(View v, MotionEvent event)
	            {
	                switch (event.getAction()) {
	                    case MotionEvent.ACTION_DOWN:
	                    	imageView.setImageResource(item.pimage);
	                        return true;
	                    case MotionEvent.ACTION_CANCEL:
	                    	imageView.setImageResource(item.image);
	                        return true;
	                    case MotionEvent.ACTION_UP:
	                    	imageView.setImageResource(item.image);
	                    	if(item.name.equals("DolceGabbana") || item.name.equals("Zara") || item.name.equals("Mango")|| item.name.equals("CalvinKlein")){
	                    		PreferencesHelper.saveBrand(item.name);	    
	                    		PreferencesHelper.saveFashion("null");	 
	                            startActivity(new Intent(Brands.this,ProductList.class));               		
	                    	}
	                    	else
	                    		Toast.makeText(getApplicationContext(),"Not developed", Toast.LENGTH_SHORT).show();
	                    	
	                        return true;
	                    default:
	                        return false;
	                }
	            }
	        });
	        return imageView;
	    
	 }
}
}
