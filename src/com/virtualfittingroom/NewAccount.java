package com.virtualfittingroom;


import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class NewAccount extends Activity {

    ImageView newAcc_yesBtn;
    ImageView newAcc_noBtn;
    public static String loaderIP="";
    public static String grabberIP="";
    public static String loaderPort="";
    public static String grabberPort="";
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.menu, menu);
    	return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    if(item.getTitle().toString().equals("Settings"))
    	startActivity(new Intent(NewAccount.this, Settings.class));   
    
    return super.onOptionsItemSelected(item);
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newaccount);
		Resources res=getResources();

        PreferencesHelper.initPrefs(getApplicationContext());
        grabberIP=res.getString(R.string.server_address_grabber);
		grabberPort=res.getString(R.string.server_port_grabber);
		loaderIP=res.getString(R.string.server_address_loader);
		loaderPort=res.getString(R.string.server_port_loader);

        PreferencesHelper.saveSettings(grabberIP, loaderIP, grabberPort, loaderPort);

        newAcc_yesBtn=(ImageView)findViewById(R.id.yesBtn);
        newAcc_noBtn=(ImageView)findViewById(R.id.noBtn);

        newAcc_yesBtn.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event)
            {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        newAcc_yesBtn.setImageResource(R.drawable.yesbtn_p_button);
                        return true;
                    case MotionEvent.ACTION_CANCEL:
                        newAcc_yesBtn.setImageResource(R.drawable.yesbtn_n_button);
                        return true;
                    case MotionEvent.ACTION_UP:
                        newAcc_yesBtn.setImageResource(R.drawable.yesbtn_n_button);
                        Intent intent = new Intent(NewAccount.this, Register.class);
                        startActivity(intent);
                        return true;
                    default:
                        return false;
                }
            }
        });

        newAcc_noBtn.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        newAcc_noBtn.setImageResource(R.drawable.nobtn_p_button);
                        return true;
                    case MotionEvent.ACTION_CANCEL:
                        newAcc_noBtn.setImageResource(R.drawable.nobtn_n_button);
                        return true;
                    case MotionEvent.ACTION_UP:
                        newAcc_noBtn.setImageResource(R.drawable.nobtn_n_button);
                        Intent intent = new Intent(NewAccount.this, Login.class);
                        startActivity(intent);
                        return true;
                    default:
                        return false;
                }
            }
        });
        
    }

}
