package com.virtualfittingroom;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity {

    public  String HARDWARE_ID;
	public  String PARAM_PASSWORD = "password";
	public  String PARAM_USERNAME = "username";
    public  boolean SAVE_CREDENTIALS = false;
    public static String address="";
    public static String port="";
    public static String namespace="";
    public static String service="";
    public static String BASE_URL = "http://192.168.1.183:10040/virtualTailor/virtualTailor/grabber";

	 AlertDialog logeandoDialog ;
    CountDownTimer RequestUntilFinished;


    Boolean finishRequesting = false;

    int countRequest = 3;
	
	public ImageView login_okBtn;
	CheckBox saveCredentials;
	EditText userText;
	EditText passText;


    DialogInterface.OnClickListener cancel_click = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialogInterface, int i) {
            logeandoDialog.dismiss();
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.menu, menu);
    	return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    if(item.getTitle().toString().equals("Settings"))
    	startActivity(new Intent(Login.this, Settings.class));   
    
    return super.onOptionsItemSelected(item);
    }
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		Resources res=getResources();
		address=PreferencesHelper.getSavedGrabberIP();
		port=PreferencesHelper.getSavedGrabberIP();
		namespace=res.getString(R.string.namespace);
		service=res.getString(R.string.service);
		
		BASE_URL="http://"+address+":"+port+"/"+namespace+"/"+service+"/sendCommand";
		
		Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/Sansation-Light.ttf");

        TextView userLabel = (TextView) findViewById(R.id.userLabel);
        userLabel.setTypeface(tf);
        TextView pinLabel = (TextView) findViewById(R.id.pinLabel);
        pinLabel.setTypeface(tf); 
        login_okBtn = (ImageView) findViewById(R.id.okBtn);
		saveCredentials = (CheckBox) findViewById(R.id.saveCredentials);
		saveCredentials.setTypeface(tf);
		userText = (EditText) findViewById(R.id.userEdit);
		userText.setTypeface(tf);
		passText = (EditText) findViewById(R.id.pinEdit);
		passText.setTypeface(tf);
	
        if(getIntent().hasExtra("user"))
        {
            userText.setText(getIntent().getCharSequenceExtra("user"));
            passText.setText(getIntent().getCharSequenceExtra("pin"));
        }else
		LoadPreCredentials();


        login_okBtn.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event)
            {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        login_okBtn.setImageResource(R.drawable.okbtn_p_button);
                        return true;
                    case MotionEvent.ACTION_CANCEL:
                        login_okBtn.setImageResource(R.drawable.okbtn_n_button);
                        return true;
                    case MotionEvent.ACTION_UP:
                        login_okBtn.setImageResource(R.drawable.okbtn_n_button);
                    	if (CheckLogin())
                		{                      
                            startActivity(new Intent(getBaseContext(),StartScan.class));	
                		}    
                    	else
                    	{
                    		Toast.makeText(Login.this, "User not registered.", Toast.LENGTH_SHORT).show();
                    		
                    	}
                        return true;
                    default:
                        return false;
                }
            }
        });


	}

	private boolean CheckLogin() {
		String user=PreferencesHelper.getSavedUser().toString();
		String pass=PreferencesHelper.getSavedPass().toString();
		String userTxt=userText.getText().toString();
		String passTxt=passText.getText().toString();
		if(user.equals(userTxt) && pass.equals(passTxt) && userTxt.length()>0 && passTxt.length()>0)
			return true;
		else
			return false;
		
	}
	

	private void LoadPreCredentials() {

			userText.setText(PreferencesHelper.getSavedUser());
			passText.setText(PreferencesHelper.getSavedPass());
		
	}


    public  boolean isNetworkAvailable() {
        ConnectivityManager cm;
        cm = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info=cm.getActiveNetworkInfo();
        return(info!=null); 
        }


}
