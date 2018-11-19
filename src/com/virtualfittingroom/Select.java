package com.virtualfittingroom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class Select extends Activity {
	ImageView brandsBtn=null;
	ImageView fashionBtn=null;
   
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
    setContentView(R.layout.select);
    
    brandsBtn=(ImageView)findViewById(R.id.selectBrands);
    fashionBtn=(ImageView)findViewById(R.id.selectFashion);
    
    brandsBtn.setOnTouchListener(new View.OnTouchListener() {
        public boolean onTouch(View v, MotionEvent event)
        {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                	brandsBtn.setImageResource(R.drawable.b_brand_select_p);
                    return true;
                case MotionEvent.ACTION_CANCEL:
                	brandsBtn.setImageResource(R.drawable.b_brand_select_n);
                    return true;
                case MotionEvent.ACTION_UP:        
                	brandsBtn.setImageResource(R.drawable.b_brand_select_n);             
                    startActivity(new Intent(Select.this,Brands.class));                    
                    return true;
                default:
                    return false;
            }
        }
    });
    
    fashionBtn.setOnTouchListener(new View.OnTouchListener() {
        public boolean onTouch(View v, MotionEvent event)
        {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                	fashionBtn.setImageResource(R.drawable.b_fashion_select_p);
                    return true;
                case MotionEvent.ACTION_CANCEL:
                	fashionBtn.setImageResource(R.drawable.b_fashion_select_n);
                    return true;
                case MotionEvent.ACTION_UP:              
                	fashionBtn.setImageResource(R.drawable.b_fashion_select_n);       
                    startActivity(new Intent(Select.this,Fashion.class));                    
                    return true;
                default:
                    return false;
            }
            
        }
      });
}


}
