package com.apollosofttech.cards;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apollosofttech.cards.app.Constans;
import com.apollosofttech.cards.survices.AppStatus;
import com.apollosofttech.cards.survices.JSONfunctions;
import com.apollosofttech.cards.survices.ProgressLoader;
import com.triggertrap.seekarc.SeekArc;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {
	TextView testText1,testText2,polLimitTxt,maxBetTxt;
	
	ProgressLoader pd;
	Toast toast;
	int tipAmountInt = 5;
	String tipUserConstant = "";
String tableId,userName;
String bootAmount;
int bootIntAmount;
Context context;
ImageView dealer,imageView1,imageView2,imageView3,imageView4,imageView5;
String apicard[] = {
        "BA", "B2", "B3", "B4", "B5", "B6", "B7", "B8", "B9", "B10", "BJ", "BQ", "BK", 
        "CA", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "C10", "CJ", "CQ", "CK",
        "FA", "F2", "F3", "F4", "F5", "F6", "F7", "F8", "F9", "F10", "FJ", "FQ", "FK",
        "LA", "L2", "L3", "L4", "L5", "L6", "L7", "L8", "L9", "L10", "LJ", "LQ", "LK"
    };
/*int dgj = "";*/
/*ExternalNewClass externalnewclass;
ChaalClass chaalclass;
CardSeeClass cardseeclass;
NewRoundClass newroundclass;
PackNewClass packnewclass;
ShowClass showclass;
StatusUpdateClass statusupdateclass;
TimeOutClass timeoutclass;
SideShowClass sideshowclass;
SideShowRejectResponseClass sideshowrejectresponseclass;
SideShowAcceptResponseClass sideshowacceptresponseclass;
SideShowAfterAcceptResponseClass sideshowafteracceptresponseclass;*/


Integer drawableCard[] = {R.drawable.b1,R.drawable.b2,R.drawable.b3,R.drawable.b4,R.drawable.b5,R.drawable.b6,R.drawable.b7,R.drawable.b8,R.drawable.b9,R.drawable.b10,R.drawable.b11,R.drawable.b12,R.drawable.b13
		,R.drawable.c1,R.drawable.c2,R.drawable.c3,R.drawable.c4,R.drawable.c5,R.drawable.c6,R.drawable.c7,R.drawable.c8,R.drawable.c9,R.drawable.c10,R.drawable.c11,R.drawable.c12,R.drawable.c13
		,R.drawable.f1,R.drawable.f2,R.drawable.f3,R.drawable.f4,R.drawable.f5,R.drawable.f6,R.drawable.f7,R.drawable.f8,R.drawable.f9,R.drawable.f10,R.drawable.f11,R.drawable.f12,R.drawable.f13
		,R.drawable.l1,R.drawable.l2,R.drawable.l3,R.drawable.l4,R.drawable.l5,R.drawable.l6,R.drawable.l7,R.drawable.l8,R.drawable.l9,R.drawable.l10,R.drawable.l11,R.drawable.l12,R.drawable.l13};
public int it1;
Timer t;
JSONArray jsonarray;
JSONObject jsonobject;
TextView coin1,coin2,coin3,coin4,coin5,coinall,
myname,user5name,user5amount,user4name,user4amount,user3name,user3amount,user2name,user2amount,user1name,user1amount, target_amount;
public String t0a,t1a,t3a,t4a,t5a,t6a,t7a,t8a,t9a;
public int t2a,t10a,t11a;
public String chal,status,table_amount,LastChal,winner,previousplayer,tipUser;
int tipAmount;
public String p1a,p2a,p3a,p4a,p5a,p1game,p2game,p3game,p4game,p5game;
ImageView user1,user2,user3,user4,user5;
LinearLayout user1layout,user2layout,user3layout,user4layout,user5layout,betLayout;
ImageView card11,card12,card13,
card21,card22,card23,
card31,card32,card33,
card41,card42,card43,
card51,card52,card53;
public int weight1,weight2,weight3,weight4,weight5;
public int amount1,amount2,amount3,amount4,amount5;
public int balance1,balance2,balance3,balance4,balance5;
public String t21a,t22a,t23a,t31a,t32a,t33a,t41a,t42a,t43a,t51a,t52a,t53a,t61a,t62a,t63a;
public String gameStatus = "";
public View userview1,userview2,userview3,userview4,userview5;
public Button cardSee,chaal,doubleAmount,packthegame,showBtn,sideshowBtn,singleAmount;
MediaPlayer chipssound,packsound,turnsound,winsound;
MediaPlayer dealsound,dealsound1,dealsound2,dealsound3,dealsound4;
protected int doubler = 1,seedoubler = 1;
public int status1,status2,status3,status4,status5;
public int cardstatus1,cardstatus2,cardstatus3,cardstatus4,cardstatus5;
public String p1status,p2status,p3status,p4status,p5status;
String BLIND = "Blind",CARDSEEN = "Seen",CHAAL = "Chaal",PACK = "Pack",TIMEOUT = "TimeOut",SIDESHOW = "Side",ACCEPT = "Accept",REJECT ="Regect";
Boolean myChaal = true;
public String lastchaalconstant="",currantchaalconstant="";
CountDownTimer tiktik30,tiktikfirst5,tiktiklast5;
private SeekArc mSeekArc;
int i = 0;
int card11location,card12location,card13location,card21location,card22location,card23location,card31location,card32location,card33location,card41location,card42location,card43location,card51location,card52location,card53location;
public RelativeLayout cardLoaderLayout;
public boolean tiktikfirst5Start = true;
public boolean sideshowrequest = true;
public String slidshowUser ="";


@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
    	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		testText1 = (TextView) findViewById(R.id.testText1);
		testText2 = (TextView) findViewById(R.id.testText2);
		polLimitTxt = (TextView) findViewById(R.id.polLimitTxt);
		maxBetTxt = (TextView) findViewById(R.id.maxBetTxt);
		context = this;
		pd = new ProgressLoader();
		 Bundle extras = getIntent().getExtras();
		    if (extras != null)
		    {tableId = extras.getString("tbl_id");
		    userName = extras.getString("user_Teen");
		    bootIntAmount = extras.getInt("boot_amount");
		    polLimitTxt.setText(" "+bootIntAmount*128);
		    maxBetTxt.setText(" "+bootIntAmount*2048);}
		    coin1 = (TextView) findViewById(R.id.coin1);coin1.setVisibility(View.INVISIBLE);
		    coin2 = (TextView) findViewById(R.id.coin2);coin2.setVisibility(View.INVISIBLE);
		    coin3 = (TextView) findViewById(R.id.coin3);coin3.setVisibility(View.INVISIBLE);
		    coin4 = (TextView) findViewById(R.id.coin4);coin4.setVisibility(View.INVISIBLE);
		    coin5 = (TextView) findViewById(R.id.coin5);coin5.setVisibility(View.INVISIBLE);
		    coinall = (TextView) findViewById(R.id.coinall);
		    myname = (TextView) findViewById(R.id.myname);
		    user5name = (TextView) findViewById(R.id.user5name);
		    user5amount = (TextView) findViewById(R.id.user5amount);
		    user4name = (TextView) findViewById(R.id.user4name);
		    user4amount = (TextView) findViewById(R.id.user4amount);
		    user3name = (TextView) findViewById(R.id.user3name);
		    user3amount = (TextView) findViewById(R.id.user3amount);
		    user2name = (TextView) findViewById(R.id.user2name);
		    user2amount = (TextView) findViewById(R.id.user2amount);
		    user1name = (TextView) findViewById(R.id.user1name);
		    user1amount = (TextView) findViewById(R.id.user1amount);
		    target_amount = (TextView) findViewById(R.id.target_amount);
		    user1layout = (LinearLayout) findViewById(R.id.user1layout);user1layout.setVisibility(View.INVISIBLE);
		    user2layout = (LinearLayout) findViewById(R.id.user2layout);user2layout.setVisibility(View.INVISIBLE);
		    user3layout = (LinearLayout) findViewById(R.id.user3layout);user3layout.setVisibility(View.INVISIBLE);
		    user4layout = (LinearLayout) findViewById(R.id.user4layout);user4layout.setVisibility(View.INVISIBLE);
		    user5layout = (LinearLayout) findViewById(R.id.user5layout);user5layout.setVisibility(View.INVISIBLE);
		    betLayout = (LinearLayout) findViewById(R.id.betLayout);betLayout.setVisibility(View.INVISIBLE);
		    card11 = (ImageView) findViewById(R.id.card1);card12 = (ImageView) findViewById(R.id.card6);card13 = (ImageView) findViewById(R.id.card11);
		    card21 = (ImageView) findViewById(R.id.card2);card22 = (ImageView) findViewById(R.id.card7);card23 = (ImageView) findViewById(R.id.card12);
		    card31 = (ImageView) findViewById(R.id.card3);card32 = (ImageView) findViewById(R.id.card8);card33 = (ImageView) findViewById(R.id.card13);
		    card41 = (ImageView) findViewById(R.id.card4);card42 = (ImageView) findViewById(R.id.card9);card43 = (ImageView) findViewById(R.id.card14);
		    card51 = (ImageView) findViewById(R.id.card5);card52 = (ImageView) findViewById(R.id.card10);card53 = (ImageView) findViewById(R.id.card15);
		    dealer = (ImageView) findViewById(R.id.dealer);dealer.setVisibility(View.VISIBLE);
		    imageView1 = (ImageView) findViewById(R.id.imageView1);
		    imageView2 = (ImageView) findViewById(R.id.imageView2);
		    imageView3 = (ImageView) findViewById(R.id.imageView3);
		    imageView4 = (ImageView) findViewById(R.id.imageView4);
		    imageView5 = (ImageView) findViewById(R.id.imageView5);
		    userview1 = findViewById(R.id.userview1);userview2 = findViewById(R.id.userview2);userview3 = findViewById(R.id.userview3);userview4 = findViewById(R.id.userview4);userview5 = findViewById(R.id.userview5);
		    cardSee = (Button) findViewById(R.id.cardSee);cardSee.setVisibility(View.INVISIBLE);
		    chaal = (Button) findViewById(R.id.chaal);
		    doubleAmount = (Button) findViewById(R.id.doubleAmount);
		    packthegame = (Button) findViewById(R.id.packthegame);
		    showBtn = (Button) findViewById(R.id.showBtn);
		    sideshowBtn = (Button) findViewById(R.id.sideshowBtn);
		    sideshowBtn.setVisibility(View.INVISIBLE);
		    singleAmount = (Button) findViewById(R.id.singleAmount);
		    chipssound = MediaPlayer.create(this, R.raw.chips_to_table);
		    packsound = MediaPlayer.create(this, R.raw.fold);
		    turnsound = MediaPlayer.create(this, R.raw.my_turn_sound);
		    winsound = MediaPlayer.create(this, R.raw.win_game_sound);
		    dealsound = MediaPlayer.create(this, R.raw.deal);
		    dealsound1 = MediaPlayer.create(this, R.raw.deal);
		    dealsound2 = MediaPlayer.create(this, R.raw.deal);
		    dealsound3 = MediaPlayer.create(this, R.raw.deal);
		    dealsound4 = MediaPlayer.create(this, R.raw.deal);
		    mSeekArc = (SeekArc) findViewById(R.id.seekArc);
		    cardLoaderLayout = (RelativeLayout) findViewById(R.id.cardLoaderLayout);
		    TranslateAnimation trans = new TranslateAnimation(0, 0, 0, 0);
		    trans.setDuration(1000);
		    trans.setFillAfter(true);
		    dealer.startAnimation(trans);
		    View.OnClickListener handler = new View.OnClickListener(){
				public void onClick(View v) {

			        switch (v.getId()) {
			        
			        case R.id.cardSee:
			        	new CardSeeClass().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);;
			        	break;
			        case R.id.chaal:
			        	new ChaalClass().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
			        	break;
			        case R.id.doubleAmount:
			        	doubler = 2;
	                    doubleAmount.setEnabled(false);
	                    singleAmount.setEnabled(true); 
			        	break;
			        case R.id.packthegame:
			        	new PackNewClass().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
			        	break;
			        case R.id.showBtn:
			        	
			        	new ShowClass().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
			        	break;
			        case R.id.sideshowBtn:
			        	new SideShowClass().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);;
			        	break;
			        case R.id.singleAmount:
			        	doubler = 1;
	                    singleAmount.setEnabled(false);
	                    doubleAmount.setEnabled(true);
			        	break;
			        case R.id.dealer:
			        	
			        	/*if (chaalclass != null && chaalclass.getStatus() != AsyncTask.Status.FINISHED){
			        		chaalclass.cancel(true);}*/
			        	final Dialog dialogTip = new Dialog(context,R.style.PauseDialog);
			            dialogTip.requestWindowFeature(Window.FEATURE_NO_TITLE);
			            dialogTip.setContentView(R.layout.tip_dilog);
			            dialogTip.show();
			            tipAmountInt = 5;
			            final TextView tip_amount = (TextView) dialogTip.findViewById(R.id.tip_amount);
			            ((Button)dialogTip.findViewById(R.id.TipCancleBtn)).setOnClickListener(new View.OnClickListener() {
			                public void onClick(View view)
			                {
			                    dialogTip.dismiss();
			                }
			            });
			            ((Button)dialogTip.findViewById(R.id.singleTip)).setOnClickListener(new View.OnClickListener() {
			                public void onClick(View view)
			                {
			                	if(tipAmountInt!=5){
			                		tipAmountInt=tipAmountInt-5;
			                	tip_amount.setText(""+tipAmountInt);}
			                }
			            });
			            ((Button)dialogTip.findViewById(R.id.doubleTip)).setOnClickListener(new View.OnClickListener() {
			                public void onClick(View view)
			                {
			                	tipAmountInt=tipAmountInt+5;
			                	tip_amount.setText(""+tipAmountInt);
			                }
			            });
			            ((Button)dialogTip.findViewById(R.id.TipcontinueBtn)).setOnClickListener(new View.OnClickListener() {
			                public void onClick(View view)
			                {
			                	new SendTipClass().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
			                	moveToOneAnother(coin3, mSeekArc, 0, 2000, 0,false);
			                    coin3.setText(""+tipAmountInt);
			                	dialogTip.dismiss();
			                }
			            });
			        	break;
			        }
			    }
			};
			findViewById(R.id.cardSee).setOnClickListener(handler);
			findViewById(R.id.chaal).setOnClickListener(handler);
			findViewById(R.id.doubleAmount).setOnClickListener(handler);
			findViewById(R.id.packthegame).setOnClickListener(handler);
			findViewById(R.id.showBtn).setOnClickListener(handler);
			findViewById(R.id.sideshowBtn).setOnClickListener(handler);
			findViewById(R.id.singleAmount).setOnClickListener(handler);
			findViewById(R.id.dealer).setOnClickListener(handler);
			 tiktik30 = new CountDownTimer(31000, 1000) {
		            public void onFinish()
		            {
		            	new TimeOutClass().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,currantchaalconstant);
		            }
		            public void onTick(long l)
		            {
		                mSeekArc.setProgress(i);
		                i = 1 + i;
		            }
		        };
}
private void start()
{coinall.clearAnimation();
if (!p1game.equals(""))
{
	
    movechaalToScreenCenter(coin1);
    coin1.setText(bootAmount);
    card11.setBackgroundResource(R.drawable.cardback);
    card12.setBackgroundResource(R.drawable.cardback);
    card13.setBackgroundResource(R.drawable.cardback);
    moveToOneAnother(card11, userview1, 1200, 200, -30,true);
    moveToOneAnother(card12, userview1, 2200, 200, 0,true);
    moveToOneAnother(card13, userview1, 3200, 200, 30,true);
}
if (!p2game.equals(""))
{
    movechaalToScreenCenter(coin2);
    coin2.setText(bootAmount);
    card21.setBackgroundResource(R.drawable.cardback);
    card22.setBackgroundResource(R.drawable.cardback);
    card23.setBackgroundResource(R.drawable.cardback);
    moveToOneAnother(card21, userview2, 1400, 200, -30,true);
    moveToOneAnother(card22, userview2, 2400, 200, 0,true);
    moveToOneAnother(card23, userview2, 3400, 200, 30,true);
}
if (!p3game.equals(""))
{
    movechaalToScreenCenter(coin3);
    coin3.setText(bootAmount);
    card31.setBackgroundResource(R.drawable.cardback);
    card32.setBackgroundResource(R.drawable.cardback);
    card33.setBackgroundResource(R.drawable.cardback);
    moveToOneAnother(card31, userview3, 1600, 200, -30,true);
    moveToOneAnother(card32, userview3, 2600, 200, 0,true);
    moveToOneAnother(card33, userview3, 3600, 200, 30,true);
    cardSee.setVisibility(View.VISIBLE);
}
if (!p4game.equals(""))
{
    movechaalToScreenCenter(coin4);
    coin4.setText(bootAmount);
    card41.setBackgroundResource(R.drawable.cardback);
    card42.setBackgroundResource(R.drawable.cardback);
    card43.setBackgroundResource(R.drawable.cardback);
    moveToOneAnother(card41, userview4, 1800, 200, -30,true);
    moveToOneAnother(card42, userview4, 2800, 200, 0,true);
    moveToOneAnother(card43, userview4, 3800, 200, 30,true);
}
if (!p5game.equals(""))
{
    movechaalToScreenCenter(coin5);
    coin5.setText(bootAmount);
    card51.setBackgroundResource(R.drawable.cardback);
    card52.setBackgroundResource(R.drawable.cardback);
    card53.setBackgroundResource(R.drawable.cardback);
    moveToOneAnother(card51, userview5, 2000, 200, -30,true);
    moveToOneAnother(card52, userview5, 3000, 200, 0,true);
    moveToOneAnother(card53, userview5, 4000, 200, 30,true);
}
}
private void alreadyStart(){
	coinall.clearAnimation();
	if (!p1game.equals(""))
	{
	   // movechaalToScreenCenter(coin1);
	    //coin1.setText(bootAmount);
	    card11.setBackgroundResource(R.drawable.cardback);
	    card12.setBackgroundResource(R.drawable.cardback);
	    card13.setBackgroundResource(R.drawable.cardback);
	    moveToOneAnother(card11, userview1, 0, 0, -30,true);
	    moveToOneAnother(card12, userview1, 0, 0, 0,true);
	    moveToOneAnother(card13, userview1, 0, 0, 30,true);
	}
	if (!p2game.equals(""))
	{
	   // movechaalToScreenCenter(coin2);
	    //coin2.setText(bootAmount);
	    card21.setBackgroundResource(R.drawable.cardback);
	    card22.setBackgroundResource(R.drawable.cardback);
	    card23.setBackgroundResource(R.drawable.cardback);
	    moveToOneAnother(card21, userview2, 0, 0, -30,true);
	    moveToOneAnother(card22, userview2, 0, 0, 0,true);
	    moveToOneAnother(card23, userview2, 0, 0, 30,true);
	}
	if (!p3game.equals(""))
	{
	   // movechaalToScreenCenter(coin3);
	    //coin3.setText(bootAmount);
	    card31.setBackgroundResource(R.drawable.cardback);
	    card32.setBackgroundResource(R.drawable.cardback);
	    card33.setBackgroundResource(R.drawable.cardback);
	    moveToOneAnother(card31, userview3, 0, 0, -30,true);
	    moveToOneAnother(card32, userview3, 0, 0, 0,true);
	    moveToOneAnother(card33, userview3, 0, 0, 30,true);
	    cardSee.setVisibility(View.VISIBLE);
	}
	if (!p4game.equals(""))
	{
	   // movechaalToScreenCenter(coin4);
	   // coin4.setText(bootAmount);
	    card41.setBackgroundResource(R.drawable.cardback);
	    card42.setBackgroundResource(R.drawable.cardback);
	    card43.setBackgroundResource(R.drawable.cardback);
	    moveToOneAnother(card41, userview4, 0, 0, -30,true);
	    moveToOneAnother(card42, userview4, 0, 0, 0,true);
	    moveToOneAnother(card43, userview4, 0, 0, 30,true);
	}
	if (!p5game.equals(""))
	{
	   // movechaalToScreenCenter(coin5);
	    //coin5.setText(bootAmount);
	    card51.setBackgroundResource(R.drawable.cardback);
	    card52.setBackgroundResource(R.drawable.cardback);
	    card53.setBackgroundResource(R.drawable.cardback);
	    moveToOneAnother(card51, userview5, 0, 0, -30,true);
	    moveToOneAnother(card52, userview5, 0, 0, 0,true);
	    moveToOneAnother(card53, userview5, 0, 0, 30,true);
	}

}
private void movechaalToScreenCenter(View view){
	view.setVisibility(View.VISIBLE);
	RelativeLayout root = (RelativeLayout) findViewById( R.id.wholelayout );
    DisplayMetrics displaymetrics = new DisplayMetrics();
    this.getWindowManager().getDefaultDisplay().getMetrics( displaymetrics );
    int statusBarOffset = displaymetrics.heightPixels - root.getMeasuredHeight();
    int originalPos[] = new int[2];
    view.getLocationOnScreen(originalPos);
    int xDest = displaymetrics.widthPixels / 2 - view.getMeasuredWidth() / 2;
    int yDest = displaymetrics.heightPixels / 2 - view.getMeasuredHeight() / 2 - statusBarOffset;
    TranslateAnimation translateanimation = new TranslateAnimation(0, xDest - originalPos[0], 0.0F, yDest - originalPos[1]);
    translateanimation.setDuration(1000);
    translateanimation.setFillAfter(false);
    view.startAnimation(translateanimation);
    view.setVisibility(View.INVISIBLE);
    chipssound.start();
}
private void moveToOneAnother(View view, View view1, int j, int k, int l, Boolean mon){
	AnimationSet animationset = new AnimationSet(true);
    RotateAnimation rotateanimation = new RotateAnimation(0, l, 1, 0.5F, 1, 1.0F);
    rotateanimation.setStartOffset(j - 100);
    rotateanimation.setDuration(100L);
    animationset.addAnimation(rotateanimation);
    int ai[] = new int[2];
    view.getLocationOnScreen(ai);
    int ai1[] = new int[2];
    view1.getLocationOnScreen(ai1);
    TranslateAnimation translateanimation = new TranslateAnimation(0.0F, ai1[0] - ai[0] - view.getMeasuredHeight() / 2, 0.0F, ai1[1] - ai[1] - view.getMeasuredWidth() / 2);
    translateanimation.setStartOffset(j);
    translateanimation.setDuration(k);
    animationset.addAnimation(translateanimation);
    animationset.setFillAfter(mon);
    view.startAnimation(animationset);
    if (view1 == userview1 ){
    	new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            dealsound.start();	
            }
        }, j);
    }
    if (view1 == userview2 ){
    	new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            dealsound1.start();	
            }
        }, j);
    }
    if (view1 == userview3 ){
    	new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            dealsound2.start();	
            }
        }, j);
    }
    if (view1 == userview4 ){
    	new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            dealsound3.start();	
            }
        }, j);
    }
    if (view1 == userview5 ){
    	new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            dealsound4.start();	
            }
        }, j);
    }
   
}
	@Override
	protected void onResume() {
		t = new Timer();
		t.scheduleAtFixedRate(new TimerTask() {
		    @Override
		    public void run() {
		    	if (AppStatus.getInstance(context).isOnline(context))
                {
		    		
		    		  new ExternalNewClass().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                }
		    }},0,2000);
		
		
		super.onResume();
	}
	@Override
	protected void onPause() {
		 t.cancel();
		 
		super.onPause();
	}
	@Override
	public void onBackPressed() {

        final Dialog dialogCustome = new Dialog(this,R.style.PauseDialog);
        dialogCustome.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogCustome.setContentView(R.layout.standup_dilog);
        dialogCustome.show();
        ((Button)dialogCustome.findViewById(R.id.continueBtn)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                dialogCustome.dismiss();
            }
        });
        ((Button)dialogCustome.findViewById(R.id.packBtn)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
            	 dialogCustome.dismiss();
            	new StandUpClass().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        });
    
	}
	public class ExternalNewClass extends AsyncTask<Void, Void, Void>{
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			
		}
		@Override
		protected Void doInBackground(Void... params) {
			try {jsonobject = JSONfunctions
					.getJSONfromURL(String.format(Constans.displayApi,8,tableId,userName));
			
				/*if (isCancelled()){
					return null;}*/
				jsonarray = jsonobject.getJSONArray("worldpopulation");
				jsonobject = jsonarray.getJSONObject(0);
				if (jsonobject.has("main_d")){t0a = jsonobject.getString("main_d");}
	            if (jsonobject.has("c")){t1a = jsonobject.getString("c");}
	            if (jsonobject.has("chal")){chal = jsonobject.getString("chal");}
	            if (jsonobject.has("chal_amount")){t2a = jsonobject.getInt("chal_amount");}
	            if (jsonobject.has("status")){status = jsonobject.getString("status");}
	            if (jsonobject.has("id")){t3a = jsonobject.getString("id");}
	            if (jsonobject.has("boot_amount")){bootAmount = jsonobject.getString("boot_amount");}
	            if (jsonobject.has("player1")){t4a = jsonobject.getString("player1");}
	            if (jsonobject.has("player2")){t5a = jsonobject.getString("player2");}
	            if (jsonobject.has("player3")){t6a = jsonobject.getString("player3");}
	            if (jsonobject.has("player4")){t7a = jsonobject.getString("player4");}
	            if (jsonobject.has("player5")){t8a = jsonobject.getString("player5");}
	            if (jsonobject.has("num_player")){t9a = jsonobject.getString("num_player");}
	            if (jsonobject.has("table_amount")){table_amount = jsonobject.getString("table_amount");}
	            if (jsonobject.has("LastChal")){LastChal = jsonobject.getString("LastChal");}
	            if (jsonobject.has("LastChalAmount")){t10a = jsonobject.getInt("LastChalAmount");}
	            if (jsonobject.has("round_player")){t11a = jsonobject.getInt("round_player");}
	            if (jsonobject.has("winplayer")){winner = jsonobject.getString("winplayer");}
	            if (jsonobject.has("d")){previousplayer = jsonobject.getString("d");}
	            if (jsonobject.has("tip_user")){tipUser = jsonobject.getString("tip_user");}
	            if (jsonobject.has("tip_Amount")){tipAmount = jsonobject.getInt("tip_Amount");}
	            if (jsonobject.has("slid_show")){slidshowUser = jsonobject.getString("slid_show");}
	            if (!status.equals("0")){
	            jsonobject = jsonarray.getJSONObject(1);
	            if (jsonobject.has("player")){p3game = jsonobject.getString("player");}
	            if (jsonobject.has("p1")){t41a = jsonobject.getString("p1");}
	            if (jsonobject.has("p2")){t42a = jsonobject.getString("p2");}
	            if (jsonobject.has("p3")){t43a = jsonobject.getString("p3");}
	            if (jsonobject.has("weight")){weight3 = jsonobject.getInt("weight");}
	            if (jsonobject.has("L_chal")){amount3 = jsonobject.getInt("L_chal");}
	            if (jsonobject.has("Balance")){balance3 = jsonobject.getInt("Balance");}
	            if (jsonobject.has("P_status")){status3 = jsonobject.getInt("P_status");
	            if(status3==0){p3status=p3game;}else if(status3==1){p3status=BLIND;}else if(status3==2){p3status=CARDSEEN;}else if(status3==3){p3status=CHAAL;}else if(status3==4){p3status=PACK;}else if(status3==5){p3status=TIMEOUT;}else if(status3==6){p3status=SIDESHOW;}else if(status3==7){p3status=ACCEPT;}else if(status3==8){p3status=REJECT;}else{p3status="";} }
	            if (jsonobject.has("card_status")){cardstatus3 = jsonobject.getInt("card_status");
	            									seedoubler = 1 + cardstatus3;}
	            jsonobject = jsonarray.getJSONObject(2);
	            if (jsonobject.has("player")){p4game = jsonobject.getString("player");}
	            if (jsonobject.has("p1")){t51a = jsonobject.getString("p1");}
	            if (jsonobject.has("p2")){t52a = jsonobject.getString("p2");}
	            if (jsonobject.has("p3")){t53a = jsonobject.getString("p3");}
	            if (jsonobject.has("weight")){weight4 = jsonobject.getInt("weight");}
	            if (jsonobject.has("L_chal")){amount4 = jsonobject.getInt("L_chal");}
	            if (jsonobject.has("Balance")){balance4 = jsonobject.getInt("Balance");}
	            if (jsonobject.has("P_status")){status4 = jsonobject.getInt("P_status");}
	            if (jsonobject.has("card_status")){cardstatus4 = jsonobject.getInt("card_status");
	            if(status4==0){p4status=p4game;}else if(status4==1){p4status=BLIND;}else if(status4==2){p4status=CARDSEEN;}else if(status4==3){p4status=CHAAL;}else if(status4==4){p4status=PACK;}else if(status4==5){p4status=TIMEOUT;}else if(status4==6){p4status=SIDESHOW;}else if(status4==7){p4status=ACCEPT;}else if(status4==8){p4status=REJECT;}else{p4status="";}}
	            jsonobject = jsonarray.getJSONObject(3);
	            if (jsonobject.has("player")){p5game = jsonobject.getString("player");}
	            if (jsonobject.has("p1")){t61a = jsonobject.getString("p1");}
	            if (jsonobject.has("p2")){t62a = jsonobject.getString("p2");}
	            if (jsonobject.has("p3")){t63a = jsonobject.getString("p3");}
	            if (jsonobject.has("weight")){weight5 = jsonobject.getInt("weight");}
	            if (jsonobject.has("L_chal")){amount5 = jsonobject.getInt("L_chal");}
	            if (jsonobject.has("Balance")){balance5 = jsonobject.getInt("Balance");}
	            if (jsonobject.has("P_status")){status5 = jsonobject.getInt("P_status");}
	            if (jsonobject.has("card_status")){cardstatus5 = jsonobject.getInt("card_status");
	            if(status5==0){p5status=p5game;}else if(status5==1){p5status=BLIND;}else if(status5==2){p5status=CARDSEEN;}else if(status5==3){p5status=CHAAL;}else if(status5==4){p5status=PACK;}else if(status5==5){p5status=TIMEOUT;}else if(status5==6){p5status=SIDESHOW;}else if(status5==7){p5status=ACCEPT;}else if(status5==8){p5status=REJECT;}else{p5status="";}}
	            jsonobject = jsonarray.getJSONObject(4);
	            if (jsonobject.has("player")){p1game = jsonobject.getString("player");}
	            if (jsonobject.has("p1")){t21a = jsonobject.getString("p1");}
	            if (jsonobject.has("p2")){t22a = jsonobject.getString("p2");}
	            if (jsonobject.has("p3")){t23a = jsonobject.getString("p3");}
	            if (jsonobject.has("weight")){weight1 = jsonobject.getInt("weight");}
	            if (jsonobject.has("L_chal")){amount1 = jsonobject.getInt("L_chal");}
	            if (jsonobject.has("Balance")){balance1 = jsonobject.getInt("Balance");}
	            if (jsonobject.has("P_status")){status1 = jsonobject.getInt("P_status");}
	            if (jsonobject.has("card_status")){cardstatus1 = jsonobject.getInt("card_status");
	            if(status1==0){p1status=p1game;}else if(status1==1){p1status=BLIND;}else if(status1==2){p1status=CARDSEEN;}else if(status1==3){p1status=CHAAL;}else if(status1==4){p1status=PACK;}else if(status1==5){p1status=TIMEOUT;}else if(status1==6){p1status=SIDESHOW;}else if(status1==7){p1status=ACCEPT;}else if(status1==8){p1status=REJECT;}else{p1status="";}}
	            jsonobject = jsonarray.getJSONObject(5);
	            if (jsonobject.has("player")){p2game = jsonobject.getString("player");}
	            if (jsonobject.has("p1")){t31a = jsonobject.getString("p1");}
	            if (jsonobject.has("p2")){t32a = jsonobject.getString("p2");}
	            if (jsonobject.has("p3")){t33a = jsonobject.getString("p3");}
	            if (jsonobject.has("weight")){weight2 = jsonobject.getInt("weight");}
	            if (jsonobject.has("L_chal")){amount2 = jsonobject.getInt("L_chal");}
	            if (jsonobject.has("Balance")){balance2 = jsonobject.getInt("Balance");}
	            if (jsonobject.has("P_status")){status2 = jsonobject.getInt("P_status");}
	            if (jsonobject.has("card_status")){cardstatus2 = jsonobject.getInt("card_status");
	            if(status2==0){p2status=p2game;}else if(status2==1){p2status=BLIND;}else if(status2==2){p2status=CARDSEEN;}else if(status2==3){p2status=CHAAL;}else if(status2==4){p2status=PACK;}else if(status2==5){p2status=TIMEOUT;}else if(status2==6){p2status=SIDESHOW;}else if(status2==7){p2status=ACCEPT;}else if(status2==8){p2status=REJECT;}else{p2status="";}}
	            }
				if(t4a.equals(userName)){p1a = t7a;p2a = t8a;p3a = t4a;p4a = t5a;p5a = t6a;}
				else if(t5a.equals(userName)){p1a = t8a;p2a = t4a;p3a = t5a;p4a = t6a;p5a = t7a;}
				else if(t6a.equals(userName)){p1a = t4a;p2a = t5a;p3a = t6a;p4a = t7a;p5a = t8a;}
				else if(t7a.equals(userName)){p1a = t5a;p2a = t6a;p3a = t7a;p4a = t8a;p5a = t4a;}
				else if(t8a.equals(userName)){p1a = t6a;p2a = t7a;p3a = t8a;p4a = t4a;p5a = t5a;}
				else {p1a = "Guest";}
			} catch (JSONException e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
				}
			return null;
		}
		protected void onPostExecute(Void unused) {
			//target_amount.setText("*"+p3a+"*");
			if(p1a.equals("Guest")){System.exit(0);t.cancel();finish();
			}
			//imageView3.setBackgroundResource(R.drawable.ic_launcher);
			if (!p1a.equals("")){user1layout.setVisibility(View.VISIBLE);
			}  else{user1layout.setVisibility(View.INVISIBLE);}
			if (!p2a.equals("")){user2layout.setVisibility(View.VISIBLE);
			} else{user2layout.setVisibility(View.INVISIBLE);}
			if (!p3a.equals("")){user3layout.setVisibility(View.VISIBLE);
			} else{user3layout.setVisibility(View.INVISIBLE);}
			if (!p4a.equals("")){user4layout.setVisibility(View.VISIBLE);
			} else{user4layout.setVisibility(View.INVISIBLE);}
			if (!p5a.equals("")){user5layout.setVisibility(View.VISIBLE);
			} else{user5layout.setVisibility(View.INVISIBLE);}
			if (!tipUserConstant.equals(tipUser)&& !tipUser.equals("")){
				if (tipUser.equals(p1a)){
					moveToOneAnother(coin1, mSeekArc, 0, 2000, 0,false);
					new ClearTipClass().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    coin1.setText(""+tipAmount);}
				if (tipUser.equals(p2a)){
					moveToOneAnother(coin2, mSeekArc, 0, 2000, 0,false);
					new ClearTipClass().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    coin2.setText(""+tipAmount);}
				if (tipUser.equals(p3a)){
					
					new ClearTipClass().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
					/*moveToOneAnother(coin3, mSeekArc, 0, 2000, 0);
                    coin3.setText(""+tipAmount);*/}
				if (tipUser.equals(p4a)){
					moveToOneAnother(coin4, mSeekArc, 0, 2000, 0,false);
					new ClearTipClass().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    coin4.setText(""+tipAmount);}
				if (tipUser.equals(p5a)){
					moveToOneAnother(coin5, mSeekArc, 0, 2000, 0,false);
					new ClearTipClass().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    coin5.setText(""+tipAmount);}
               
				tipUserConstant = tipUser;
			}
			if (weight1 == 0){card11.setBackgroundResource(0);card12.setBackgroundResource(0);card13.setBackgroundResource(0);}
            if (weight2 == 0){card21.setBackgroundResource(0);card22.setBackgroundResource(0);card23.setBackgroundResource(0);}
            if (weight3 == 0){card31.setBackgroundResource(0);card32.setBackgroundResource(0);card33.setBackgroundResource(0);cardSee.setVisibility(View.INVISIBLE);}
            if (weight4 == 0){card41.setBackgroundResource(0);card42.setBackgroundResource(0);card43.setBackgroundResource(0);}
            if (weight5 == 0){card51.setBackgroundResource(0);card52.setBackgroundResource(0);card53.setBackgroundResource(0);}
            coinall.setText(table_amount);
            myname.setText(p3a);
            if (status.equals("0")){
            	
            	user1name.setText(p1a);user2name.setText(p2a);user3name.setText(p3a);user4name.setText(p4a);user5name.setText(p5a);
            	
            	gameStatus = status;
                lastchaalconstant = LastChal;
                currantchaalconstant = chal;
                if(tiktikfirst5Start){
                	tiktikfirst5Start=false;
                	tiktikfirst5 = new CountDownTimer(6000, 1000) {
    		            public void onFinish()
    		            {  
    		           
    		            	it1 = 4;
    	                    toast = Toast.makeText(getApplicationContext(), "creating new Round", Toast.LENGTH_SHORT);
    	                    toast.show();
    	                    new NewRoundClass().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    	                    new Handler().postDelayed(new Runnable() {
    	                        @Override
    	                        public void run() {
    	                        	toast.cancel();  
    	                        }
    	                    }, 900);
    		            }
    		            public void onTick(long l)
    		            {
    		               /* mSeekArc.setProgress(i);
    		                i = 1 + i;*/
    		            }
    		        };
    		        tiktikfirst5.start();
                }
            }
            if (status.equals("1")){
            	mSeekArc.setVisibility(View.VISIBLE);
            	if (!status.equals(gameStatus))
                {
            		if(gameStatus.equals("0")){
            			start();
            		}
            		if(gameStatus.equals("")){
            			alreadyStart();
            		}
            		/*if (newroundclass != null && newroundclass.getStatus() != AsyncTask.Status.FINISHED){
		            	newroundclass.cancel(true);}*/
                    gameStatus = status;
                    if (chal.equals(p3a)){betLayout.setVisibility(View.VISIBLE);}
                }
            	if(!slidshowUser.equals("")){
            		
            		if(sideshowrequest){sideshowrequest = false;
            		toast = Toast.makeText(getApplicationContext(), slidshowUser+" Requested Sideshow", Toast.LENGTH_SHORT);
                    toast.show();
                    tiktik30.cancel();
                    i = 1;
                    tiktik30.start();
                    if(previousplayer.equals(userName)){
                    	final Dialog dialogCustome1 = new Dialog(context, R.style.PauseDialog);
                        dialogCustome1.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialogCustome1.setContentView(R.layout.side_show_dilog);
                        dialogCustome1.setCancelable(false);
                        dialogCustome1.setCanceledOnTouchOutside(false);
                        dialogCustome1.show();
                        ((TextView)dialogCustome1.findViewById(R.id.sideshow_user)).setText(slidshowUser);
                        ((Button)dialogCustome1.findViewById(R.id.acceptSideshowBtn)).setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view)
                            {
                            	if(!slidshowUser.equals("")){
                            new SideShowAcceptResponseClass().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                            	}
                            	dialogCustome1.dismiss();
                            }
                        });
                        ((Button)dialogCustome1.findViewById(R.id.rejectSideshowBtn)).setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view)
                            {
                            if(!slidshowUser.equals("")){new SideShowRejectResponseClass().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                            }dialogCustome1.dismiss();}
                        });
                    }
                    }
            		
                    	if(previousplayer.equals(p5game)&& status5==7){
                    		new SideShowAfterAcceptResponseClass().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,p5game);}
                		if(previousplayer.equals(p4game)&& status4==7){
                			new SideShowAfterAcceptResponseClass().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,p4game);}
                		if(previousplayer.equals(p3game)&& status3==7){
                			new SideShowAfterAcceptResponseClass().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,p3game);}
                		if(previousplayer.equals(p2game)&& status2==7){
                			new SideShowAfterAcceptResponseClass().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,p2game);}
                    
            	}else{sideshowrequest = true;}
            	
            	if (t11a == 2){showBtn.setVisibility(View.VISIBLE);sideshowBtn.setVisibility(View.INVISIBLE);}
            	else{showBtn.setVisibility(View.INVISIBLE);}
            	
            	if(cardstatus3==1 && t11a > 2){
            		if(previousplayer.equals(p2game)&& cardstatus2 == 1){showBtn.setVisibility(View.INVISIBLE);sideshowBtn.setVisibility(View.VISIBLE);}
            		else if(previousplayer.equals(p1game)&& cardstatus1 == 1){showBtn.setVisibility(View.INVISIBLE);sideshowBtn.setVisibility(View.VISIBLE);}
            		else if(previousplayer.equals(p5game)&& cardstatus5 == 1){showBtn.setVisibility(View.INVISIBLE);sideshowBtn.setVisibility(View.VISIBLE);}
            		else if(previousplayer.equals(p4game)&& cardstatus4 == 1){showBtn.setVisibility(View.INVISIBLE);sideshowBtn.setVisibility(View.VISIBLE);}
            		else{showBtn.setVisibility(View.INVISIBLE);sideshowBtn.setVisibility(View.INVISIBLE);}
            	}
            	
            	target_amount.setText(""+(t2a * seedoubler * doubler));
            	
            	
            	if (!p1a.equals("")){user1name.setText(p1status);user1amount.setText(""+amount1);
            	if (chal.equals(p1a)){user1layout.setBackgroundColor(Color.parseColor("#1E90FF"));betLayout.setVisibility(View.INVISIBLE);}else{user1layout.setBackgroundColor(Color.parseColor("#33000000"));}}
    			if (!p2a.equals("")){user2name.setText(p2status);user2amount.setText(""+amount2);
    			if (chal.equals(p2a)){user2layout.setBackgroundColor(Color.parseColor("#1E90FF"));betLayout.setVisibility(View.INVISIBLE);}else{user2layout.setBackgroundColor(Color.parseColor("#33000000"));}}
    			if (!p3a.equals("")){user3name.setText(p3status);user3amount.setText(""+balance3);
    			if (chal.equals(p3a)){user3layout.setBackgroundColor(Color.parseColor("#1E90FF"));
    			if(myChaal){
                    turnsound.start();
                    betLayout.setVisibility(View.VISIBLE);
                    
                    chaal.setEnabled(true);
                    packthegame.setEnabled(true);
                    showBtn.setEnabled(true);
                    sideshowBtn.setEnabled(true);
                    singleAmount.setEnabled(false); 
                    myChaal = Boolean.valueOf(false);
                    if (t2a <= bootIntAmount*32){doubleAmount.setEnabled(true);
                    } else{doubleAmount.setEnabled(false);}
                
    			}
    			}else{myChaal=true;user3layout.setBackgroundColor(Color.parseColor("#33000000"));}}
    			if (!p4a.equals("")){user4name.setText(p4status);user4amount.setText(""+amount4);
    			if (chal.equals(p4a)){user4layout.setBackgroundColor(Color.parseColor("#1E90FF"));betLayout.setVisibility(View.INVISIBLE);}else{user4layout.setBackgroundColor(Color.parseColor("#33000000"));}}
    			if (!p5a.equals("")){user5name.setText(p5status);user5amount.setText(""+amount5);
    			if (chal.equals(p5a)){user5layout.setBackgroundColor(Color.parseColor("#1E90FF"));betLayout.setVisibility(View.INVISIBLE);}else{user5layout.setBackgroundColor(Color.parseColor("#33000000"));}}
    			if (!lastchaalconstant.equals(LastChal)){
    				if (LastChal.equals(p1a)){
    					movechaalToScreenCenter(coin1);
                        coin1.setText(""+amount1);}
    				if (LastChal.equals(p2a)){
    					movechaalToScreenCenter(coin2);
                        coin2.setText(""+amount2);}
    				if (LastChal.equals(p3a)){
    					movechaalToScreenCenter(coin3);
                        coin3.setText(""+amount3);}
    				if (LastChal.equals(p4a)){
    					movechaalToScreenCenter(coin4);
                        coin4.setText(""+amount4);}
    				if (LastChal.equals(p5a)){
    					movechaalToScreenCenter(coin5);
                        coin5.setText(""+amount5);}
                   
                    lastchaalconstant = LastChal;
    			}
    			if (!currantchaalconstant.equals(chal)){
                    tiktik30.cancel();
                    i = 1;
                    tiktik30.start();
                    currantchaalconstant = chal;
    			}
    			if (cardstatus3 == 0)
                {
                    chaal.setText(BLIND);
                } else
                if (cardstatus3 == 1)
                {
                    cardSee.setVisibility(View.INVISIBLE);
                    chaal.setText(CHAAL);
                    if (card31location != Arrays.asList(apicard).indexOf(t41a))
                    {
                        card31location = Arrays.asList(apicard).indexOf(t41a);
                        card32location = Arrays.asList(apicard).indexOf(t42a);
                        card33location = Arrays.asList(apicard).indexOf(t43a);
                        card31.setBackgroundResource(drawableCard[card31location]);
                        card32.setBackgroundResource(drawableCard[card32location]);
                        card33.setBackgroundResource(drawableCard[card33location]);
                    }
                } else
                {
                    cardSee.setVisibility(View.VISIBLE);
                    return;
                }
    			}else{mSeekArc.setVisibility(View.INVISIBLE);cardSee.setVisibility(View.INVISIBLE);}
            if (status.equals("2") && !status.equals(gameStatus)){
            	/*if (cardseeclass != null && cardseeclass.getStatus() != AsyncTask.Status.FINISHED){
            		cardseeclass.cancel(true);}
            	if (chaalclass != null && chaalclass.getStatus() != AsyncTask.Status.FINISHED){
            		chaalclass.cancel(true);}
            	if (packnewclass != null && packnewclass.getStatus() != AsyncTask.Status.FINISHED){
            		packnewclass.cancel(true);}
            	if (showclass != null && showclass.getStatus() != AsyncTask.Status.FINISHED){
            		showclass.cancel(true);}
            	if (timeoutclass != null && timeoutclass.getStatus() != AsyncTask.Status.FINISHED){
            		timeoutclass.cancel(true);}
            	if (sideshowclass != null && sideshowclass.getStatus() != AsyncTask.Status.FINISHED){
            		sideshowclass.cancel(true);}
            	if (sideshowrejectresponseclass != null && sideshowrejectresponseclass.getStatus() != AsyncTask.Status.FINISHED){
            		sideshowrejectresponseclass.cancel(true);}
            	if (sideshowacceptresponseclass != null && sideshowacceptresponseclass.getStatus() != AsyncTask.Status.FINISHED){
	            	statusupdateclass.cancel(true);}
            	if (sideshowafteracceptresponseclass != null && sideshowafteracceptresponseclass.getStatus() != AsyncTask.Status.FINISHED){
            		sideshowafteracceptresponseclass.cancel(true);}*/
            	tiktik30.cancel();
            	gameStatus = status;
                winsound.start();
                cardSee.setVisibility(View.INVISIBLE);
                betLayout.setVisibility(View.INVISIBLE);
                
                if (!p1a.equals("")){user1name.setText(p1status);}
    			if (!p2a.equals("")){user2name.setText(p2status);}
    			if (!p3a.equals("")){user3name.setText(p3status);user3amount.setText(""+balance3);}
    			if (!p4a.equals("")){user4name.setText(p4status);}
    			if (!p5a.equals("")){user5name.setText(p5status);}
    			user1amount.setText("");
                user2amount.setText("");
                user4amount.setText("");
                user5amount.setText("");
                if(balance3<bootIntAmount){new StandUpClass().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);}
               
                if(t11a != 1){
//
                if(t11a == 2){
                	toast = Toast.makeText(getApplicationContext(), " ask for Show", Toast.LENGTH_SHORT);
                    toast.show();
                }
                    if (weight1 != 0)
                    {card11location = Arrays.asList(apicard).indexOf(t21a);
                     card12location = Arrays.asList(apicard).indexOf(t22a);
                     card13location = Arrays.asList(apicard).indexOf(t23a);
                     card11.setBackgroundResource(drawableCard[card11location]);
                     card12.setBackgroundResource(drawableCard[card12location]);
                     card13.setBackgroundResource(drawableCard[card13location]);
                    }
                    if (weight2 != 0)
                    {
                        card21location = Arrays.asList(apicard).indexOf(t31a);
                        card22location = Arrays.asList(apicard).indexOf(t32a);
                        card23location = Arrays.asList(apicard).indexOf(t33a);
                        card21.setBackgroundResource(drawableCard[card21location]);
                        card22.setBackgroundResource(drawableCard[card22location]);
                        card23.setBackgroundResource(drawableCard[card23location]);
                    }
                    if (weight3 != 0)
                    {	card31location = Arrays.asList(apicard).indexOf(t41a);
                    card32location = Arrays.asList(apicard).indexOf(t42a);
                    card33location = Arrays.asList(apicard).indexOf(t43a);
                    card31.setBackgroundResource(drawableCard[card31location]);
                    card32.setBackgroundResource(drawableCard[card32location]);
                    card33.setBackgroundResource(drawableCard[card33location]);}
                    if (weight4 != 0)
                    {
                        card41location = Arrays.asList(apicard).indexOf(t51a);
                        card42location = Arrays.asList(apicard).indexOf(t52a);
                        card43location = Arrays.asList(apicard).indexOf(t53a);
                        card41.setBackgroundResource(drawableCard[card41location]);
                        card42.setBackgroundResource(drawableCard[card42location]);
                        card43.setBackgroundResource(drawableCard[card43location]);
                    }
                    if (weight5 != 0)
                    {
                        card51location = Arrays.asList(apicard).indexOf(t61a);
                        card52location = Arrays.asList(apicard).indexOf(t62a);
                        card53location = Arrays.asList(apicard).indexOf(t63a);
                        card51.setBackgroundResource(drawableCard[card51location]);
                        card52.setBackgroundResource(drawableCard[card52location]);
                        card53.setBackgroundResource(drawableCard[card53location]);
                    }}
                
                    if (winner.equals("player1")){
                    	if(t4a.equals(p1game)){moveToOneAnother(coinall, coin1, 0, 2000, 0,true);}
                    	else if(t4a.equals(p2game)){moveToOneAnother(coinall, coin2, 0, 2000, 0,true);}
                    	else if(t4a.equals(p3game)){moveToOneAnother(coinall, coin3, 0, 2000, 0,true);}
                    	else if(t4a.equals(p4game)){moveToOneAnother(coinall, coin4, 0, 2000, 0,true);}
                    	else if(t4a.equals(p5game)){moveToOneAnother(coinall, coin5, 0, 2000, 0,true);}
                    }
                    else if (winner.equals("player2")){
                    	if(t5a.equals(p1game)){moveToOneAnother(coinall, coin1, 0, 2000, 0,true);}
                    	else if(t5a.equals(p2game)){moveToOneAnother(coinall, coin2, 0, 2000, 0,true);}
                    	else if(t5a.equals(p3game)){moveToOneAnother(coinall, coin3, 0, 2000, 0,true);}
                    	else if(t5a.equals(p4game)){moveToOneAnother(coinall, coin4, 0, 2000, 0,true);}
                    	else if(t5a.equals(p5game)){moveToOneAnother(coinall, coin5, 0, 2000, 0,true);}
                    }
                    else if (winner.equals("player3")){
                    	if(t6a.equals(p1game)){moveToOneAnother(coinall, coin1, 0, 2000, 0,true);}
                	else if(t6a.equals(p2game)){moveToOneAnother(coinall, coin2, 0, 2000, 0,true);}
                	else if(t6a.equals(p3game)){moveToOneAnother(coinall, coin3, 0, 2000, 0,true);}
                	else if(t6a.equals(p4game)){moveToOneAnother(coinall, coin4, 0, 2000, 0,true);}
                	else if(t6a.equals(p5game)){moveToOneAnother(coinall, coin5, 0, 2000, 0,true);}}
                    else if (winner.equals("player4")){
                    	if(t7a.equals(p1game)){moveToOneAnother(coinall, coin1, 0, 2000, 0,true);}
                	else if(t7a.equals(p2game)){moveToOneAnother(coinall, coin2, 0, 2000, 0,true);}
                	else if(t7a.equals(p3game)){moveToOneAnother(coinall, coin3, 0, 2000, 0,true);}
                	else if(t7a.equals(p4game)){moveToOneAnother(coinall, coin4, 0, 2000, 0,true);}
                	else if(t7a.equals(p5game)){moveToOneAnother(coinall, coin5, 0, 2000, 0,true);}}
                    else if (winner.equals("player5")){
                    	if(t8a.equals(p1game)){moveToOneAnother(coinall, coin1, 0, 2000, 0,true);}
                	else if(t8a.equals(p2game)){moveToOneAnother(coinall, coin2, 0, 2000, 0,true);}
                	else if(t8a.equals(p3game)){moveToOneAnother(coinall, coin3, 0, 2000, 0,true);}
                	else if(t8a.equals(p4game)){moveToOneAnother(coinall, coin4, 0, 2000, 0,true);}
                	else if(t8a.equals(p5game)){moveToOneAnother(coinall, coin5, 0, 2000, 0,true);}}
                    tiktiklast5 = new CountDownTimer(6000, 1000) {
    		            public void onFinish()
    		            {
    		            	new StatusUpdateClass().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    	                    
    		            }
    		            public void onTick(long l)
    		            {
    		               
    		            }
    		        };
    		        tiktiklast5.start();
                    
                
            }
		}
		}
	public class CardSeeClass extends AsyncTask<Void, Void, Void>{
		private final HttpClient Client3 = new DefaultHttpClient();
        private String Content3;
        private String Error3 = null; 
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			testText1.setText("Cardsee");
			cardSee.setVisibility(View.INVISIBLE);
            cardLoaderLayout.setVisibility(View.VISIBLE);
		}
		@Override
		protected Void doInBackground(Void... params) {
			try{
				/*if (isCancelled()){
					return null;}*/
				 HttpGet httpget = new HttpGet(String.format(Constans.cardSeeApi,8,userName,tableId));
	                ResponseHandler<String> responseHandler = new BasicResponseHandler();
	                Content3 = Client3.execute(httpget, responseHandler);
	            }
	            catch (ClientProtocolException clientprotocolexception)
	            {
	                Error3 = clientprotocolexception.getMessage();
	            }
	            catch (IOException ioexception)
	            {
	                Error3 = ioexception.getMessage();
	            }
			card31location = Arrays.asList(apicard).indexOf(t41a);
            card32location = Arrays.asList(apicard).indexOf(t42a);
            card33location = Arrays.asList(apicard).indexOf(t43a);
			return null;
		}
		protected void onPostExecute(Void unused) {
			 if (Content3.equals("1"))
	            {
				 testText2.setText("Cardsee");
	                cardLoaderLayout.setVisibility(View.INVISIBLE);
	                card31.setBackgroundResource(drawableCard[card31location].intValue());
	                card32.setBackgroundResource(drawableCard[card32location].intValue());
	                card33.setBackgroundResource(drawableCard[card33location].intValue());
	            } else
	            {
	               // Toast.makeText(getApplicationContext(), (new StringBuilder("+")).append(Content3).append("+").toString(), 1).show();
	            }
		}
		}
	public class ChaalClass extends AsyncTask<Void, Void, Void>{
		private final HttpClient Client1 = new DefaultHttpClient();
        private String Content1;
        private String Error1 = null; 
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			testText1.setText("Chaal");
			chaal.setEnabled(false);
			betLayout.setVisibility(View.INVISIBLE);
			
		}
		@Override
		protected Void doInBackground(Void... params) {
			try{
				/*if (isCancelled()){
					return null;
				}*/
				 HttpGet httpget = new HttpGet(String.format(Constans.chaalApi,8,userName,tableId,doubler-1));
	                ResponseHandler<String> responseHandler = new BasicResponseHandler();
	                Content1 = Client1.execute(httpget, responseHandler);
	            }
	            catch (ClientProtocolException clientprotocolexception)
	            {
	                Error1 = clientprotocolexception.getMessage();
	            }
	            catch (IOException ioexception)
	            {
	                Error1 = ioexception.getMessage();
	            }
			return null;
		}
		protected void onPostExecute(Void unused) {
			testText2.setText("Chaal");
			if (Content1.equals("1")){
                doubler = 1;
                chaal.setEnabled(true);
                return;
            } else{
                //Toast.makeText(getApplicationContext(), (new StringBuilder("Reply ")).append(Content1).toString(), 1).show();
           }
		}}
	public class NewRoundClass extends AsyncTask<Void, Void, Void>{
		private final HttpClient Client4 = new DefaultHttpClient();
        private String Content4;
        private String Error4 = null; 
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			testText1.setText("NewRound");
		}
		@Override
		protected Void doInBackground(Void... params) {
			try{
				/*if (isCancelled()){
					return null;}*/
				 HttpGet httpget = new HttpGet(String.format(Constans.newRoundUpApi,8,tableId));
	                ResponseHandler<String> responseHandler = new BasicResponseHandler();
	                Content4 = Client4.execute(httpget, responseHandler);
	            }
	            catch (ClientProtocolException clientprotocolexception)
	            {
	                Error4 = clientprotocolexception.getMessage();
	            }
	            catch (IOException ioexception)
	            {
	                Error4 = ioexception.getMessage();
	            }			return null;
		}
		protected void onPostExecute(Void unused) {
			testText2.setText("Newround");
		tiktikfirst5Start=true;}
		}
	public class PackNewClass extends AsyncTask<Void, Void, Void>{
		private final HttpClient Client2 = new DefaultHttpClient();
        private String Content2;
        private String Error2 = null; 
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			testText1.setText("Pack");
			packthegame.setEnabled(false);
			betLayout.setVisibility(View.INVISIBLE);
			
		}
		@Override
		protected Void doInBackground(Void... params) {
			try{
				/*if (isCancelled()){
					return null;}*/
				 HttpGet httpget = new HttpGet(String.format(Constans.packApi,8,userName,tableId));
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
			testText2.setText("Pack");
			packthegame.setEnabled(true);
		}
		}
	public class ShowClass extends AsyncTask<Void, Void, Void>{
		private final HttpClient Client5 = new DefaultHttpClient();
        private String Content5;
        private String Error5 = null; 
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			testText1.setText("Show");
			showBtn.setEnabled(false);
			betLayout.setVisibility(View.INVISIBLE);
			
		}
		@Override
		protected Void doInBackground(Void... params) {
			try{
				/*if (isCancelled()){
					return null;}*/
				 HttpGet httpget = new HttpGet(String.format(Constans.showApi,8,userName,tableId));
	                ResponseHandler<String> responseHandler = new BasicResponseHandler();
	                Content5 = Client5.execute(httpget, responseHandler);
	            }
	            catch (ClientProtocolException clientprotocolexception)
	            {
	                Error5 = clientprotocolexception.getMessage();
	            }
	            catch (IOException ioexception)
	            {
	                Error5 = ioexception.getMessage();
	            }
			return null;
		}
		protected void onPostExecute(Void unused) {
			testText2.setText("Show");
			showBtn.setEnabled(true);
		}}
	public class StandUpClass extends AsyncTask<Void, Void, Void>{
		private final HttpClient Client6 = new DefaultHttpClient();
        private String Content6;
        private String Error6 = null; 
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			testText1.setText("Standup");
			pd.displayProgressDialog(false, "Standing up...", context);
		}
		@Override
		protected Void doInBackground(Void... params) {
			try{
				
				 HttpGet httpget = new HttpGet(String.format(Constans.standUpApi,8,tableId,userName));
	                ResponseHandler<String> responseHandler = new BasicResponseHandler();
	                Content6 = Client6.execute(httpget, responseHandler);
	            }
	            catch (ClientProtocolException clientprotocolexception)
	            {
	                Error6 = clientprotocolexception.getMessage();
	            }
	            catch (IOException ioexception)
	            {
	                Error6 = ioexception.getMessage();
	            }
			return null;
		}
		protected void onPostExecute(Void unused) {
			testText2.setText("Standup");
			pd.dismiss();
			
			System.exit(0);
            t.cancel();
            finish();
		}}
	public class StatusUpdateClass extends AsyncTask<Void, Void, Void>{
		private final HttpClient Client7 = new DefaultHttpClient();
        private String Content7;
        private String Error7 = null; 
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			testText1.setText("Status");
			toast = Toast.makeText(getApplicationContext(), "Wait For New Round", Toast.LENGTH_SHORT);
            toast.show();
			card11.setBackgroundResource(0);
            card12.setBackgroundResource(0);
            card13.setBackgroundResource(0);
            card21.setBackgroundResource(0);
            card22.setBackgroundResource(0);
            card23.setBackgroundResource(0);
            card31.setBackgroundResource(0);
            card32.setBackgroundResource(0);
            card33.setBackgroundResource(0);
            card41.setBackgroundResource(0);
            card42.setBackgroundResource(0);
            card43.setBackgroundResource(0);
            card51.setBackgroundResource(0);
            card52.setBackgroundResource(0);
            card53.setBackgroundResource(0);
            
		}
		@Override
		protected Void doInBackground(Void... params) {
			try{
				/*if (isCancelled()){
					return null;}*/
				 HttpGet httpget = new HttpGet(String.format(Constans.statusUpdateApi,8,tableId));
	                ResponseHandler<String> responseHandler = new BasicResponseHandler();
	                Content7 = Client7.execute(httpget, responseHandler);
	            }
	            catch (ClientProtocolException clientprotocolexception)
	            {
	                Error7 = clientprotocolexception.getMessage();
	            }
	            catch (IOException ioexception)
	            {
	                Error7 = ioexception.getMessage();
	            }
			return null;
		}
		protected void onPostExecute(Void unused) {
			testText2.setText("Status");
			tiktiklast5.cancel();
		}}
	public class TimeOutClass extends AsyncTask<String, Void, Void>{
		private final HttpClient Client8 = new DefaultHttpClient();
        private String Content8;
        private String Error8 = null; 
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			testText1.setText("Timeout");
			
		}
		
		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			
			try{
				/*if (isCancelled()){
					return null;}*/
				 HttpGet httpget = new HttpGet(String.format(Constans.timeOutApi,8,params[0],tableId));
	                ResponseHandler<String> responseHandler = new BasicResponseHandler();
	                Content8 = Client8.execute(httpget, responseHandler);
	            }
	            catch (ClientProtocolException clientprotocolexception)
	            {
	                Error8 = clientprotocolexception.getMessage();
	            }
	            catch (IOException ioexception)
	            {
	                Error8 = ioexception.getMessage();
	            }
			return null;
		}
		protected void onPostExecute(Void unused) {
			testText2.setText("Timeout");
			/*toast = Toast.makeText(getApplicationContext(), "Timeout end"+currantchaalconstant, Toast.LENGTH_SHORT);
            toast.show();*/
		}
		
		}
	public class SideShowClass extends AsyncTask<Void, Void, Void>{
		private final HttpClient Client9 = new DefaultHttpClient();
        private String Content9;
        private String Error9 = null; 
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			testText1.setText("Side");
			sideshowBtn.setEnabled(false);
			betLayout.setVisibility(View.INVISIBLE);
			
		}
		@Override
		protected Void doInBackground(Void... params) {
			try{
				/*if (isCancelled()){
					return null;}*/
				 HttpGet httpget = new HttpGet(String.format(Constans.sideshowApi,8,userName,tableId));
	                ResponseHandler<String> responseHandler = new BasicResponseHandler();
	                Content9 = Client9.execute(httpget, responseHandler);
	            }
	            catch (ClientProtocolException clientprotocolexception)
	            {
	                Error9 = clientprotocolexception.getMessage();
	            }
	            catch (IOException ioexception)
	            {
	                Error9 = ioexception.getMessage();
	            }
			return null;
		}
		protected void onPostExecute(Void unused) {
			testText2.setText("Side");
			sideshowBtn.setEnabled(true);
		}
		}
	public class SideShowRejectResponseClass extends AsyncTask<Void, Void, Void>{
		private final HttpClient Client10 = new DefaultHttpClient();
        private String Content10;
        private String Error10 = null; 
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			testText1.setText("RejectSide");
			//showBtn.setEnabled(false);
		}
		@Override
		protected Void doInBackground(Void... params) {
			try{
				/*if (isCancelled()){
					return null;}*/
				 HttpGet httpget = new HttpGet(String.format(Constans.sideshowResponseApi,8,userName,0,tableId));
	                ResponseHandler<String> responseHandler = new BasicResponseHandler();
	                Content10 = Client10.execute(httpget, responseHandler);
	            }
	            catch (ClientProtocolException clientprotocolexception)
	            {
	                Error10 = clientprotocolexception.getMessage();
	            }
	            catch (IOException ioexception)
	            {
	                Error10 = ioexception.getMessage();
	            }
			return null;
		}
		protected void onPostExecute(Void unused) {
			testText2.setText("RejectSide");
		}
		}
	public class SideShowAcceptResponseClass extends AsyncTask<Void, Void, Void>{
		private final HttpClient Client11 = new DefaultHttpClient();
        private String Content11;
        private String Error11 = null; 
		@Override
		protected void onPreExecute() {
			testText1.setText("AcceptSide");
			super.onPreExecute();
		if(slidshowUser.equals(p2game)){
			card21location = Arrays.asList(apicard).indexOf(t31a);
			card22location = Arrays.asList(apicard).indexOf(t32a);
            card23location = Arrays.asList(apicard).indexOf(t33a);
            card21.setBackgroundResource(drawableCard[card21location]);
            card22.setBackgroundResource(drawableCard[card22location]);
            card23.setBackgroundResource(drawableCard[card23location]);}
		else if(slidshowUser.equals(p1game)){
			card11location = Arrays.asList(apicard).indexOf(t21a);
            card12location = Arrays.asList(apicard).indexOf(t22a);
            card13location = Arrays.asList(apicard).indexOf(t23a);
            card11.setBackgroundResource(drawableCard[card11location]);
            card12.setBackgroundResource(drawableCard[card12location]);
            card13.setBackgroundResource(drawableCard[card13location]);}
		else if(slidshowUser.equals(p5game)){
			card51location = Arrays.asList(apicard).indexOf(t61a);
	        card52location = Arrays.asList(apicard).indexOf(t62a);
	        card53location = Arrays.asList(apicard).indexOf(t63a);
	        card51.setBackgroundResource(drawableCard[card51location]);
	        card52.setBackgroundResource(drawableCard[card52location]);
	        card53.setBackgroundResource(drawableCard[card53location]);}
		else if(slidshowUser.equals(p4game)){
			card41location = Arrays.asList(apicard).indexOf(t51a);
	        card42location = Arrays.asList(apicard).indexOf(t52a);
	        card43location = Arrays.asList(apicard).indexOf(t53a);
	        card41.setBackgroundResource(drawableCard[card41location]);
	        card42.setBackgroundResource(drawableCard[card42location]);
	        card43.setBackgroundResource(drawableCard[card43location]);}
		else if(slidshowUser.equals(p3game)){
			card31location = Arrays.asList(apicard).indexOf(t41a);
	        card32location = Arrays.asList(apicard).indexOf(t42a);
	        card33location = Arrays.asList(apicard).indexOf(t43a);
	        card31.setBackgroundResource(drawableCard[card31location]);
	        card32.setBackgroundResource(drawableCard[card32location]);
	        card33.setBackgroundResource(drawableCard[card33location]);}
				
		}
		@Override
		protected Void doInBackground(Void... params) {
			try{
				/*if (isCancelled()){
					return null;}*/
				 HttpGet httpget = new HttpGet(String.format(Constans.sideshowResponseApi,8,userName,1,tableId));
	                ResponseHandler<String> responseHandler = new BasicResponseHandler();
	                Content11 = Client11.execute(httpget, responseHandler);
	            }
	            catch (ClientProtocolException clientprotocolexception)
	            {
	                Error11 = clientprotocolexception.getMessage();
	            }
	            catch (IOException ioexception)
	            {
	                Error11 = ioexception.getMessage();
	            }
			return null;
		}
		protected void onPostExecute(Void unused) {
			testText2.setText("AcceptSide");
		}
		}
	public class SideShowAfterAcceptResponseClass extends AsyncTask<String, Void, Void>{
		private final HttpClient Client12 = new DefaultHttpClient();
        private String Content12;
        private String Error12 = null; 
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			testText1.setText("AfterSide");
			if(slidshowUser.equals(userName)){
			if(previousplayer.equals(p5game)&& status5==7){
    			card51location = Arrays.asList(apicard).indexOf(t61a);
                card52location = Arrays.asList(apicard).indexOf(t62a);
                card53location = Arrays.asList(apicard).indexOf(t63a);
                card51.setBackgroundResource(drawableCard[card51location]);
                card52.setBackgroundResource(drawableCard[card52location]);
                card53.setBackgroundResource(drawableCard[card53location]);}
    		if(previousplayer.equals(p4game)&& status4==7){
    			card41location = Arrays.asList(apicard).indexOf(t51a);
                card42location = Arrays.asList(apicard).indexOf(t52a);
                card43location = Arrays.asList(apicard).indexOf(t53a);
                card41.setBackgroundResource(drawableCard[card41location]);
                card42.setBackgroundResource(drawableCard[card42location]);
                card43.setBackgroundResource(drawableCard[card43location]);}
    		if(previousplayer.equals(p3game)&& status3==7){
    			card31location = Arrays.asList(apicard).indexOf(t41a);
                card32location = Arrays.asList(apicard).indexOf(t42a);
                card33location = Arrays.asList(apicard).indexOf(t43a);
                card31.setBackgroundResource(drawableCard[card31location]);
                card32.setBackgroundResource(drawableCard[card32location]);
                card33.setBackgroundResource(drawableCard[card33location]);}
    		if(previousplayer.equals(p2game)&& status2==7){
    			card21location = Arrays.asList(apicard).indexOf(t31a);
                card22location = Arrays.asList(apicard).indexOf(t32a);
                card23location = Arrays.asList(apicard).indexOf(t33a);
                card21.setBackgroundResource(drawableCard[card21location]);
                card22.setBackgroundResource(drawableCard[card22location]);
                card23.setBackgroundResource(drawableCard[card23location]);}}
			
		}
		@Override
		protected Void doInBackground(String... params) {

			/*new Handler().postDelayed(new Runnable() {
	            @Override
	            public void run() {*/
	               
	            
	        try{
				Thread.sleep(1000);
				/*if (isCancelled()){
					return null;}*/
  				 HttpGet httpget = new HttpGet(String.format(Constans.sideshowResponseAcceptApi,8,tableId,params[0]));
  	                ResponseHandler<String> responseHandler = new BasicResponseHandler();
  	                Content12 = Client12.execute(httpget, responseHandler);
  	            }
  	            catch (ClientProtocolException clientprotocolexception)
  	            {
  	                Error12 = clientprotocolexception.getMessage();
  	            }
	        	catch (InterruptedException e)
	            {
	                Error12 = e.getMessage();
	            }
  	            catch (IOException ioexception)
  	            {
  	                Error12 = ioexception.getMessage();
  	            }
	           /* }
	        }, 1000);*/
			return null;
		}
		protected void onPostExecute(Void unused) {
			testText2.setText("AfterSide");
		}
		/*@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			return null;
		}*/
		}
	public class SendTipClass extends AsyncTask<Void, Void, Void>{
		private final HttpClient Client13 = new DefaultHttpClient();
        private String Content13;
        private String Error13 = null; 
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			testText1.setText("Tip");
			//showBtn.setEnabled(false);
		}
		@Override
		protected Void doInBackground(Void... params) {
			try{
				
				 HttpGet httpget = new HttpGet(String.format(Constans.tipApi,8,userName,tipAmountInt,tableId));
	                ResponseHandler<String> responseHandler = new BasicResponseHandler();
	                Content13 = Client13.execute(httpget, responseHandler);
	            }
	            catch (ClientProtocolException clientprotocolexception)
	            {
	                Error13 = clientprotocolexception.getMessage();
	            }
	            catch (IOException ioexception)
	            {
	                Error13 = ioexception.getMessage();
	            }
			return null;
		}
		protected void onPostExecute(Void unused) {
			testText2.setText("Tip");
		}
		}
	public class ClearTipClass extends AsyncTask<Void, Void, Void>{
		private final HttpClient Client14 = new DefaultHttpClient();
        private String Content14;
        private String Error14 = null; 
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			testText1.setText("ClearTip");
		}
		@Override
		protected Void doInBackground(Void... params) {
			try{
				
				 HttpGet httpget = new HttpGet(String.format(Constans.tipClearApi,8,tableId));
	                ResponseHandler<String> responseHandler = new BasicResponseHandler();
	                Content14 = Client14.execute(httpget, responseHandler);
	            }
	            catch (ClientProtocolException clientprotocolexception)
	            {
	                Error14 = clientprotocolexception.getMessage();
	            }
	            catch (IOException ioexception)
	            {
	                Error14 = ioexception.getMessage();
	            }
			return null;
		}
		protected void onPostExecute(Void unused) {
			testText1.setText("RejectSide");
		}
		}
}
