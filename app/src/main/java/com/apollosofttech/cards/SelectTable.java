package com.apollosofttech.cards;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apollosofttech.cards.apihelper.ApiHelper;
import com.apollosofttech.cards.apihelper.RetrofitInstance;
import com.apollosofttech.cards.survices.AppStatus;
import com.apollosofttech.cards.survices.ProgressLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectTable extends Activity {
    private final String LOG = "SelectTable";
    private String username;
    private int balance;
    LinearLayout layout2nd;
    ProgressLoader pd;
    Context context;
    Button booot10btn, booot20btn, booot50btn, booot100btn, booot200btn, booot500btn, booot1000btn, booot2000btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_selecttable);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("user_Teen");
            balance = extras.getInt("user_bal");
        }
        context = this;
        booot10btn = findViewById(R.id.booot10btn);
        booot20btn = findViewById(R.id.booot20btn);
        booot20btn.setVisibility(View.INVISIBLE);
        booot50btn = findViewById(R.id.booot50btn);
        booot50btn.setVisibility(View.INVISIBLE);
        booot100btn = findViewById(R.id.booot100btn);
        booot100btn.setVisibility(View.INVISIBLE);
        booot200btn = findViewById(R.id.booot200btn);
        booot200btn.setVisibility(View.INVISIBLE);
        booot500btn = findViewById(R.id.booot500btn);
        booot500btn.setVisibility(View.INVISIBLE);
        booot1000btn = findViewById(R.id.booot1000btn);
        booot1000btn.setVisibility(View.INVISIBLE);
        booot2000btn = findViewById(R.id.booot2000btn);
        booot2000btn.setVisibility(View.INVISIBLE);
        layout2nd = findViewById(R.id.layout2nd);
        layout2nd.setVisibility(View.INVISIBLE);
        if (balance > 20480) {
            booot20btn.setVisibility(View.VISIBLE);
        }
        if (balance > 51200) {
            booot50btn.setVisibility(View.VISIBLE);
        }
        if (balance > 102400) {
            booot100btn.setVisibility(View.VISIBLE);
        }
        if (balance > 512000) {
            booot200btn.setVisibility(View.VISIBLE);
            layout2nd.setVisibility(View.VISIBLE);
        }
        if (balance > 1024000) {
            booot500btn.setVisibility(View.VISIBLE);
        }
        if (balance > 2048000) {
            booot1000btn.setVisibility(View.VISIBLE);
        }
        if (balance >= 2048000) {
            booot2000btn.setVisibility(View.VISIBLE);
        }
        pd = new ProgressLoader();

        findViewById(R.id.booot10btn).setOnClickListener(handler);
        findViewById(R.id.booot20btn).setOnClickListener(handler);
        findViewById(R.id.booot50btn).setOnClickListener(handler);
        findViewById(R.id.booot100btn).setOnClickListener(handler);
        findViewById(R.id.booot200btn).setOnClickListener(handler);
        findViewById(R.id.booot500btn).setOnClickListener(handler);
        findViewById(R.id.booot1000btn).setOnClickListener(handler);
        findViewById(R.id.booot2000btn).setOnClickListener(handler);
    }

    private View.OnClickListener handler = new View.OnClickListener() {
        public void onClick(View v) {

            switch (v.getId()) {

                case R.id.booot10btn:
                    if (AppStatus.getInstance(context).isOnline(context)) {
                        getSelectedTable(10);
                    } else {
                        showToast("No Internet Connection!");
                    }
                    break;
                case R.id.booot20btn:
                    if (AppStatus.getInstance(context).isOnline(context)) {
                        getSelectedTable(20);
                    } else {
                        showToast("No Internet Connection!");
                    }
                    break;
                case R.id.booot50btn:
                    if (AppStatus.getInstance(context).isOnline(context)) {
                        getSelectedTable(50);
                    } else {
                        showToast("No Internet Connection!");
                    }
                    break;
                case R.id.booot100btn:
                    if (AppStatus.getInstance(context).isOnline(context)) {
                        getSelectedTable(100);
                    } else {
                        showToast("No Internet Connection!");
                    }
                    break;
                case R.id.booot200btn:
                    if (AppStatus.getInstance(context).isOnline(context)) {
                        getSelectedTable(200);
                    } else {
                        showToast("No Internet Connection!");
                    }
                    break;
                case R.id.booot500btn:
                    if (AppStatus.getInstance(context).isOnline(context)) {
                        getSelectedTable(500);
                    } else {
                        showToast("No Internet Connection!");
                    }
                    break;
                case R.id.booot1000btn:
                    if (AppStatus.getInstance(context).isOnline(context)) {
                        getSelectedTable(1000);
                    } else {
                        showToast("No Internet Connection!");
                    }
                    break;
                case R.id.booot2000btn:
                    if (AppStatus.getInstance(context).isOnline(context)) {
                        getSelectedTable(2000);
                    } else {
                        showToast("No Internet Connection!");
                    }
                    break;
            }
        }
    };
