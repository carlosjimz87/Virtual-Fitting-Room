package com.virtualfittingroom;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Message;
import android.widget.ImageView;
import android.widget.Toast;

public class Scanning extends Activity {
    ComunicationServer comunicationServer;
    ComunicationServer measurementsServer;
    private static String address="";
    private static String port="";
    private static String namespace="";
    private static String service="";
    private Resources res=null;
    private static String BASE_URL = "http://192.168.1.183:10040/virtualTailor/virtualTailor/grabber";
	ImageView bgView;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
        setContentView(R.layout.scanning);
        res= getResources();
		address=PreferencesHelper.getSavedLoaderIP();
		port=PreferencesHelper.getSavedLoaderPort();
		namespace=res.getString(R.string.namespace);
		service=res.getString(R.string.service);

		bgView=(ImageView)findViewById(R.id.bgView);
		   if(PreferencesHelper.getSavedGender().equals("Female"))
	        	bgView.setImageDrawable(res.getDrawable(R.drawable.p_scanning_women));
	        else
	            bgView.setImageDrawable(res.getDrawable(R.drawable.p_scanning_men));
		
		BASE_URL="http://"+address+":"+port+"/"+namespace+"/"+service+"/sendCommand";
		
		StartServer(Constants.REQUEST_STATUS_COMMAND ,BASE_URL);        
       // StartDialog();
		
}


    MessagesClass mMessagesClassData = new MessagesClass(){
        public void handleMessage (Message m) {
        	try{
            if(!measurementsServer.isCancelled())
            {
	            Bundle e = new Bundle();
	            e = m.getData();
	           String medidas =(e.getString("msg"));
	           if(medidas.contains("*M")){
	           PreferencesHelper.saveMeasures(medidas);	           
	           measurementsServer.stop();
	          // Toast.makeText(getApplicationContext(), medidas, Toast.LENGTH_SHORT).show();
	           startActivity(new Intent(Scanning.this,Select.class));
	           finish();
	           }
	           else if(medidas.contains("E")){
	        	   measurementsServer.stop();
	        	   Toast.makeText(getApplicationContext(), R.string.server_error, Toast.LENGTH_SHORT).show();
		           startActivity(new Intent(Scanning.this,Login.class));
		           finish();
	           }
           }
        	}
            catch(Exception e){
            	Toast.makeText(getApplicationContext(), R.string.server_error, Toast.LENGTH_SHORT).show();
                //descargandoDialog.dismiss();
            	startActivity(new Intent(Scanning.this,Login.class));
            	measurementsServer.stop();
                finish();
            }
        }
    };
    
	
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
            case Constants.MEASURING_STATUS:
            	PreferencesHelper.saveServiceStatus("Operating");
            	StartServer(Constants.REQUEST_STATUS_COMMAND ,BASE_URL);    
            	break;
            
            case Constants.FINISHED_STATUS:
            	if(PreferencesHelper.getSaveServiceStatus().equals("Operating")){
            		PreferencesHelper.saveServiceStatus("StandBy");
                    // descargandoDialog.dismiss();                	
                     comunicationServer.stop();
                     BASE_URL="http://"+address+":"+port+"/"+namespace+"/"+service+"/getmeasurements";
             		StartServerMeasurements(BASE_URL);
        		}
            	else
            	{
                	PreferencesHelper.saveServiceStatus("StandBy");
                	StartServer(Constants.REQUEST_STATUS_COMMAND ,BASE_URL);   
        		}
                break;
        
            
            
            /*
            case Constants.WAITING_FOR_ORDER:
            	PreferencesHelper.saveServiceStatus("StandBy");
                	comunicationServer.stop();
            		Toast.makeText(getApplicationContext(), R.string.server_error, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getBaseContext(),Login.class));		                    
                    finish();
                break; 
                
            	case Constants.SENDING_DAR_FILES_STATUS:
            		PreferencesHelper.saveServiceStatus("StandBy");
            		res=getResources();
            		address=PreferencesHelper.getSavedLoaderIP();
            		port=PreferencesHelper.getSavedLoaderPort();
                    BASE_URL="http://"+address+":"+port+"/"+namespace+"/"+service+"/sendCommand";
            		StartServer(Constants.REQUEST_STATUS_COMMAND ,BASE_URL);  
            		break;
            		
            	case Constants.READY_TO_LOAD_FILES:
            		if(PreferencesHelper.getSaveServiceStatus().equals("StandBy")){
            		PreferencesHelper.saveSettingsState("Operating");
	                StartServer(Constants.REQUEST_STATUS_COMMAND ,BASE_URL);
            		}
                	else
                	{
                		Toast.makeText(getApplicationContext(), R.string.server_error, Toast.LENGTH_SHORT).show();	                   
	                    startActivity(new Intent(getBaseContext(),Login.class));
	                    comunicationServer.stop();
	                    finish();
            		}
            		break;
            		            		
                case Constants.RECONSTRUCTING_UPPER_STATUS:
            		if(PreferencesHelper.getSaveServiceStatus().equals("StandBy")){
                    //descargandoDialog.dismiss();
                	PreferencesHelper.saveSettingsState("Operating");
                    comunicationServer.stop();                                		
                    startActivity(new Intent(Scanning.this,DataRecovery.class));
                    finish();
            		}
                	else
                	{
                		Toast.makeText(getApplicationContext(), R.string.server_error, Toast.LENGTH_SHORT).show();	                   
	                    startActivity(new Intent(getBaseContext(),Login.class));
	                    comunicationServer.stop();
	                    finish();
            		}
                    break;
                    
                case Constants.RECONSTRUCTING_LOWER_STATUS:
                    //descargandoDialog.dismiss();
                    comunicationServer.stop();
                    startActivity(new Intent(Scanning.this,DataRecovery.class));
                    finish();
                    break;
                    */
                case Constants.UNDEFINED_STATUS:
                	PreferencesHelper.saveServiceStatus("StandBy");
                	Toast.makeText(getApplicationContext(), R.string.server_error, Toast.LENGTH_SHORT).show();
                   // descargandoDialog.dismiss();
                    comunicationServer.stop();
                    startActivity(new Intent(Scanning.this,Login.class));
                    finish();              
                    break;
                    
                case Constants.TIME_OUT:
                	PreferencesHelper.saveServiceStatus("StandBy");
                	Toast.makeText(getApplicationContext(), R.string.server_not_found, Toast.LENGTH_SHORT).show();
                    //descargandoDialog.dismiss();
                    comunicationServer.stop();
                    startActivity(new Intent(Scanning.this,Login.class));
                    finish();    
                    break;
                    
                case Constants.ERROR:
                	PreferencesHelper.saveServiceStatus("StandBy");
                	Toast.makeText(getApplicationContext(), R.string.server_error, Toast.LENGTH_SHORT).show();
                    //descargandoDialog.dismiss();
                    comunicationServer.stop();
                    startActivity(new Intent(Scanning.this,Login.class));
                    finish();    
                    break;
                default :
                	StartServer(Constants.REQUEST_STATUS_COMMAND ,BASE_URL);    
               
            }


        }
            
        }
        catch(Exception e){
        	Toast.makeText(getApplicationContext(), R.string.server_error, Toast.LENGTH_SHORT).show();
            //descargandoDialog.dismiss();
            comunicationServer.stop();      
            startActivity(new Intent(Scanning.this,Login.class));
            finish();  
        }
        }
    };

    ImageView startButton;
   // AlertDialog descargandoDialog ;

    private void StartServer(int Command,String baseURL){
   	 comunicationServer=null;
		 comunicationServer = new ComunicationServer(mMessagesClass);
        comunicationServer.addVar("command" , Command+ "");      
        comunicationServer.setBaseUrl(baseURL);
        comunicationServer.execute();     	
   } 
    
    private void StartServerMeasurements(String baseURL){
    	 measurementsServer=null;
    	 measurementsServer = new ComunicationServer(mMessagesClassData);    
    	 measurementsServer.setBaseUrl(baseURL);
        measurementsServer.execute();     	
    } 
/*
    DialogInterface.OnClickListener cancel_click = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialogInterface, int i) {
            //   descargandoDialog.dismiss();
            //descargandoDialog.dismiss();
            comunicationServer.stop();
            Intent login = new Intent(getBaseContext(),Scanning.class);
            startActivity(login);
            finish();
        }
    };*/
/*
    void StartDialog(){
        try {

            descargandoDialog = new  ProgressDialog(Scanning.this);
            descargandoDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel" , cancel_click);
            descargandoDialog.setTitle(R.string.operacion_curso);
            descargandoDialog.setMessage("Espere");
            descargandoDialog.show();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }*/





}