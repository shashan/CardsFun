package com.apollosofttech.cards.survices;


import com.apollosofttech.cards.R;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;



public class ProgressLoader {
Context contextM;
Dialog progressDialog = null;


	   public Dialog displayProgressDialog(String toastMessage , Context context)
	    {
		contextM = context;
	
		try {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View layout = inflater.inflate(R.layout.dialogbox , null);
			
			// set a message
//			ImageView image = (ImageView) layout.findViewById(R.id.Toast_Image);
//			image.setVisibility(View.GONE);
			TextView text = (TextView) layout.findViewById(R.id.Toast_Message);
			text.setText(Html.fromHtml(toastMessage));
			text.setTextColor(Color.WHITE);

			progressDialog = new Dialog(context , R.style.DialogCustomTheme);
			progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			progressDialog.setContentView(layout);
			
			Window window = progressDialog.getWindow();
		    window.setGravity(Gravity.CENTER);
		    window.setLayout(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			progressDialog.show();
			progressDialog.setCanceledOnTouchOutside(false);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return progressDialog;
	}

	   
	   public Dialog displayProgressDialog(Boolean cancelable ,String toastMessage , Context context)
	    {
		contextM = context;
	
		try {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View layout = inflater.inflate(R.layout.dialogbox , null);
			
			TextView text = (TextView) layout.findViewById(R.id.Toast_Message);
//			ProgressBar  progress  = (ProgressBar) layout.findViewById(R.id.progressBar1);
//			progress.setVisibility(View.VISIBLE);
//			progress.setMax(100);
//			progress.setProgress(percentage);
			text.setText(Html.fromHtml(toastMessage));
			text.setTextColor(Color.WHITE);

			progressDialog = new Dialog(context , R.style.DialogCustomTheme);
			progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			progressDialog.setContentView(layout);
			
			Window window = progressDialog.getWindow();
		    window.setGravity(Gravity.CENTER);
		    window.setLayout(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			progressDialog.show();
			progressDialog.setCanceledOnTouchOutside(false);
			progressDialog.setCancelable(cancelable);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return progressDialog;
	    } 
	   
	   
	public Dialog dismiss(){
	  
		
	    try {
			progressDialog.dismiss();
			progressDialog.setCanceledOnTouchOutside(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return progressDialog;
	}
}
