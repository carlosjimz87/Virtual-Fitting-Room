package com.virtualfittingroom;

import java.util.Vector;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends Activity {

	public  String PARAM_USERNAME     = null;
	public  String PARAM_PIN          = "";
	public  String PARAM_CONFIRM_PIN  = null;
	public  String PARAM_NAME         = null;

	public static String address="";
    public static String port="";
    public static String namespace="";
    public static String service="";
    public static String BASE_URL = "http://192.168.1.183:10040/virtualTailor/virtualTailor/grabber";

    static PreferencesHelper PREFERENCES;
	private  RegisterTask RegTask = null;
     AlertDialog registrandoDialog ;
    boolean finishRequesting = false;
    int countRequest = 3;

	ImageView register_okBtn = null;
    EditText nombreEditText;
    EditText userEditText;
    EditText pinEditText;
    Spinner genderSpinner;
    EditText confirmEditText;
	
    DialogInterface.OnClickListener cancel_click = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialogInterface, int i) {
            registrandoDialog.dismiss();
            RegTask.cancel(true);
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
    	startActivity(new Intent(Register.this, Settings.class));   
    
    return super.onOptionsItemSelected(item);
    }
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		Resources res=getResources();
		address=PreferencesHelper.getSavedGrabberIP();
		port=PreferencesHelper.getSavedGrabberIP();
		namespace=res.getString(R.string.namespace);
		service=res.getString(R.string.service);
		
		BASE_URL="http://"+address+":"+port+"/"+namespace+"/"+service+"/sendCommand";
		

		Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/Sansation-Light.ttf");
				
		TextView nameLabel = (TextView)findViewById(R.id.nameLabel);
		nameLabel.setTypeface(tf);
		TextView genderLabel = (TextView)findViewById(R.id.genderLabel);
		genderLabel.setTypeface(tf);
		TextView userLabel = (TextView)findViewById(R.id.userLabel);
		userLabel.setTypeface(tf);
		TextView pinLabel = (TextView)findViewById(R.id.pinLabel);
		pinLabel.setTypeface(tf);
		TextView confirmLabel = (TextView)findViewById(R.id.confirmLabel);
		confirmLabel.setTypeface(tf);
		
         nombreEditText = (EditText)findViewById(R.id.nameEdit);
         nombreEditText.setTypeface(tf);
         userEditText   = (EditText)findViewById(R.id.userEdit);
         userEditText.setTypeface(tf);
         genderSpinner   = (Spinner)findViewById(R.id.genderSpinner);        
         pinEditText    = (EditText)findViewById(R.id.pinEdit);
         pinEditText.setTypeface(tf);
         confirmEditText= (EditText)findViewById(R.id.confirmEdit);
         confirmEditText.setTypeface(tf);

        Spinner spinner = (Spinner) findViewById(R.id.genderSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.gender_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

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
                        if(CheckCredec())
                        {                          

                            //finishRequesting = false;
                            PreferencesHelper.saveUserPassGender(userEditText.getText().toString(),pinEditText.getText().toString(),genderSpinner.getSelectedItem().toString());
                           
                            Intent login = new Intent(getBaseContext(),Login.class);
                            startActivity(login);
                            // Registrar();
                            //RequestUntilFinished.start();

                        }
                        return true;
                    default:
                        return false;
                }
            }
        });
		
	}


	private boolean CheckCredec() {

		View vistaConFoco = null;
		boolean cancel = false;

		PARAM_USERNAME     = userEditText.getText().toString();
		PARAM_NAME         = nombreEditText.getText().toString();
		PARAM_PIN          = pinEditText.getText().toString();
		PARAM_CONFIRM_PIN  = confirmEditText.getText().toString();

		if (TextUtils.isEmpty(PARAM_NAME)) {
			
			Toast.makeText(Register.this, R.string.empty_name,Toast.LENGTH_LONG).show();
			vistaConFoco = ((EditText) findViewById(R.id.nameEdit));
			cancel = true;
			
		} else if (PARAM_PIN.length() < 4) {
			
			Toast.makeText(Register.this, R.string.pin_corto, Toast.LENGTH_LONG).show();
			vistaConFoco = ((EditText) findViewById(R.id.pinEdit));
			cancel = true;
			
		} else if (TextUtils.isEmpty(PARAM_USERNAME)) {
			
			Toast.makeText(Register.this, R.string.empty_user, Toast.LENGTH_LONG).show();
			vistaConFoco = ((EditText) findViewById(R.id.userEdit));
			cancel = true;
			
		}else if (!PARAM_CONFIRM_PIN.equals(PARAM_PIN) ) {
			
			Toast.makeText(Register.this, R.string.pin_no_coincide,	Toast.LENGTH_LONG).show();
			vistaConFoco = findViewById(R.id.confirmEdit);
			cancel = true;
		}

		if (cancel) {
			vistaConFoco.requestFocus();
		}

		return !cancel;
	}

	public void Registrar() {

			RegTask = new RegisterTask();
			RegTask.execute();
		}



    public  boolean isNetworkAvailable() {
        ConnectivityManager cm;
        cm = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info=cm.getActiveNetworkInfo();
        return(info!=null); }

		 class RegisterTask extends AsyncTask<Void, Context, Boolean> {

		@Override
		protected Boolean doInBackground(Void... arg) {

			if (RegisterUsingGET())
				return true;
			else
				return false;

		}

        public Boolean RegisterUsingGET() {

            try {

                if(isNetworkAvailable()){

                    Vector<NameValuePair> vars = new Vector<NameValuePair>();

                    vars.add(new BasicNameValuePair("command", Constants.REQUEST_STATUS_COMMAND+""));
                    // vars.add(new BasicNameValuePair("HARDWARE_ID", "as"));

                    String url = BASE_URL + "?"
                            + URLEncodedUtils.format(vars, null);

                    HttpGet request = new HttpGet(url);
                    ResponseHandler<String> responseHandler = new BasicResponseHandler();
                    HttpClient client = new DefaultHttpClient();
                    String responseBody;
                    responseBody = client.execute(request, responseHandler);

                    responseBody = XMLHelper.returnXmlObject(responseBody)  ;

                    if (Integer.parseInt(responseBody) ==   Constants.READY_TO_CAPTURE_STATUS) {
                       // RequestUntilFinished.cancel();
                        return true;

                    } else if(Integer.parseInt(responseBody) ==   Constants.UNDEFINED_STATUS)
                    {
                        finishRequesting = true;
                        return false;
                    }

                }  else
                    Toast.makeText(Register.this,R.string.red_no_disponible, Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                e.printStackTrace();

                return false;
            }

            return false;

        }


		@Override
		protected void onPostExecute(final Boolean succes) {

            registrandoDialog.dismiss();

            if(succes)
            {
                finishRequesting = true;
            Intent login = new Intent(getBaseContext(),Login.class);
                login.putExtra("user",userEditText.getText().toString());
                login.putExtra("pin",pinEditText.getText().toString());
            startActivity(login);
                finish();
            }else
                Toast.makeText(getBaseContext(),R.string.register_error, Toast.LENGTH_LONG );

		}

	}

}
