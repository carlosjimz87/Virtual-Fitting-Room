package com.virtualfittingroom;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Message;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.Toast;

public class DataRecovery extends Activity {
    ComunicationServer comunicationServer;
    ComunicationServer measurementsServer;
    public static String address="";
    public static String port="";
    public static String namespace="";
    public static String service="";
    public static String BASE_URL = "http://192.168.1.183:10040/virtualTailor/virtualTailor/grabber";
    ImageView bgView;
    
    public boolean onKeyUp(int keyCode, KeyEvent event) {
    	if (keyCode == KeyEvent.KEYCODE_BACK) {
    		if(!comunicationServer.isCancelled())
    		comunicationServer.stop();
    		if(!measurementsServer.isCancelled())
    			measurementsServer.stop();
    	return true;
    	}
    	return super.onKeyUp(keyCode, event);
    	}
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
        setContentView(R.layout.scandatarecover);
        Resources res=getResources();
		address=PreferencesHelper.getSavedLoaderIP();
		port=PreferencesHelper.getSavedLoaderPort();
		namespace=res.getString(R.string.namespace);
		service=res.getString(R.string.service);
		
		bgView=(ImageView)findViewById(R.id.bgView);
		   if(PreferencesHelper.getSavedGender().equals("Female"))
	        	bgView.setImageDrawable(res.getDrawable(R.drawable.p_data_recovery_women));
	        else
	            bgView.setImageDrawable(res.getDrawable(R.drawable.p_data_recovery_men));
		
		BASE_URL="http://"+address+":"+port+"/"+namespace+"/"+service+"/sendCommand";
		
		StartServer(Constants.REQUEST_STATUS_COMMAND ,BASE_URL);        

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
            	PreferencesHelper.saveServiceStatus("StandBy");
    			Toast.makeText(getApplicationContext(), R.string.server_error, Toast.LENGTH_SHORT).show();	                   
                startActivity(new Intent(getBaseContext(),Login.class));
                comunicationServer.stop();
                finish();        		
    		break;
            
        	case Constants.READY_TO_LOAD_FILES:
            	PreferencesHelper.saveServiceStatus("StandBy");
        			Toast.makeText(getApplicationContext(), R.string.server_error, Toast.LENGTH_SHORT).show();	                   
                    startActivity(new Intent(getBaseContext(),Login.class));
                    comunicationServer.stop();
                    finish();        		
        		break;
        		
                case Constants.FINISHED_STATUS:
                	PreferencesHelper.saveServiceStatus("StandBy");
                   // descargandoDialog.dismiss();                	
                    comunicationServer.stop();
                    BASE_URL="http://"+address+":"+port+"/"+namespace+"/"+service+"/getmeasurements";
            		StartServerMeasurements(BASE_URL);            		
                    break;                
                    
                case Constants.UNDEFINED_STATUS:
                	PreferencesHelper.saveServiceStatus("StandBy");
                	Toast.makeText(getApplicationContext(), R.string.server_not_found, Toast.LENGTH_SHORT).show();
                    //descargandoDialog.dismiss();
                    comunicationServer.stop();
                    startActivity(new Intent(DataRecovery.this,Login.class));
                    finish();   
                    break;
                    
                case Constants.TIME_OUT:
                	PreferencesHelper.saveServiceStatus("StandBy");
                	Toast.makeText(getApplicationContext(), R.string.server_not_found, Toast.LENGTH_SHORT).show();
                    //descargandoDialog.dismiss();
                    comunicationServer.stop();
                    startActivity(new Intent(DataRecovery.this,Login.class));
                    finish();    
                    break;
                    
                case Constants.ERROR:
                	PreferencesHelper.saveServiceStatus("StandBy");
                	Toast.makeText(getApplicationContext(), R.string.server_error, Toast.LENGTH_SHORT).show();
                    //descargandoDialog.dismiss();
                    comunicationServer.stop();
                    startActivity(new Intent(DataRecovery.this,Login.class));
                    finish();    
                    break;
                    
                default :
                    	StartServer(Constants.REQUEST_STATUS_COMMAND ,BASE_URL);                    	
                    	break;
                        
               
            }

            }
        	}
            catch(Exception e){
            	Toast.makeText(getApplicationContext(), R.string.server_error, Toast.LENGTH_SHORT).show();
                //descargandoDialog.dismiss();
                comunicationServer.stop();   
                startActivity(new Intent(DataRecovery.this,Login.class));
                finish();  
            }
        }        
    };

    private void StartServerMeasurements(String baseURL){
     	 measurementsServer=null;
     	 measurementsServer = new ComunicationServer(mMessagesClassData);    
     	 measurementsServer.setBaseUrl(baseURL);
         measurementsServer.execute();     	
     } 
    
    MessagesClass mMessagesClassData = new MessagesClass(){
        public void handleMessage (Message m) {
        	try{
            if(!measurementsServer.isCancelled())
            {
	            Bundle e = new Bundle();
	            e = m.getData();
	           String medidas =(e.getString("msg"));
	           if(medidas.contains("M")){
	           PreferencesHelper.saveMeasures(medidas);	           
	           measurementsServer.stop();
	           //Toast.makeText(getApplicationContext(), medidas, Toast.LENGTH_SHORT).show();
	           startActivity(new Intent(DataRecovery.this,Select.class));
	           finish();
	           }
	           else if(medidas.contains("E")){
	           //StartServerMeasurements(BASE_URL);
	        	   measurementsServer.stop();
	        	   Toast.makeText(getApplicationContext(), R.string.server_error, Toast.LENGTH_SHORT).show();
		           startActivity(new Intent(DataRecovery.this,Login.class));
		           finish();
	           }
	           else{
	        	  
	           }
           }
        	}
            catch(Exception e){
            	Toast.makeText(getApplicationContext(), R.string.server_error, Toast.LENGTH_SHORT).show();
                //descargandoDialog.dismiss();
            	startActivity(new Intent(DataRecovery.this,Login.class));
                comunicationServer.stop();
                finish();
            }
        }
    };
    
    ImageView startButton;

    private void StartServer(int Command,String baseURL){
   	 comunicationServer=null;
		 comunicationServer = new ComunicationServer(mMessagesClass);
        comunicationServer.addVar("command" , Command+ "");      
        comunicationServer.setBaseUrl(baseURL);
        comunicationServer.execute();     	
   } 


}