package com.virtualfittingroom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends Activity {
    /** Called when the activity is first created. */
	private final int DELAY_LENGHT = 1000;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        

        new Handler().postDelayed(new Runnable(){
            public void run() {
                try {
                    Intent mainIntent = new Intent(Splash.this,NewAccount.class);
                    ImagesLoader.LoadImages();
    				Thread.sleep(2000);
    				Splash.this.startActivity(mainIntent);
    				Splash.this.finish();
    			} catch (InterruptedException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
               
            }
        }, DELAY_LENGHT);
    }
}