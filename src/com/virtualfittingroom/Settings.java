package com.virtualfittingroom;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Settings extends Activity {

	public  String LOADER_IP     = null;
	public  String LOADER_PORT          = null;
	public  String GRABBER_IP  = null;
	public  String GRABBER_PORT         = null;

    public static String BASE_URL = "http://192.168.1.183:10040/virtualTailor/virtualTailor/grabber";


	ImageView register_okBtn = null;
    EditText loaderIP;
    EditText grabberIP;
    EditText grabberPort;
    EditText loaderPort;
	
   
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		Resources res=getResources();
			
		Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/Sansation-Light.ttf");
		
		TextView grabberIPLabel = (TextView)findViewById(R.id.grabberIPLabel);
		grabberIPLabel.setTypeface(tf);
		TextView loaderIPLabel = (TextView)findViewById(R.id.loaderIPLabel);
		loaderIPLabel.setTypeface(tf);
		TextView grabberPortLabel = (TextView)findViewById(R.id.grabberPortLabel);
		grabberPortLabel.setTypeface(tf);
		TextView loaderPortLabel = (TextView)findViewById(R.id.loaderPortLabel);
		loaderPortLabel.setTypeface(tf);
		
         loaderIP = (EditText)findViewById(R.id.loaderIP);
         loaderIP.setTypeface(tf);
         grabberIP   = (EditText)findViewById(R.id.grabberIP);
         grabberIP.setTypeface(tf);
         loaderPort    = (EditText)findViewById(R.id.loaderPort);
         loaderPort.setTypeface(tf);
         grabberPort= (EditText)findViewById(R.id.grabberPort);
         grabberPort.setTypeface(tf);

         if(PreferencesHelper.getSettingsState().equals("False")){
	         grabberIP.setText(res.getString(R.string.server_address_grabber));
	         grabberPort.setText(res.getString(R.string.server_port_grabber));
	         loaderIP.setText(res.getString(R.string.server_address_loader));
	         loaderPort.setText(res.getString(R.string.server_port_loader));
         }
         else{

             grabberIP.setText(PreferencesHelper.getSavedGrabberIP());
             grabberPort.setText(PreferencesHelper.getSavedGrabberPort());
             loaderIP.setText(PreferencesHelper.getSavedLoaderIP());
             loaderPort.setText(PreferencesHelper.getSavedLoaderPort());
         }
         
        register_okBtn = (ImageView)findViewById(R.id.okBtn);

        register_okBtn.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        register_okBtn.setImageResource(R.drawable.okbtn_p_button);
                        return true;
                    case MotionEvent.ACTION_CANCEL:
                        register_okBtn.setImageResource(R.drawable.okbtn_n_button);
                        return true;
                    case MotionEvent.ACTION_UP:
                        register_okBtn.setImageResource(R.drawable.okbtn_n_button);
                        if(CheckData())
                        {                        
                        	
                            PreferencesHelper.saveSettings(GRABBER_IP,LOADER_IP,GRABBER_PORT,LOADER_PORT);                            
                            PreferencesHelper.saveSettingsState("True");
                            Toast.makeText(getApplicationContext(), R.string.settings_saved, Toast.LENGTH_SHORT).show();
                            Intent login = new Intent(getBaseContext(),Login.class);
                            startActivity(login);
                            finish();
                        }
                        return true;
                    default:
                        return false;
                }
            }
        });
		
	}


	private boolean CheckData() {

		View vistaConFoco = null;
		boolean cancel = false;

		LOADER_IP     = loaderIP.getText().toString();
		GRABBER_IP         = grabberIP.getText().toString();
		LOADER_PORT          = loaderPort.getText().toString();
		GRABBER_PORT  = grabberPort.getText().toString();

		if (TextUtils.isEmpty(GRABBER_IP)) {
			
			Toast.makeText(Settings.this, R.string.empty_grabberIP,Toast.LENGTH_LONG).show();
			vistaConFoco = ((EditText) findViewById(R.id.grabberIP));
			cancel = true;
			
		}
		if (TextUtils.isEmpty(LOADER_IP)) {
			
			Toast.makeText(Settings.this, R.string.empty_loaderIP,Toast.LENGTH_LONG).show();
			vistaConFoco = ((EditText) findViewById(R.id.loaderIP));
			cancel = true;
			
		}else if (TextUtils.isEmpty(GRABBER_PORT)) {
			
			Toast.makeText(Settings.this, R.string.empty_grabberPort, Toast.LENGTH_LONG).show();
			vistaConFoco = ((EditText) findViewById(R.id.grabberPort));
			cancel = true;
			
		}else if (TextUtils.isEmpty(LOADER_PORT)) {
			
			Toast.makeText(Settings.this, R.string.empty_loaderPort, Toast.LENGTH_LONG).show();
			vistaConFoco = ((EditText) findViewById(R.id.loaderPort));
			cancel = true;
			
		}
		if (cancel) {
			vistaConFoco.requestFocus();
		}

		return !cancel;
	}



}
