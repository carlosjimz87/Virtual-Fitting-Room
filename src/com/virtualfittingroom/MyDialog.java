package com.virtualfittingroom;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.Window;

public class MyDialog extends ProgressDialog {

	public MyDialog(Context context) {
	    super(context,R.style.Theme_Custom); 
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	}

}
