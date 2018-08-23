package com.apollosofttech.cards;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.apollosofttech.cards.survices.AppStatus;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

public class First extends Activity {
	  private static int SPLASH_TIME_OUT = 3000;
	TextView testtextView1,testtextView2;
	Button testbutton1;
	int i = 1;
	 Context context;
	  @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first);
		testtextView1 = (TextView) findViewById(R.id.testtextView1);
		testtextView2= (TextView) findViewById(R.id.testtextView2);
		testbutton1 = (Button) findViewById(R.id.testbutton1);
		context = this;
		View.OnClickListener handler = new View.OnClickListener(){
			public void onClick(View v) {

		        switch (v.getId()) {
		        
		        case R.id.testbutton1:
		        	if (AppStatus.getInstance(context).isOnline(context))
	                {
		        		new checkNet().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
	                }else{testtextView1.setText("No net");}
		        	break;
		        }
		    }
		};
		findViewById(R.id.testbutton1).setOnClickListener(handler);
		/*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(First.this, Login.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
                finish();
            }
        }, SPLASH_TIME_OUT);*/
	}
	  public class checkNet extends AsyncTask<Void, Void, Void>{
			private final HttpClient Client2 = new DefaultHttpClient();
	        private String Content2;
	        private String Error2 = null; 
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				testtextView1.setText(""+i);
				
			}
			@Override
			protected Void doInBackground(Void... params) {
				try{
					i++;
					 HttpGet httpget = new HttpGet("http://omeximcrop.com/Teenpatti/api/boot8/btesh.php?no=5");
		                ResponseHandler<String> responseHandler = new BasicResponseHandler();
		                Content2 = Client2.execute(httpget, responseHandler);
		            }
		            catch (ClientProtocolException clientprotocolexception)
		            {
		                Error2 = clientprotocolexception.getMessage();
		            }
		            catch (IOException ioexception)
		            {
		                Error2 = ioexception.getMessage();
		            }
				return null;
			}
			protected void onPostExecute(Void unused) {
				testtextView2.setText(Content2);
			}
			}
}