private void getSelectedTable(final int bootAmount){
    pd.displayProgressDialog(false, "Checking Avalable Table...", context);
    ApiHelper apiHelper = RetrofitInstance.getRetrofitInstance().create(ApiHelper.class);
    Log.d(LOG, "get_Table : " + username + " : " + bootAmount);
    if (AppStatus.getInstance(context).isOnline(context)) {
        Call<String> getTableCall = apiHelper.get_table(username, bootAmount);
        getTableCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                pd.dismiss();
                if (response.code() == 200) {
                    Log.d(LOG, response.body());
                    try {
                        JSONObject jsonobject = new JSONObject(response.body());
                        JSONArray jsonarray = jsonobject.getJSONArray("feed");
                        jsonobject = jsonarray.getJSONObject(0);
                        String errorcod = null;
                        if (jsonobject.has("errorcod")) {
                            errorcod = jsonobject.getString("errorcod");
                        }
                        if (errorcod != null && errorcod.equals("0")) {
                            String match_table_id = null;
                            if (jsonobject.has("tbl_id")) {
                                match_table_id = jsonobject.getString("tbl_id");
                            }
                                            /*if (jsonobject.has("Player1")) {
                                                usEr1 = jsonobject.getString("Player1");
                                            }
                                            if (jsonobject.has("Player2")) {
                                                usEr2 = jsonobject.getString("Player2");
                                            }
                                            if (jsonobject.has("Player3")) {
                                                usEr3 = jsonobject.getString("Player3");
                                            }
                                            if (jsonobject.has("Player4")) {
                                                usEr4 = jsonobject.getString("Player4");
                                            }
                                            if (jsonobject.has("Player5")) {
                                                usEr5 = jsonobject.getString("Player5");
                                            }*/
                            Intent intent = new Intent(SelectTable.this, MainActivity.class);
                            intent.putExtra("tbl_id", match_table_id);
                            intent.putExtra("user_Teen", username);
                            intent.putExtra("boot_amount", bootAmount);
                            startActivity(intent);
                            finish();
                        } else {
                            showToast("Can't find Table");
                        }
                    } catch (JSONException e) {
                        showToast("Invalid Responce");
                        e.printStackTrace();
                    }

                } else {
                    showToast("Can't find server");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                pd.dismiss();
                showToast("Network Error");
            }
        });

    } else {
        showToast("No Internet Connection!");
    }
}


    public void showToast(String message) {
        LayoutInflater inflater = getLayoutInflater();
        View toastRoot = inflater.inflate(R.layout.notification_toast,
                null);
        Toast toast = new Toast(context);
        TextView tc = toastRoot.findViewById(R.id.notifi);
        tc.setText(message);
        toast.setView(toastRoot);
        toast.setGravity(Gravity.CENTER_HORIZONTAL
                | Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
    }
}