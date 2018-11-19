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
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;

public class ComunicationServer extends AsyncTask<Object,Object,Object> {
	HttpClient 				client=null;
	ResponseHandler<String> responseHandler=null;
	HttpGet 				request =null;
    MessagesClass        	mMessagesClass;
    String                	BASE_URL;
    int                   	FINAL_COMMAND;
    String                	responseBody;
    Vector<NameValuePair> 	vars = new Vector<NameValuePair>();
    int                   	TIMEOUT_VALUE=30000;

  public void setBaseUrl(String url)
  {
      BASE_URL = url;
  }

  public void addVar(String key , String value)
  {
      vars.add(new BasicNameValuePair(key, value));
  }

   public void setFinalCommand(int command)
   {
       FINAL_COMMAND = command;
   }

    public void stop()
    {
        try {
        if(request!=null)
        	request.abort();        	
          cancel(true);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }


    private void sendMessageToExternalHandler(String msg)
    {
        Message m =mMessagesClass.obtainMessage();
        Bundle e = new Bundle();
        e.putString("msg",msg);
        m.setData(e);
        mMessagesClass.sendMessage(m);
    }

    ComunicationServer(MessagesClass MessagesClass)
    {

        mMessagesClass =  MessagesClass;
    }


    private void comunicar() {

               try {


                if(isNetworkAvailable()){


                    if(vars !=null &&  !vars.isEmpty() )
                        BASE_URL = BASE_URL + "?" + URLEncodedUtils.format(vars, null);

                    HttpGet request = new HttpGet(BASE_URL);

                    responseHandler = new BasicResponseHandler();

                    HttpParams httpParameters = new BasicHttpParams();
                    HttpConnectionParams.setConnectionTimeout(httpParameters,TIMEOUT_VALUE);
                    HttpConnectionParams.setSoTimeout(httpParameters, TIMEOUT_VALUE);
                    
                    client = new DefaultHttpClient(httpParameters);

                    responseBody = client.execute(request, responseHandler);

                    responseBody = XMLHelper.returnXmlObject(responseBody)  ;

                    if (Integer.parseInt(responseBody) == FINAL_COMMAND) {
                        if(!isCancelled())
                        {
                        sendMessageToExternalHandler(Constants.ERROR+"");
                        this.stop();
                        }
                    }

                } // else
                   // Toast.makeText(Start3dScannerActivity.this, R.string.red_no_disponible, Toast.LENGTH_SHORT).show();

            } catch (Exception ex) {
                ex.printStackTrace();
                if(!isCancelled())
                sendMessageToExternalHandler(Constants.ERROR+"");
            }


      //  }

    }



    public  boolean isNetworkAvailable() {
     //   ConnectivityManager cm;
       // cm = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
       // NetworkInfo info=cm.getActiveNetworkInfo();
      //  return(info!=null);
        return true;
    }


    @Override
    protected Object doInBackground(Object... objects) {
        comunicar();
        return true;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        sendMessageToExternalHandler(responseBody);
    }
}
