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

public class Fashion extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.fashion);
	    
	    	    GridView view = (GridView)findViewById(R.id.fashionGrid);
	    
	    FashionItem[] items = new FashionItem[9];        
	    

		if(PreferencesHelper.getSavedGender().equals("Female")){
	    items[0]=new FashionItem("full",R.drawable.fashionwomen_full_n,R.drawable.fashionwomen_full_p);
	    items[1]=new FashionItem("shirts",R.drawable.fashionwomen_shirt_n,R.drawable.fashionwomen_shirt_p);
	    items[2]=new FashionItem("pants",R.drawable.fashionwomen_trouser_n,R.drawable.fashionwomen_trouser_p);
	    items[3]=new FashionItem("shorts",R.drawable.fashionwomen_shorts_n,R.drawable.fashionwomen_shorts_p);
	    items[4]=new FashionItem("skirt",R.drawable.fashionwomen_skirt_n,R.drawable.fashionwomen_skirt_p);
	    items[5]=new FashionItem("underware",R.drawable.fashionwomen_under_n,R.drawable.fashionwomen_under_p);
	    items[6]=new FashionItem("bikini",R.drawable.fashionwomen_beachunder_n,R.drawable.fashionwomen_beachunder_p);
	    items[7]=new FashionItem("shoes",R.drawable.fashionwomen_shoe_n,R.drawable.fashionwomen_shoe_p);
	    items[8]=new FashionItem("hat",R.drawable.fashionwomen_hat_n,R.drawable.fashionwomen_hat_p);
		}
		else{
			items[0]=new FashionItem("full",R.drawable.fashionmen_full_n,R.drawable.fashionmen_full_p);
		    items[1]=new FashionItem("shirts",R.drawable.fashionmen_shirt_n,R.drawable.fashionmen_shirt_p);
		    items[2]=new FashionItem("pants",R.drawable.fashionmen_trouser_n,R.drawable.fashionmen_trouser_p);
		    items[3]=new FashionItem("shorts",R.drawable.fashionmen_shorts_n,R.drawable.fashionmen_shorts_p);
		    items[4]=new FashionItem("skirt",R.drawable.fashionmen_acces_n,R.drawable.fashionmen_acces_p);
		    items[5]=new FashionItem("underware",R.drawable.fashionmen_under_n,R.drawable.fashionmen_under_p);
		    items[6]=new FashionItem("bikini",R.drawable.fashionmen_beachunder_n,R.drawable.fashionmen_beachunder_p);
		    items[7]=new FashionItem("shoes",R.drawable.fashionmen_shoe_n,R.drawable.fashionmen_shoe_p);
		    items[8]=new FashionItem("hat",R.drawable.fashionwomen_hat_n,R.drawable.fashionwomen_hat_p);
		}
	    view.setAdapter(new IconAdapter(getApplicationContext(),items));
	}

	class FashionItem {
		final String name;
		final int image;
		final int pimage;
		FashionItem(String name, int image,int pimage) {
		this.name = name;
		this.image = image;
		this.pimage = pimage;
		}
		}

	public class IconAdapter extends BaseAdapter {
		
		private FashionItem[] items;
		private Context context;
		
		IconAdapter(Context context,FashionItem... items) {
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
		        final FashionItem item = items[index];
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
		                    	if(item.name.equals("full") || item.name.equals("shirts") || item.name.equals("pants") || item.name.equals("shorts")){
		                    		PreferencesHelper.saveFashion(item.name);	   	    
		                    		PreferencesHelper.saveBrand("null");  
		                            startActivity(new Intent(Fashion.this,ProductList.class));               		
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