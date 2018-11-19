package com.virtualfittingroom;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Message;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class StartScan extends Activity {
    ComunicationServer comunicationServer;
    public static String address="";
    public static String port="";
    public static String namespace="";
    public static String service="";
    public static String BASE_URL = "http://192.168.1.183:10040/virtualTailor/virtualTailor/grabber";
    ImageView startBtn;
	ImageView bgView;
	ImageView cancelBtn;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.menu, menu);
    	return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    if(item.getTitle().toString().equals("Settings"))
    	startActivity(new Intent(StartScan.this, Settings.class));   
    
    return super.onOptionsItemSelected(item);
    }

   
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		
		super.onCreate(savedInstanceState);
        setContentView(R.layout.startscan);
        Resources res=getResources();
		address=PreferencesHelper.getSavedGrabberIP();
		port=PreferencesHelper.getSavedGrabberPort();
		namespace=res.getString(R.string.namespace);
		service=res.getString(R.string.service);
		
		BASE_URL="http://"+address+":"+port+"/"+namespace+"/"+service+"/sendCommand";
        startBtn=(ImageView)findViewById(R.id.startScan);
        bgView=(ImageView)findViewById(R.id.bgView);
        if(PreferencesHelper.getSavedGender().equals("Female"))
        	bgView.setImageDrawable(res.getDrawable(R.drawable.p_start_scan_women));
        else
            bgView.setImageDrawable(res.getDrawable(R.drawable.p_start_scan_men));
        
        startBtn.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event)
            {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    	startBtn.setImageResource(R.drawable.b_start_scan_p);
                        return true;
                    case MotionEvent.ACTION_CANCEL:
                    	startBtn.setImageResource(R.drawable.b_start_scan_n);
                        return true;
                    case MotionEvent.ACTION_UP:
                    	startBtn.setEnabled(false);
                    	startBtn.setImageResource(R.drawable.b_start_scan_n);
                    	StartServer(Constants.REQUEST_STATUS_COMMAND ,BASE_URL); 
                        StartDialog();
                    	startBtn.setEnabled(true);
                        return true;
                    default:
                        return false;
                }
            }
        });

        
 
}

    MessagesClass mMessagesClass = new MessagesClass(){
        public void handleMessage (Message m) {
        	try{
	            if(!comunicationServer.isCancelled())
	            {
	            Bundle e = new Bundle();
	            e = m.getData();
	            int returnCommand = Integer.parseInt(e.getString("msg"));
	
	            switch(returnCommand)
	            {
	                case Constants.WAITING_FOR_ORDER:
	                	if(PreferencesHelper.getSaveServiceStatus().equals("StandBy")){
	                	comunicationServer.stop();
	                	StartServer(Constants.START_CAPTURE_COMMAND ,BASE_URL);
	                	}
	                	else
	                	{
	                		Toast.makeText(getApplicationContext(), R.string.server_error, Toast.LENGTH_SHORT).show();
		                    if(descargandoDialog!=null)
	                		descargandoDialog.dismiss();
		                    Intent scanning = new Intent(getBaseContext(),Login.class);
		                    startActivity(scanning);
		                    comunicationServer.stop();		                    
	            		}	
	                    break;        
	
	                case Constants.CAPTURING_STATUS:
	                	PreferencesHelper.saveServiceStatus("StandBy");
	                	comunicationServer.stop();
	                	descargandoDialog.dismiss(); 
	                    Intent scanning = new Intent(getBaseContext(),Scanning.class);
	                    startActivity(scanning);
	                    finish();
	                    break;                            
	                    
	                case Constants.UNDEFINED_STATUS:
	                	PreferencesHelper.saveServiceStatus("StandBy");
	                	Toast.makeText(getApplicationContext(), R.string.server_error, Toast.LENGTH_SHORT).show();
	                    descargandoDialog.dismiss();
	                    comunicationServer.stop();
	                    break;
	
	                case Constants.TIME_OUT:
	                	PreferencesHelper.saveServiceStatus("StandBy");
	                	Toast.makeText(getApplicationContext(), R.string.server_not_found, Toast.LENGTH_SHORT).show();
	                    descargandoDialog.dismiss();
	                    comunicationServer.stop();
	                    break;
	                case Constants.ERROR:
	                	PreferencesHelper.saveServiceStatus("StandBy");
	                	Toast.makeText(getApplicationContext(), R.string.server_error, Toast.LENGTH_SHORT).show();
	                    descargandoDialog.dismiss();
	                    comunicationServer.stop();
	                    break;
	                default :
	                	StartServer(Constants.REQUEST_STATUS_COMMAND ,BASE_URL);         
	                		
	            }
	
	            }
        	}
	        catch(Exception e){
            	Toast.makeText(getApplicationContext(), R.string.server_error, Toast.LENGTH_SHORT).show();
                descargandoDialog.dismiss();
                comunicationServer.stop();         
                startActivity(new Intent(StartScan.this,Login.class));
                finish();  
	        }
	     
        }
    };


    ImageView startButton;
    ProgressDialog descargandoDialog ;


    private void StartServer(int Command,String baseURL){
    	comunicationServer=null;
    	 comunicationServer = new ComunicationServer(mMessagesClass);
    	 comunicationServer.addVar("command" , Command+ "");      
    	 comunicationServer.setBaseUrl(baseURL);
    	 comunicationServer.execute();     	
    }    
    
    void StartDialog(){
        try {

            descargandoDialog = new  ProgressDialog(StartScan.this); 
            descargandoDialog.show();
            descargandoDialog.setContentView(R.layout.dialog);
            
            
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }





}